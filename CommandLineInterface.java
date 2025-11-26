package Supo2.project.Notes;

import java.util.Scanner;

/**
 * CommandLineInterface handles text-based interaction with the user:
 * - Reads commands
 * - Calls NotesManager methods
 * - Prints results to the terminal
 * It provides a very simple command-line menu system.
 */


public class CommandLineInterface {

    // Where notes are stored/managed
    private final NotesManager manager; 
    // Reads user input             
    private final Scanner scanner = new Scanner(System.in); 


    public CommandLineInterface(NotesManager manager) {
        this.manager = manager;
    }

    
    // Main loop - continues accepting commands until the user types quit/exit.
    public void run() {
        System.out.println("Welcome to Notes CLI. Type 'help' for commands.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            // Exit condition
            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            handleCommand(input);
        }
    }

    // Parses and executes a single command entered by the user.
    private void handleCommand(String input) {
        if (input.isEmpty()) return;

        // Split into command and optional arguments
        String[] parts = input.split("\\s+", 2);
        String cmd = parts[0].toLowerCase();
        String args = (parts.length > 1) ? parts[1] : "";

        switch (cmd) {
            case "help":
                printHelp();
                break;
            case "list":
                listNotes();
                break;
            case "new":
                createNote();
                break;
            case "show":
                showNote(args);
                break;
            case "edit":
                editNote(args);
                break;
            case "delete":
                deleteNote(args);
                break;
            default:
                System.out.println("Unknown command. Type 'help' for a list of commands.");
        }
    }

    // Command Handlers
    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("  help        - show this help message");
        System.out.println("  list        - list all notes");
        System.out.println("  new         - create a new note");
        System.out.println("  show <id>   - show a note's title + content");
        System.out.println("  edit <id>   - change a note's title + content");
        System.out.println("  delete <id> - remove a note");
        System.out.println("  quit / exit - close the app");
    }

    private void listNotes() {
        if (manager.getAllNotes().isEmpty()) {
            System.out.println("No notes yet.");
            return;
        }
        for (Note note : manager.getAllNotes()) {
            System.out.println(note);
        }
    }

    private void createNote() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Content: ");
        String content = scanner.nextLine();

        Note note = manager.addNote(title, content);
        System.out.println("Created note with id " + note.getId());
    }

    private void showNote(String args) {
        int id = parseId(args, "show <id>");
        if (id == -1) return;

        Note note = manager.getNote(id);
        if (note == null) {
            System.out.println("No note with id " + id);
        } else {
            System.out.println("Title: " + note.getTitle());
            System.out.println("Content: " + note.getContent());
        }
    }

    private void editNote(String args) {
        int id = parseId(args, "edit <id>");
        if (id == -1) return;

        Note note = manager.getNote(id);
        if (note == null) {
            System.out.println("No note with id " + id);
            return;
        }

        // Show current and allow user to update
        System.out.println("Current title: " + note.getTitle());
        System.out.print("New title (leave blank to keep): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) note.setTitle(newTitle);

        System.out.println("Current content: " + note.getContent());
        System.out.print("New content (leave blank to keep): ");
        String newContent = scanner.nextLine();
        if (!newContent.isEmpty()) note.setContent(newContent);

        System.out.println("Note updated.");
    }

    private void deleteNote(String args) {
        int id = parseId(args, "delete <id>");
        if (id == -1) return;

        boolean removed = manager.deleteNote(id);
        if (removed) System.out.println("Deleted note " + id);
        else System.out.println("No note with id " + id);
    }

    // Helper method to safely parse an ID from string input.
    // Returns -1 if invalid.
    private int parseId(String args, String usage) {
        args = args.trim();
        if (args.isEmpty()) {
            System.out.println("Usage: " + usage);
            return -1;
        }
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id. Usage: " + usage);
            return -1;
        }
    }
}


