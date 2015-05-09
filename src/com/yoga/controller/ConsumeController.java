package com.yoga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoga.dao.TbConsumeDAO;
import com.yoga.entity.TbConsume;
import com.yoga.util.Constants;
import com.yoga.util.JsonResponse;
import com.yoga.util.Page;
@Controller
@RequestMapping("/")
public class ConsumeController  {

	private TbConsumeDAO dao = new TbConsumeDAO();
	public JsonResponse<TbConsume> add() {
		return null;
	}

	public JsonResponse<TbConsume> delete() {
		return null;
	}

	public JsonResponse<TbConsume> update() {
		return null;
	}
	
	@RequestMapping(value = "consume/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<com.yoga.entity.TbConsume> list(@RequestParam(required = true, defaultValue = "1") int page,
																	 @RequestParam(required = true, defaultValue = "10") int size, 
																	 @RequestParam(required = false) final String id,
																	 @RequestParam(required = false) final String name,
																	 @RequestParam(required = false) final String price) {
		JsonResponse<com.yoga.entity.TbConsume> jsonResponse = new JsonResponse<com.yoga.entity.TbConsume>();
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
			Page<com.yoga.entity.TbConsume> findAll = dao.findAll(page, size, params);
			for(com.yoga.entity.TbConsume tb :findAll.getContent()){
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

}
