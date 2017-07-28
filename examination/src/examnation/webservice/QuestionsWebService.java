package examnation.webservice;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import examination.bean.Questions;
import examination.dao.QuestionsDAO;

@Component
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML,
		MediaType.APPLICATION_XML })
@Path("questions")
public class QuestionsWebService {

	@Autowired
	QuestionsDAO questionsDAO;

	/**
	 * 增加考试题目
	 * 
	 * @return
	 */
	@POST
	@Path("/add")
	@Produces("text/plain;charset=utf-8")
	public String add(Questions questions) {
		String string = judge(questions);
		if (string != null) {
			return string;
		}
		boolean bo = questionsDAO.add(questions);
		if (bo == false) {
			string = "增加考试题目失败";
			return string;
		}
		string = "增加考试题目成功";
		return string;
	}

	private String judge(Questions questions) {
		String string = null;
		if (questions.getQuestionDesc() == null) {
			string = "请输入题目正文";
			return string;
		}
		if (questions.getQuestionType() == null) {
			string = "请输入题目类型";
			return string;
		}
		if (questions.getStandardAnswer() == null) {
			string = "请输入正确答案";
			return string;
		}
		if (questions.getDefaultScore() == null) {
			string = "请输入默认题目分数";
			return string;
		}
		return string;
	}

	/**
	 * 查询考试题目
	 * 
	 * @return
	 */
	@GET
	@Path("/select")
	public List<Map<String, Object>> select(@QueryParam("examinationName") String examinationName) {
		if(examinationName==null){
			examinationName="c.EXAMINATIOM_NAME";
		}
		return questionsDAO.select(examinationName);
	}

	/**
	 * 删除考试题目
	 * 
	 * @return
	 */
	@DELETE
	@Path("/delete")
	@Produces("text/plain;charset=utf-8")
	public String delete(@QueryParam("id") Integer id) {
		String string;
		if (id == null) {
			string = "请输入数值";
			return string;
		}
		boolean i = questionsDAO.delete(id);

		if (i) {
			string = "删除考试安排成功";
			return string;
		}
		string = "删除考试安排失败";
		return string;
	}

	/**
	 * 更改考试题目
	 * 
	 * @return
	 */
	@PUT
	@Path("/put")
	@Produces("text/plain;charset=utf-8")
	public String update(Questions questions) {
		String string = judge(questions);
		if (string != null) {
			return string;
		}
		boolean bo = questionsDAO.update(questions);
		if (bo == false) {
			string = "增加考试题目失败";
			return string;
		}
		string = "增加考试题目成功";
		return string;
	}
}
