package examination.dao;

import java.util.List;
import java.util.Map;

import examination.bean.Examination;

public interface ExaminationDAO {
	// 增加考试安排
	public boolean add(Examination examination);

	// 查询考试安排
	public List<Map<String, Object>> select();

	// 修改考试安排
	public boolean update(Examination examination);

	// 删除考试安排
	public boolean delete(int id);

}
