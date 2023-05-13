import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javafx.scene.effect.Light.Point;

public class Tournament implements Serializable {
    private int eachStageDays;
    private LocalDate endDate;
    private ArrayList<Team> participants = new ArrayList<>();
    private ArrayList<Team> flexible = new ArrayList<>();
    private int capacity;
    // private String participationType;
    private boolean registerationOpen;
    private Sport sport;
    private LocalDate startDate;
    private int teamNumber;
    private String tournamentType;
    private ArrayList<Match> matches = new ArrayList<>();
    private ArrayList<Match> flexMatchs = new ArrayList<>();
    private String name;

    public Tournament(String name, int eachStageDays, LocalDate startDate, LocalDate endtDate, Sport sport,
            int teamNumber, String tournamentType, int capacity) {
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

    public void deleteParticipant(int i) {
        // if (this.registerationOpen) {
        Team team = this.getParticipants().get(i);
        this.participants.remove(i);
        System.out.println(this.getParticipants());
        // this.participants.remove(team);
        team.tournamentEnroll(null);
        // team.delete();
        // }
    }

    public void displayTournamentResult() {

    }

    public void generateMatches() {
        if (this.registerationOpen) {
            if (tournamentType.equals("Elimination")) {
                flexible = new ArrayList<>(participants);
                elimination();

            } else {
                roundRobin();
            }
        }

    }

    public void Refresh() {
        flexMatchs.clear();
        elimination();
    }

    public void elimination() {
        // flexible = new ArrayList<>(participants);
        int numOfRounds = (int) Math.ceil(Math.log(flexible.size()) / Math.log(2));
        if (numOfRounds > 0){
            //System.out.println(numOfRounds + "NumRounds");

            flexible = new ArrayList<>(flexible);

            double a = flexible.size() - Math.pow(2, numOfRounds - 1);
            //System.out.println(a);

            int w = 1;
            //System.out.println(matches.isEmpty());
            if (!matches.isEmpty()) {
                Match match = matches.get(matches.size() - 1);
                w = w + match.getMatchNumber();
                //System.out.println("____________________" + w);
            }

        LocalDate matchDate = startDate;

            for (int i = 0; i < a * 2; i = i + 2) {
                Team t1 = flexible.get(i);
                Team t2 = flexible.get(i + 1);
                Match match = new Match((i / 2) + w, t1, t2,matchDate);
                //System.out.println((i / 2) + w + "    " + match.getMatchNumber() + "      " + match.getTeam1());
                //System.out.println(match.toString() + match.getMatchNumber());
                flexMatchs.add(match);
                matches.add(match);
                // System.out.println(matches.get(i).toString());
                matchDate = matchDate.plusDays(eachStageDays);

            }

            for (int i = 0 ,j=0; i <flexMatchs.size(); i++) {
                //System.out.println(flexMatchs.size() + " " + flexible.size() + " " + i * 2);
                flexible.remove(i * 2 -j);
                flexible.remove(i * 2 -j);
                Team t = flexMatchs.get(i).getWinner();
                //System.out.println(t.getName()+"  uuuuuuuuuuuuuu " + i + matches.get(i));
                flexible.add(i * 2 -j, t);
                j++;
            }

            flexMatchs.clear();
            elimination();
        }

    }
    // LocalDate matchDate = startDate;
    // Team[][] purification = new Team[numOfRounds + 1][];

    // for (int i = 0; i < numOfRounds + 1; i++) {
    // purification[i] = new Team[1 << (numOfRounds - i)];
    // }
    // System.out.println("-----------------------------------------");
    // for (int n=0;n<purification.length;n++){
    // for(int u=0;u <purification[n].length;u++){
    // System.out.print(purification[n][u]+" ");
    // }
    // System.out.println("");
    // }
    // System.out.println("-----------------------------------------");

    // Collections.shuffle(participants);
    // int matchesBeforeElimination = participants.size() - (1 << (numOfRounds -
    // 1));
    // System.out.println(matchesBeforeElimination+" match before ");

    // for (int i = 0; i < matchesBeforeElimination; i += 2) {
    // matches.add(new Match(participants.get(i), participants.get(i + 1),
    // matchDate));
    // System.out.println("loop1");
    // }

    // if (matchesBeforeElimination > 0) {
    // matchDate = matchDate.plusDays(1 + eachStageDays);
    // }

    // int j = 1, i = 0;
    // for (i = 0; i < matchesBeforeElimination; i++) {
    // purification[j - 1][i / 2] = matches.get(i).getWinner();
    // System.out.println("loop2");
    // }
    // System.out.println(purification[j - 1][i]+" purification 1"+purification[j -
    // 1][i + 1]);

    // i = i * 2;
    // System.out.println(name);
    // for (i = i; i < participants.size(); i++) {
    // purification[j - 1][i - matchesBeforeElimination] = participants.get(i);
    // }
    // System.out.println(purification[j - 1][i]+" purification 2"+purification[j -
    // 1][i + 1]);
    // // Only generate matches for the first round
    // int numRoundsToGenerate = 1;
    // while (j <= numRoundsToGenerate) {
    // i = 0;
    // matchDate = matchDate.plusDays(1 + eachStageDays);
    // System.out.println(purification[j-1].length);
    // while (i < purification[j - 1].length) {
    // matches.add(new Match(purification[j - 1][i], purification[j - 1][i + 1],
    // matchDate));
    // System.out.println(purification[j - 1][i]+" purification "+purification[j -
    // 1][i + 1]);
    // i = i + 2;
    // }
    // j++;
    // }
    // System.out.println("*****************************************");
    // for (int n=0;n<purification.length;n++){
    // for(int u=0;u <purification[n].length;u++){
    // System.out.print(purification[n][u]+" ");
    // }
    // System.out.println("");
    // }
    // System.out.println("*****************************************");
    // }

    public void roundRobin() {
        int numTeams = participants.size();
        if (numTeams % 2 != 0) {
            Team byeTeam = new Team("---"); // create a bye team if the number of teams is odd
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
                matches.add(new Match(i, homeTeams.get(i), awayTeams.get(i), matchDate));
                matchDate = matchDate.plusDays(eachStageDays);
            }
            Collections.rotate(participants.subList(1, participants.size()), 1); // rotate the teams for the next round
        }
    }

    // public String getTypes (){
    // return tournamentType;
    // }

    public LocalDate getEndDate() {
        return this.endDate;
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

    public String tString() {
        return name;
    }

    public String numberString() {
        return teamNumber + "";
    }

    public boolean isIn(Student st) {
        for (int i = 0; i < participants.size(); i++) {
            for (int j = 0; j < participants.get(i).getMembers().size(); j++) {
                if (participants.get(i).getMembers().get(j).equals(st)) {
                    return true;
                }
            }
        }
        return false;
    }

}