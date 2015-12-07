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
      System.out.println("Please choose an action: attack, growl, focus, defend, injure");
      String action = a.next();
      if (action.equals("attack")){
         this.currentAction = "attack";
      } else if(action.equals("growl")){
         this.currentAction = "growl";
      } else if(action.equals("defend")){
         currentAction = "defend";
      } else if(action.equals("focus")){
         currentAction = "focus";
      } else if(action.equals("injure")){ //there's probably a better name for this
         currentAction = "injure";
      }else {
         System.out.println("Invalid Action");
         this.chooseAction();
      }
   }
   
   public void actionEffect(Mathemon enemy){
      
      if (this.currentAction.equals("attack")){
         this.attack(enemy);
      }else if(this.currentAction.equals("growl")){
         this.lowerDefence(enemy);
      }else if(this.currentAction.equals("defend")){
         this.raiseDefence();
      }else if(this.currentAction.equals("focus")){
         this.raiseAttack();
      }else if(this.currentAction.equals("injure")){
         this.lowerAttack(enemy);
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
   
   public void raiseDefence(){
      System.out.println("Current Defence: " + this.defence);
      int defenceRaise = this.genRndmInt(2);
      this.defence = this.defence + defenceRaise;
      System.out.println(this.name + " focuses on defence! Defence increased by " + defenceRaise + ". Defence is now " + this.defence);
   }
   
   public void raiseAttack(){
      System.out.println("Current Attack: " + this.attack);
      int attackRaise = this.genRndmInt(2);
      this.attack = this.attack + attackRaise;
      System.out.println(this.name + " is getting pumped up! Attack increased by " + attackRaise + ". Attack is now " + this.attack);
   }
   public void lowerAttack(Mathemon other){
      int attackReduce = this.genRndmInt(2); //play with this to find a good number
      int newAttack = other.attack - attackReduce;
      if(other.attack == 1){
         System.out.println("Opponent's attack can go no lower.");
      }else if(newAttack < 1){
         System.out.println(this.name+ " injures " +other.name+ "! " + other.name + "'s attack is lowered!");
         other.attack = 1; //makes sure that attack doesn't go lower than 1
      }else{
         System.out.println(this.name+ " injures " +other.name+ "! " + other.name + "'s attack is lowered!");
         other.attack = newAttack;
      }
   }
}
   
   
