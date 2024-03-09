package persistence;

import model.CourseList;
import org.json.JSONObject;

import java.io.*;

// A class that interprets course list data and writes it into JSON format and saves to a file directory
// Citation: Adapted from Workroom project provided by 210 course staff
public class JsonWriter {
    private PrintWriter writer;
    private String directory;

    // EFFECTS: creates writer with its given directory to write to
    public JsonWriter(String directory) {
        this.directory = directory;
    }

    // MODIFIES: this
    // EFFECTS: opens writer, if no file found at directory, throw FileNotFoundException
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(directory));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of course list to the file
    public void write(CourseList cl) {
        JSONObject json = cl.toJson();
        writer.print(json.toString());
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }
}
