package examination.dao;

import java.util.List;
import java.util.Map;

public interface ExamResultDAO {
	public  List<Map<String,Object>> queryAllResult(String userName);
	public  List<Map<String,Object>> get(String userName,int examinationId,int qustionType);
	public  List<Map<String,Object>> getSw(int examinationId);
	public List<Map<String,Object>> get(int examinationId);
}
