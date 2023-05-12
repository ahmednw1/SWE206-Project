import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
    private String email;
    private String ID;
    private String name;
    private String pass;


    protected boolean access;

    // constructor
    public User(String email, String ID, String name, String pass) {
        this.email = email;
        this.ID = ID;
        this.name = name;
        this.pass = pass;
    }

    public User(String email, String ID, String name) {
        this.email = email;
        this.ID = ID;
        this.name = name;
    }
    public User(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    // getters and setters
    public String getEmail() {
        return email;
    }

    public abstract boolean  getAccess();
    public abstract ArrayList<Team>  getTeams();

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    

}