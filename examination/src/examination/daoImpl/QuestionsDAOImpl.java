package examination.daoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import examination.bean.Questions;
import examination.dao.QuestionsDAO;

public class QuestionsDAOImpl implements QuestionsDAO {
	public void init() {
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean add(Questions questions) {
		String sql = "insert into QUESTIONS ("
				+ "QUESTION_DESC,QUESTION_TYPE,ANSWER_A,ANSWER_B,ANSWER_C,ANSWER_D,ANSWER_E,ANSWER_F,ANSWER_G,STANDARD_ANSWER,DEFAULT_SCORE,IMPORT_TIME) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { questions.getQuestionDesc(),
				questions.getQuestionType(), questions.getQuestionA(),
				questions.getQuestionB(), questions.getQuestionC(),
				questions.getQuestionD(), questions.getQuestionE(),
				questions.getQuestionF(), questions.getQuestionG(),
				questions.getStandardAnswer(), questions.getDefaultScore(),
				questions.getImportTime() };
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
	public List<Map<String, Object>> select(String examinationName) {
		List<Map<String, Object>> list;
		String sql = " select * "
				+ " from QUESTIONS A,EXAMINATIOM_QUESTIONS B,EXAMINATIOM C"
				+ " where A.id=B.QUESTION_ID and B.EXAMINATIOM_ID =C.id and c.EXAMINATIOM_NAME="
				+ examinationName;
		try {
			list = this.jdbcTemplate.queryForList(sql);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Questions questions) {
		String sql = "update QUESTIONS "
				+ " set QUESTION_DESC=?,QUESTION_TYPE=?,ANSWER_A=?, "
				+ " ANSWER_B=?,ANSWER_C=?,ANSWER_D=?,ANSWER_E=?, "
				+ "ANSWER_F=?,ANSWER_G=?,STANDARD_ANSWER=?,DEFAULT_SCORE=?,IMPORT_TIME=?";
		Object[] params = { questions.getQuestionDesc(),
				questions.getQuestionType(), questions.getQuestionA(),
				questions.getQuestionB(), questions.getQuestionC(),
				questions.getQuestionD(), questions.getQuestionE(),
				questions.getQuestionF(), questions.getQuestionG(),
				questions.getStandardAnswer(), questions.getDefaultScore(),
				questions.getImportTime() };
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
	public boolean delete(int id) {
		String sql = "delete from QUESTIONS where id=" + id;
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
}
