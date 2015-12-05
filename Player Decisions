//irrelevent plot options, adds a story where there really isn't one
import java.util.Scanner;
import java.lang.Math;

public class PlotStuff{
   
   public void PlotDecision(){
      
      Scanner a = new Scanner(System.in);
      
      //randomizes the decision to be made
      double d = (Math.ceil(Math.random() * 7));
      int rndm = (int) d;
          
      //calls a method to make the player decide something
      if (rndm == 1){
         this.leftRight();
      } else if (rndm == 2){
         this.upDown();   
      } else if (rndm == 3){
         this.downHole();
      } else if (rndm == 4){
         this.elevator();
      } else if (rndm == 5){
         this.door();
      } else if (rndm == 6){
         this.lockedDoor();
      } else{
         System.out.println("Nothing interesting happens...");
      }
   }
   
   private void leftRight(){
   
      Scanner a = new Scanner(System.in);
      String choice = "";
         
      System.out.println("The hallway has ended, do you want to turn right or left");
      choice = a.next();
      //makes sure the player made a valid choice
      if (choice.equals("right")||choice.equals("left")){
         System.out.println("You turn "+choice);
      }else {
      //if their choice is invalid, they are told and the function is recurred
         System.out.println("That is not an option, sorry");
         leftRight();
      }
   }
   
   //these should all be binary decisions, and therefore use the same format
   private void upDown(){
      Scanner a = new Scanner(System.in);
      String choice = "";
         
      System.out.println("You come across a staircase, do you want to go up or down?");
      choice = a.next();
      if (choice.equals("up")||choice.equals("down")){
         System.out.println("You go "+choice);
      }else {
         System.out.println("That is not an option, sorry");
         upDown();
      }
   }
   private void downHole(){
      Scanner a = new Scanner(System.in);
      String choice = "";
         
      System.out.println("There is a hole with a ladder in front of you, would you like to climb down?");
      choice = a.next();
      if (choice.equals("yes")||choice.equals("no")){
         System.out.println("Good Choice");
      }else {
         System.out.println("That is not an option, sorry");
         downHole();
      }
   }
   private void elevator(){
      Scanner a = new Scanner(System.in);
      String choice = "";
         
      System.out.println("You come to an elevator, do you want to go up a floor?");
      choice = a.next();
      if (choice.equals("yes")||choice.equals("no")){
         System.out.println("Good Choice");
      }else {
         System.out.println("That is not an option, sorry");
         elevator();
      }
   }
   private void door(){
      Scanner a = new Scanner(System.in);
      String choice = "";
         
      System.out.println("Do you want to go through the door?");
      choice = a.next();
      if (choice.equals("yes")||choice.equals("no")){
         System.out.println("Good Choice");
      }else {
         System.out.println("That is not an option, sorry");
         door();
      }
   }
   private void lockedDoor(){
      Scanner a = new Scanner(System.in);
      String choice = "";
         
      System.out.println("There is a locked door with 3 buttons, which one do you want to press?");
      choice = a.next();
      if (choice.equals("1")||choice.equals("2")||choice.equals("3")){
         System.out.println("The Door Opens");
      }else {
         System.out.println("That is not an option, sorry");
         lockedDoor();
      }
   }
}
       
      
