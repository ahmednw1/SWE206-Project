import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    private ArrayList<Student> students;
    private String name;
    private Tournament enrolledTournament;
    private int points;
    private int scoreFor;
    private int scoreAgainst;

    public Team(String name) {
        this.name = name;
        this.students = new ArrayList<Student>();
        this.enrolledTournament = null;
    }

    public void addMember(Student student) {
        this.students.add(student);
    }

    public void deleteMember(Student student) {
        this.students.remove(student);
    }

    public ArrayList<Student> getMembers(int index) {
        return this.students;
    }

    public String getName() {
        return this.name;
    }

    public Tournament getTournament() {
        return this.enrolledTournament;
    }

    public void tournamentEnroll(Tournament tournament) {
        this.enrolledTournament = tournament;
        tournament.addParticipant(this);
    }
}