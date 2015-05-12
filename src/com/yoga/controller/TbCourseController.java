package com.yoga.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbCourseDAO;
import com.yoga.entity.TbCourse;
import com.yoga.entity.TbCourseType;
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
public class TbCourseController  {

	/**
	 * 获取dao
	 */
	private TbCourseDAO dao = new TbCourseDAO();

	/**
	 * 添加信息
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "course/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbCourse> add(final String id, final String name,
			final String price,final String date,final String time1,final String time2,String typeId, String type) {
		JsonResponse<TbCourse> jsonResponse = new JsonResponse<TbCourse>();
		try {
			TbCourse entity = getBean(id, name, price,date,time1,time2,Integer.parseInt(typeId),type);
			dao.save(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.COURSE, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.COURSE, Constants.FAILURE));
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
	@RequestMapping(value = "course/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbCourse> edit(final String id, final String name,
			final String price,final String date,final String time1,final String time2,String typeId, String type) {
		JsonResponse<TbCourse> jsonResponse = new JsonResponse<TbCourse>();
		try {
			TbCourse entity = getBean(id, name, price,date,time1,time2,Integer.parseInt(typeId),type);
			dao.update(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.COURSE, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.COURSE, Constants.FAILURE));
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
	@RequestMapping(value = "course/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id, final String name,
			final String price,final String date,final String time1,final String time2,String typeId,String type) {
		try {
			TbCourse entity = getBean(id, name, price,date,time1,time2,Integer.parseInt(typeId),type);
			dao.update(entity);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("course/index");
	}
	
	
	@RequestMapping(value = "course/alllist.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbCourse> list() {
		JsonResponse<TbCourse> jsonResponse = new JsonResponse<TbCourse>();
		try {
			List<TbCourse> findAll = dao.findAll();
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.COURSE, Constants.SUCCESS));
			jsonResponse.setList(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.COURSE, Constants.FAILURE));
		}
		return jsonResponse;
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
	@RequestMapping(value = "course/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbCourse> list(@RequestParam(required = true, defaultValue = "1") int page,
																	 @RequestParam(required = true, defaultValue = "10") int size, 
																	 @RequestParam(required = false) final String id,
																	 @RequestParam(required = false) final String name,
																	 @RequestParam(required = false) final String date,
																	 @RequestParam(required = false) final String price) {
		JsonResponse<TbCourse> jsonResponse = new JsonResponse<TbCourse>();
		//获取对应的参数
		String[] params = new String[]{id,name,date,price};
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
			Page<TbCourse> findAll = dao.findAll(page, size, params);
//			List<TbCourse> temp = new ArrayList<TbCourse>();
//			for(TbCourse tb :findAll.getContent()){
//				tb.setTbMemberCourseDetails(null);
//				tb.setTbStaffCourseClassroomses(null);
//				TbCourseType tbCourseType = tb.getTbCourseType();
//				TbCourseType tempType = new TbCourseType();
//				tempType.setType(tbCourseType.getType());
//				tempType.setId(tbCourseType.getId());
//				tb.setTbCourseType(tempType);
//				temp.add(tb);
//			}
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.COURSE, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.COURSE, Constants.FAILURE));
		}
		return jsonResponse;
	}
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "course/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbCourse course = new TbCourse();
		try {
			course = dao.findById(id);
			modelMap.put("update", "update");
			modelMap.put("course", course);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("course/add");
	}
	
	/**
	 * 重构代码
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	private TbCourse getBean(final String id, final String name, final String price, final String date,
			final String time1, final String time2,int typeId, String type) {
		TbCourse entity = null;
		try {
			String newId = new String(id.getBytes("iso8859-1"), "UTF-8");
			String newname = new String(name.getBytes("iso8859-1"), "UTF-8");
			String newprice = new String(price.getBytes("iso8859-1"), "UTF-8");
			String newdate = new String(date.getBytes("iso8859-1"), "UTF-8");
			String newtime1 = new String(time1.getBytes("iso8859-1"), "UTF-8");
			String newtime2 = new String(time2.getBytes("iso8859-1"), "UTF-8");
			String newtype = new String(type.getBytes("iso8859-1"), "UTF-8");
			entity = new TbCourse();
			entity.setCourseId(newId);
			entity.setCouresName(newname);
			entity.setCoursePrice(newprice);
			System.out.println(TbClassroomsController.class.getName()+"..debug->>" + newdate);
			entity.setCourseDate(DateUtil.str2Date(newdate, "yyyy-MM-dd"));
			entity.setCourseTime1(Time.valueOf(newtime1));
			entity.setCourseTime2(Time.valueOf(newtime2));
			TbCourseType tbCourseType = new TbCourseType();
			tbCourseType.setId(typeId);
			tbCourseType.setType(newtype);
			entity.setTbCourseType(tbCourseType);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
}
