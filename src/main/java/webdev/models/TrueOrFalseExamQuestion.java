package webdev.models;

import javax.persistence.Entity;

@Entity
public class TrueOrFalseExamQuestion extends BaseExamQuestion {
	private boolean isTrue;

	public boolean isTrue() {
		return isTrue;
	}
	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
}
