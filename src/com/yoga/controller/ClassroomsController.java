package com.yoga.controller;

import org.hibernate.Transaction;
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
public class ClassroomsController implements BaseController<TbClassrooms> {

	private TbClassroomsDAO classroomsDAO = new TbClassroomsDAO();

	@RequestMapping(value = "classrooms/add.html", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	@Override
	public JsonResponse<TbClassrooms> add(@RequestBody final TbClassrooms entity) {
		JsonResponse<TbClassrooms> jsonResponse = new JsonResponse<TbClassrooms>();
		try {
			TbClassrooms findById = classroomsDAO.findById(entity.getClassroomsId());
			if(findById != null){
				jsonResponse.setMsg(Constants.getTip(Constants.EXIST));
			}else{
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

	@RequestMapping(value = "classrooms/delete.html", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	@Override
	public JsonResponse<TbClassrooms> delete(@RequestBody final TbClassrooms entity) {
		JsonResponse<TbClassrooms> jsonResponse = new JsonResponse<TbClassrooms>();
		try {
			classroomsDAO.delete(entity);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.DELETE, Constants.CLASSROOM, Constants.SUCCESS));
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.DELETE, Constants.CLASSROOM, Constants.FAILURE));
		}
		return jsonResponse;
	}

	@Override
	public JsonResponse<TbClassrooms> update(@RequestBody final TbClassrooms entity) {

		return null;
	}

	@RequestMapping(value = "classrooms/list.html", method = RequestMethod.GET)
	@ResponseBody
	@Override
	public JsonResponse<TbClassrooms> list(@RequestParam(required = true, defaultValue = "1") int page,
																	 @RequestParam(required = true, defaultValue = "10") int size, 
																	 @RequestParam(required = false) final String[] params) {
		JsonResponse<TbClassrooms> jsonResponse = new JsonResponse<TbClassrooms>();
		try {
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					// 编码有问题,get传过来的参数
					String newStr = new String(params[i].getBytes("iso8859-1"), "UTF-8");
					params[i] = newStr;
				}
			}
			Page<TbClassrooms> findAll = classroomsDAO.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.CLASSROOM, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.CLASSROOM, Constants.FAILURE));
		}
		return jsonResponse;
	}

	@Override
	public JsonResponse<TbClassrooms> showOne(String... params) {
		return null;
	}

}
