public class watchprofile {
    private String team;
    private Integer points;
    private Integer rank;


    public watchprofile(String team, Integer points, Integer rank) {
        this.team = team;
        this.points = points;
        this.rank = rank;
    }

    public String getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }
    
    public int getRank() {
        return rank;
    }
}
