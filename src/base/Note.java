package base;

import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note> {
	private Date date;
	private String title;
	public Note(String title) {
		this.title = title;
		date = new Date();
	}
	public String getTitle() {
		return title;
	}
	@Override
	public int hashCode() {
		return Objects.hash(title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}
	@Override
	public int compareTo(Note o) {
		if(o.date.before(this.date)) {
			return -1;
		} else if (o.date.after(this.date)) {
			return 1;
		}
		return 0;
	}
	public String toString() {
		return date.toString() + "\t" + title;
	}
}
