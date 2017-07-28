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
	 * ���ӿ��԰���
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
			string = "��������ȷ����ֵ";
			return string;
		}
		examination.setTotalScore(i);
		boolean bo = examinationDAO.add(examination);
		if (bo == false) {
			string = "���ӿ��԰���ʧ��";
			return string;
		}
		string = "���ӿ��԰��ųɹ�";
		return string;
	}

	private String judge(Examination examination) {
		String string = null;
		if (examination.getExaminationName() == null) {
			string = "�����뿼������";
			return string;
		}
		if (examination.getExaminationEndTime() == null) {
			string = "�����뿼�Կ�ʼʱ��";
			return string;
		}
		if (examination.getExaminationStartTime() == null) {
			string = "�����뿼�Խ���ʱ��";
			return string;
		}

		if (examination.getSingleChoiceQuestionNum() == null
				|| examination.getSingleChoiceQuestionNum() < 0) {
			string = "��������ȷ�ĵ�ѡ�����";
			return string;
		}
		if (examination.getSingleChoiceQuestionScore() == null
				|| examination.getSingleChoiceQuestionScore() < 0) {
			string = "��������ȷ�ĵ�ѡ�����";
			return string;
		}
		if (examination.getMultipleChoiceQuestionNum() == null
				|| examination.getMultipleChoiceQuestionNum() < 0) {
			string = "��������ȷ�Ķ�ѡ�����";
			return string;
		}
		if (examination.getMultipleChoiceQuestionScore() == null
				|| examination.getMultipleChoiceQuestionScore() < 0) {
			string = "��������ȷ�Ķ�ѡ�����";
			return string;
		}
		if (examination.getTrueOrFalseQuestionNum() == null
				|| examination.getTrueOrFalseQuestionNum() < 0) {
			string = "��������ȷ���ж������";
			return string;
		}
		if (examination.getTrueOrFalseQuestionScore() == null
				|| examination.getTrueOrFalseQuestionScore() < 0) {
			string = "��������ȷ���ж������";
			return string;
		}

		if (examination.getGapFillingNum() == null
				|| examination.getGapFillingNum() < 0) {
			string = "��������ȷ����������";
			return string;
		}
		if (examination.getGapFillingScore() == null
				|| examination.getGapFillingScore() < 0) {
			string = "��������ȷ����շ���";
			return string;
		}

		if (examination.getShortAnswerQustionNum() == null
				|| examination.getShortAnswerQustionNum() < 0) {
			string = "��������ȷ�ļ�������";
			return string;
		}
		if (examination.getShortAnswerQustionScore() == null
				|| examination.getShortAnswerQustionScore() < 0) {
			string = "��������ȷ�ļ� �������";
			return string;
		}
		return string;
	}

	/**
	 * ��ѯ���԰���
	 * 
	 * @return
	 */
	@GET
	@Path("/select")
	public List<Map<String, Object>> select() {
		return examinationDAO.select();
	}

	/**
	 * ɾ�����԰���
	 * 
	 * @return
	 */
	@DELETE
	@Path("/delete")
	@Produces("text/plain;charset=utf-8")
	public String delete(@QueryParam("id") Integer id) {
		String string;
		if (id == null) {
			string = "��������ֵ";
			return string;
		}
		boolean i = examinationDAO.delete(id);

		if (i) {
			string = "ɾ�����԰��ųɹ�";
			return string;
		}
		string = "ɾ�����԰���ʧ��";
		return string;
	}

	/**
	 * ���Ŀ��԰���
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
			string = "��������ȷ����ֵ";
			return string;
		}
		examination.setTotalScore(i);
		boolean bo = examinationDAO.update(examination);
		if (bo == false) {
			string = "���Ŀ��԰���ʧ��";
			return string;
		}
		string = " ���Ŀ��԰��ųɹ�";
		return string;
	}
}
