import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Tournament {
    private int eachStageDays;
    private LocalDate endDate;
    private ArrayList<Team> participants;
    //private String participationType;
    private boolean registerationOpen;
    private Sport sport;
    private LocalDate startDate;
    private int teamNumber;
    private String tournamentType;
    private ArrayList<Match> matches;

    public Tournament(int eachStageDays, LocalDate startDate, Sport sport, int teamNumber, String tournamentType ,ArrayList<Team> participants) {
        this.eachStageDays = eachStageDays;
        this.startDate = startDate;
        this.sport = sport;
        this.teamNumber = teamNumber;
        this.tournamentType = tournamentType;
        this.participants = participants;
        this.matches = new ArrayList<>();
        this.registerationOpen = true;
    }

    public void addEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void addParticipant(Team team) {
        if (this.participants.size() < this.teamNumber && this.registerationOpen) {
            this.participants.add(team);
        }
    }

    public void addStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void closeRegistration() {
        this.registerationOpen = false;
    }

    public void deleteParticipant(Team team) {
        if (this.participants.contains(team) && this.registerationOpen) {
            this.participants.remove(team);
        }
    }

    public void displayTournamentResult() {
        
    }

    public void generateMatches() {
        if(tournamentType.equals("Elimination")) {
            int numOfRounds = (int) Math.ceil(Math.log(teamNumber + 1) / Math.log(2));
            int numOfMatches = (int) teamNumber-1;
//            List<Match> matches = new ArrayList<>();
            LocalDate matchDate = startDate;
            Team[][] Purification= new Team[numOfRounds+1][];

            for(int i=0;i<numOfRounds+1;i++){
                Purification[numOfRounds-1]=new Team[2^i];
            }

            Collections.shuffle(participants);
            int matchesBf= 2*(participants.size()-(2^(numOfRounds-1)));

            for(int i=0;i<matchesBf;i=i+2){
                matches.add(new Match(participants.get(i),participants.get(i+1),matchDate));
            }

            if(0<matchesBf){
                matchDate = matchDate.plusDays (1+eachStageDays);
            }

            int j=0,i=0;
            for (i=0;i<matchesBf;i++){
                Purification[j][i]=matches.get(i).getWinner();
            }
            i=i*2;
            for(i=i;i<participants.size();i++){
                Purification[j][i-matchesBf]=participants.get(i);
            }

            j++;
            i=0;

            while (true){
                while ( i<Purification[j-1].length){
                    matches.add(new Match(Purification[j-1][i], Purification[j-1][i+1],matchDate));
                    Purification[j][i/2]=matches.get(matches.size()-1).getWinner();
                    i=i+2;
                }
                if (j <= numOfRounds){
                    j++;
                    i=0;
                    matchDate = matchDate.plusDays (1+eachStageDays);
                }else
                    break;

            }
            return;

        }else {
            ArrayList<Team> teams = new ArrayList<Team>();
            for (Team team : this.participants) {
                teams.add(team);
            }
            int numTeams = teams.size();
            int numOfRounds = numTeams%2==0?numTeams-1:numTeams;
            Team[][] Purification= new Team[numOfRounds][participants.size()/2];


            for (int i=0;i<participants.size();i++){
                for (int j=i+1;i<participants.size();j++){

                }
            }
        }

    }

    public String getTypes (){
        return tournamentType;
    }

    public LocalDate getEndDate() {
        return this.endDate ;
    }

    public ArrayList<Match> getMatches() {
        return this.matches;
    }

    public ArrayList<Team> getParticipants() {
        return this.participants;
    }

    public Sport getSport() {
        return this.sport;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public String getStatus() {
        LocalDate today = LocalDate.now(); 
        if (this.participants.size() < this.teamNumber && this.registerationOpen) {
            return "Registration is still open";
        } else if (this.participants.size() == this.teamNumber && this.registerationOpen) {
            return "Registration is closed";
        } else if (today.isBefore(startDate)) {
            return "Tournament has not started yet";
        } else if (today.isAfter(endDate)) {
            return "Tournament has ended";
        } else {
            return "Tournament is in progress";
        }
    }

    public int getTeamNumber() {
        return this.teamNumber;
    }

    public String getType() {
        return this.tournamentType;
    }

    public void openRegistration() {
        this.registerationOpen = true;
    }

    public void selectParticipationType(String participationType) {
        this.tournamentType = participationType;
    }

    public void selectSport(Sport sport) {
        this.sport = sport;
    }

    public void selectTournamentType(String tournamentType) {
        this.tournamentType = tournamentType;
    }
}