import java.util.*;
public class TheHangedMan
{
 static Scanner in = new Scanner(System.in);
 public  static void main (String[] args)
 {
  String the_magic_word = in.nextLine();
  System.out.println(the_magic_word); 
  
  //String b ="b";
  //System.out.println(b.indexOf("b",0));//0
  
  
  
  String blank ="";
  
  for (int i=0; i<the_magic_word.length() ; i++)
   {
    if(the_magic_word.indexOf(" ", i )- i == 0)//if current index (i) ==" "
    { blank +=" ";}
    else{ blank +="-";}
   } 
   
   System.out.println(blank);
}
}