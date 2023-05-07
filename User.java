import java.io.Serializable;

public abstract class User implements Serializable {
    private String email;
    private int ID;
    private String name;
    private String pass;

    protected boolean access;

    // constructor
    public User(String email, int ID, String name, String pass) {
        this.email = email;
        this.ID = ID;
        this.name = name;
        this.pass = pass;
    }

    public User(String email, int ID, String name) {
        this.email = email;
        this.ID = ID;
        this.name = name;
    }
    // getters and setters
    public String getEmail() {
        return email;
    }

    public abstract boolean  getAccess();
    public void setEmail(String email) {
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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