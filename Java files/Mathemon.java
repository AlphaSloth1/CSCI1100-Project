//class for Mathemon, used for generation
import java.lang.Math;
import java.util.Scanner;

public class Mathemon{
   
   //defining variables
   String name;
   String type;
   
   //combat variables
   int typeValue;   
   int attack;
   int defence;
   int initialHitPoints;
   int hitPoints;
   int energy;
   
   String action1 = "";
   String action2 = "";
   String action3 = "";
   
   String currentAction = "";
   
   //string that outputs useful info
   String infoString = "";
   
   //method which generates new mathemon objects
   public Mathemon(String name, String type){
      this.name = name;
      this.type = type;
      this.attack = 0;
      this.defence = 0;
      this.hitPoints = 0;
      this.energy = 0;
      
      this.updateInfoString();     
   }
   
   public void addStats(){
      this.attack = this.genRndmInt(4);
      this.defence = this.genRndmInt(2);
      this.hitPoints = this.genRndmInt(7)+11;
      this.energy = this.genRndmInt(5)+5;
      
      this.initialHitPoints = this.hitPoints;
      
      this.updateInfoString();  
   }
   
   //update info string, use whenever a value is changed
   private void updateInfoString(){
      this.infoString = "Name: "+name+"\nType: "+type+"\nAttack: "+attack+"\nDefence: "+defence+"\nHit Points: "+hitPoints+ "\nEnergy: "+energy;
   }
   
   //generates an integer between 1 and max
   private int genRndmInt(int max){
      double d = (Math.ceil(Math.random() * max));
      return (int) d;
   }
   //variable setters
   public void setAttack(int x){
      this.attack = x;
      this.updateInfoString();
   }
   public void setDefence(int x){
      this.defence = x;
      this.updateInfoString();
   }
   public void setHP(int x){
      this.hitPoints = x;
      this.updateInfoString();
   }
   public void setEn(int x){
      this.energy = x;
      this.updateInfoString();
   }
   
   //varibale getters
   public String getInfo(){
      return this.infoString;
   }
   public String getName(){
      return this.name;
   }
   public int getTypeValue(){
      return this.typeValue;
   }
   public int getAttack(){
      return this.attack;
   }
   public int getDefence(){
      return this.defence;
   }
   public int getInitHP(){
      return this.initialHitPoints;
   }
   public int getHP(){
      return this.hitPoints;
   }
   public int getEn(){
      return this.energy;
   }
   
   //actions
   
   public void chooseAction(){
      Scanner a = new Scanner(System.in);
      System.out.println("Please choose an action: attack or growl(lowers opponent's defence");
      String action = a.next();
      if (action.equals("attack")){
         this.currentAction = "attack";
      } else if(action.equals("growl")){
         this.currentAction = "growl";
      } else if(action.equals(this.action2)){
         currentAction = this.action2;
      } else if(action.equals(this.action3)){
         currentAction = this.action3;
      } else {
         System.out.println("Invalid Action");
         this.chooseAction();
      }
   }
   
   public void actionEffect(Mathemon enemy){
      
      if (this.currentAction.equals("attack")){
         this.attack(enemy);
      }else if(this.currentAction.equals("growl")){
         this.lowerDefence(enemy);
      }else {
         System.out.println("Action not implemented, you use Attack instead");
         this.attack(enemy);
      }     
   }
   
   public void attack(Mathemon other){
      System.out.println(this.name+ " attacks "+other.name);
      int dmg = (this.genRndmInt(6)+this.attack)-(this.genRndmInt(4)+other.defence);
      if (dmg < 0){
         dmg = 0;
      }
      System.out.println("Damage: " + dmg);
      other.hitPoints = other.hitPoints - dmg;
   }
   
   public void lowerDefence(Mathemon other){
      int defenceReduce = this.genRndmInt(2); //play with this to find a good number
      int newDefence = other.defence - defenceReduce;
      if(other.defence == 0){
         System.out.println("Opponent's defence can go no lower.");
      }else if(newDefence < 0){
         System.out.println(this.name+ " acts scary! " +other.name+ "'s defense is lowered!");
         other.defence = 0; //makes sure that defense doesn't go lower than 0
      }else{
         System.out.println(this.name+ " acts scary! " +other.name+ "'s defense is lowered!");
         other.defence = newDefence;
      }
   }

}
   
   
