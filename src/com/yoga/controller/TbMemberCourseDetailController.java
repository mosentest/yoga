package com.yoga.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.dao.TbCourseDAO;
import com.yoga.dao.TbMemberCourseDAO;
import com.yoga.dao.TbMemberCourseDetailDAO;
import com.yoga.dao.TbStaffCourseClassroomsDAO;
import com.yoga.dto.MemberCourseDetailDTO;
import com.yoga.entity.TbCourse;
import com.yoga.entity.TbMember;
import com.yoga.entity.TbMemberCourse;
import com.yoga.entity.TbMemberCourseDetail;
import com.yoga.entity.TbStaffCourseClassrooms;
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
public class TbMemberCourseDetailController {

	/**
	 * 获取dao
	 */
	private TbCourseDAO dao = new TbCourseDAO();
	private TbMemberCourseDAO memberCourseDAO = new TbMemberCourseDAO();
	private TbMemberCourseDetailDAO memberCourseDetailDAO = new TbMemberCourseDetailDAO();
	private TbStaffCourseClassroomsDAO staffCourseClassroomsDAO = new TbStaffCourseClassroomsDAO();

	/**
	 * 添加信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "memberCourse/add", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResponse<TbMemberCourse> add(@RequestBody final MemberCourseDetailDTO[] memberCourseDetailDTOs) {
		JsonResponse<TbMemberCourse> jsonResponse = new JsonResponse<TbMemberCourse>();
		try {
			// 用会员的编号+时间,作为消费的编号
			// TODO 修改数据库的字段长度
			// TODO还有一个数量没考虑到
			double cost = 0.0;
			for (MemberCourseDetailDTO memberConsumeDetailDTO : memberCourseDetailDTOs) {
				String consumeId = memberConsumeDetailDTO.getCourseId();
				TbStaffCourseClassrooms findById = staffCourseClassroomsDAO.findById(Integer.parseInt(consumeId));
				String courseId = findById.getTbCourse().getCourseId();
				TbCourse findById2 = dao.findById(courseId);
				String consumePrice = findById2.getCoursePrice();
				cost += Double.parseDouble(consumePrice);
			}
			String memberCourseId = (memberCourseDetailDTOs[0].getMemberId() + System.currentTimeMillis());
			TbMemberCourse entity = getBean(memberCourseId, memberCourseDetailDTOs[0].getMemberId(), cost + "");
			memberCourseDAO.save(entity);
			for (MemberCourseDetailDTO memberCourseDetailDTO : memberCourseDetailDTOs) {
				TbMemberCourseDetail memberCourseDetail = new TbMemberCourseDetail();
				TbStaffCourseClassrooms tbCourse = new TbStaffCourseClassrooms();
				tbCourse.setId(Integer.parseInt(memberCourseDetailDTO.getCourseId()));
				TbMemberCourse tbMemberCourse = new TbMemberCourse();
				tbMemberCourse.setMemberCourseId(memberCourseId);
				memberCourseDetail.setTbCourse(tbCourse);
				memberCourseDetail.setTbMemberCourse(tbMemberCourse);
				memberCourseDetailDAO.save(memberCourseDetail);
			}
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.COURSE, Constants.SUCCESS));
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.ADD, Constants.COURSE, Constants.FAILURE));
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
	@RequestMapping(value = "memberCourse/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id, final String memberId, String cost) {
		try {
			TbMemberCourse entity = getBean(id, memberId, cost);
			memberCourseDAO.update(entity);
			memberCourseDAO.delete(entity);
			memberCourseDetailDAO.delete(entity.getMemberCourseId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("memberCourse/index");
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
	@RequestMapping(value = "memberCourse/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbMemberCourse> list(@RequestParam(required = true, defaultValue = "1") int page,
			@RequestParam(required = true, defaultValue = "10") int size, @RequestParam(required = false) final String id,
			@RequestParam(required = false) final String name) {
		JsonResponse<TbMemberCourse> jsonResponse = new JsonResponse<TbMemberCourse>();
		// 获取对应的参数
		String[] params = new String[] { id, name };
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
			Page<TbMemberCourse> findAll = memberCourseDAO.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.COURSE, Constants.SUCCESS));
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(Constants.getTip(Constants.GET, Constants.COURSE, Constants.FAILURE));
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
	private TbMemberCourse getBean(final String id, final String memberId, String cost) {
		TbMemberCourse entity = null;
		try {
			entity = new TbMemberCourse();
			entity.setMemberCourseId(id);
			TbMember tbMember = new TbMember();
			tbMember.setMemberId(memberId);
			entity.setTbMember(tbMember);
			entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
			entity.setCost(cost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}
