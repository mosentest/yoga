package com.yoga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	private TbClassroomsDAO classroomsDAO = new TbClassroomsDAO();

	@RequestMapping(value = "classrooms/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbClassrooms> add(final String id,final String name,final String state) {
		JsonResponse<TbClassrooms> jsonResponse = new JsonResponse<TbClassrooms>();
		try {
			TbClassrooms findById = classroomsDAO.findById(id);
			if(findById != null){
				jsonResponse.setMsg(Constants.getTip(Constants.EXIST));
			}else{
				TbClassrooms entity = getBean(id, name, state);
				classroomsDAO.save(entity);
				jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.CLASSROOM, Constants.SUCCESS));
			}
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.CLASSROOM, Constants.FAILURE));
		}
		return jsonResponse;
	}

	private TbClassrooms getBean(final String id, final String name,
			final String state) {
		TbClassrooms entity = new TbClassrooms();
		entity.setClassroomsId(id);
		entity.setClassroomsName(name);
		entity.setClassroomsState("1".equals(state) ? true : false);
		return entity;
	}

	@RequestMapping(value = "classrooms/delete", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResponse<TbClassrooms> delete(final String id,final String name,final String state) {
		JsonResponse<TbClassrooms> jsonResponse = new JsonResponse<TbClassrooms>();
		try {
			TbClassrooms entity = getBean(id, name, state);
			classroomsDAO.delete(entity);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.DELETE, Constants.CLASSROOM, Constants.SUCCESS));
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.DELETE, Constants.CLASSROOM, Constants.FAILURE));
		}
		return jsonResponse;
	}

	public JsonResponse<TbClassrooms> update() {

		return null;
	}

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
			Page<TbClassrooms> findAll = classroomsDAO.findAll(page, size, params);
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

	public JsonResponse<TbClassrooms> showOne(@RequestParam(required = false) final String id,
			 								  @RequestParam(required = false) final String name) {
		JsonResponse<TbClassrooms> jsonResponse = new JsonResponse<TbClassrooms>();
		//获取对应的参数
		String[] params = new String[]{id,name};
		try {
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					// 编码有问题,get传过来的参数
					String newStr = new String(params[i].getBytes("iso8859-1"), "UTF-8");
					params[i] = newStr;
				}
			}
		}catch(Exception exception){
			
		}
		return null;
	}

}
