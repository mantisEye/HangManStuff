import java.util.*;
public class TheHangManGame1
{
   static Scanner in = new Scanner(System.in);
   public  static void main (String[] args)
   {
      //String the_magic_word = randomWordSelection();
      ArrayList<String> game_name = new ArrayList<String>(); // created an arraylist here
      System.out.println(randomWordSelection(game_name));
   } 
   public static String randomWordSelection(ArrayList game_name)
   {
      String word; //the puzzle word. Will be given a game title as a string.
      int number = (int)(Math.random()*23)+1; //generate random number  
   //******************************
      game_name.add("Tertis");
      game_name.add("The Last Of Us");
      game_name.add("Crash Bandicoot");
      game_name.add("Twisted Metal");
      game_name.add("Metal Gear");
      game_name.add("Resident Evil");
      game_name.add("Spyro The Dragon");
      game_name.add("Half Life");
      game_name.add("Team Fortress");
      game_name.add("Worms");
      game_name.add("Red Dead Redemption");
      game_name.add("Dark Souls");                           //adding the names
      game_name.add("Portal");
      game_name.add("The Witcher");
      game_name.add("Madden");
      game_name.add("God Of War");
      game_name.add("Devil May Cry");
      game_name.add("Street Fighter");
      game_name.add("Tomb Raider");
      game_name.add("Tekken");
      game_name.add("World Of Warcraft");
      game_name.add("Rayman");
      game_name.add("Super Smash Bros");
      game_name.add("Snpier Elite");
   //***********************************  
      word = (String)game_name.get(number);      // getting the random word; I had to typecast here because you cant convert an object into string.
      return word;          // returning the word
   }
   
   
}