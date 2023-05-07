public class Admin extends User {

    public Admin(String email, int ID, String name, String pass) {
        super(email, ID, name, pass);
        super.access = true;
    }
    public Admin(String email, int ID, String name) {
        super(email, ID, name);
        super.access = true;
    }

    public boolean getAccess() {
        return super.access;
    }

}