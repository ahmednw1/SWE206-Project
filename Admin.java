import java.util.ArrayList;

public class Admin extends User {

    public Admin(String email, String ID, String name, String pass) {
        super(email, ID, name, pass);
        super.access = true;
    }
    public Admin(String email, String ID, String name) {
        super(email, ID, name);
        super.access = true;
    }
    public Admin(String ID, String name) {
        super(ID, name);
        super.access = true;
    }

    public boolean getAccess() {
        return super.access;
    }
    @Override
    public ArrayList<Team> getTeams() {
        return null;
    }

}