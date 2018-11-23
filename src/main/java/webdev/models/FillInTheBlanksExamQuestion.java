package webdev.models;

import javax.persistence.Entity;

@Entity
public class FillInTheBlanksExamQuestion extends BaseExamQuestion {
	String blanks;
	
	public String[] getBlanks() {
		if (blanks == null) return null;
		return blanks.split(",");
	}
	public void setBlanks(String[] blanks) {
		StringBuilder sb = new StringBuilder();
		for (String s : blanks) {
			sb.append(s).append(",");
		}
		this.blanks = sb.toString();
	}
}
