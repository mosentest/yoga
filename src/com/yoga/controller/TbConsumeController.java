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

import com.yoga.dao.TbConsumeDAO;
import com.yoga.entity.TbConsume;
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
public class TbConsumeController  {

	/**
	 * 获取dao
	 */
	private TbConsumeDAO dao = new TbConsumeDAO();

	/**
	 * 添加信息
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "consume/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbConsume> add(final String id,final String name,final String price) {
		JsonResponse<TbConsume> jsonResponse = new JsonResponse<TbConsume>();
		try {
			TbConsume entity = getBean(id, name, price);
			dao.save(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.CONSUME, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.CONSUME, Constants.FAILURE));
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
	@RequestMapping(value = "consume/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbConsume> edit(final String id,final String name,final String price) {
		JsonResponse<TbConsume> jsonResponse = new JsonResponse<TbConsume>();
		try {
			TbConsume entity = getBean(id, name, price);
			dao.update(entity);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.CONSUME, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.EDIT, Constants.CONSUME, Constants.FAILURE));
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
	@RequestMapping(value = "consume/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id, final String name,final String price) {
		try {
			TbConsume entity = getBean(id, name, price);
			dao.update(entity);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("consume/index");
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
	@RequestMapping(value = "consume/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbConsume> list(@RequestParam(required = true, defaultValue = "1") int page,
																	 @RequestParam(required = true, defaultValue = "10") int size, 
																	 @RequestParam(required = false) final String id,
																	 @RequestParam(required = false) final String name,
																	 @RequestParam(required = false) final String price) {
		JsonResponse<TbConsume> jsonResponse = new JsonResponse<TbConsume>();
		//获取对应的参数
		String[] params = new String[]{id,name,price};
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
			Page<TbConsume> findAll = dao.findAll(page, size, params);
			for(TbConsume tb :findAll.getContent()){
				tb.setTbMemberConsumeDetails(null);
			}
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.CONSUME, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.CONSUME, Constants.FAILURE));
		}
		return jsonResponse;
	}
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "consume/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbConsume consume = new TbConsume();
		try {
			consume =dao.findById(id);
			modelMap.put("update", "update");
			modelMap.put("consume", consume);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("consume/add");
	}
	
	/**
	 * 重构代码
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	private TbConsume getBean(final String id, final String name,
			final String price) {
		TbConsume entity =null;
		try {
			String newId = new String(id.getBytes("iso8859-1"), "UTF-8");
			String newname = new String(name.getBytes("iso8859-1"), "UTF-8");
			String newprice = new String(price.getBytes("iso8859-1"), "UTF-8");
			entity = new TbConsume();
			entity.setConsumeId(newId);
			entity.setConsumeName(newname);
			entity.setConsumePrice(newprice);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
