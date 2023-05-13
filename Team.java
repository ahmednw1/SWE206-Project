import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Team implements Serializable,Comparable<Team>{
    private ArrayList<Student> students;
    private String name;
    private Tournament enrolledTournament;
    private int points;
    private int scoreFor;
    private int scoreAgainst;
    private int rank;

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

    public ArrayList<Student> getMembers() {
        return this.students;
    }

    public String getName() {
        return this.name;
    }

    public Tournament getTournament() {
        return this.enrolledTournament;
    }
    public Tournament setTournament(Tournament t) {
        return this.enrolledTournament = t;
    }



    public void updatePoints(int point){
        points += point;
    }

    public void updateScore(int scoreFor, int scoreAgainst){
        this.scoreFor += scoreFor;
        this.scoreAgainst += scoreAgainst;
    }
    public String toString(){
        return name;
    }

  
    public int getPoints(){
        return this.points;
    }

    
    

    @Override
    public int compareTo(Team o) {
        return Integer.compare(this.points, o.points);
    }

    

    // @Override
    // public int compare(Team o1, Team o2) {
    //     if(o1.points > o2.points){
    //         return 1;
    //     }else if(o1.points < o2.points){
    //         return -1;
    //     }else{
    //         return 0;
    //     }
    // }
}