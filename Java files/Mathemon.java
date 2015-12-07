//class for Mathemon, used for generation
import java.lang.Math;
import java.util.Scanner;

public class Mathemon{
   
   //defining variables
   String name;
   int XP = 0;
   int lvl = 0;
   int score = 0;
   
   int[] reqXP = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
   
   //combat variables
   int initialAttack;
   int attack;
   int initialDefence;
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
   public Mathemon(String name){
      this.name = name;
      this.attack = 0;
      this.defence = 0;
      this.hitPoints = 0;
      this.energy = 0;
      
      this.updateInfoString();     
   }
   
   public void addStats(){
      this.attack = this.genRndmInt(3)+1;
      this.defence = this.genRndmInt(2);
      this.hitPoints = this.genRndmInt(7)+11;
      this.energy = this.genRndmInt(5)+5;
      
      this.initialHitPoints = this.hitPoints;
      this.initialAttack = this.attack;
      this.initialDefence = this.defence;
      
      this.updateInfoString();  
   }
   
   //update info string, use whenever a value is changed
   private void updateInfoString(){
      this.infoString = "Name: "+name+"\nAttack: "+attack+"\nDefence: "+defence+"\nHit Points: "+hitPoints+ "\nEnergy: "+energy;
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
      }else if(this.currentAction.equals("growl") && this.energy > 0){
         this.lowerDefence(enemy);
      }else if(this.currentAction.equals("defend") && this.energy > 0){
         this.raiseDefence();
      }else if(this.currentAction.equals("focus") && this.energy > 0){
         this.raiseAttack();
      }else if(this.currentAction.equals("injure") && this.energy > 0){
         this.lowerAttack(enemy);
      }else {
         System.out.println("You are out of energy, you use Attack instead");
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
      this.energy--;
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
      this.energy--;
      System.out.println("Current Defence: " + this.defence);
      int defenceRaise = this.genRndmInt(2);
      this.defence = this.defence + defenceRaise;
      System.out.println(this.name + " focuses on defence! Defence increased by " + defenceRaise + ". Defence is now " + this.defence);
   }
   
   public void raiseAttack(){
      this.energy--;
      System.out.println("Current Attack: " + this.attack);
      int attackRaise = this.genRndmInt(2);
      this.attack = this.attack + attackRaise;
      System.out.println(this.name + " is getting pumped up! Attack increased by " + attackRaise + ". Attack is now " + this.attack);
   }
   public void lowerAttack(Mathemon other){
      this.energy--;
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
   
   //triggers when the player wins a fight
   public void postCombat(){
      //increases players score
      this.score++;
      //heals player after combat has ended
      this.hitPoints = this.initialHitPoints;
      this.attack = this.initialAttack;
      this.defence = this.initialDefence;
      System.out.println("You recover from the fight, all stats are back to normal");
      
      //adds XP and checks for level
      System.out.println("You get some XP");
      this.XP++;
      if (!(this.lvl == 10)){
         if (this.XP >= this.reqXP[this.lvl]){
            System.out.println("Level Up!!!");
            this.levelUp();
         } else {
            System.out.println("You have "+this.XP+" XP, you need "+this.reqXP[this.lvl]+" to level up");
         }
      }
   }
   
   //levels the player up
   public void levelUp(){
      Scanner a = new Scanner(System.in);
      
      this.XP = 0;
      this.lvl ++;
      System.out.println("Choose a stat to increase.");
      String statChoice = a.next();
      
      if(statChoice.equals("attack")){
         this.initialAttack++;
         this.attack = this.initialAttack;
         this.updateInfoString();
         System.out.println(this.infoString);
      } else if(statChoice.equals("defence")){
         this.initialDefence++;
         this.defence = this.initialDefence;
         this.updateInfoString();
         System.out.println(this.infoString);
      } else if(statChoice.equals("HP")){
         this.initialHitPoints +=3;
         this.hitPoints = this.initialHitPoints;
         this.updateInfoString();
         System.out.println(this.infoString);
      } else if(statChoice.equals("energy")){
         this.energy +=3;
         this.updateInfoString();
         System.out.println(this.infoString);
      } else {
         System.out.println("Invalid Choice");
         this.levelUp();
      }
   }
   
   public void diffBuff(int x){
      
      for (int i = 0; i < x;i++){
         int buff = this.genRndmInt(3);
         
         if (buff == 1){
            this.attack++;
         } else if (buff == 2){
            this.defence++;
         } else if (buff == 3){
            this.hitPoints +=2;
         }
      }
   }
       
}
   
   
