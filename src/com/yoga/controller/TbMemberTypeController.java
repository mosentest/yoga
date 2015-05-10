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

import com.yoga.dao.TbMemberTypeDAO;
import com.yoga.entity.TbMemberType;
import com.yoga.util.Constants;
import com.yoga.util.JsonResponse;

/**
 * action
 * 
 * @author wwb
 * 
 */
@Controller
@RequestMapping("/")
public class TbMemberTypeController  {

	/**
	 * 获取dao
	 */
	private TbMemberTypeDAO dao = new TbMemberTypeDAO();

	/**
	 * 添加信息
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "memberType/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbMemberType> add(final int id, String type) {
		JsonResponse<TbMemberType> jsonResponse = new JsonResponse<TbMemberType>();
		try {
			TbMemberType entity = getBean(id,type);
			dao.save(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.MEMBER, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.MEMBER, Constants.FAILURE));
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
	@RequestMapping(value = "memberType/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbMemberType> edit(final int id, String type) {
		JsonResponse<TbMemberType> jsonResponse = new JsonResponse<TbMemberType>();
		try {
			TbMemberType entity = getBean(id, type);
//			dao.update(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.MEMBER, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.MEMBER, Constants.FAILURE));
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
	@RequestMapping(value = "memberType/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int id,String type) {
		try {
			TbMemberType entity = getBean(id,type);
//			dao.update(entity);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("memberType/index");
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
	@RequestMapping(value = "memberType/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbMemberType> list() {
		JsonResponse<TbMemberType> jsonResponse = new JsonResponse<TbMemberType>();
		try {
			List<TbMemberType> findAll = dao.findAll();
			for(TbMemberType tb :findAll){
				tb.setTbMembers(null);
			}
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.MEMBER, Constants.SUCCESS));
			jsonResponse.setList(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.MEMBER, Constants.FAILURE));
		}
		return jsonResponse;
	}
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "memberType/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final int id,ModelMap modelMap) {
		TbMemberType memberType = new TbMemberType();
		try {
			memberType =dao.findById(id);
			modelMap.put("update", "update");
			modelMap.put("memberType", memberType);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("member/add");
	}
	
	/**
	 * 重构代码
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	private TbMemberType getBean(int id, String type) {
		TbMemberType entity = null;
		try {
			String newtype = new String(type.getBytes("iso8859-1"), "UTF-8");
			entity = new TbMemberType();
			entity.setId(id);
			entity.setType(newtype);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
}
