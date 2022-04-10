public class Team{
  private String school;
  private int wins;
  private int loses;
  private double winPercent;
  public Team(String s, int w, int l){
    school=s;
    wins=w;
    loses=l;
  }
  //acessor methods
  public String getSchool(){
    return school;
  }
  public int getWins(){
    return wins;
  }
  public int getLoses(){
    return loses;
  }
  public double getWinPercentage(){
    calcWinPercent();
    return winPercent;
  }

  //mutator methods
  public void getSchool(String s){
    school=s;
  }
  public void getWins(int n){
    wins=n;
  }
  public void getLoses(int n){
    loses=n;
  }
  public void calcWinPercent(){
    winPercent=(double)wins/(wins+loses);
  }
  //public String toString(){
    //calcWinPercent();
    //return getSchool();
  //}
  public static double getTeamScore(double w1, double w2){
    return Math.max(w1,w2);
  }
}