package examination.dao;

import java.util.List;
import java.util.Map;

public interface StartExamDAO {
	// ��ѯ��������
	public List<Map<String, Object>> getNum();

	// �����ȡ ����
	public List<Map<String, Object>> get(int type, int num);

	// �����
	public boolean saveAnswer(String  userName, int examinationId, int quetionsId,
			 String userAnswer,int QUESTION_SCORE, Integer score,int questionType);

	// ��ѯ����ʱ��
	public Map<String, Object> getExamTime();

	// ��ȡ��ȷ��
	public List<Map<String, Object>> getCorrect(int questionsId,
			int examinationId);

	// ������ʦ����
	public boolean update(String userName, int examinationId, int questionsId,
			String checkUser, int checkScore);

	// ����ѧ���ܷ�
	public int getTotalScore(String userName, int examinationId);

	// �����ܷ�
	public boolean saveScore(String userName, int examinationId,int totalScore);
	//�жϿ����Ƿ��Ѿ���½���տ��Խ���
	public Map<String ,Object> limit(String userName,int examinationId);
	public Map<String, Object> plan();
}
