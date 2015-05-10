package com.yoga.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbMemberDAO;
import com.yoga.entity.TbMember;
import com.yoga.entity.TbMemberType;
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
public class TbMemberController  {

	/**
	 * 获取dao
	 */
	private TbMemberDAO dao = new TbMemberDAO();

	/**
	 * 添加信息
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "member/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbMember> add(final String id, final String username, final String name,
								      final String sex,final String card,final String phone,
								      final String address,String typeId, String type) {
		JsonResponse<TbMember> jsonResponse = new JsonResponse<TbMember>();
		try {
			TbMember entity = getBean(id, username, name, sex, card, phone, address, 
									  Integer.parseInt(typeId), type);
			dao.save(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.MEMBER, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.MEMBER, Constants.FAILURE));
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
	@RequestMapping(value = "member/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbMember> edit(final String id, final String username, final String name,
									   final String sex,final String card,final String phone,
									   final String address,String typeId, String type) {
		JsonResponse<TbMember> jsonResponse = new JsonResponse<TbMember>();
		try {
			TbMember entity = getBean(id, username, name, sex, card, phone, address, 
									  Integer.parseInt(typeId), type);
			dao.update(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.MEMBER, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.MEMBER, Constants.FAILURE));
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
	@RequestMapping(value = "member/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id) {
		try {
			TbMember entity = dao.findById(id);
			dao.update(entity);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("member/index");
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
	@RequestMapping(value = "member/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbMember> list(@RequestParam(required = true, defaultValue = "1") int page,
																	 @RequestParam(required = true, defaultValue = "10") int size, 
																	 @RequestParam(required = false) final String id,
																	 @RequestParam(required = false) final String name,
																	 @RequestParam(required = false) final String address) {
		JsonResponse<TbMember> jsonResponse = new JsonResponse<TbMember>();
		//获取对应的参数
		String[] params = new String[]{id,name,address};
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
			Page<TbMember> findAll = dao.findAll(page, size, params);
			for(TbMember tb : findAll.getContent()){
				TbMemberType tbMemberType = tb.getTbMemberType();
				TbMemberType tempType = new TbMemberType();
				tempType.setType(tbMemberType.getType());
				tempType.setId(tbMemberType.getId());
				tb.setTbMemberType(tempType);
			}
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.MEMBER, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.MEMBER, Constants.FAILURE));
		}
		return jsonResponse;
	}
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "member/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbMember member = new TbMember();
		try {
			member =dao.findById(id);
			modelMap.put("update", "update");
			modelMap.put("member", member);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("member/add");
	}
	
	/**
	 * 重构代码
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	private TbMember getBean(final String id, final String username, final String name,
							 final String sex,final String card,final String phone,
							 final String address,int typeId, String type) {
		TbMember entity = null;
		try {
			String newId = new String(id.getBytes("iso8859-1"), "UTF-8");
			String newusername = new String(username.getBytes("iso8859-1"), "UTF-8");
			String newname = new String(name.getBytes("iso8859-1"), "UTF-8");
			String newsex = new String(sex.getBytes("iso8859-1"), "UTF-8");
			String newcard = new String(card.getBytes("iso8859-1"), "UTF-8");
			String newphone = new String(phone.getBytes("iso8859-1"), "UTF-8");
			String newaddress = new String(address.getBytes("iso8859-1"), "UTF-8");
			String newtype = new String(type.getBytes("iso8859-1"), "UTF-8");
			entity = new TbMember();
			entity.setMemberId(newId);
			entity.setMemberUsername(newusername);
			entity.setMemberName(newname);
			//预定0为男，1为女
			entity.setMemberSex("1".equals(newsex) ? true : false);
			entity.setMemberCard(newcard);
			entity.setMemberPhone(newphone);
			entity.setMemberAddress(newaddress);
			TbMemberType tbMemberType = new TbMemberType();
			tbMemberType.setId(typeId);
			tbMemberType.setType(newtype);
			entity.setTbMemberType(tbMemberType);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
}
