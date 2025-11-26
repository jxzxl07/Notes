package Supo2.project.Notes;

// Main creates the app components and starts the CLI loop.

public class Main {
    public static void main(String[] args) {
        NotesManager manager = new NotesManager();
        CommandLineInterface cli = new CommandLineInterface(manager);
        cli.run();
    }
}


