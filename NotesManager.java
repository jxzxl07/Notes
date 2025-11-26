package Supo2.project.Notes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

// NotesManager is responsible for managing a collection of Note objects.
// It offers basic CRUD functionality:
// This class does not handle user input.


public class NotesManager {

    // LinkedHashMap preserves insertion order for nicer listing
    private final Map<Integer, Note> notes = new LinkedHashMap<>();
    private int nextId = 1; // generates unique note IDs

    
    // Adds a new note to the collection.
    // Returns the note that was just created
    public Note addNote(String title, String content) {
        Note note = new Note(nextId++, title, content);
        notes.put(note.getId(), note);
        return note;
    }

    // Retrieves a note by its ID.
    // Returns the note, or null if not found
    public Note getNote(int id) {
        return notes.get(id);
    }

    
    // Deletes a note by ID.
    // Returns true if a note was removed, false if no such note exists
    public boolean deleteNote(int id) {
        return notes.remove(id) != null;
    }

    // Returns all notes for listing in the CLI.
    public Collection<Note> getAllNotes() {
        return notes.values();
    }
}


