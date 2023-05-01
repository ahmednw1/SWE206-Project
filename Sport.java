public class Sport {
    private String description;
    private String name;

    public Sport(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void getPoint(Match match) {
        int[] scores = match.getScore();
        Team[] teams = match.getTeams();

        //  round robin tournaments
        if (match.getTournament().getType() == Tournament.Type.ELIMINATION) {
            if (scores[0] > scores[1]) {
                // The winning team move  to the next round
                match.getTournament().moveTeamToNextRound(teams[0]);
            } else {
                // The winning team move to the next round
                match.getTournament().moveTeamToNextRound(teams[1]);
            }
        }



        //  round robin tournaments
        if (match.getTournament().getType() == Tournament.Type.ROUND_ROBIN) {
            if (scores[0] > scores[1]) {
                teams[0].addPoints(3);
            } else if (scores[0] < scores[1]) {
                teams[1].addPoints(3);
            } else {
                teams[0].addPoints(1);
                teams[1].addPoints(1);
            }
        }
    }
}
