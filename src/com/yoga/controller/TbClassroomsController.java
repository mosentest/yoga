package com.yoga.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbClassroomsDAO;
import com.yoga.entity.TbClassrooms;
import com.yoga.util.Constants;
import com.yoga.util.JsonResponse;
import com.yoga.util.Page;

/**
 * 课程action
 * 
 * @author hwb
 * 
 */
@Controller
@RequestMapping("/")
public class TbClassroomsController  {

	/**
	 * 获取dao
	 */
	private TbClassroomsDAO dao = new TbClassroomsDAO();

	/**
	 * 添加信息
	 * @param id
	 * @param name
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "classrooms/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbClassrooms> add(final String id,final String name,final String state) {
		JsonResponse<TbClassrooms> jsonResponse = new JsonResponse<TbClassrooms>();
		try {
			TbClassrooms entity = getBean(id, name, state);
			dao.save(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.CLASSROOM, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.CLASSROOM, Constants.FAILURE));
		}
		return jsonResponse;
	}

	/**
	 * 编辑信息
	 * @param id
	 * @param name
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "classrooms/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbClassrooms> edit(final String id,final String name,final String state) {
		JsonResponse<TbClassrooms> jsonResponse = new JsonResponse<TbClassrooms>();
		try {
			TbClassrooms entity = getBean(id, name, state);
			dao.update(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.CLASSROOM, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.CLASSROOM, Constants.FAILURE));
		}
		return jsonResponse;
	}
	
	/**
	 * 删除信息
	 * @param id
	 * @param name
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "classrooms/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id, final String name,final String state) {
		try {
			TbClassrooms entity = getBean(id, name, state);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("classrooms/index");
	}
	
	/**
	 * 获取列表
	 * @param page
	 * @param size
	 * @param id
	 * @param name
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "classrooms/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbClassrooms> list(@RequestParam(required = true, defaultValue = "1") int page,
																	 @RequestParam(required = true, defaultValue = "10") int size, 
																	 @RequestParam(required = false) final String id,
																	 @RequestParam(required = false) final String name,
																	 @RequestParam(required = false) final String state) {
		JsonResponse<TbClassrooms> jsonResponse = new JsonResponse<TbClassrooms>();
		//获取对应的参数
		String[] params = new String[]{id,name,state};
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
			Page<TbClassrooms> findAll = dao.findAll(page, size, params);
			for(TbClassrooms tb :findAll.getContent()){
				tb.setTbStaffCourseClassroomses(null);
			}
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.CLASSROOM, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.CLASSROOM, Constants.FAILURE));
		}
		return jsonResponse;
	}
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "classrooms/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbClassrooms classrooms = new TbClassrooms();
		try {
			classrooms =dao.findById(id);
			modelMap.put("update", "update");
			modelMap.put("classrooms", classrooms);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("classrooms/add");
	}
	
	/**
	 * 重构代码
	 * @param id
	 * @param name
	 * @param state
	 * @return
	 */
	private TbClassrooms getBean(final String id, final String name,
			final String state) {
		TbClassrooms entity =null;
		try {
			String newId = new String(id.getBytes("iso8859-1"), "UTF-8");
			String newname = new String(name.getBytes("iso8859-1"), "UTF-8");
			String newstate = new String(state.getBytes("iso8859-1"), "UTF-8");
			entity = new TbClassrooms();
			entity.setClassroomsId(newId);
			entity.setClassroomsName(newname);
			entity.setClassroomsState("1".equals(newstate) ? true : false);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
