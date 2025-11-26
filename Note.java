package Supo2.project.Notes;


// A Note object represents one note with a unique id, a title, and content.
// It stores data only.

public class Note {
    private final int id;       // unique identifier for the note
    private String title;       // short text describing the note
    private String content;     // main content of the note

    
    // Constructor to build a note with a given id, title and content.
    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Basic getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // toString() is used for listing notes in the CLI.
    // Example: [3] Shopping list
    @Override
    public String toString() {
        return "[" + id + "] " + title;
    }
}



