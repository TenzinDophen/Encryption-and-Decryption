import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class Encrypt{
   public static void main(String args[]) throws IOException{
   Scanner key = new Scanner (new File("input.txt"));
   String encrypted_msg = "";
   char A = 'A';
   while(key.hasNext()){
      String letter = key.next();
      for (int i = 0; i < letter.length(); i++){
         int num = (int)letter.charAt(i)- (int)A + 1;  
         Deck cards = new Deck("deck.txt");
         int value = cards.nextKeyValue();
         int sum = num + value;
         if (sum > 26)
            sum = sum -26;
         encrypted_msg = encrypted_msg + (char)((int)A + sum - 1);   
   }
   }
   System.out.println(encrypted_msg);
   }
   }