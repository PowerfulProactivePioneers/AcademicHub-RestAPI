package requestClass;

public class ClassDate {
	private String date,cid;
	
	public ClassDate(String date, String cid) {
		super();
		this.date = date;
		this.cid = cid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "ClassDate [date=" + date + ", cid=" + cid + "]";
	}
	
	
	
}
