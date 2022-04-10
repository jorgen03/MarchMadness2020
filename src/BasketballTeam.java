import java.util.*;
public class BasketballTeam extends Team{
  private int rank;
  private double offense;//offensive efficiency
  private double defense;//defensive efficiency
  private double tempo;//tempo
  private int sos;//strength of schedule rank
  private int oRank;//offensive rank
  private int dRank;///defensive rank
  private int tRank;//tempo rank
  
  public BasketballTeam(String s, int w, int l, int r, double o, double d, double t, int s2, int oR, int dR, int tR){
    super(s, w, l);
    rank=r;
    offense=o;
    defense=d;
    tempo=t;
    sos=s2;
    oRank=oR;
    dRank=dR;
    tRank=tR;
  }
  public double getOffense(){
    return offense;
  }
  public double getDefense(){
    return defense;
  }
  public double getTempo(){
    return tempo;
  }
  public int getRank(){
    return rank;
  }
  public int getTRank(){
    return tRank;
  }
  public double getTeamScore(){
    //ideally the getTeamScore is low
    double avgOffense=offense-113.04375;
    double avgDefense=97.69375-defense;
    double n =1;
    n+=(rank*3);//the team score is set to the rank
    n+=(sos*((1-getWinPercentage())/2))+(sos*0.5);//strength of schedule is factored into the bracket
    n+=Math.pow((oRank*dRank), 0.75);
    //n+=(Math.pow(tRank,0.8)/5);
    
    n=Math.pow(n,0.461);
    return n;
  }
  public String toString(){
    String s ="";
    s=getSchool()+"Team Score: "+getTeamScore()+" \nRecord: "+getWins()+"-"+getLoses()+"\nOffensive efficiency: "+oRank+"\nDefensive efficiency: "+tRank+"\nTempo: "+tRank+"\nStrength of Schedule: "+sos;
    
    s+="\n";
    return s;
    
  }
}