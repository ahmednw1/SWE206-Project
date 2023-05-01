import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    // public static void main(String[] args) {
    //     // Create a list of participating teams
    //     ArrayList<Team> teams = new ArrayList<>();
    //     teams.add(new Team("Arsenal"));
    //     teams.add(new Team("Chelsea"));
    //     teams.add(new Team("Liverpool"));
    //     teams.add(new Team("Manchester United"));
    //     teams.add(new Team("Manchester City"));
    //     teams.add(new Team("Tottenham"));
    //     Sport sport = new Sport("footable");
    //     // Create a new soccer tournament
    //     Tournament soccerTournament = new Tournament(1,LocalDate.of(2023, 6, 1),sport,4, "Round Robin", teams);

    //     // Set the tournament start and end dates
    //     soccerTournament.addStartDate(LocalDate.of(2023, 6, 1));
    //     soccerTournament.addEndDate(LocalDate.of(2023, 6, 30));

    //     // Generate the round-robin matches for the tournament
    //     soccerTournament.generateMatches();

    //     // Print out the matches
    //     ArrayList<Match> matches = soccerTournament.getMatches();
    //     for (Match match : matches) {
    //         System.out.println(match.getTeam1().getName() + " vs " + match.getTeam2().getName());
    //     }
    // }

    // public static void main(String[] args) {
    //     // Create a sample tournament with 6 teams
    //     ArrayList<Team> teams = new ArrayList<>();
    //     teams.add(new Team("Team 1"));
    //     teams.add(new Team("Team 2"));
    //     teams.add(new Team("Team 3"));
    //     teams.add(new Team("Team 4"));
    //     teams.add(new Team("Team 5"));
    //     teams.add(new Team("Team 6"));
    //     Sport sport = new Sport("footable");
    
    //     Tournament soccerTournament = new Tournament(1,LocalDate.of(2023, 6, 1),sport,4, "Round Robin", teams);
    
    
    //     // Generate the matches in 3 rounds
    //     soccerTournament.generateMatches();
    
    
    //     // Display the matches as a table
    //     System.out.println("Round\tHome Team\t\tAway Team\t\tSport");
    //     System.out.println("-------------------------------------------------------");
    //     for (int i = 0; i < soccerTournament.getMatches().size(); i++) {
    //         Match match = soccerTournament.getMatches().get(i);
    //         System.out.printf("%d\t%s\t\t%s\t\n",
    //             i/soccerTournament.getParticipants().size() + 1, // calculate the round number
    //             match.getTeam1().getName(), match.getTeam2().getName());
    //     }
    // }

    // public static void main(String[] args) {
    //     // Create a sample tournament with 6 teams
    //     ArrayList<Team> teams = new ArrayList<>();
    //     teams.add(new Team("Team 1"));
    //     teams.add(new Team("Team 2"));
    //     teams.add(new Team("Team 3"));
    //     teams.add(new Team("Team 4"));
    //     teams.add(new Team("Team 5"));
    //     teams.add(new Team("Team 6"));
    //     Sport sport = new Sport("footable");
    //     // Create a new soccer tournament
    //     Tournament tournament = new Tournament(1,LocalDate.of(2023, 6, 1),sport,4, "Round Robin", teams);
    
    //     // Generate the matches in 3 rounds with each team playing once in each round
    //     tournament.generateMatches();
    
    //     // Display the matches as a table
    //     System.out.println("Round\tHome Team\t\tAway Team\t\tSport");
    //     System.out.println("-------------------------------------------------------");
    //     int roundNumber = 1;
    //     for (int i = 0; i < tournament.getMatches().size(); i++) {
    //         Match match = tournament.getMatches().get(i);
    //         System.out.printf("%d\t%s\t\t%s\t\n",
    //             roundNumber, match.getTeam1().getName(), match.getTeam2().getName());
    //         // Increment round number after each match
    //         if ((i + 1) % tournament.getParticipants().size() == 0) {
    //             roundNumber++;
    //         }
    //     }
    // }

    public static void main(String[] args) {
        // Create a sample tournament with 6 teams
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(new Team("Team 1"));
        teams.add(new Team("Team 2"));
        teams.add(new Team("Team 3"));
        teams.add(new Team("Team 4"));
        teams.add(new Team("Team 5"));
        
        Sport sport = new Sport("footable");
         Tournament tournament = new Tournament(1,LocalDate.of(2023, 6, 1),sport,4, "Round Robin", teams);

    
        // Generate the matches and ensure that each team plays only once in each round
        tournament.roundRobin();
    
        // Display the matches as a table
        System.out.println("Round\tHome Team\t\tAway Team\t");
        System.out.println("-------------------------------------------------------");
        int roundNumber = 1;
        for (int i = 0; i < tournament.getMatches().size(); i++) {
            Match match = tournament.getMatches().get(i);
            System.out.printf("%d\t%s\t\t%s\t\n",
                roundNumber, match.getTeam1().getName(), match.getTeam2().getName());
            // Increment round number after each match
            if ((i + 1) % (tournament.getParticipants().size() / 2) == 0) {
                roundNumber++;
            }
        }
    }
    
    
    
}




