//attempt at game
import java.util.Scanner;
import java.lang.Math;

public class PokeMath{
   public static void main(String[] args){
      
      theGame();
       
   }
   
    public static void theGame(){
      
      PlotStuff plot = new PlotStuff();
      
      Scanner a = new Scanner(System.in);
      int gameDiff = 1;
      boolean inGame = true;
      String diff = "";
         
      System.out.println("Enter a name for you Mathemon");
      String name = a.next();
      System.out.println("Enter a type of Mathemon");
      
      String type = a.next();
      Mathemon player = new Mathemon(name, type);        
      System.out.println(player.getInfo());
         
      System.out.println("Oh No, it has no stats!");
      player.addStats();
      System.out.println(player.getInfo());
      System.out.println("There we go, much better! Do you want to keep these stats?");
      String keepStats = a.next();
      while(!(keepStats.equals("yes"))){
         player.addStats();
         System.out.println(player.getInfo());
         System.out.println("There we go, much better! Do you want to keep these stats?");
         keepStats = a.next();
      }
     
      System.out.println(""); 
      while(!(diff.equals("easy") || diff.equals("medium") || diff.equals("hard"))){
         System.out.println("Choose a difficulty (easy, medium, hard)");
         diff = a.next();
      }
         
      while(inGame == true){
         double d = (Math.ceil(Math.random() * 4));
         int fights = (int) d;
         
         for (int i = 0; i<fights; i++){
            plot.PlotDecision();
            fight(player, diff);
         }
            
         plot.PlotDecision();
         bossFight(player, diff);
            
         gameDiff++;
         System.out.println("Do you want to continue?");
         String cont = a.next();
         if (cont.equals("no"))inGame = false;
      } 
      
      gameOver();
  }
   
    public static void fight(Mathemon player, String diff){
      Mathemon opp = new Mathemon(getName(), getType());
      opp.addStats();
      System.out.println("You encounter a "+opp.type);
      while (1 == 1){
         System.out.println("Choose an action");
         player.chooseAction();
         if(mathQuestion(diff)){
            player.actionEffect(opp);
         }
         if(opp.getHP() <= 0){
            System.out.println("The "+opp.type+" has been defeated");
            break;           
         }
         opp.attack(player);
         if (player.getHP() <= 0){
            System.out.println("You have been defeated. \nGAME OVER!!!");
            gameOver();
         }
         System.out.println("Your HP: "+player.getHP()+" Opponents HP: "+opp.getHP()+" Opponent's Defence: "+opp.getDefence());
      }
      player.setHP(player.getInitHP());
      System.out.println("You recover from the fight, HP is now full");         
   }
   
   public static void bossFight(Mathemon player, String diff){
      Mathemon opp = new Mathemon(getName(), "Boss");
      opp.addStats();
      System.out.println("You encounter a "+opp.type);
      while (1 == 1){
         System.out.println("Choose an action");
         player.chooseAction();
         if(mathQuestion(diff)){
            player.actionEffect(opp);
         }
         if(opp.getHP() <= 0){
            System.out.println("The "+opp.type+" has been defeated");
            break;           
         }
         opp.attack(player);
         if (player.getHP() <= 0){
            System.out.println("You have been defeated. \nGAME OVER!!!");
            gameOver();
         }
         System.out.println("Your HP: "+player.getHP()+" Opponents HP: "+opp.getHP());
      }  
   }
   
   public static boolean mathQuestion(String diff){
      if(diff.equals("easy")){
         return easyQuestion();
      }else if(diff.equals("medium")){
         return medQuestion();
      }else if(diff.equals("hard")){
         return hardQuestion();
      }else {
         return false;
      }
   }
   
   public static double numberGenerator(int max, int min){
      //returns a number between max and min
      return Math.floor(Math.random()*(max - min) + min);
   }
   
   public static boolean easyQuestion(){
      Scanner a = new Scanner(System.in);
      int max = 20, min = 1; //variables so we can easily change difficulty
      double num1 = numberGenerator(max, min);
      double num2 = numberGenerator(max, min);
      double answer = num1 + num2;
      System.out.print("What is " + num1 + " + " + num2 + "? "); 
      double playerGuess = a.nextDouble();
      if(playerGuess == answer){
         return true;
      } else {
         System.out.println("Sorry, the correct answer is " + answer);
         return false;
      }
   }
   
   
   public static boolean medQuestion(){
      Scanner a = new Scanner(System.in);
      int max = 10, min = 1; //variables so we can easily change difficulty
      double num1 = numberGenerator(max, min);
      double num2 = numberGenerator(max, min);
      double answer = num1 - num2;
      System.out.print("What is " + num1 + " - " + num2 + "? "); 
      double playerGuess = a.nextDouble();
      if(playerGuess == answer){
         return true;
      } else {
         System.out.println("Sorry, the correct answer is " + answer);
         return false;
      }
   }
   
   public static boolean hardQuestion(){
      Scanner a = new Scanner(System.in);
      int max = 10, min = 1; //variables so we can easily change difficulty
      double num1 = numberGenerator(max, min);
      double num2 = numberGenerator(max, min);
      double answer = num1 * num2;
      System.out.print("What is " + num1 + " x " + num2 + "? "); 
      double playerGuess = a.nextDouble();
      if(playerGuess == answer){
         return true;
      } else {
         System.out.println("Sorry, the correct answer is " + answer);
         return false;
      }
   }

   
   public static void gameOver(){
   
      Scanner a = new Scanner(System.in);
      
      System.out.println("Thank you for playing, would you like to play again?");
      String again = a.next();
      if(again.equals("no")){
        System.exit(0);
      } else if (again.equals("yes")){
        theGame();
      } else gameOver();
   }
   
   public static String getName(){
      return "testName";
   }
   public static String getType(){
      return "testType";
   }

}
