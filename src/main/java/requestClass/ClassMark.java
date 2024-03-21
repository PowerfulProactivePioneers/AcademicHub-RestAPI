package requestClass;

public class ClassMark {
	private String cid,exam;

	public ClassMark(String cid, String exam) {
		super();
		this.cid = cid;
		this.exam = exam;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	@Override
	public String toString() {
		return "ClassMark [cid=" + cid + ", exam=" + exam + "]";
	}
	
}
