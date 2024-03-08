package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new ExamPrepApp();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to retrieve file, Cannot run application");
        }
    }
}
