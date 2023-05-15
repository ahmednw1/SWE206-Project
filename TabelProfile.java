public class TabelProfile {
    private String team;
    private String tournament;
    private Integer points;


    public TabelProfile(String team, String tournament,Integer points) {
        this.team = team;
        this.tournament = tournament;
        this.points = points;
    }

    public String getTeam() {
        return team;
    }

    public String getTournamnet() {
        return tournament;
    }
    public int getPoints() {
        // if(points ==null){
        //     points=0;
        // }
        return points;
    }
}
