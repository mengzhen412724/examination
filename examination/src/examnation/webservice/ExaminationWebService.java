package examnation.webservice;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import examination.bean.Examination;
import examination.dao.ExaminationDAO;

@Component
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML,
		MediaType.APPLICATION_XML })
@Path("examination")
public class ExaminationWebService {

	@Autowired
	ExaminationDAO examinationDAO;

	/**
	 * 增加考试安排
	 * 
	 * @return
	 */
	@POST
	@Path("/add")
	@Produces("text/plain;charset=utf-8")
	public String add(Examination examination) {
		String string = judge(examination);
		if (string != null) {
			return string;
		}
		int i = examination.getSingleChoiceQuestionNum()
				* examination.getSingleChoiceQuestionScore()
				+ examination.getMultipleChoiceQuestionNum()
				* examination.getMultipleChoiceQuestionScore()
				+ examination.getTrueOrFalseQuestionNum()
				* examination.getTrueOrFalseQuestionScore()
				+ examination.getGapFillingNum()
				* examination.getGapFillingScore()
				+ examination.getShortAnswerQustionNum()
				* examination.getShortAnswerQustionScore();
		if (i == 0) {
			string = "请输入正确的数值";
			return string;
		}
		examination.setTotalScore(i);
		boolean bo = examinationDAO.add(examination);
		if (bo == false) {
			string = "增加考试安排失败";
			return string;
		}
		string = "增加考试安排成功";
		return string;
	}

	private String judge(Examination examination) {
		String string = null;
		if (examination.getExaminationName() == null) {
			string = "请输入考试名称";
			return string;
		}
		if (examination.getExaminationEndTime() == null) {
			string = "请输入考试开始时间";
			return string;
		}
		if (examination.getExaminationStartTime() == null) {
			string = "请输入考试结束时间";
			return string;
		}

		if (examination.getSingleChoiceQuestionNum() == null
				|| examination.getSingleChoiceQuestionNum() < 0) {
			string = "请输入正确的单选题个数";
			return string;
		}
		if (examination.getSingleChoiceQuestionScore() == null
				|| examination.getSingleChoiceQuestionScore() < 0) {
			string = "请输入正确的单选题分数";
			return string;
		}
		if (examination.getMultipleChoiceQuestionNum() == null
				|| examination.getMultipleChoiceQuestionNum() < 0) {
			string = "请输入正确的多选题个数";
			return string;
		}
		if (examination.getMultipleChoiceQuestionScore() == null
				|| examination.getMultipleChoiceQuestionScore() < 0) {
			string = "请输入正确的多选题分数";
			return string;
		}
		if (examination.getTrueOrFalseQuestionNum() == null
				|| examination.getTrueOrFalseQuestionNum() < 0) {
			string = "请输入正确的判断题个数";
			return string;
		}
		if (examination.getTrueOrFalseQuestionScore() == null
				|| examination.getTrueOrFalseQuestionScore() < 0) {
			string = "请输入正确的判断题分数";
			return string;
		}

		if (examination.getGapFillingNum() == null
				|| examination.getGapFillingNum() < 0) {
			string = "请输入正确的填空题个数";
			return string;
		}
		if (examination.getGapFillingScore() == null
				|| examination.getGapFillingScore() < 0) {
			string = "请输入正确的填空分数";
			return string;
		}

		if (examination.getShortAnswerQustionNum() == null
				|| examination.getShortAnswerQustionNum() < 0) {
			string = "请输入正确的简答题个数";
			return string;
		}
		if (examination.getShortAnswerQustionScore() == null
				|| examination.getShortAnswerQustionScore() < 0) {
			string = "请输入正确的简 答题分数";
			return string;
		}
		return string;
	}

	/**
	 * 查询考试安排
	 * 
	 * @return
	 */
	@GET
	@Path("/select")
	public List<Map<String, Object>> select() {
		return examinationDAO.select();
	}

	/**
	 * 删除考试安排
	 * 
	 * @return
	 */
	@DELETE
	@Path("/delete")
	@Produces("text/plain;charset=utf-8")
	public String delete(@QueryParam("id") Integer id) {
		String string;
		if (id == null) {
			string = "请输入数值";
			return string;
		}
		boolean i = examinationDAO.delete(id);

		if (i) {
			string = "删除考试安排成功";
			return string;
		}
		string = "删除考试安排失败";
		return string;
	}

	/**
	 * 更改考试安排
	 * 
	 * @return
	 */
	@PUT
	@Path("/put")
	@Produces("text/plain;charset=utf-8")
	public String update(Examination examination) {
		String string = judge(examination);
		if (string != null) {
			return string;
		}
		int i = examination.getSingleChoiceQuestionNum()
				* examination.getSingleChoiceQuestionScore()
				+ examination.getMultipleChoiceQuestionNum()
				* examination.getMultipleChoiceQuestionScore()
				+ examination.getTrueOrFalseQuestionNum()
				* examination.getTrueOrFalseQuestionScore()
				+ examination.getGapFillingNum()
				* examination.getGapFillingScore()
				+ examination.getShortAnswerQustionNum()
				* examination.getShortAnswerQustionScore();
		if (i == 0) {
			string = "请输入正确的数值";
			return string;
		}
		examination.setTotalScore(i);
		boolean bo = examinationDAO.update(examination);
		if (bo == false) {
			string = "更改考试安排失败";
			return string;
		}
		string = " 更改考试安排成功";
		return string;
	}
}
