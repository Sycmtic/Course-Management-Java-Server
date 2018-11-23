package webdev.models;

import javax.persistence.Entity;

@Entity
public class MultipleChoiceExamQuestion extends BaseExamQuestion {
	private String options;
	private int correctOption;
	
	public String[] getOptions() {
		if (options == null) return null;
		return options.split(",");
	}
	public void setOptions(String[] options) {
		StringBuilder sb = new StringBuilder();
		for (String s : options) {
			sb.append(s).append(",");
		}
		this.options = sb.toString();
	}
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}	
}
