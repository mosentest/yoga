package com.yoga.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbUserDAO;
import com.yoga.dao.TbUserRoleDAO;
import com.yoga.entity.TbRole;
import com.yoga.entity.TbStaff;
import com.yoga.entity.TbUser;
import com.yoga.entity.TbUserRole;
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
public class TbUserController {

	/**
	 * 获取dao
	 */
	private TbUserDAO dao = new TbUserDAO();
	private TbUserRoleDAO userRoleDAO = new TbUserRoleDAO();

	/**
	 * 添加信息
	 * 
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "user/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbUser> add(final String id, final String username, final String password, final String staffId,final String roleIds) {
		JsonResponse<TbUser> jsonResponse = new JsonResponse<TbUser>();
		try {
			TbUser entity =getBean(id, username, password, staffId);
			String userId = dao.save(entity);
			String newroleIds = new String(roleIds.getBytes("iso8859-1"), "UTF-8");
			String[] roles = newroleIds.split(",");
			for(String roleId :roles){
				TbUser tbUser = new TbUser();
				tbUser.setUserId(userId);
				TbRole tbRole = new TbRole();
				tbRole.setId(Integer.parseInt(roleId));
				TbUserRole transientInstance = new TbUserRole(tbUser, tbRole);
				userRoleDAO.save(transientInstance);
			}
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.USER, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.USER, Constants.FAILURE));
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
	@RequestMapping(value = "user/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbUser> edit(final String id, final String username, final String password, final String staffId,final String roleIds) {
		JsonResponse<TbUser> jsonResponse = new JsonResponse<TbUser>();
		try {
			TbUser entity = getBean(id, username, password, staffId);
			// 更新用户表
			dao.update(entity);
			userRoleDAO.delete(entity.getUserId());
			String newroleIds = new String(roleIds.getBytes("iso8859-1"), "UTF-8");
			String[] roles = newroleIds.split(",");
			for(String roleId :roles){
				TbUser tbUser = new TbUser();
				tbUser.setUserId(entity.getUserId());
				TbRole tbRole = new TbRole();
				tbRole.setId(Integer.parseInt(roleId));
				TbUserRole transientInstance = new TbUserRole(tbUser, tbRole);
				userRoleDAO.save(transientInstance);
			}
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.USER, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.USER, Constants.FAILURE));
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
	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id, final String username, final String password, final String staffId) {
		try {
			TbUser entity = getBean(id, username, password, staffId);
			// 更新用户表
			dao.update(entity);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("user/index");
	}
	
	/**
	 * 获取列表
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "user/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbUser> list(@RequestParam(required = true, defaultValue = "1") int page,
														  @RequestParam(required = true, defaultValue = "10") int size,
														  @RequestParam(required = false) final String id,
														  @RequestParam(required = false) final String username,
														  @RequestParam(required = false) final String name) {
		JsonResponse<TbUser> jsonResponse = new JsonResponse<TbUser>();
		// 获取对应的参数
		String[] params = new String[] { id, username, name };
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
			Page<TbUser> findAll = dao.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.USER, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.USER, Constants.FAILURE));
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
	@RequestMapping(value = "user/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id, ModelMap modelMap) {
		TbUser tbUser = new TbUser();
		try {
			//编号不可能是中文，无需要处理
			tbUser = dao.findById(id);
			modelMap.put("update", "update");
			modelMap.put("user", tbUser);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return new ModelAndView("user/add");
	}

	/**
	 * 重构代码
	 * 
	 */
	private TbUser getBean(final String id, final String username, final String password, final String staffId) {
		TbUser entity = null;
		try {
			String newuserId = new String(id.getBytes("iso8859-1"), "UTF-8");
			String newname = new String(username.getBytes("iso8859-1"), "UTF-8");
			String newpassword = null;
			newpassword = new String(password.getBytes("iso8859-1"), "UTF-8");
			String newstaffId = new String(staffId.getBytes("iso8859-1"), "UTF-8");
			entity = new TbUser();
			entity.setUserId(newuserId);
			entity.setUserUsername(newname);
			entity.setUserPassword(newpassword);
			TbStaff tbStaff = new TbStaff();
			tbStaff.setStaffId(newstaffId);
			entity.setTbStaff(tbStaff);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}

}
