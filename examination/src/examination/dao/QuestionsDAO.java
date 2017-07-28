package examination.dao;

import java.util.List;
import java.util.Map;

import examination.bean.Questions;

public interface QuestionsDAO {
	// 增加考试题目
	public boolean add(Questions questions);

	// 查询考试题目
	public List<Map<String, Object>> select(String examinationName);

	// 修改考试题目
	public boolean update(Questions questions);

	// 删除考试题目
	public boolean delete(int id);

}
