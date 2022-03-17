package base;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements Serializable {
	private ArrayList<Folder> folders;
	public NoteBook() {
		folders = new ArrayList<>();
	}
	public NoteBook(String file){
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			NoteBook readNoteBook = (NoteBook) objectInputStream.readObject();
			this.folders = readNoteBook.folders;
		}catch (Exception ex){
			ex.printStackTrace();
			assert false;
		}
	}
	private final long serialVersionUID = 1L;
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	public ArrayList<Folder> getFolders() {
		return folders;
	}
	public boolean insertNote(String folderName, Note note) {
		Folder folder = null;
		for (Folder f: folders ) {
			if (f.equals(new Folder(folderName))) {
				folder = f;
				break;
			}
		}
		if(folder == null) {
			folder = new Folder(folderName);
			folders.add(folder);
		}
		for(Note n : folder.getNotes()) {
			if(n.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		folder.addNote(note);
		return true;
	}
	public void sortFolders() {
		for (Folder folder : folders) {
			folder.sortNotes();
		}
		Collections.sort(folders);
	}
	public List<Note> searchNotes(String keywords) {
		ArrayList<Note> found = new ArrayList<Note>();
		for (Folder folder: folders) {
			found.addAll(folder.searchNotes(keywords));
		}
		return found;
	}
	/**
	 * method to save the NoteBook instance to file
	 *
	 * @param file, the path of the file where to save the object serialization
	 * @return true if save on file is successful, false otherwise
	 */
	public boolean save(String file) {
		try{
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
			outputStream.writeObject(this);
			outputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}


}
