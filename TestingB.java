import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class TestingB
{
   static Scanner in = new Scanner(System.in);
   public  static void main (String[] args)
   {
      operatingDisplay();
       
       //JOptionPanae.showMessageDialog(null, "Enter the location of the file");
      System.out.println("Enter the location of the file"); // add a box pop-up thing   
      String FILENAME = in.nextLine();
          
      ArrayList<String> name = new ArrayList<>();
   
      BufferedReader br = null;
      FileReader fr = null;
   
      try 
      {
       fr = new FileReader(FILENAME);
       br = new BufferedReader(fr);
      
       String sCurrentLine;
      
       br = new BufferedReader(new FileReader(FILENAME));
      
       while ((sCurrentLine = br.readLine()) != null) 
         { name.add(sCurrentLine);}
      } 
      
      catch (IOException e)
      {
       e.printStackTrace();
      } 
      
      finally 
      {
        try 
         {
           if (br != null)
              br.close();
         
           if (fr != null)
              fr.close();
         } 
        catch (IOException ex) {
            ex.printStackTrace();
                               }
      }
      
      String magic_word = extraSpaceFilter(RandomName(name)).toLowerCase();
      openingSequence();
      instructionsDisplay();
      String blank = hideStringAndDisplay(magic_word);
         
      int health = 8;//you have 8 health
   
      ArrayList <String> inputs = new ArrayList<>();//user inputs will be stored in this var //used in part1
   
   
      do{//enter the loop
      
      //PART ONE : Check repeated inputs, prevent the player to enter the same thing more than once.
      
         String user_input;// user input will be stored in this var
      
         do{
            System.out.println("");
            System.out.print("What is your guess?");
            user_input = (extraSpaceFilter(in.nextLine())).toLowerCase(); //get user input and clean it with those methods
         
            if(inputs.contains(user_input)){System.out.println("You already guessed this.");}//if the variable input was found in the 'inputs' array, then sop.
            if(user_input.equals(" ") || user_input.equals("")){System.out.println("Invaled Input,try again.");}// heck if the user intered anything realy.
         }while (inputs.contains(user_input)|| user_input.equals(" ") || user_input.equals(""));//if the string input was found in the 'inputs' array,then repeat the loop. else if variable input wasent found, then exit the loop to proceed the program...
      //..also, if the user didnt enter anything then repeat this loop.
      
         inputs.add(user_input);//since it was already checked and cleared, we add it to the array so that the user won't be able to say it again.
      
      //PART ONE DONE.
      
      
      //PART TWO: CHECK IF THE INPUT EXIST IN THE GAME_NAME AND WHERE ITS LOCATED.
      // note: the purpose of this part is to creat an array that holds the indexes where the user_input is found in the magic_word. 
      // for example: magic_word = "living dead"   input = "d"  therefore..array list: {7,10}
      
         List<Integer> locations = new ArrayList<>();//indexes will be stored as integers //used in part2
      
         int pin_point = 0;// ignore
         int check_ahead= 0;// ignore
      
         if(magic_word.indexOf(user_input)== -1) {System.out.println("Wrong: "); health -=1; drawPic(health);}//if the input does not exist in the magic_word at all, we take one life and repeat the loop from begining.
         
         else{//else if it does actualy exist, then let proceed the following code.
            do{
            //System.out.println(magic_word.indexOf(user_input, pin_point));
               locations.add(magic_word.indexOf(user_input, pin_point));   //save the index number into the array(locations). 
               pin_point = (magic_word.indexOf(user_input, pin_point))+1; // ignore,you must +1 so it moves on from that index. 
               check_ahead = (magic_word.indexOf(user_input, pin_point));// ignore
            // pint point will sets the ground to where the next index could be, and so start searching from index (pin point) rather from begining.
            //check ahead wil alwyas look forword if the input repeated.if it spits out -1,then exit this loop
            }while( check_ahead != -1 ) ;
         
         //PART TWO DONE.
         
         //PART THREE: MINUPILATE THE BLANK STRING AND CHAMGED INDEXES BASED ON THE PREVIOUS STEP.
         //note:in the previous step, we gathered the index numbers and stored  them in the array list(locations).
         //now we will use that information to update the blank string and replace some of the blanks with the right character.
         //for example: magic_word = "living dead"   input = "d"  array list: {7,10} blank="------ ----"  therefore....blank="------ d--d" 
         
            for(int i = 0; i < locations.size(); i++) 
            { 
               int spot = locations.get(i);
            // we have the user input
            // we have the index location
            // we have blank string to modify 
               String section1 = blank.substring(0,spot);
               String section2 = user_input; 
               String section3 = blank.substring( section1.length() + section2.length(), blank.length());
               blank = section1 + section2 + section3;
            }
            System.out.println("Correct: "+spaceOut(capitalize(blank)));
         }
      
      }while (health>0 && blank.indexOf("_")!= -1);
      if (health==0) {loseDisplay(magic_word);} 
      else {winDisplay();}
   }
 
//*************************************************************************************************************************************************************************
//this method takes whatever string and space it out by adding " " after each letter.
   public static String spaceOut(String whatever)
   {
      String spaced_out ="";
      for(int i=0; i<whatever.length(); i++)
      {
         spaced_out += whatever.substring(i,i+1);
         spaced_out += " ";
      }
      return spaced_out;
   } 
  
//*************************************************************************************************************************************************************************
//this methods takes a stirng and clears exterior space. It also clean extra spaces within the word.
// noting, this method isnt perfect, but it works the way it is. probelm has to do with functionality with errors.
//if the string is made all of spaces, then it will leave one space bar.
   public static String extraSpaceFilter(String user_input)
   {
      while(user_input.indexOf(" ") == 0 && user_input.length() > 1) {user_input=user_input.substring(1);}
      while(user_input.lastIndexOf(" ")==user_input.length()-1 && user_input.length() > 1) {user_input=user_input.substring(0,user_input.length()-1);}
   
      int hook = 0;
      while(user_input.indexOf(" ",hook+1)!= -1)
      {hook = user_input.indexOf(" ",hook+1);
         while(user_input.substring(hook+1,hook+2).equals(" "))
         {
            String part1 = user_input.substring(0,hook);
            String part2 = user_input.substring(hook+1);
            user_input = part1 + part2 ; 
         }
      }
      return user_input;
   } 
//*************************************************************************************************************************************************************************
//this method draw the hanged men steps based on health, except it doesmt draw health 0.
   public static void drawPic(int health)
   {
      if (health==0){
         return;}// will draw Game-Over screen seperatly.so when zero, then exit.
      String face="";
      switch(health){
         case 7: face = "(^0^ )"; 
            break;
         case 6: face = "(^-^ )"; 
            break;
         case 5: face = "(^_^;)"; 
            break;
         case 4: face = "(._.;)"; 
            break;
         case 3: face = "(o_O )"; 
            break;
         case 2: face = "(-_- )"; 
            break;
         case 1: face = "(~<_~)"; 
            break;
      }
   
      System.out.println(" __________________________");
      System.out.println("|The Nuke Will Explode At..|");
      System.out.println("|   ___        _  {(_(_^ ) |");
      System.out.println("|  [___]     _|_|_  (_ { } |");
      System.out.println("|   /*\\      "+face+"   ()^{ |");
      System.out.println("|  [===]     /|_|\\     |  {|");
      System.out.println("|~~|("+health+")|~~~~~~|~|~~~~~~|(0)|");  
      System.out.println("|~ (___) ~~  ~  ~~~  ~ |   |");
      System.out.println("|______________________|___|");
   } 
 
//*************************************************************************************************************************************************************************
   public static void loseDisplay(String magic_word)
   {
      System.out.println(" ___________________  __________________  _______________________");
      System.out.println("|                   ||       _--_       ||         _____         |");
      System.out.println("|                   ||     _(    )_     ||        |     |\\       |");
      System.out.println("|      00:00        ||    (________)    ||     ___|     ||__     |");
      System.out.println("|                   ||  _____))((_____  ||~~~~|    R.I.P    |\\~~~|");
      System.out.println("| Bomb Iintiated... || (______________) ||    |___  lol  ___||   |");
      System.out.println("|                   ||_______))((_______||        |     |\\__\\|   |");
      System.out.println("|___________________||__________________||________|_____||_______|");
      System.out.println("");
      System.out.println("                          GAME OVER");
      System.out.println("The word was: " + capitalize(magic_word));
   }

//*************************************************************************************************************************************************************************
   public static void winDisplay()
   {
      System.out.println(" ____________________________________________");
      System.out.println("| ____ _____---===___--___-   _|             |");
      System.out.println("| _______-----____===_       |_|             |");
      System.out.println("|  ____----======______-- -_   |  (^0^)/     |");
      System.out.println("| __---___---__==    --_-__ ___   (|_|       |");
      System.out.println("| ____--====    __---  __--     ___(_)____   |");
      System.out.println("|   __--____-- __----__     ~~~ ]_] (8) | \\  |");
      System.out.println("| ________------==__    ~~~~~~~~]_]__ __|_/  |");
      System.out.println("|____________________________________________|");
      System.out.println("               YOU WIN");
   }
//************************************************************************************************************************************************************************
//this method takes a method and capitalize each first letter in every word.This may not be a perfect proof for errors and such.
   public static String capitalize(String user_input)
   {
   //Capitlize very first Letter:
      String first_part = (user_input.substring(0,1)).toUpperCase();// first part that is first letter capitalized.
      String second_part = user_input.substring(1);// the rest of string without the first letter.
      user_input = first_part + second_part;
   //Donw with that.
   
   //Capialize first letter subsequent to space:
      int hook=0;
      while (user_input.indexOf(" ",hook+1) != -1)
      { hook = user_input.indexOf(" ",hook+1); //toss the hook to the next space
         String part1 =user_input.substring(0,hook+1);//from begining to the space.
         String part2 =(user_input.substring(hook+1,hook+2)).toUpperCase();//the actually letter/index to be capitalized
         String part3 =user_input.substring(hook+2);//the reaming string after the capitalized index
         user_input = part1 + part2 + part3;
      }
      return user_input;
   } 
 
//************************************************************************************************************************************************************************
 //Rijan's methods
   public static String RandomName(ArrayList name)
   {
      String word; 
      word = (String)name.get(getRandomNumber(name));      // getting the random word; I had to typecast here because you cant convert an object into string.
      return word; 
     
   }
//************************************************************************************************************************************************************************
//Rijan's method
   public static int getRandomNumber(ArrayList name)
   {
      int number = (int)(Math.random()* name.size()-1)+1; 
      return number;
   }
//************************************************************************************************************************************************************************
// this method takesa string and turn in into blank version bt switching all letters to underlines, and also it desplay word&letter counts for the user as a hint.
  public static String hideStringAndDisplay (String magic_word)
   {  
      int word_counter =1;
      int letter_counter =0;
      String blank = "";
      for(int i = 0; i < magic_word.length(); i++)
      { 
       if(magic_word.indexOf(" ", i ) - i == 0 )
        { blank  += " ";  word_counter++;}
       else
        {blank   += "_";  letter_counter++;}
      } 
      System.out.println("*The Text consists of "+ word_counter +" word(s) and "+ letter_counter +" letters: " + spaceOut(blank) );
      
      return blank;
   }
//*************************************************************************************************************************************************************************
  public static void operatingDisplay ()
   {
   System.out.println(" {0==0}**)=========\\*/=========(**{0==0}");
   System.out.println(" _]__[_<>0<>0<>0==**Y**==0<>0<>0<>_]__[_");
   System.out.println("(6)==(9)                         (9)==(6)");
   System.out.println("  ]||[           WELCOME!          ]||[");
   System.out.println("  ||||       TO THE HANGING MAN    ||||"); 
   System.out.println("  ||||                             ||||");
   System.out.println("  ||||                             ||||");
   System.out.println("  ||||       By Rijan Timsina      ||||");
   System.out.println("  ||||              &&             ||||");
   System.out.println("  ||||          EdgeLord666        ||||");
   System.out.println("  ||||                             ||||");
   System.out.println("  ||||             2017            ||||");
   System.out.println(" _]||[_                           _]||[_");
   System.out.println("(9)==(6)*************************(6)==(9)");
   System.out.println("\n");
   }
//**************************************************************************************************************************************************************************
// display rules, instructions, how to play etc. the words/letters counter will be done in hideStringAndDisplay string.
  public static void instructionsDisplay ()
   {
    System.out.println("\nInstructions:\n*Guess the blank text. \n*No numbers or symbols. \n*You have eight tires.");
   }
//*************************************************************************************************************************************************************************
// display the opening for the 'story'
  public static void openingSequence ()
  {
 System.out.println("");
 System.out.println("PROLOGUE:***************************************************************\n");
 System.out.println("    Once Upon A time....            A Nuke Feel From The Sky.");
 System.out.println(" _________________________            ____________________");
 System.out.println("|    (  )  ^^ ^^          |  | | |   |    _        |     ||      ");
 System.out.println("|{ }         ^^        {{ |   | |    |  {V_}     __|_   _||        ");
 System.out.println("|{{ }           ___   {{ }|  | | |   |   /\\     [_________|");
 System.out.println("|~)(~~~~~~~~~~~/||_\\__~~)(|  {_V_}   |~~|==|~~~~~(_ ((__ _|      ");
 System.out.println("|~~~  _,_,_\\__'=O===O='  ~|   /*\\    |~ |8)| ~~ ~(0_)(0_) |");
 System.out.println("|~   '-O--=O--'   ~~~~    |  |=+=|   |~~   ~~ ~   \\ <.  / |");
 System.out.println("|_________________________|  |(8)|   |~ wHaT Da..? \\(-`)  |");
 System.out.println("                             \\===/   |______________\\_____|");
 System.out.println("                              \\_/");
 System.out.println("\n************************* win or die! ***********************************");
 System.out.println("");
  }
//*************************************************************************************************************************************************************************
//those two methods makes it easier to sop statements
  public static void sop (String x)
  {
   System.out.print(x);
  }
  
  public static void sopln (String x)
  {
   System.out.println(x);
  }
//*************************************************************************************************************************************************************************

}
// notes: when typing spaces. it will ACTUALLY process it if the ther is a space in the game name.
// I really gotta flesh it out
// I must re mloed it.
// edit extra space filter to clear spaced inside 
// do checking system to avoid things like not intering anything
// caps first letters
// more to come.
// give introductory hint + 1 word and 6 letters.
// first give out general hedkight : its  a name of classical game
// second, rles: its only conisst of letters, no numbers, pucncqations, symbols.
//three words, 9 letters.
// if u input a word, letters wont count , u have to type letters individulally
// opening sequence
// genertae way more built in documents and rename the files. ex: Video_Game_Titles, Historic_Figures, Movies.