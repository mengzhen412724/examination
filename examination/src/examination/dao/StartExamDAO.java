package examination.dao;

import java.util.List;
import java.util.Map;

public interface StartExamDAO {
	// 查询试题数量
	public List<Map<String, Object>> getNum();

	// 随机抽取 试题
	public List<Map<String, Object>> get(int type, int num);

	// 保存答案
	public boolean saveAnswer(String  userName, int examinationId, int quetionsId,
			 String userAnswer,int QUESTION_SCORE, Integer score,int questionType);

	// 查询考试时间
	public Map<String, Object> getExamTime();

	// 获取正确答案
	public List<Map<String, Object>> getCorrect(int questionsId,
			int examinationId);

	// 保存老师评分
	public boolean update(String userName, int examinationId, int questionsId,
			String checkUser, int checkScore);

	// 计算学生总分
	public int getTotalScore(String userName, int examinationId);

	// 保存总分
	public boolean saveScore(String userName, int examinationId,int totalScore);
	//判断考试是否已经登陆当日考试界面
	public Map<String ,Object> limit(String userName,int examinationId);
	public Map<String, Object> plan();
}
