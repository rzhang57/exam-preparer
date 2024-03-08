package persistence;

import model.CourseList;
import org.json.JSONObject;

import java.io.*;

public class JsonWriter {
    private PrintWriter writer;
    private String directory;

    public JsonWriter(String directory) {
        this.directory = directory;
    }


    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(directory));
    }

    public void write(CourseList cl) {
        JSONObject json = cl.toJson();
        writer.print(json.toString());
    }

    public void close() {
        writer.close();
    }
}
