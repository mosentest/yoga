package com.yoga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbClassroomsDAO;
import com.yoga.dao.TbStaffCourseClassroomsDAO;
import com.yoga.entity.TbClassrooms;
import com.yoga.entity.TbCourse;
import com.yoga.entity.TbStaff;
import com.yoga.entity.TbStaffCourseClassrooms;
import com.yoga.util.Constants;
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
public class TbStaffCourseClassroomsController  {

	/**
	 * 获取dao
	 */
	private TbStaffCourseClassroomsDAO dao = new TbStaffCourseClassroomsDAO();

	/**
	 * 添加信息
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "staffCourseClassrooms/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbStaffCourseClassrooms> add(final String id,final String staffId, final String courseId, final String classroomsId) {
		JsonResponse<TbStaffCourseClassrooms> jsonResponse = new JsonResponse<TbStaffCourseClassrooms>();
		try {
			TbStaffCourseClassrooms entity =getBean(id, staffId, courseId, classroomsId);
			dao.save(entity);
			//更新课室的状态
			TbClassroomsDAO classroomsDAO = new TbClassroomsDAO();
			TbClassrooms findById = classroomsDAO.findById(classroomsId);
			findById.setClassroomsState(true);
			classroomsDAO.update(findById);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.TASK, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.TASK, Constants.FAILURE));
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
	@RequestMapping(value = "staffCourseClassrooms/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbStaffCourseClassrooms> edit(final String id,final String staffId, final String courseId, final String classroomsId) {
		JsonResponse<TbStaffCourseClassrooms> jsonResponse = new JsonResponse<TbStaffCourseClassrooms>();
		try {
			TbStaffCourseClassrooms entity =getBean(id, staffId, courseId, classroomsId);
			dao.update(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.TASK, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.TASK, Constants.FAILURE));
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
	@RequestMapping(value = "staffCourseClassrooms/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id,final String staffId, final String courseId, final String classroomsId) {
		try {
			TbStaffCourseClassrooms entity = getBean(id, staffId, courseId, classroomsId);
			dao.update(entity);
			dao.delete(entity);
			//更新课室的状态
			TbClassroomsDAO classroomsDAO = new TbClassroomsDAO();
			TbClassrooms findById = classroomsDAO.findById(classroomsId);
			findById.setClassroomsState(false);
			classroomsDAO.update(findById);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("staffCourseClassrooms/index");
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
	@RequestMapping(value = "staffCourseClassrooms/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbStaffCourseClassrooms> list(@RequestParam(required = true, defaultValue = "1") int page,
																	 @RequestParam(required = true, defaultValue = "10") int size, 
																	 @RequestParam(required = false) final String staffName,
																	 @RequestParam(required = false) final String courseName,
																	 @RequestParam(required = false) final String classroomsName) {
		JsonResponse<TbStaffCourseClassrooms> jsonResponse = new JsonResponse<TbStaffCourseClassrooms>();
		//获取对应的参数
		String[] params = new String[]{staffName,courseName,classroomsName};
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
			Page<TbStaffCourseClassrooms> findAll = dao.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.TASK, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.TASK, Constants.FAILURE));
		}
		return jsonResponse;
	}
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "staffCourseClassrooms/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final int id,ModelMap modelMap) {
		TbStaffCourseClassrooms staffCourseClassrooms = new TbStaffCourseClassrooms();
		try {
			staffCourseClassrooms = dao.findById(id);
			modelMap.put("update", "update");
			modelMap.put("staffCourseClassrooms", staffCourseClassrooms);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("staffCourseClassrooms/add");
	}
	
	/**
	 * 重构代码
	 * @return
	 */
	private TbStaffCourseClassrooms getBean(final String id,final String staffId, final String courseId, final String classroomsId) {
		TbStaffCourseClassrooms entity = null;
		try {
			entity = new TbStaffCourseClassrooms();
			TbStaff tbStaff = new TbStaff();
			TbCourse tbCourse = new TbCourse();
			TbClassrooms tbClassrooms = new TbClassrooms();
			
			tbStaff.setStaffId(staffId);
			tbCourse.setCourseId(courseId);
			tbClassrooms.setClassroomsId(classroomsId);
			
			//添加的时候无用
			if(!"".equals(id)){
				entity.setId(Integer.parseInt(id));
			}
			entity.setTbStaff(tbStaff);
			entity.setTbCourse(tbCourse);
			entity.setTbClassrooms(tbClassrooms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
}
