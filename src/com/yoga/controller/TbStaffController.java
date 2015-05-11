package com.yoga.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbStaffDAO;
import com.yoga.dao.TbStaffDetailDAO;
import com.yoga.entity.TbStaff;
import com.yoga.entity.TbStaffDetail;
import com.yoga.util.Constants;
import com.yoga.util.DateUtil;
import com.yoga.util.JsonResponse;
import com.yoga.util.Page;

/**
 * action
 * 
 * @author wwb
 * 
 */
@Controller
@RequestMapping("/")
public class TbStaffController  {

	/**
	 * 获取dao
	 */
	private TbStaffDAO dao = new TbStaffDAO();
	private TbStaffDetailDAO staffDetailDAO= new TbStaffDetailDAO();

	/**
	 * 添加信息
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "staffDetail/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbStaffDetail> add(final String id, final String name, final String sex,
										  final String age,final String post,final String phone,
										  final String card,final String address, String email,
										  final String time,final String staffDetailId) {
		JsonResponse<TbStaffDetail> jsonResponse = new JsonResponse<TbStaffDetail>();
		try {
			TbStaffDetail entity = getBean(id, name, sex, age, post, phone, card, address, email, time, staffDetailId);
			dao.save(entity.getTbStaff());
			staffDetailDAO.save(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.STAFF, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.STAFF, Constants.FAILURE));
		}
		return jsonResponse;
	}

	/**
	 * 编辑信息
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "staffDetail/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbStaffDetail> edit(final String id, final String name, final String sex,
									  final String age,final String post,final String phone,
									  final String card,final String address, String email,
									  final String time,final String staffDetailId) {
		JsonResponse<TbStaffDetail> jsonResponse = new JsonResponse<TbStaffDetail>();
		try {
			TbStaffDetail entity = getBean(id, name, sex, age, post, phone, card, address, email, time,staffDetailId);
			//更新员工表
			dao.update(entity.getTbStaff());
			//更新员工详细表
			staffDetailDAO.update(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.STAFF, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.STAFF, Constants.FAILURE));
		}
		return jsonResponse;
	}
	
	/**
	 * 删除信息
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "staffDetail/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id, final String name, final String sex,
							  final String age,final String post,final String phone,
							  final String card,final String address, String email,
							  final String time,final String staffDetailId) {
		try {
			TbStaffDetail entity = getBean(id, name, sex, age, post, phone, card, address, email, null,staffDetailId);
			//更新员工表
			dao.update(entity.getTbStaff());
			//更新员工详细表
			staffDetailDAO.update(entity);
			dao.delete(entity.getTbStaff());
			staffDetailDAO.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("staff/index");
	}
	
	/**
	 * 获取列表
	 * @param page
	 * @param size
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "staffDetail/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbStaffDetail> list(@RequestParam(required = true, defaultValue = "1") int page,
																	 @RequestParam(required = true, defaultValue = "10") int size, 
																	 @RequestParam(required = false) final String id,
																	 @RequestParam(required = false) final String name,
																	 @RequestParam(required = false) final String post) {
		JsonResponse<TbStaffDetail> jsonResponse = new JsonResponse<TbStaffDetail>();
		//获取对应的参数
		String[] params = new String[]{id,name,post};
		try {
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					// 编码有问题,get传过来的参数
					if(params[i] != null && !"".equals(params[i])){
						String newStr = new String(params[i].getBytes("iso8859-1"), "UTF-8");
						params[i] = newStr;
					}
				}
			}
			//xml文件修改lazy=false
			Page<TbStaffDetail> findAll = staffDetailDAO.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.STAFF, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.STAFF, Constants.FAILURE));
		}
		return jsonResponse;
	}
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "staffDetail/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbStaffDetail tbStaffDetail = new TbStaffDetail();
		try {
			tbStaffDetail = staffDetailDAO.findById(Integer.parseInt(id));
			modelMap.put("update", "update");
			modelMap.put("tbStaffDetail", tbStaffDetail);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("staff/add");
	}
	
	/**
	 * 重构代码
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	private TbStaffDetail getBean(final String id, final String name, final String sex,
							 final String age,final String post,final String phone,
							 final String card,final String address, String email,
							 final String time,final String staffDetailId) {
		TbStaffDetail detail = null;
		TbStaff entity = null;
		try {
			String newId = new String(id.getBytes("iso8859-1"), "UTF-8");
			String newname = new String(name.getBytes("iso8859-1"), "UTF-8");
			String newsex = new String(sex.getBytes("iso8859-1"), "UTF-8");
			String newage = new String(age.getBytes("iso8859-1"), "UTF-8");
			String newpost = new String(post.getBytes("iso8859-1"), "UTF-8");
			String newcard = new String(card.getBytes("iso8859-1"), "UTF-8");
			String newphone = new String(phone.getBytes("iso8859-1"), "UTF-8");
			String newaddress = new String(address.getBytes("iso8859-1"), "UTF-8");
			String newemail = new String(email.getBytes("iso8859-1"), "UTF-8");
			String newtime = null;
			if(time != null){
				newtime = new String(time.getBytes("iso8859-1"), "UTF-8");
			}
			detail = new TbStaffDetail();
			entity = new TbStaff();
			entity.setStaffId(newId);
			entity.setStaffName(newname);
			entity.setStaffAge(Short.parseShort(newage));
			entity.setStaffPost(newpost);
			//预定0为男，1为女
			entity.setStaffSex("1".equals(newsex) ? true : false);
			entity.setStaffPhone(newphone);
			if("".equals(staffDetailId)){
				detail.setId(null);
			}else{
				detail.setId(Integer.parseInt(staffDetailId));
			}
			detail.setStaffAddress(newaddress);
			detail.setStaffCard(newcard);
			detail.setStaffEmail(newemail);
			System.out.println(newtime);
			if(newtime != null){
				//时间这里会异常
//			Date str2Date = DateUtil.str2Date(newtime, "yyyy-MM-dd");
				newtime+=" 00:00:00";
				detail.setStaffTime(Timestamp.valueOf(newtime));
			}
			detail.setTbStaff(entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return detail;
	}
	
}
