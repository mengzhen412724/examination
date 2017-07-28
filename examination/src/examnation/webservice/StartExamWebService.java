package examnation.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import examination.dao.StartExamDAO;

@Component
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML,
		MediaType.APPLICATION_XML })
@Path("exam")
public class StartExamWebService {

	@Autowired
	private StartExamDAO StartExamDAO;

	/**
	 * ��������һ�ο��԰���
	 * 
	 * @return
	 */
	@GET
	@Path("plan")
	public Map<String, Object> plan() {
		Map<String, Object> map = StartExamDAO.plan();
		return map;
	}

	/**
	 * �ж��˻��Ƿ��Ѿ���½����
	 * 
	 * @return
	 */
	@GET
	@Path("mit")
	@Produces("text/plain;charset=utf-8")
	public String limit(@QueryParam("userName") String userName,
			@QueryParam("examinationId") int examinationId) {
		String string;
		Map<String, Object> map = StartExamDAO.limit(userName, examinationId);
		if ("0".equals(map.get("count(*)").toString())) {
			string = "���˺Ż�δ��½����";
			return string;
		}
		string = "���˺���δ��½����";
		return string;
	}

	/**
	 * ��ѯ����ʱ��(��ʼʱ�䡢����ʱ�䡢����ʱ)
	 * 
	 * @return
	 */
	@GET
	@Path("/get")
	public Map<String, Object> getExamTime() {
		Map<String, Object> map = StartExamDAO.getExamTime();
		if (map == null) {
			map = new HashMap<String, Object>();
			map.put("��ܰ��ʾ", "���޿���");
		}
		return map;
	}

	/**
	 * ����ԇ�}
	 * 
	 * @return
	 */
	@GET
	@Path("/getQuestions")
	public List<Object> getQuestions() {
		List<Object> total = new ArrayList<Object>();
		List<Map<String, Object>> num = StartExamDAO.getNum();
		if (num == null || num.size() == 0) {
			return null;
		}
		int single = (int) num.get(0).get("SINGLE_CHOICE_QUESTION_NUM");
		int multiple = (int) num.get(0).get("MULTIPLE_CHOICE_QUESTION_NUM");
		int judge = (int) num.get(0).get("TRUE_OF_FALSE_QUESTION_NUM");
		int shortAnswer = (int) num.get(0).get("SHORT_ANSWER_QUESTION_NUM");
		int gapFilling = (int) num.get(0).get("GAP_FILLING_NUM");
		List<Map<String, Object>> singleChoice = StartExamDAO.get(0, single);
		List<Map<String, Object>> multipleChoice = StartExamDAO
				.get(1, multiple);
		List<Map<String, Object>> judgeChoice = StartExamDAO.get(2, judge);
		List<Map<String, Object>> swChoice = StartExamDAO.get(4, shortAnswer);
		List<Map<String, Object>> gap = StartExamDAO.get(3, gapFilling);
		total.add(singleChoice);
		total.add(multipleChoice);
		total.add(judgeChoice);
		total.add(gap);
		total.add(swChoice);
		return total;
	}

	/**
	 * ���濼�Խ��
	 * 
	 * @return
	 */
	@POST
	@Path("/submmit")
	@Produces("text/plain;charset=utf-8")
	public String submmit(List<Map<String, Object>> list) {
//		System.out.println(list.toString());
		String string = "";
		boolean bo = true;
		for (int i = 0; i < list.size(); i++) {
			int score = 0;
			String userName = (String) list.get(i).get("USER_NAME");
			int examinationId = (int) list.get(i).get("EXAMINATIOM_ID");
			int questionsId = (int) list.get(i).get("QUESTIONS_ID");
			String answer = (String) list.get(i).get("USER_ANSWER");
			if (answer == null) {
				answer = " ";
			}
			// �жϽ���Ƿ���ȷ
			List<Map<String, Object>> resultList = StartExamDAO.getCorrect(
					questionsId, examinationId);
			Integer ques_score = 0;
			if ("0".equals(resultList.get(0).get("QUESTION_TYPE").toString())) {
				score = (int) (resultList.get(1)
						.get("SINGLE_CHOICE_QUESTION_SCORE"));
				if (answer.equals(resultList.get(0).get("STANDARD_ANSWER")
						.toString())) {
					ques_score = score;
				}
				bo = StartExamDAO.saveAnswer(userName, examinationId,
						questionsId, answer, score, ques_score, 0);
			}
			if ("1".equals(resultList.get(0).get("QUESTION_TYPE").toString())) {
				score = (int) (resultList.get(1)
						.get("MULTIPLE_CHOICE_QUESTION_SCORE"));
				if (answer.equals(resultList.get(0).get("STANDARD_ANSWER")
						.toString())) {
					ques_score = score;
				}
				bo = StartExamDAO.saveAnswer(userName, examinationId,
						questionsId, answer, score, ques_score, 1);
			}

			if ("2".equals(resultList.get(0).get("QUESTION_TYPE").toString())) {
				score = (int) (resultList.get(1)
						.get("TRUE_OF_FALSE_QUESTION_SCORE"));
				if (answer.equals(resultList.get(0).get("STANDARD_ANSWER")
						.toString().toLowerCase())) {
					ques_score = score;
				}
				bo = StartExamDAO.saveAnswer(userName, examinationId,
						questionsId, answer, score, ques_score, 2);
			}
			if ("3".equals(resultList.get(0).get("QUESTION_TYPE").toString())) {
				score = (int) (resultList.get(1)
						.get("GAP_FILLING_SCORE"));
				bo = StartExamDAO.saveAnswer(userName, examinationId,
						questionsId, answer, score, -1, 3);
			}
			if ("4".equals(resultList.get(0).get("QUESTION_TYPE").toString())) {
				score = (int) (resultList.get(1)
						.get("SHORT_ANSWER_QUESTION_SCORE"));
				bo = StartExamDAO.saveAnswer(userName, examinationId,
						questionsId, answer, score, -1, 4);
			}
		}
		if (bo == false) {
			string = "�Ծ��ύʧ��";
			return string;
		}
		if (string == "") {
			string = "���׾�";
		}
		string = "�Ծ��ύ�ɹ�";
		return string;

	}

	/**
	 * ��ʦ�ľ������� userId��examinationId��questionsId��checkUser��checkTime��checkScore
	 * 
	 * @return
	 */
	@GET
	@Path("/update")
	@Produces("text/plain;charset=utf-8")
	public String update(@QueryParam("USER_NAME") String userName,
			@QueryParam("EXAMINATIOM_ID") int examinationId,
			@QueryParam("QUESTIONS_ID") int questionsId,
			@QueryParam("CHECK_USER") String checkUser,
			@QueryParam("CHECK_SCORE") int checkScore) {
		// ����ʦ�ļ���u�ִ��뵽����
		String string = null;
		if (userName == null) {
			string = "�����뿼����";
			return string;
		}
		if (examinationId == 0) {
			string = "�����뿼�Ժ�";
			return string;
		}
		if (questionsId == 0) {
			string = "��������Ŀ��";
			return string;
		}
		if (checkUser == null) {
			string = "�������ľ���";
			return string;
		}
		if (checkScore == 0) {
			string = "����������";
			return string;
		}
		boolean i = StartExamDAO.update(userName, examinationId, questionsId,
				checkUser, checkScore);
		if (i == false) {
			string = "����ʧ��";
			return string;
		}
		/*// �����ִܷ��뵽����
		int totalScore = StartExamDAO.getTotalScore(userName, examinationId);
		boolean bo = StartExamDAO
				.saveScore(userName, examinationId, totalScore);
		if (bo == true) {
			string = "�ύ�ɹ�";
			return string;
		}*/
		string = "���ֳɹ�";
		return string;
	}
}
