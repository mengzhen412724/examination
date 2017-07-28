package examination.dao;

import java.util.List;
import java.util.Map;

import examination.bean.Questions;

public interface QuestionsDAO {
	// ���ӿ�����Ŀ
	public boolean add(Questions questions);

	// ��ѯ������Ŀ
	public List<Map<String, Object>> select(String examinationName);

	// �޸Ŀ�����Ŀ
	public boolean update(Questions questions);

	// ɾ��������Ŀ
	public boolean delete(int id);

}
