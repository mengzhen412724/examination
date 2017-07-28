package examination.bean;

import java.io.Serializable;
import java.security.Timestamp;

public class ExaminationQuestionsUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int userId;
	private int examinationId;
	private int questionsId;
	private Timestamp modifyTime;
	private String userAnswer;
	private int questionsScore;
	private String checkUser;
	private Timestamp checkTime;
	private int checkScore;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(int examinationId) {
		this.examinationId = examinationId;
	}

	public int getQuestionsId() {
		return questionsId;
	}

	public void setQuestionsId(int questionsId) {
		this.questionsId = questionsId;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public int getQuestionsScore() {
		return questionsScore;
	}

	public void setQuestionsScore(int questionsScore) {
		this.questionsScore = questionsScore;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public Timestamp getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}

	public int getCheckScore() {
		return checkScore;
	}

	public void setCheckScore(int checkScore) {
		this.checkScore = checkScore;
	}

}
