package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Folder implements Comparable<Folder> {
	private ArrayList<Note> notes;
	private String name;

	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note note : notes) {
			if (note instanceof TextNote) {
				nText++;
			} else if (note instanceof ImageNote) {
				nImage++;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}

	public void sortNotes() {
		Collections.sort(this.notes);
	}

	@Override
	public int compareTo(Folder o) {
		return this.name.compareTo(o.name);
	}

	private boolean isNoteContainsWord(Note note, String keyword) {
		if (note.getTitle().toUpperCase().contains(keyword.toUpperCase())) {
			return true;
		} else if (note instanceof TextNote) {
			TextNote _note = (TextNote) note;
			if (_note.content.toUpperCase().contains(keyword.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	public List<Note> searchNotes(String keywords) {
		String[] splitStr = keywords.split("\\s+");
		ArrayList<Note> found = new ArrayList<Note>();
		for (Note note : notes) {
			int cases = 0;
			int cases_matched = 0;
			for (int i = 0; i < splitStr.length; i++) {
				if (i + 2 < splitStr.length && (splitStr[i + 1].toUpperCase().equals("OR"))) {
					cases += 1;
					if (isNoteContainsWord(note, splitStr[i]) || isNoteContainsWord(note, splitStr[i + 2])) {
						if (!found.contains(note))
							cases_matched += 1;
					}
					i += 2;
				} else {
					cases += 1;
					if (isNoteContainsWord(note, splitStr[i])) {
						if (!found.contains(note))
							cases_matched += 1;
					}
				}
			}
			if (cases == cases_matched) {
				found.add(note);
			}
		}
		return found;
	}
}
