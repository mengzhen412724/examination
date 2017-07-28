package examination.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import examination.dao.StartExamDAO;

@Component
public class StartExamDAOImpl implements StartExamDAO {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Override
	public Map<String, Object> limit(String userName, int examinationId) {
		String sql = "select count(*)  from examinatiom_questions_user "
				+ " where USER_NAME=? AND EXAMINATIOM_ID=? "
				+ " and MODIFY_TIME>=(select EXAMINATIOM_START_TIME from examinatiom where id=(select max(id) from examinatiom)) "
				+ " and MODIFY_TIME<=(select EXAMINATIOM_END_TIME from examinatiom where id=(select max(id) from examinatiom))";
		Object[] params = { userName, examinationId };
		try {
			Map<String, Object> map = this.jdbcTemplate
					.queryForMap(sql, params);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> get(int type, int num) {

		List<Map<String, Object>> list = null;
		String sql = "select distinct(B.QUESTION_ID),B.EXAMINATIOM_ID,A.QUESTION_DESC ,A.QUESTION_TYPE,A.ANSWER_A,A.ANSWER_B,A.ANSWER_C,A.ANSWER_D,A.ANSWER_E,A.ANSWER_F,A.ANSWER_G"
				+ " from QUESTIONS A,EXAMINATIOM_QUESTIONS B,EXAMINATIOM C"
				+ " where A.id=B.QUESTION_ID and B.EXAMINATIOM_ID =(select max(id) from EXAMINATIOM) "
				+ " and QUESTION_TYPE=? " + " order by rand()  limit ?";
		Object[] params = { type, num };
		try {
			list = jdbcTemplate.queryForList(sql, params);
//			System.out.println(list);
		} catch (Exception e) {
//			System.out.println(2);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean saveAnswer(String userName, int examinationId,
			int quetionsId, String userAnswer, int questionScore, Integer score,
			int questionType) {
		String sql = "insert into examinatiom_questions_user "
				+ " (USER_NAME,EXAMINATIOM_ID,QUESTIONS_ID,MODIFY_TIME,USER_ANSWER,QUESTION_SCORE,CHECK_USER,CHECK_TIME,CHECK_SCORE,QUESTION_TYPE) "
				+ " values(?,?,?,null,?,?,?,null,?,?)";
		if (score == -1) {
			score=null;
		}
		Object[] params = { userName, examinationId, quetionsId, userAnswer,
				questionScore, "system", score, questionType };
		try {
			int i = jdbcTemplate.update(sql, params);
			if (i >= 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<String, Object> getExamTime() {
		String sql = "select id,EXAMINATIOM_NAME,EXAMINATIOM_START_TIME,EXAMINATIOM_END_TIME,ROUND(UNIX_TIMESTAMP()-  UNIX_TIMESTAMP(EXAMINATIOM_START_TIME)) LEFTTIME from EXAMINATIOM where id=(select max(id) from EXAMINATIOM)";
		try {
			Map<String, Object> map = this.jdbcTemplate.queryForMap(sql);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getNum() {
		List<Map<String, Object>> list = null;
		String Sql = "select SINGLE_CHOICE_QUESTION_NUM ,MULTIPLE_CHOICE_QUESTION_NUM,TRUE_OF_FALSE_QUESTION_NUM,SHORT_ANSWER_QUESTION_NUM,GAP_FILLING_NUM "
				+ " from EXAMINATIOM "
				+ " where id= (select max(id) from EXAMINATIOM)";
		try {
			list = this.jdbcTemplate.queryForList(Sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getCorrect(int questionsId,
			int examinationId) {
		Map<String, Object> asMap = null;
		Map<String, Object> coreMap = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String asSql = "select QUESTION_TYPE,STANDARD_ANSWER from QUESTIONS where id ="
				+ questionsId;
		String coreSql = "select SINGLE_CHOICE_QUESTION_SCORE,MULTIPLE_CHOICE_QUESTION_SCORE,TRUE_OF_FALSE_QUESTION_SCORE,GAP_FILLING_SCORE,SHORT_ANSWER_QUESTION_SCORE from EXAMINATIOM where id ="
				+ examinationId;
		try {
			asMap = this.jdbcTemplate.queryForMap(asSql);
			coreMap = this.jdbcTemplate.queryForMap(coreSql);
			list.add(asMap);
			list.add(coreMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean update(String userName, int examinationId, int questionsId,
			String checkUser, int checkScore) {
		String sql = "update  EXAMINATIOM_QUESTIONS_USER "
				+ " set CHECK_USER ='" + checkUser + " ',CHECK_SCORE ="
				+ checkScore
				+ " where USER_NAME =? and EXAMINATIOM_ID=? and QUESTIONS_ID=?";
		Object[] params = { userName, examinationId,questionsId };
		try {
			int i = this.jdbcTemplate.update(sql, params);
			if (i >= 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getTotalScore(String userName, int examinationId) {
		String sql = "select sum(CHECK_SCORE) from EXAMINATIOM_QUESTIONS_USER "
				+ " where USER_NAME =? and EXAMINATIOM_ID=?";
		Object[] params = { userName, examinationId };
		int score = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = this.jdbcTemplate.queryForMap(sql, params);
			score = (int) map.get("sum(CHECK_SCORE)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return score;
	}

	@Override
	public boolean saveScore(String userName, int examinationId, int totalScore) {
		String sql = "insert into EXAMINATIOM_USER_SCORE(USER_NAME,EXAMINATIOM_ID,USER_TOTAL_SCORE)values("
				+ userName + "," + examinationId + "," + totalScore + ")";
		try {
			int i = this.jdbcTemplate.update(sql);
			if (i >= 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<String, Object> plan() {
		String sql = " select EXAMINATIOM_NAME,EXAMINATIOM_START_TIME,EXAMINATIOM_END_TIME from examinatiom "
				+ " where id=(select (max(id) from examinatiom)";
		try {
			Map<String, Object> map = this.jdbcTemplate.queryForMap(sql);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
