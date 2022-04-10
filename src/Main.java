import java.util.*;
class Main {
  public static void main(String[] args) {
    System.out.println("NCAA Bracket Challenge\nHow many times would you like to simulate each Game?");
      //when I simulated the bracket I entered I simulated 5 games. My bracket is semi-random
      int sim=TextIO.getlnInt();
      Bracket Teams = new Bracket("src/Teams.txt");
      System.out.println(Teams);
      Teams.playBracket(sim);
    
  }
}