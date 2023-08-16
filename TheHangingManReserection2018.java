import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
public class TheHangingManReserection2018 
{
   static Scanner in = new Scanner(System.in);
   public  static void main (String[] args)
   {
   
      infoDisplay();// show legal information.
    
    //PART ZERO : come up with the magic word, whether by exterior file or default categories.
      String magic_word = null;
      int key = 0;//when the magic_word has been assigned, we will get the key to get out of this loop
      
      sopln("Using an exterior (.txt) file allows you to-\n-incorporate your own puzzle words to this program.\n");// we display this out side the loop cuz we only want to show it once.
      do{
         System.out.println("Do you want to use the in-game default words lists? yes or no");
         String q = extraSpaceFilter(in.nextLine()).toLowerCase();
      
      //if the user entered "yes"
         if (q.equals("yes"))
         {
            magic_word = extraSpaceFilter(inGameWordSelector()).toLowerCase();;// make him choose catgory method.
            key = 1;
         }
         
         //if the user enter "no"
         else if  (q.equals("no"))
         {
            do{
               System.out.println("Enter the file's full/absolute path, type \"help\" for info, or \"back\" to return.");
               String location = extraSpaceFilter(in.nextLine()).toLowerCase();
            
               if (new File(location).exists() && new File(location).isFile() ) 
               
               {
                  ArrayList <String> name = new ArrayList<>();
                  findFile(location, name);                     
                  magic_word = extraSpaceFilter(RandomName(name)).toLowerCase();
                     
                  key= 1;
               } 
                                
               else if (location.equals("help"))
               {           
                locatingFilesInstruction ();
               }
               
               else if (location.equals("back")) 
               { 
                  break;
               }
               else
               {
                  System.out.println("The file is not found");
               }
            }while( key == 0);
         }
         
         //if the user entered gibberish.
         else 
         {
            System.out.println("Invalid input,try again.");
         }
         
      }while(key == 0);
      //PART ZERO DONE.
   
   
      openingSequence();//Prologue
      instructionsDisplay();//show manual
      String blank = hideStringAndDisplay(magic_word);//here we get out blank var
      System.out.println("\n**************************** BEGIN **************************************");
         
      int health = 8;//you have 8 health
   
      ArrayList <String> inputs = new ArrayList<>();//user inputs will be stored in this var //used in part1
   
   
      do{//enter the loop
      
      //PART ONE : Check repeated inputs, prevent the player to enter the same thing more than once.
      
         String user_input;// user input will be stored in this var
      
         do{
            System.out.print("\nWhat is your guess?");
            user_input = (extraSpaceFilter(in.nextLine())).toLowerCase(); //get user input and clean it with those methods
         
            if(inputs.contains(user_input)){System.out.println("This guess already has been made.");}//if the variable input was found in the 'inputs' array, then sop.
            if(user_input.equals(" ") || user_input.equals("")){System.out.println("Invaled Input,try again.");}// heck if the user intered anything realy.
         }while (inputs.contains(user_input)|| user_input.equals(" ") || user_input.equals(""));//if the string input was found in the 'inputs' array,then repeat the loop. else if variable input wasent found, then exit the loop to proceed the program...
      //..also, if the user didnt enter anything then repeat this loop.
      
         inputs.add(user_input);//since it was already checked and cleared, we add it to the array so that the user won't be able to say it again.
      
      //PART ONE DONE.
      
      
      //PART TWO: CHECK IF THE INPUT EXIST IN THE GAME_NAME AND WHERE ITS LOCATED.
      // note: the purpose of this part is to creat an array that holds the indexes where the user_input is found in the magic_word. 
      // for example: magic_word = "living dead"   input = "d"  therefore..array list: {7,10}
      //BUT WAIT!! one big edit happenes here. Basically, the user input will be seperated into an array where each letter will be a String varaible. Then we will go throught the whole process from here for each individual... 
      //...varaible. So basically, we gonna make a for loop with .length() itterations where each iteration is for each one of those varaibles. look at older versions to see a simpler version of this process.
      
         if(magic_word.indexOf(user_input)== -1) {System.out.println("WRONG: "); health -=1; drawPic(health);}//if the input does not exist in the magic_word at all, we take one life and repeat the loop from begining.
         else{//else if it does actualy exist, then let proceed the following code.
         
            ArrayList<String> backage = wordSeperator(user_input);// this is the list that will hold each individual letter
         //From this point, user_input becomes current_letter !!
         
            for(int current_index/*from backage*/=0; current_index< backage.size();current_index++)
            {
               String current_letter = backage.get(current_index);
            
               List<Integer> locations = new ArrayList<>();//indexes will be stored as integers //used in part2
            
               int pin_point = 0;// ignore
               int check_ahead= 0;// ignore
            
                  
               do{
               //System.out.println(magic_word.indexOf(user_input, pin_point));
                  locations.add(magic_word.indexOf(current_letter, pin_point));   //save the index number into the array(locations). 
                  pin_point = (magic_word.indexOf(current_letter, pin_point))+1; // ignore,you must +1 so it moves on from that index. 
                  check_ahead = (magic_word.indexOf(current_letter, pin_point));// ignore
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
                  String section2 = current_letter; 
                  String section3 = blank.substring( section1.length() + section2.length(), blank.length());
                  blank = section1 + section2 + section3;
               }
            }
            System.out.println("CORRECT: "+spaceOut(capitalize(blank)));
         }
      
      }while (health>0 && blank.indexOf("_")!= -1);
      if (health==0) {loseDisplay(magic_word);} 
      else {winDisplay();}
      
      endDisplay();
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
      System.out.println("\n                    YOU WIN");
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
      word = (String)name.get(getRandomNumber(name));// getting the random word; I had to typecast here because you cant convert an object into string.
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
      System.out.println("*The Text consists of "+ word_counter +" word(s) and "+ letter_counter +" letters: " + spaceOut(blank) +".");
      
      return blank;
   }
//*************************************************************************************************************************************************************************
   public static void infoDisplay ()
   {
      System.out.println(" {0==0}**)=========\\*/=========(**{0==0}");
      System.out.println(" _]__[_<>0<>0<>0==**Y**==0<>0<>0<>_]__[_");
      System.out.println("(6)==(9)                         (9)==(6)");
      System.out.println("  ]||[                             ]||[");
      System.out.println("  ||||        THE HANGING MAN      ||||"); 
      System.out.println("  ||||                             ||||");
      System.out.println("  ||||       By Rijan Timsina      ||||");
      System.out.println("  ||||               &             ||||");
      System.out.println("  ||||        Ibrahim Skouti       ||||");
      System.out.println("  ||||                             ||||");
      System.out.println("  ||||          1st edition        ||||");
      System.out.println(" _]||[_                           _]||[_");
      System.out.println("(9)==(6)*************************(6)==(9)");
      System.out.println("\n");
   }
//**************************************************************************************************************************************************************************
// display rules, instructions, how to play etc. the words/letters counter will be done in hideStringAndDisplay string.
   public static void instructionsDisplay ()
   {
      System.out.println("INSTRUCTIONS:\n*Guess the blank text. \n*No numbers or symbols. \n*You have eight tires.");
   }
//*************************************************************************************************************************************************************************
// display the opening for the 'story'
   public static void openingSequence ()
   {
      System.out.println("");
      System.out.println("*************************** PROLOGUE ************************************\n");
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
      System.out.println("\n************************** WIN or DIE! **********************************");
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
   public static String inGameWordSelector()
   {
      System.out.println("Enter te number of your prefered category:");
      System.out.println("1.Animals \n2.Historical figures \n3.Movies \n4.Sports \n5.Fruits & vegetables \n6.Transportation \n7.Places \n8.Random");
      int catigory = getCatigoryNumber();
   //Make all the arrays 
      String animal[] = {"elephant","lion","tiger","rabbit","snake","giraffe","wolf","owl","bear","deer","eagle","dolphin","vulture","whale","mouse"};
      String historical[] = {"abraham lincoln","john f kennedy","adolf hitler","beneto mussolini","joseph stalin","mao zi dung","winston churchill",
         "genghis khan","albert einstein","alexander the great","charles darwin","king louis","muhammad ali","hammurabi"};
      String movies[] = {"Home alone","Finding nemo","titanic","Toy story","The Incredibles","Shrek","Mission Impossible","KungFu panda","godzilla",
         "Avatar","Star Wars","Lion King","Interstellar","Frozen"};
      String sport[] = {"soccer","Football","volleyball","Lacrosse","cricket","hockey","baseball","Badminton","Nascar","Formula one","rally","fencing",
         "archery","rugby"};
      String fruit_veg[] = {"banana","watermelon","mango","gauava","apple","Pineapple","Kiwifruit","Carrot","Eggplant","strawberry","cherry"};
      String transportation[] = {"Car","Plane","Bus","boat","tram","train","air balloon","submarine"};
      String places[] = {"Great Pyramid of Giza","statue of liberty","eiffel tower","Taj Mahal","Niagara Falls","Great Wall of China","Tower of Pisa",
         "Mount Everest","Big Ben ","Burj Khalifa","Hagia Sophia","Sistine chapel","Vatican City","Mount Rushmore"};
   //arrays are made.
   
      int random;
      String magic_word = ""; //must give value to avoid "variable magic_word might not have been initialized" error.
      switch(catigory)
      { 
         case 1: random =(int)(Math.random()* animal.length);         magic_word = animal[random];      
            break;
         case 2: random =(int)(Math.random()* historical.length);     magic_word = historical[random];  
            break;
         case 3: random =(int)(Math.random()* movies.length);         magic_word = movies[random];      
            break;
         case 4: random =(int)(Math.random()* sport.length);          magic_word = sport[random];       
            break;
         case 5: random =(int)(Math.random()* fruit_veg.length);      magic_word = fruit_veg[random];   
            break;
         case 6: random =(int)(Math.random()* transportation.length); magic_word = transportation[random];
            break;
         case 7: random =(int)(Math.random()* places.length);         magic_word = places[random];      
            break;
      }
      return magic_word;
   }
//*************************************************************************************************************************************************************************
//this method is made speicifically made to get user input for catigory number. it takes it as a string becuase we cant error check intgers. then it converted to an int.
   public static int getCatigoryNumber()
   {
      String something;
      int number = -1;
      while( number == -1 )
      {
         something = extraSpaceFilter(in.nextLine()).toLowerCase();
         //the user could have been entered the number or the whole catigory word, so if he did actually enter the word, then we will...
         //..take that and switch it to the correspodning number.
         switch(something)
         {
           case "animal": something ="1"; break;
           case "animals": something = "1"; break;
           case "historical figures": something = "2"; break;
           case "historical figure": something = "2"; break;
           case "historic figures": something = "2"; break;
           case "historic figure": something = "2"; break;
           case "movies": something = "3"; break;
           case "movie": something = "3"; break;
           case "sports": something = "4"; break;
           case "Sport": something = "4"; break;
           case "fruit & vegetables": something = "5"; break;
           case "fruit and vegetables": something = "5"; break;
           case "fruit": something = "5"; break;
           case "fruit & veg": something = "5"; break;
           case "fruits & vegetables": something = "5"; break;
           case "fruits and vegetables": something = "5"; break;
           case "fruit & vegetable": something = "5"; break;
           case "fruit and vegetable": something = "5"; break;
           case "transportation": something = "6"; break;
           case "transportations": something = "6"; break;
           case "places": something = "7"; break;
           case "place": something = "7"; break;
           case "random": something = "8"; break;
           default: something = something.substring(0,1);
         }
         
         switch(something)
         {
            case "1" : number = 1; 
               break;
            case "2" : number = 2; 
               break;
            case "3" : number = 3; 
               break;
            case "4" : number = 4; 
               break;
            case "5" : number = 5; 
               break;
            case "6" : number = 6; 
               break;
            case "7" : number = 7; 
               break;
            case "8" : number = (int)(Math.random()*7)+1; 
               break;//if the user chose random, then we will choose a random for him.
            default  : System.out.println("This input is invalid, try again:");
         }
      }
      return number;
   }
   
//***********************************************************************************************************************************************************************
   public static ArrayList findFile(String FILENAME, ArrayList name)
   {
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
      return name;
   }
//*************************************************************************************************************************************************************************
   public static void endDisplay ()
   {
      sopln("\n*************************************************************************");
      sop("                   THANK YOU FOR PLAYING");
   }
//************************************************************************************************************************************************************************
//this method takes a string and seperate all letters as individual strings in an array ignoring spaces & repeated letters.
   public static  ArrayList wordSeperator (String word)
   {
      ArrayList<String> backage = new ArrayList<>();
      for(int i=0; i< word.length(); i++)
      {
         String current_holder = word.substring(0+i,1+i);
         if (!(current_holder.equals(" ") || backage.contains(current_holder)))
         {backage.add(current_holder);}
      }
      return backage;
   }
//************************************************************************************************************************************************************************
//the maunual on how to incorprate exterior information. 
   public static void locatingFilesInstruction ()
   {
    System.out.println("How to use an exterior file correctly:");
    System.out.println("Step 1: find the file you want to use, make sure it is a(.txt)format.");
    System.out.println("Step 2: right click on it, go to 'Properties', copy it's location.");
    System.out.println("Step 3: Once you have the location, include the file name with it's file extension.");
    System.out.println("Example: C:\\Users\\personA\\Desktop\\Puzzle Words Lists\\CarBrands.txt");
    System.out.println("Note: If you downloaded this from GitHub, two sample files should be in the zip folder.");
    System.out.println();
   }
//**************************************************************************************************************************************************************************
}

/* THINGS TO FIX BEFORE LAUNCH:
1-fix the guid on how to enter file loction, location, path,adrress. - ok
2-"You already mades this guess". -ok
3-revers the question "do u want to use exterior file?" into 
something like "do u want to use the default options?" so that
they most likely enter yes than no and avoid this shit. -okay
4-make a window style. 
*/