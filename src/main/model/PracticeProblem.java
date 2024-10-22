package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a practice problem with a problem title, solution, and status
public class PracticeProblem implements Writable {

    private String problemTitle;
    private String problemBody;
    private String solution;
    private int status;

    /*
    REQUIRES: problemTitle, problemBody, and solution are strings with > 0 length
    EFFECTS: A practice problem is created with a set problem title, body
             and solution
     */
    public PracticeProblem(String problemTitle, String problemBody, String solution) {
        this.problemTitle = problemTitle;
        this.problemBody = problemBody;
        this.solution = solution;
        this.status = 0;
    }

    public PracticeProblem(String problemTitle, String problemBody, String solution, int status) {
        this.problemTitle = problemTitle;
        this.problemBody = problemBody;
        this.solution = solution;
        this.status = status;
    }

    /*
    REQUIRES: problemTitle, problemBody are strings with > 0 length
    EFFECTS: A practice problem is created with a set problem title, body
             without a solution
     */
    public PracticeProblem(String problemTitle, String problemBody) {
        this.problemTitle = problemTitle;
        this.problemBody = problemBody;
        this.solution = "";
        this.status = 0;
    }

    // EFFECTS: returns title of this problem
    public String getTitle() {
        return problemTitle;
    }

    // EFFECTS: returns body of this problem
    public String getBody() {
        return problemBody;
    }

    // EFFECTS: returns solution of this problem
    public String getSolution() {
        return solution;
    }

    // EFFECTS: returns status of this problem
    public int getStatus() {
        return status;
    }

    // REQUIRES: int s is 0 <= s <= 2
    // MODIFIES: this
    // EFFECTS: sets current status to given integer s
    public void setStatus(int s) {
        status = s;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pTitle", problemTitle);
        json.put("pBody", problemBody);
        json.put("pSolution", solution);
        json.put("pStatus", status);
        return json;
    }

}
