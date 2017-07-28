package examination.daoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import examination.dao.ExamResultDAO;

public class ExamResultDAOImpl implements ExamResultDAO {

	public void init() {
	}

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> queryAllResult(String userName) {
		/*String sql = "SELECT A.id examId ,name,time ,score,total  FROM  "
				+ "  (SELECT EXAMINATIOM_NAME name,ID,DATE_FORMAT(EXAMINATIOM_START_TIME,'%y-%m-%d') time"
				+ " FROM examinatiom "
				+ " GROUP BY id) A, "
				+ " (SELECT EXAMINATIOM_ID, SUM(check_score) score"
				+ " FROM examinatiom_questions_user  "
				+ " WHERE question_type!=3 AND USER_NAME=? "
				+ " GROUP BY EXAMINATIOM_ID)C, "
				+ " (SELECT EXAMINATIOM_ID, SUM(check_score) total"
				+ " FROM examinatiom_questions_user "
				+ " WHERE USER_NAME=? "
				+ " GROUP BY EXAMINATIOM_ID)D "
				+ " WHERE C.EXAMINATIOM_ID=D.EXAMINATIOM_ID  AND A.ID=C.EXAMINATIOM_ID "
               +  " ORDER BY A.ID DESC";*/
		String sql = "SELECT A.id examId ,name,time ,total  FROM  "
				+ "  (SELECT EXAMINATIOM_NAME name,ID,DATE_FORMAT(EXAMINATIOM_START_TIME,'%y-%m-%d') time"
				+ " FROM examinatiom "
				+ " GROUP BY id) A, "
				+ " (SELECT EXAMINATIOM_ID, SUM(check_score) total"
				+ " FROM examinatiom_questions_user "
				+ " WHERE USER_NAME=? "
				+ " GROUP BY EXAMINATIOM_ID)D "
				+ " WHERE A.ID=D.EXAMINATIOM_ID "
                +  " ORDER BY A.ID DESC";
		
		Object[] params = { userName};
		try {
			List<Map<String, Object>> list = this.jdbcTemplate.queryForList(
					sql, params);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> get(String userName, int examinationId,
			int qustionType) {
		String sql = " SELECT QUESTION_DESC, ANSWER_A,ANSWER_B , ANSWER_C,ANSWER_D , ANSWER_E ,ANSWER_F,ANSWER_G ,STANDARD_ANSWER ,USER_ANSWER,CHECK_SCORE FROM "
				+ " (SELECT Id,QUESTION_TYPE,QUESTION_DESC, ANSWER_A,ANSWER_B , ANSWER_C,ANSWER_D , ANSWER_E ,ANSWER_F,ANSWER_G ,STANDARD_ANSWER "
				+ " FROM questions"
				+ " WHERE QUESTION_TYPE=?)A, "
				+ " (SELECT QUESTIONS_ID ,CHECK_SCORE,USER_ANSWER "
				+ " FROM examinatiom_questions_user "
				+ " WHERE USER_NAME=? AND EXAMINATIOM_ID= ?)B "
				+ " WHERE A.id=B.QUESTIONS_ID";
		Object[] params = { qustionType, userName, examinationId };
		try {
			List<Map<String, Object>> list = this.jdbcTemplate.queryForList(
					sql, params);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getSw(int examinationId) {
		String sql = " SELECT A.QUESTIONS_ID, A.USER_NAME ,A.QUESTION_TYPE,B.QUESTION_DESC,B.STANDARD_ANSWER,A.USER_ANSWER,C.SHORT_ANSWER_QUESTION_SCORE ,C.GAP_FILLING_SCORE "
				+ " FROM examinatiom_questions_user A, questions B,examinatiom C "
				+ " WHERE A.QUESTION_TYPE in(3,4)  AND A.EXAMINATIOM_ID=? AND A.CHECK_SCORE is  null "
				+ " AND A.QUESTIONS_ID=B.ID AND  A.EXAMINATIOM_ID=C.ID "
				+ " LIMIT 1";
		Object[] params = { examinationId };
		try {
			List<Map<String, Object>> list = this.jdbcTemplate.queryForList(
					sql, params);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> get(int examinationId) {
		String sql=" SELECT USER_NAME,SUM(check_SCORE) "
				+ " FROM examinatiom_questions_user "
				+ " WHERE EXAMINATIOM_ID=" + examinationId
				+ " GROUP BY USER_NAME "
				+ " ORDER BY SUM(check_SCORE) DESC";
		try {
			List<Map<String, Object>> list = this.jdbcTemplate
					.queryForList(sql);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
