package examination.daoImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import examination.bean.Examination;
import examination.dao.ExaminationDAO;

public class ExaminationDAOImpl implements ExaminationDAO {

	public void init() {
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean add(Examination examination) {
		String sql = "insert into examinatiom(EXAMINATIOM_NAME,EXAMINATIOM_START_TIME,EXAMINATIOM_END_TIME,SINGLE_CHOICE_QUESTION_NUM,"
				+ "SINGLE_CHOICE_QUESTION_SCORE,MULTIPLE_CHOICE_QUESTION_NUM,MULTIPLE_CHOICE_QUESTION_SCORE,TRUE_OF_FALSE_QUESTION_NUM,"
				+ "TRUE_OF_FALSE_QUESTION_SCORE,SHORT_ANSWER_QUESTION_NUM,SHORT_ANSWER_QUESTION_SCORE,GAP_FILLING_NUM,GAP_FILLING_SCORE,TOTAL_SCORE,EXAMINATIOM_DESC)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { examination.getExaminationName(),
				Timestamp.valueOf(examination.getExaminationStartTime()),
				Timestamp.valueOf(examination.getExaminationEndTime()),
				examination.getSingleChoiceQuestionNum(),
				examination.getSingleChoiceQuestionScore(),
				examination.getMultipleChoiceQuestionNum(),
				examination.getMultipleChoiceQuestionScore(),
				examination.getTrueOrFalseQuestionNum(),
				examination.getTrueOrFalseQuestionScore(),
				examination.getShortAnswerQustionNum(),
				examination.getShortAnswerQustionScore(),
				examination.getGapFillingNum(),
				examination.getGapFillingScore(),
				examination.getTotalScore(), examination.getExaminationDesc() };
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
	public List<Map<String, Object>> select() {
		String sql = "select *  from examinatiom order by id asc";
		List<Map<String, Object>> list;
		try {
			list = this.jdbcTemplate.queryForList(sql);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Examination examination) {
		String sql = "update examinatiom "
				+ " set EXAMINATIOM_NAME=?,EXAMINATIOM_START_TIME=?,EXAMINATIOM_END_TIME=?,"
				+ " SINGLE_CHOICE_QUESTION_NUM=?,SINGLE_CHOICE_QUESTION_SCORE=?,MULTIPLE_CHOICE_QUESTION_NUM=?, "
				+ " MULTIPLE_CHOICE_QUESTION_SCORE=?,TRUE_OF_FALSE_QUESTION_NUM=?,TRUE_OF_FALSE_QUESTION_SCORE=?, "
				+ " SHORT_ANSWER_QUESTION_NUM=?,SHORT_ANSWER_QUESTION_SCORE=?,GAP_FILLING_NUM=?,GAP_FILLING_SCORE=?,TOTAL_SCORE=?,EXAMINATIOM_DESC=? "
				+ " where id =? ";
		Object[] params = { examination.getExaminationName(),
				Timestamp.valueOf(examination.getExaminationStartTime()),
				Timestamp.valueOf(examination.getExaminationEndTime()),
				examination.getSingleChoiceQuestionNum(),
				examination.getSingleChoiceQuestionScore(),
				examination.getMultipleChoiceQuestionNum(),
				examination.getMultipleChoiceQuestionScore(),
				examination.getTrueOrFalseQuestionNum(),
				examination.getTrueOrFalseQuestionScore(),
				examination.getShortAnswerQustionNum(),
				examination.getShortAnswerQustionScore(),
				examination.getGapFillingNum(),
				examination.getGapFillingScore(),
				examination.getTotalScore(), 
				examination.getExaminationDesc(),
				examination.getId() };
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
		String sql = "delete from examinatiom where id=" + id;
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
