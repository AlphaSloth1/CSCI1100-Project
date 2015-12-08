//Runs the game, holds all the non-object methods
import java.util.Scanner;
import java.lang.Math;

public class PokeMath{
   public static void main(String[] args){
      //calls the game
      theGame();
       
   }
   
   //seperate function so that it can be recurred easily
    public static void theGame(){
      
      Scanner a = new Scanner(System.in);
      
      System.out.println("Welcome to pokeMath, the math battle game. \nYour very own Mathemon adventure is about to unfold!\n");
      System.out.println("Would you like to learn more about the game? (type 'yes') ");
      String showGuide = a.next();
      
      if(showGuide.equals("yes")){
         System.out.println("In this game you'll be given a creature with its own unique stats. \nYour Mathemon has attack, defence, hit points and energy. \nAttack is how much damage can be dealt, defence is how much enemy attacks are negated, hit points represent how many attacks you can endure, and energy is used to perform special actions.");
         System.out.println("These special actions are growl,  focus, defend, and injure. Growl lowers the enemy's defence, focus raises your Mathemon's attack, defend raises your Mathemon's defence, and injure lowers the enemy's attack.\nType 'yes' or 'no' where appropriate to navigate the game world.\nThe game will now begin.\n");
      }else{
         System.out.println("Good luck!\n");
      }
      
      
      //loads in the irrelevant decisons
      PlotStuff plot = new PlotStuff();
      
      int gameDiff = 0;
      boolean inGame = true;
      String diff = "";
       
      //game startup  
      System.out.println("Enter a name for your Mathemon");
      String name = a.next();
      
      Mathemon player = new Mathemon(name);        
      player.addStats();
      System.out.println(player.getInfo());
      System.out.println("Stats have been generated. Do you want to keep these stats?");
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
       
      //loop runs gameplay  
      while(inGame == true){
         double d = (Math.ceil(Math.random() * 4));
         int fights = (int) d;
         
         for (int i = 0; i<fights; i++){
            plot.PlotDecision();
            fight(player, diff, gameDiff);
         }
            
         plot.PlotDecision();
         bossFight(player, diff, gameDiff);
            
         gameDiff++;
         System.out.println("You have entered the next level of the dungeon.");
         System.out.println("Do you want to continue?");
         String cont = a.next();
         if (cont.equals("no"))inGame = false;
      } 
      
      //calls the end game function if the player wants to quit
      gameOver(player);
  }
   
    //base combat function
    public static void fight(Mathemon player, String diff, int gameDiff){
      //create an opponent
      Mathemon opp = new Mathemon(getName());
      opp.addStats();
      opp.diffBuff(gameDiff);
      System.out.println("You encounter a "+opp.name);
      //runs the actual combat
      while (1 == 1){

         player.chooseAction();
         if(mathQuestion(diff)){
            player.actionEffect(opp);
         }
         if(opp.getHP() <= 0){
            System.out.println("The "+opp.name+" has been defeated");
            break;           
         }
         opp.attack(player);
         if (player.getHP() <= 0){
            System.out.println("You have been defeated. \nGAME OVER!!!");
            gameOver(player);
         }
         System.out.println("Your HP: "+player.getHP()+" || Your energy: " + player.getEn() +"\t Opponent's HP: "+opp.getHP());
      }
      player.postCombat();        
   }
   
   //this is the same as the normal fight, but a boss
   //should have slightly increases difficulty
   public static void bossFight(Mathemon player, String diff, int gameDiff){
      Mathemon opp = new Mathemon("Boss_"+getName());
      opp.addStats();
      opp.diffBuff(gameDiff + 1);
      System.out.println("You encounter a "+opp.name);
      while (1 == 1){

         player.chooseAction();
         if(mathQuestion(diff)){
            player.actionEffect(opp);
         }
         if(opp.getHP() <= 0){
            System.out.println("The "+opp.name+" has been defeated");
            break;           
         }
         opp.attack(player);
         if (player.getHP() <= 0){
            System.out.println("You have been defeated. \nGAME OVER!!!");
            gameOver(player);
         }
         System.out.println("Your HP: "+player.getHP()+" || Your energy: " + player.getEn() + "\t Opponent's HP: "+opp.getHP());
      }  
      player.XP++;
      player.postCombat();
   }
   
   //calls the correct difficulty of math question
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
   
   //easy difficuty question generator
   public static boolean easyQuestion(){
      Scanner a = new Scanner(System.in);
      int max = 12, min = 1; //variables so we can easily change difficulty
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
   
   //medium difficulty question generator
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
   
   //hard difficulty question generator
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

   //end of game, can recur the game to play again
   public static void gameOver(Mathemon player){
   
      Scanner a = new Scanner(System.in);
      
      System.out.println("Thank you for playing, would you like to play again?");
      String again = a.next();
      if(again.equals("no")){
        System.out.println("You final score is: "+player.score);
        System.exit(0);
      } else if (again.equals("yes")){
        System.out.println("Your final score is: "+player.score);
        theGame();
      } else gameOver(player);
   }
   
   //generates a random name
   public static String getName(){
      double d = (Math.ceil(Math.random() * 10));
      int num = (int) d;
      
      if (num == 1){
         return "Addichu";
      } else if (num == 2){
         return "Subtractamander";
      } else if (num == 3){
         return "Fractoid";
      } else if (num == 4){
         return "Plusen";
      } else if (num == 5){
         return "Minen";
      } else if (num == 6){
         return "Dividypuff";
      } else if (num == 7){
         return "Multiplizard";
      } else if (num == 8){
         return "Trigtortle";
      } else if (num == 9){
         return "Calcfairy";
      } else if (num == 10){
         return "Quadralax";
      } else return "error";

   }
}
