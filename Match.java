import java.io.Serializable;
import java.sql.Date;

public class Match implements Serializable{
    private Date date;
    private int[] score;
    private Sport sport;
    private Team[] teams;
    private Tournament tournament;


    public Date getDate(){
        return date;
    }

    public Tournament getTournament(){
        return tournament;
    }

    public int[] getScore(){
        return score;
    }

    public void recordScore(int team1Score, int team2Score){
        int[] newScore = {team1Score, team2Score};
        score = newScore;
    }

    public void editresult(int team1Score, int team2Score){
        int[] newScore = {team1Score, team2Score};
        score = newScore;
    }



}
