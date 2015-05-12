package com.yoga.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbConsumeDAO;
import com.yoga.dao.TbMemberConsumeDAO;
import com.yoga.dao.TbMemberConsumeDetailDAO;
import com.yoga.entity.TbConsume;
import com.yoga.entity.TbMember;
import com.yoga.entity.TbMemberConsume;
import com.yoga.entity.TbMemberConsumeDetail;
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
public class TbMemberConsumeDetailController  {

	/**
	 * 获取dao
	 */
	private TbConsumeDAO dao = new TbConsumeDAO();
	private  TbMemberConsumeDAO memberConsumeDAO  = new TbMemberConsumeDAO();
	private TbMemberConsumeDetailDAO memberConsumeDetailDAO = new TbMemberConsumeDetailDAO();

	/**
	 * 添加信息
	 * @return
	 */
	@RequestMapping(value = "memberConsume/add", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbMemberConsume> add(final String id, final String memberId, final String cost, HttpSession session) {
		JsonResponse<TbMemberConsume> jsonResponse = new JsonResponse<TbMemberConsume>();
		try {
			//TODO还有一个数量没考虑到
			TbMemberConsume entity = getBean(id, memberId, cost);
			String memberConsumeId = memberConsumeDAO.save(entity);
			session.getAttribute("");
//			String newconsumeIds = new String(consumeIds.getBytes("iso8859-1"), "UTF-8");
//			String[] consumes = newconsumeIds.split(",");
//			for(String consumeId :consumes){
//				TbMemberConsumeDetail memberConsumeDetail = new TbMemberConsumeDetail();
//				TbConsume tbConsume = new TbConsume();
//				tbConsume.setConsumeId(consumeId);
//				TbMemberConsume tbMemberConsume = new TbMemberConsume();
//				tbMemberConsume.setMemberConsumeId(memberConsumeId);
//				memberConsumeDetail.setTbConsume(tbConsume);
//				memberConsumeDetail.setTbMemberConsume(tbMemberConsume);
//				memberConsumeDetailDAO.save(memberConsumeDetail);
//			}
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
	@RequestMapping(value = "memberConsume/edit", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbMemberConsume> edit(final String id, final String memberId, final String cost, HttpSession session) {
		JsonResponse<TbMemberConsume> jsonResponse = new JsonResponse<TbMemberConsume>();
		try {
			TbMemberConsume entity = getBean(id, memberId, cost);
			memberConsumeDAO.update(entity);
			memberConsumeDetailDAO.delete(entity.getMemberConsumeId());
//			String newconsumeIds = new String(consumeIds.getBytes("iso8859-1"), "UTF-8");
//			String[] consumes = newconsumeIds.split(",");
//			for(String consumeId :consumes){
//				TbMemberConsumeDetail memberConsumeDetail = new TbMemberConsumeDetail();
//				TbConsume tbConsume = new TbConsume();
//				tbConsume.setConsumeId(consumeId);
//				TbMemberConsume tbMemberConsume = new TbMemberConsume();
//				tbMemberConsume.setMemberConsumeId(entity.getMemberConsumeId());
//				memberConsumeDetail.setTbConsume(tbConsume);
//				memberConsumeDetail.setTbMemberConsume(tbMemberConsume);
//				memberConsumeDetailDAO.update(memberConsumeDetail);
//			}
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
	@RequestMapping(value = "memberConsume/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id, final String memberId, final String cost) {
		try {
			TbMemberConsume entity = getBean(id, memberId, cost);
			memberConsumeDAO.update(entity);
			memberConsumeDAO.delete(entity);
			memberConsumeDetailDAO.delete(entity.getMemberConsumeId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("memberConsume/index");
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
	@RequestMapping(value = "memberConsume/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbMemberConsume> list(@RequestParam(required = true, defaultValue = "1") int page,
																	 @RequestParam(required = true, defaultValue = "10") int size, 
																	 @RequestParam(required = false) final String id,
																	 @RequestParam(required = false) final String name) {
		JsonResponse<TbMemberConsume> jsonResponse = new JsonResponse<TbMemberConsume>();
		//获取对应的参数
		String[] params = new String[]{id,name};
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
			Page<TbMemberConsume> findAll = memberConsumeDAO.findAll(page, size, params);
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
	@RequestMapping(value = "memberConsume/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbMemberConsume memberConsume = new TbMemberConsume();
		try {
			memberConsume =memberConsumeDAO.findById(id);
			modelMap.put("update", "update");
			modelMap.put("memberConsume", memberConsume);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("memberConsume/add");
	}
	
	/**
	 * 重构代码
	 * 
	 * @param id订单号
	 * @param memberId会员编号
	 * @param cost消费总额
	 * @return
	 */
	private TbMemberConsume getBean(final String id, final String memberId, final String cost) {
		TbMemberConsume entity =null;
		try {
			entity.setMemberConsumeId(id);
			TbMember tbMember = new TbMember();
			tbMember.setMemberId(memberId);
			entity.setTbMember(tbMember );
			entity.setCost(cost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}
