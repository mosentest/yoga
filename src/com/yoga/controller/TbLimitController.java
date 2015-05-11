package com.yoga.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbLimitDAO;
import com.yoga.entity.TbLimit;
import com.yoga.util.Constants;
import com.yoga.util.DateUtil;
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
public class TbLimitController  {

	/**
	 * 获取dao
	 */
	private TbLimitDAO dao = new TbLimitDAO();

	/**
	 * 添加信息
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "limit/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbLimit> add(final String id, final String name, final String href) {
		JsonResponse<TbLimit> jsonResponse = new JsonResponse<TbLimit>();
		try {
			TbLimit entity = getBean(id, name, href);
			dao.save(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.STAFF, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.STAFF, Constants.FAILURE));
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
	@RequestMapping(value = "limit/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbLimit> edit(final String id, final String name, final String href) {
		JsonResponse<TbLimit> jsonResponse = new JsonResponse<TbLimit>();
		try {
			TbLimit entity = getBean(id, name, href);
			//更新员工表
			dao.update(entity);
			//更新员工详细表
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.STAFF, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.STAFF, Constants.FAILURE));
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
	@RequestMapping(value = "limit/delete&oper=false", method = RequestMethod.POST)
	public ModelAndView delete(final String id, final String name, final String href) {
		try {
			TbLimit entity = getBean(id, name, href);
			//更新员工表
			dao.update(entity);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("staff/index");
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
	@RequestMapping(value = "limit/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbLimit> list(@RequestParam(required = true, defaultValue = "1") int page,
																	 @RequestParam(required = true, defaultValue = "10") int size, 
																	 @RequestParam(required = false) final String name) {
		JsonResponse<TbLimit> jsonResponse = new JsonResponse<TbLimit>();
		//获取对应的参数
		String[] params = new String[]{name};
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
			//xml文件修改lazy=false
			Page<TbLimit> findAll = dao.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.STAFF, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.STAFF, Constants.FAILURE));
		}
		return jsonResponse;
	}
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "limit/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbLimit TbLimit = new TbLimit();
		try {
			TbLimit = dao.findById(Integer.parseInt(id));
			modelMap.put("update", "update");
			modelMap.put("limit", TbLimit);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("limit/add");
	}
	
	/**
	 * 重构代码
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	private TbLimit getBean(final String id, final String name, final String href) {
		TbLimit entity = null;
		try {
			String newId = new String(id.getBytes("iso8859-1"), "UTF-8");
			String newname = new String(name.getBytes("iso8859-1"), "UTF-8");
			String newhref = new String(href.getBytes("iso8859-1"), "UTF-8");
			entity = new TbLimit();
			entity.setId(Integer.parseInt(newId));
			entity.setName(newname);
			entity.setHref(newhref);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
}
