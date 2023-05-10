import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Tournament implements Serializable{
    private int eachStageDays;
    private LocalDate endDate;
    private ArrayList<Team> participants = new ArrayList<>();
    private int capacity;
    //private String participationType;
    private boolean registerationOpen;
    private Sport sport;
    private LocalDate startDate;
    private int teamNumber;
    private String tournamentType;
    private ArrayList<Match> matches= new ArrayList<>();
    private String name;

    public Tournament(String name,int eachStageDays, LocalDate startDate, LocalDate endtDate,Sport sport, int teamNumber, String tournamentType ,int capacity ) {
        this.eachStageDays = eachStageDays;
        this.startDate = startDate;
        this.endDate = endtDate;
        this.sport = sport;
        this.teamNumber = teamNumber;
        this.tournamentType = tournamentType;
        this.matches = new ArrayList<>();
        this.registerationOpen = true;
        this.capacity = capacity;
        this.name = name;
    }

    public void addEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void addParticipant(Team team) {
        if (this.participants.size() < this.capacity && this.registerationOpen) {
            this.participants.add(team);
        }
    }
    public String getName() {
        return name;
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
            elimination();

        }else {
            roundRobin();
        }

    }

    public void elimination() {
        int numOfRounds = (int) Math.ceil(Math.log(participants.size() + 1) / Math.log(2));
        LocalDate matchDate = startDate;
        Team[][] purification = new Team[numOfRounds + 1][];
        
        for (int i = 0; i < numOfRounds + 1; i++) {
            purification[i] = new Team[1 << (numOfRounds - i)];
        }
        
        Collections.shuffle(participants);
        int matchesBeforeElimination = participants.size() - (1 << (numOfRounds - 1));
        
        for (int i = 0; i < matchesBeforeElimination; i += 2) {
            matches.add(new Match(participants.get(i), participants.get(i + 1), matchDate));
        }
        
        if (matchesBeforeElimination > 0) {
            matchDate = matchDate.plusDays(1 + eachStageDays);
        }
        
        int j = 1, i = 0;
        for (i = 0; i < matchesBeforeElimination; i++) {
            purification[j - 1][i / 2] = matches.get(i).getWinner();
        }
        
        i = i * 2;
        for (i = i; i < participants.size(); i++) {
            purification[j - 1][i - matchesBeforeElimination] = participants.get(i);
        }
        
        // Only generate matches for the first round
        int numRoundsToGenerate = 1;
        while (j <= numRoundsToGenerate) {
            i = 0;
            matchDate = matchDate.plusDays(1 + eachStageDays);
            while (i < purification[j - 1].length) {
                matches.add(new Match(purification[j - 1][i], purification[j - 1][i + 1], matchDate));
                i = i + 2;
            }
            j++;
        }
    }
    
    
    
    

    public void roundRobin() {
        int numTeams = participants.size();
        if (numTeams % 2 != 0) {
            Team byeTeam = new Team("BYE"); // create a bye team if the number of teams is odd
            participants.add(byeTeam);
            numTeams++;
        }
    
        List<Team> homeTeams = new ArrayList<>();
        List<Team> awayTeams = new ArrayList<>();
    
        LocalDate matchDate = startDate;
        int numStagesToGenerate = 1;
        for (int stage = 0; stage < numStagesToGenerate; stage++) {
            for (int i = 0; i < numTeams / 2; i++) {
                int j = numTeams - 1 - i;
                homeTeams.add(participants.get(i));
                awayTeams.add(participants.get(j));
                matches.add(new Match(homeTeams.get(i), awayTeams.get(i), matchDate));
                matchDate = matchDate.plusDays(eachStageDays);
            }
            Collections.rotate(participants.subList(1, participants.size()), 1); // rotate the teams for the next round
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

    public boolean getRegistrationStatus() {
        return this.registerationOpen;
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

    
    public String tString(){
        return name;
    }
    public String numberString(){
        return teamNumber + "";
    }
    public boolean isIn(Student st){
        for(int i=0;i<participants.size();i++){
            for(int j=0;j<participants.get(i).getMembers().size();j++){
                if(participants.get(i).getMembers().get(j).equals(st)){
                    return true;
                }
            } 
        }
        return false;
    }
    
    
}