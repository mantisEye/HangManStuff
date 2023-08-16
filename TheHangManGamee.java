import java.util.*;

public class TheHangManGame
{
   static Scanner in = new Scanner(System.in);
   public  static void main (String[] args) throws Exception
   {      
      ArrayList<String> game_name = new ArrayList<String>(); // created an arraylist here
      createGameList(game_name);
      String name = randomWordSelection(game_name);
      System.out.println(name);      
      String fake_name = "";
      if(fake_name != null)
      {
         for(int i = 0; i < name.length(); i++)
         { 
            if(name.indexOf(" ", i ) - i == 0 )
            {
               fake_name += " "; 
            }
            else
               fake_name += "_ "; 
         }      
      } 
      System.out.println(fake_name);
   }
   public static String randomWordSelection(ArrayList game_name)
   {
      String word; 
      word = (String)game_name.get(getRandomNumber(game_name));      // getting the random word; I had to typecast here because you cant convert an object into string.
      return word;          // returning the word
   } 
   public static int getRandomNumber(ArrayList game_name)
   {
      int number = (int)(Math.random()* game_name.size()-1)+1; 
      return number;
   }  
   public static ArrayList createGameList(ArrayList game_name)
   {
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
      game_name.add("Dark Souls");                          
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
      game_name.add("Payday");
      return game_name;
   }
}