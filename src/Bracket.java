import java.util.*;
public class Bracket {
  //colors used in System.out.println
  public static final String WHITE = "\u001B[37m";
  public static final String GREEN = "\u001B[32m";
  public static final String RED = "\u001B[31m";
  public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  public static final String BLACK = "\u001B[30m";
  
  
  ArrayList<BasketballTeam> myBracket = new ArrayList<BasketballTeam>();//I used an arraylist because I figured it would be easier to take teams out
  public Bracket(String file){//this fills the file with teams
    TextIO.readFile(file);//this is the file that is read. It is passed in
    TextIO.getln();//skips first line
    for(int i=0; i<64; i++){//64 teams in the bracket. Fills every teams stats
      int rank = TextIO.getInt();
      String name = TextIO.getWord();
      int wins = TextIO.getInt();
      int loses = TextIO.getInt();
      double offense = TextIO.getDouble();
      double defense = TextIO.getDouble();
      double tempo = TextIO.getDouble();
      int sos = TextIO.getInt();
      int oRank = TextIO.getInt();
      int dRank = TextIO.getInt();
      int tRank = TextIO.getInt();
      myBracket.add(new BasketballTeam(name, wins, loses, rank, offense, defense, tempo, sos, oRank, dRank, tRank));
      TextIO.getln();
    }
  }
  
  public int play99(int pos1, int pos2, int runs){//simulates a matchup a specific amount of times
    int count1=0;//counts the wins for both teams
    int count2=0;
    double winP;//this is the win percentage it will be printed out
    if(runs%2==0)//you can't run the program an odd amount of times, it wouldn't make sense
      runs++;
    for(int i=0; i<runs; i++){//uses the playGame() method to simulate a game a specified amount of times
      if(playGame(pos1,pos2)==pos1)
        count1++;
      else
        count2++;
    }
    
    winP=(double)count2/(count1+count2);//win percentage
    winP *= 100; // makes it a percent instead of a decimal
   
    //this prints out who wins in color and returns the team that lost so they can be removed from the bracket
    if(count1<count2){
      System.out.print(GREEN+myBracket.get(pos1).getSchool());
      System.out.print(BLACK+" will win "+ winP +"% of the time against ");
      System.out.print(RED+myBracket.get(pos2).getSchool());
      System.out.println(BLACK+" after "+(count1+count2)+" games. The average score of "+avgScore(pos1,pos2)[0]+"-"+avgScore(pos1,pos2)[1]);
      return pos2;
    }
      System.out.print(RED+myBracket.get(pos1).getSchool());
      System.out.print(BLACK+" will win "+ winP +"% of the time against ");
      System.out.print(GREEN+myBracket.get(pos2).getSchool());
      System.out.println(BLACK+" after "+(count1+count2)+" games. The average score of "+avgScore(pos1,pos2)[0]+"-"+avgScore(pos1,pos2)[1]);
    return pos1;
  }
  
  public void playBracket(int runs){//this runs the whole bracket. 
    int loser=0;//position of the loser
    while(myBracket.size()>1){
      System.out.println(ANSI_WHITE_BACKGROUND + BLACK + "Round of "+myBracket.size());
      int run=myBracket.size();//this is the size of the bracket before the teams are removed
      for(int i=0; i<(run/2); i++){
        loser = play99(i,i+1,runs);//calls the play99 method which calls the play game method a specifed amount of times
        myBracket.remove(loser);//the loser is removed from the bracket
        }
      System.out.println("");
        
        
    }
    System.out.print(GREEN+myBracket.get(0).getSchool());//this is the winner
    System.out.println(BLACK+" has won the 2019 NCAA tournament simulation");
    
  }
  public int[] avgScore(int pos1, int pos2){//this mehtod is a projection of the everage score bas on the tempo, offensive efficiency and defesive effiency of both teams added up and divided
    double avg = (myBracket.get(pos1).getOffense()+myBracket.get(pos1).getDefense()+myBracket.get(pos2).getOffense()+myBracket.get(pos2).getDefense())/400;//the offense and defense tempo are added up. It is they divided by 400 becasue it is effiency per 100 possesions, and there are 4 inputs
    avg=avg*((myBracket.get(pos1).getTempo()+myBracket.get(pos2).getTempo())/2);//the points per possesion is then multiplied by the average tempo of both teams
    double margin=(Math.max(myBracket.get(pos1).getTeamScore(),myBracket.get(pos2).getTeamScore())-Math.min(myBracket.get(pos1).getTeamScore(),myBracket.get(pos2).getTeamScore()))/2;//the margin of victory is calculated using getTeamScore(); it is divided by two because it is then added and subtracted in the program 
    int[] score = new int[2];//this is what is returned
    
    
    if(myBracket.get(pos1).getTeamScore()<myBracket.get(pos2).getTeamScore()){
      score[0]=(int)(avg+margin+0.5);
      score[1]=(int)(avg-margin+0.5);
    }else{
      score[0]=(int)(avg-margin+0.5);
      score[1]=(int)(avg+margin+0.5);
    }
    return score;
  }
  
  
  public int playGame(int pos1, int pos2){//this method simulates individual games
    double avg = (myBracket.get(pos1).getOffense()+myBracket.get(pos1).getDefense()+myBracket.get(pos2).getOffense()+myBracket.get(pos2).getDefense())/400;//the offense and defense tempo are added up. It is they divided by 400 becasue it is effiency per 100 possesions, and there are 4 inputs
    avg=avg*((myBracket.get(pos1).getTempo()+myBracket.get(pos2).getTempo())/2);//the points per possesion is then multiplied by the average tempo of both teams
    double margin=(Math.max(myBracket.get(pos1).getTeamScore(),myBracket.get(pos2).getTeamScore())-Math.min(myBracket.get(pos1).getTeamScore(),myBracket.get(pos2).getTeamScore()))/2;//the margin of victory is calculated using getTeamScore(); it is divided by two because it is then added and subtracted in the program 
    
    int score1;//these are the score integers I will use to determine which team wins each games
    int score2;
    
    if(myBracket.get(pos1).getTeamScore()<myBracket.get(pos2).getTeamScore()){//here the margin is factored into the score. added 0.5 because java doesn't round
      score1=(int)(avg+margin+0.5);
      score2=(int)(avg-margin+0.5);
    }else{
      score1=(int)(avg-margin+0.5);
      score2=(int)(avg+margin+0.5);
    }
    int tRank=(myBracket.get(pos1).getTRank()+myBracket.get(pos2).getTRank())/150;
    //random numbers are factored in. I had to use trial and error to get the numbers right
    final  double t1=0.17;
    final  double t2=23.4+tRank;//the tempo is factored in because there is more luck if you have less # ofposesions the more luck factors into games
    final  double t3=0.963;
    final  double t4=15;
    score1+=(int)(Math.pow(Math.random()*t1+t3,t2)+Math.random()*t4);
    score1-=(int)(Math.pow(Math.random()*t1+t3,t2)+Math.random()*t4);
    score2+=(int)(Math.pow(Math.random()*t1+t3,t2)+Math.random()*t4);
    score2-=(int)(Math.pow(Math.random()*t1+t3,t2)+Math.random()*t4);
    
    if(score1==score2)//if the score is tied I just advance the lower ranked team.
      if(myBracket.get(pos1).getRank()<myBracket.get(pos2).getRank())
        score1++;
      else
        score2++;
    
    
    if(score2<score1){//returns the team that loses so they can be removed from the bracket
      //System.out.println(myBracket[pos1].getSchool()+ " beat "+(myBracket[pos2].getSchool())+" with a score of "+score1+"-"+score2);
      return pos2;
    }else{
      //System.out.println(myBracket[pos2].getSchool()+ " beat "+myBracket[pos1].getSchool()+" with a score of "+score2+"-"+score1);
      return pos1;
    }
    
    
  }
  public String toString(){//this is if I want to print out the teams and their stats
    String s = "";
    for(int i=0; i<myBracket.size(); i++){
      s+=myBracket.get(i).toString()+"\n";
    }
    return s;
  }
  
}