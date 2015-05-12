package com.yoga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yoga.dao.TbUserDAO;
import com.yoga.dao.TbUserRoleDAO;
import com.yoga.dto.UserDTO;
import com.yoga.entity.TbUser;
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
	private TbUserRoleDAO roleDAO = new TbUserRoleDAO();

	@RequestMapping(value = "login")
	public JsonResponse<UserDTO> login(@RequestBody final UserDTO userDTO) {
		TbUser object = userDTO.toObject();
		return null;
	}

	public JsonResponse<UserDTO> logout() {
		return null;
	}
}
