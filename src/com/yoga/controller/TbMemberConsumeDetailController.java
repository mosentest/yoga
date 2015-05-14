package com.yoga.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbConsumeDAO;
import com.yoga.dao.TbMemberConsumeDAO;
import com.yoga.dao.TbMemberConsumeDetailDAO;
import com.yoga.dto.MemberConsumeDetailDTO;
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
	@RequestMapping(value = "memberConsume/add", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public JsonResponse<TbMemberConsume> add(@RequestBody final MemberConsumeDetailDTO[] memberConsumeDetailDTOs) {
		JsonResponse<TbMemberConsume> jsonResponse = new JsonResponse<TbMemberConsume>();
		try {
			//TODO还有一个数量没考虑到
			double cost=0.0;
			for(MemberConsumeDetailDTO memberConsumeDetailDTO : memberConsumeDetailDTOs){
				String consumeId = memberConsumeDetailDTO.getConsumeId();
				TbConsume findById = dao.findById(consumeId);
				String consumePrice = findById.getConsumePrice();
				cost += (Double.parseDouble(memberConsumeDetailDTO.getConsumeNum()) * Double.parseDouble(consumePrice));
			}
			//用会员的编号+时间,作为消费的编号
			//TODO 修改数据库的字段长度
			String memberConsumeId= memberConsumeDetailDTOs[0].getMemberId()+System.currentTimeMillis();
			TbMemberConsume entity = getBean(memberConsumeId, memberConsumeDetailDTOs[0].getMemberId(), cost+"");
			memberConsumeDAO.save(entity);
			for(MemberConsumeDetailDTO memberConsumeDetailDTO : memberConsumeDetailDTOs){
				TbMemberConsumeDetail memberConsumeDetail = new TbMemberConsumeDetail();
				TbConsume tbConsume = new TbConsume();
				tbConsume.setConsumeId(memberConsumeDetailDTO.getConsumeId());
				TbMemberConsume tbMemberConsume = new TbMemberConsume();
				tbMemberConsume.setMemberConsumeId(memberConsumeId);
				memberConsumeDetail.setTbConsume(tbConsume);
				memberConsumeDetail.setTbMemberConsume(tbMemberConsume);
				memberConsumeDetail.setNum(Integer.parseInt(memberConsumeDetailDTO.getConsumeNum()));
				memberConsumeDetailDAO.save(memberConsumeDetail);
			}
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
			entity = new TbMemberConsume();
			entity.setMemberConsumeId(id);
			TbMember tbMember = new TbMember();
			tbMember.setMemberId(memberId);
			entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
			entity.setTbMember(tbMember );
			entity.setCost(cost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}
