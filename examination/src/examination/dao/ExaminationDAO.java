package examination.dao;

import java.util.List;
import java.util.Map;

import examination.bean.Examination;

public interface ExaminationDAO {
	// ���ӿ��԰���
	public boolean add(Examination examination);

	// ��ѯ���԰���
	public List<Map<String, Object>> select();

	// �޸Ŀ��԰���
	public boolean update(Examination examination);

	// ɾ�����԰���
	public boolean delete(int id);

}
