package examination.bean;

import java.io.Serializable;

public class Examination implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String examinationName;
	private String examinationStartTime;
	private String examinationEndTime;
	private Integer singleChoiceQuestionNum;
	private Integer singleChoiceQuestionScore;
	private Integer multipleChoiceQuestionNum;
	private Integer multipleChoiceQuestionScore;
	private Integer trueOrFalseQuestionNum;
	private Integer trueOrFalseQuestionScore;
	private Integer gapFillingNum;
	private Integer gapFillingScore;
	private Integer shortAnswerQustionNum;
	private Integer shortAnswerQustionScore;
	private Integer totalScore;
	private String examinationDesc;

	
	
	public Integer getGapFillingNum() {
		return gapFillingNum;
	}

	public void setGapFillingNum(Integer gapFillingNum) {
		this.gapFillingNum = gapFillingNum;
	}

	public Integer getGapFillingScore() {
		return gapFillingScore;
	}

	public void setGapFillingScore(Integer gapFillingScore) {
		this.gapFillingScore = gapFillingScore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public String getExaminationStartTime() {
		return examinationStartTime;
	}

	public void setExaminationStartTime(String examinationStartTime) {
		this.examinationStartTime = examinationStartTime;
	}

	public String getExaminationEndTime() {
		return examinationEndTime;
	}

	public void setExaminationEndTime(String examinationEndTime) {
		this.examinationEndTime = examinationEndTime;
	}

	public Integer getSingleChoiceQuestionNum() {
		return singleChoiceQuestionNum;
	}

	public void setSingleChoiceQuestionNum(Integer singleChoiceQuestionNum) {
		this.singleChoiceQuestionNum = singleChoiceQuestionNum;
	}

	public Integer getSingleChoiceQuestionScore() {
		return singleChoiceQuestionScore;
	}

	public void setSingleChoiceQuestionScore(Integer singleChoiceQuestionScore) {
		this.singleChoiceQuestionScore = singleChoiceQuestionScore;
	}

	public Integer getMultipleChoiceQuestionNum() {
		return multipleChoiceQuestionNum;
	}

	public void setMultipleChoiceQuestionNum(Integer multipleChoiceQuestionNum) {
		this.multipleChoiceQuestionNum = multipleChoiceQuestionNum;
	}

	public Integer getMultipleChoiceQuestionScore() {
		return multipleChoiceQuestionScore;
	}

	public void setMultipleChoiceQuestionScore(
			Integer multipleChoiceQuestionScore) {
		this.multipleChoiceQuestionScore = multipleChoiceQuestionScore;
	}

	public Integer getTrueOrFalseQuestionNum() {
		return trueOrFalseQuestionNum;
	}

	public void setTrueOrFalseQuestionNum(Integer trueOrFalseQuestionNum) {
		this.trueOrFalseQuestionNum = trueOrFalseQuestionNum;
	}

	public Integer getTrueOrFalseQuestionScore() {
		return trueOrFalseQuestionScore;
	}

	public void setTrueOrFalseQuestionScore(Integer trueOrFalseQuestionScore) {
		this.trueOrFalseQuestionScore = trueOrFalseQuestionScore;
	}

	public Integer getShortAnswerQustionNum() {
		return shortAnswerQustionNum;
	}

	public void setShortAnswerQustionNum(Integer shortAnswerQustionNum) {
		this.shortAnswerQustionNum = shortAnswerQustionNum;
	}

	public Integer getShortAnswerQustionScore() {
		return shortAnswerQustionScore;
	}

	public void setShortAnswerQustionScore(Integer shortAnswerQustionScore) {
		this.shortAnswerQustionScore = shortAnswerQustionScore;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public String getExaminationDesc() {
		return examinationDesc;
	}

	public void setExaminationDesc(String examinationDesc) {
		this.examinationDesc = examinationDesc;
	}

}
