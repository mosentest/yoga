package com.yoga.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbRoleDAO;
import com.yoga.entity.TbRole;
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
public class TbRoleController {

	/**
	 * 获取dao
	 */
	private TbRoleDAO dao = new TbRoleDAO();

	/**
	 * 添加信息
	 * 
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "role/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbRole> add(final String id, final String name) {
		JsonResponse<TbRole> jsonResponse = new JsonResponse<TbRole>();
		try {
			TbRole entity = getBean(id, name);
			dao.save(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.ROLE, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.ROLE, Constants.FAILURE));
		}
		return jsonResponse;
	}

	/**
	 * 编辑信息
	 * 
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "role/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbRole> edit(final String id, final String name) {
		JsonResponse<TbRole> jsonResponse = new JsonResponse<TbRole>();
		try {
			TbRole entity = getBean(id, name);
			// 更新员工表
			dao.update(entity);
			// 更新员工详细表
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.ROLE, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.ROLE, Constants.FAILURE));
		}
		return jsonResponse;
	}

	/**
	 * 删除信息
	 * 
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "role/delete&oper=false", method = RequestMethod.POST)
	public ModelAndView delete(final String id, final String name) {
		try {
			TbRole entity = getBean(id, name);
			// 更新员工表
			dao.update(entity);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("role/index");
	}

	/**
	 * 获取列表
	 * 
	 * @param page
	 * @param size
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "role/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbRole> list(@RequestParam(required = true, defaultValue = "1") int page,
			@RequestParam(required = true, defaultValue = "10") int size, @RequestParam(required = false) final String name) {
		JsonResponse<TbRole> jsonResponse = new JsonResponse<TbRole>();
		// 获取对应的参数
		String[] params = new String[] { name };
		try {
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					// 编码有问题,get传过来的参数
					if (params[i] != null && !"".equals(params[i])) {
						String newStr = new String(params[i].getBytes("iso8859-1"), "UTF-8");
						params[i] = newStr;
					}
				}
			}
			// xml文件修改lazy=false
			Page<TbRole> findAll = dao.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.ROLE, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.ROLE, Constants.FAILURE));
		}
		return jsonResponse;
	}

	/**
	 * 获取其中一个信息，并跳转页面
	 * 
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "role/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id, ModelMap modelMap) {
		TbRole TbRole = new TbRole();
		try {
			TbRole = dao.findById(Integer.parseInt(id));
			modelMap.put("update", "update");
			modelMap.put("role", TbRole);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return new ModelAndView("role/add");
	}

	/**
	 * 重构代码
	 * 
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	private TbRole getBean(final String id, final String name) {
		TbRole entity = null;
		try {
			String newId = new String(id.getBytes("iso8859-1"), "UTF-8");
			String newname = new String(name.getBytes("iso8859-1"), "UTF-8");
			entity = new TbRole();
			entity.setId(Integer.parseInt(newId));
			entity.setType(newname);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}

}
