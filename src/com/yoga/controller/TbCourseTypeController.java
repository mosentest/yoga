package com.yoga.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbCourseTypeDAO;
import com.yoga.entity.TbCourseType;
import com.yoga.util.Constants;
import com.yoga.util.JsonResponse;

/**
 * action
 * 
 * @author hwb
 * 
 */
@Controller
@RequestMapping("/")
public class TbCourseTypeController  {

	/**
	 * 获取dao
	 */
	private TbCourseTypeDAO dao = new TbCourseTypeDAO();

	/**
	 * 添加信息
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "courseType/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbCourseType> add(final int id, String type) {
		JsonResponse<TbCourseType> jsonResponse = new JsonResponse<TbCourseType>();
		try {
			TbCourseType entity = getBean(id,type);
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
	@RequestMapping(value = "courseType/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbCourseType> edit(final int id, String type) {
		JsonResponse<TbCourseType> jsonResponse = new JsonResponse<TbCourseType>();
		try {
			TbCourseType entity = getBean(id, type);
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
	@RequestMapping(value = "courseType/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int id,String type) {
		try {
			TbCourseType entity = getBean(id,type);
			dao.update(entity);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("courseType/index");
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
	@RequestMapping(value = "courseType/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbCourseType> list() {
		JsonResponse<TbCourseType> jsonResponse = new JsonResponse<TbCourseType>();
		try {
			List<TbCourseType> findAll = dao.findAll();
			for(TbCourseType tb :findAll){
				tb.setTbCourses(null);
			}
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
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "courseType/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final int id,ModelMap modelMap) {
		TbCourseType courseType = new TbCourseType();
		try {
			courseType =dao.findById(id);
			modelMap.put("update", "update");
			modelMap.put("courseType", courseType);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("courseType/add");
	}
	
	/**
	 * 重构代码
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	private TbCourseType getBean(int id, String type) {
		TbCourseType entity = null;
		try {
			String newtype = new String(type.getBytes("iso8859-1"), "UTF-8");
			entity = new TbCourseType();
			entity.setId(id);
			entity.setType(newtype);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
}
