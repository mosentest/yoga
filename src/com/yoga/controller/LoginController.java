package com.yoga.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbRoleDAO;
import com.yoga.dao.TbUserDAO;
import com.yoga.dao.TbUserRoleDAO;
import com.yoga.dto.UserDTO;
import com.yoga.entity.TbRole;
import com.yoga.entity.TbUser;
import com.yoga.entity.TbUserRole;
import com.yoga.util.Base64Util;
import com.yoga.util.JsonResponse;

/**
 * 登录控制器
 * 
 * @author wwb
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {

	private TbUserDAO userDAO = new TbUserDAO();
	private TbUserRoleDAO userRoleDAO = new TbUserRoleDAO();
	String msg = "";

	@RequestMapping(value = "login.html", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResponse<String> login(@RequestBody final UserDTO userDTO, HttpSession session) {
		JsonResponse<String> jsonResponse = new JsonResponse<String>();
		
		TbUser tbUser = null;
		try {
			if(userDTO == null){
				msg = "请输入帐号和密码";
				throw new Exception(msg);
			}else{
				if(userDTO.getUsername() == null || "".equals(userDTO.getUsername().trim())){
					msg = "帐号不能为空";
					throw new Exception(msg);
				}
				if(userDTO.getUsername() == null || "".equals(userDTO.getPassword().trim())){
					msg = "密码不能为空";
					throw new Exception(msg);
				}
				List<TbUser> findByUserUsername = userDAO.findByUserUsername(userDTO.getUsername().trim());
				if (findByUserUsername == null || findByUserUsername.size() == 0) {
					msg = "不存在该用户";
					throw new Exception(msg);
				} else {
					tbUser = findByUserUsername.get(0);
					//解密
					byte[] decode = Base64Util.decode(userDTO.getPassword().trim());
					if (tbUser != null && !tbUser.getUserPassword().equals(new String(decode))) {
						msg = "帐号或密码错误";
						throw new Exception(msg);
					}
				}
				List<TbUserRole> userRole = userRoleDAO.findAll(tbUser.getUserId());
				if(userRole == null || userRole.size()==0){
					msg = "帐号或密码错误";
					throw new Exception(msg);
				}
				for(TbUserRole role :userRole){
					if(role.getTbRole().getId() == 2){
						session.setAttribute("roleId", "2");
						break;
					}else{
						session.setAttribute("roleId", ""+role.getTbRole().getId());
						break;
					}
				}
				session.setAttribute("username", tbUser.getUserUsername());
				jsonResponse.setSuccess(true);
			}
		} catch (Exception e) {
			jsonResponse.setMsg(msg);
			jsonResponse.setSuccess(false);
			e.printStackTrace();
		}
		return jsonResponse;
	}

	@RequestMapping(value = "logout.html",  method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String string = attributeNames.nextElement();
			session.removeAttribute(string);
		}
		return new ModelAndView("login");
	}
}
