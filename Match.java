import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Match implements Serializable{
    private LocalDate date;
    private Tournament tournament;
    private Team team1;
    private Team team2;
    private int team1score;
    private int team2score;
    private boolean isEdited=false;

    public Match(Team team1, Team team2){
       this.team1 = team1;
       this.team2 = team2;
    }

    public Match(Team team1, Team team2, LocalDate date){
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setdate(LocalDate date){
        this.date = date;
    }

    public Tournament getTournament(){
        return tournament;
    }

    public ArrayList<Integer> getScore(){
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(team1score);
        scores.add(team2score);
        return scores;
    }

    public void recordScore(int team1Score, int team2Score){
        this.team1score = team1Score;
        this.team2score = team2Score;
        //if getType = round robin
        recordPoints();
        isEdited=true;
    }

    public void editresult(int team1Score, int team2Score){
        Team winner1 = getWinner();
        this.team1score = team1Score;
        this.team2score = team2Score;
        Team winner2 = getWinner();
        //if getType = round robin
        if(winner1 != winner2){
            editPoints();
        }
        isEdited=true;
    }

    public void recordPoints(){
        if(team1score > team2score){
            team1.updatePoints(3);
        }else if(team2score > team1score){
            team2.updatePoints(3);
        }else{
            team1.updatePoints(1);
            team2.updatePoints(1);
        }
    }

    public void editPoints(){
        if(team1score > team2score){
            team1.updatePoints(3);
            team2.updatePoints(-3);
        }else{
            team2.updatePoints(3);
            team1.updatePoints(-3);
        }
    }

    public Team getWinner(){
        if (team1score > team2score){
            return team1;
        }else if( team2score > team1score){
            return team2;
        }else{
            return null;
        }
    }

    public Team getLoser(){
        if (team1score > team2score){
            return team2;
        }else if( team2score > team1score){
            return team1;
        }else{
            return null;
        }
    }

    public String getTeam1(){
        if(team1 == null){
            return "---";
        }else{
            return team1.toString();
        }
        
    }

    public String getTeam2(){
        if(team2 == null){
            return "---";
        }else{
            return team2.toString();
        }
    }

   




}