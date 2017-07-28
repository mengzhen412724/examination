package examnation.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import examination.dao.ExamResultDAO;

@Component
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML,
		MediaType.APPLICATION_XML })
@Path("result")
public class ExamResultWebService {

	@Autowired
	private ExamResultDAO ExamResultDAO;

	/**
	 * ��ѯѧ�����ο��Խ��
	 * 
	 * @return
	 */
	@GET
	@Path("/allResult")
	public List<Map<String, Object>> queryAllResult(
			@QueryParam("userName") String userName) {
		if (userName == null) {
			return null;
		}
		List<Map<String, Object>> list = ExamResultDAO.queryAllResult(userName);
		return list;
	}

	/**
	 * �鿴�Ծ�÷����
	 * 
	 * @return
	 */
	@GET
	@Path("/allQuestions")
	public List<Object> get(@QueryParam("userName") String userName,
			@QueryParam("examinationId") Integer examinationId) {
		if (userName == null) {
			return null;
		}
		if (examinationId == null) {
			return null;
		}
		List<Object> list = new ArrayList<Object>();
		List<Map<String, Object>> singleChoice = ExamResultDAO.get(userName,
				examinationId, 0);
		List<Map<String, Object>> multipleChoice = ExamResultDAO.get(userName,
				examinationId, 1);
		List<Map<String, Object>> judgeChoice = ExamResultDAO.get(userName,
				examinationId, 2);
		List<Map<String, Object>> fill = ExamResultDAO.get(userName,
				examinationId, 3);
		List<Map<String, Object>> swChoice = ExamResultDAO.get(userName,
				examinationId, 4);
		list.add(singleChoice);
		list.add(multipleChoice);
		list.add(judgeChoice);
		list.add(fill);
		list.add(swChoice);
		return list;
	}

	/**
	 * ���ֽ���
	 * 
	 * @return
	 */
	@GET
	@Path("/swChoice")
	public List<Map<String, Object>> get(
			@QueryParam("examinationId") Integer examinationId) {
		if (examinationId == null) {
			return null;
		}
		List<Map<String, Object>> list = ExamResultDAO.getSw(
				examinationId);
		return list;
	}

	/**
	 * ��ѯͬһ����������ѧ���ɼ�
	 * 
	 * @return
	 */
	@GET
	@Path("/get")
	public List<Map<String, Object>> getSw(
			@QueryParam("examinationId") Integer examinationId) {
		if (examinationId == null) {
			return null;
		}
		List<Map<String, Object>> list = ExamResultDAO.get(examinationId);
		return list;
	}
}
