import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Match implements Serializable{
    private Date date;
    private ArrayList<Integer> score;
    //private Sport sport;
    private ArrayList<Team> teams;
    private Tournament tournament;

    public Match(Team team1, Team team2){
        teams.add(team1);
        teams.add(team2);
    }

    public Date getDate(){
        return date;
    }

    public void setdate(Date date){
        this.date = date;
    }

    public Tournament getTournament(){
        return tournament;
    }

    public ArrayList<Integer> getScore(){
        return score;
    }

    public void recordScore(int team1Score, int team2Score){
        ArrayList<Integer> newScore = new ArrayList<>();
        newScore.add(team1Score);
        newScore.add(team2Score);
        score = newScore;
        
    }

    public void editresult(int team1Score, int team2Score){
        ArrayList<Integer> newScore = new ArrayList<>();
        newScore.add(team1Score);
        newScore.add(team2Score);
        score = newScore;
    }





}
