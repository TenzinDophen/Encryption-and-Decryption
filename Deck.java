import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Deck{
   private class Node{
      private int data;
      private Node prev;
      private Node next;
   }
   private Node head; 

//The constructor takes one parameter, which is the name of the file to read the order of the cards from.
//It stored the items in the form of doubly circular linked node
   public Deck(String filename){
      try {
         head = null;
         Scanner input = new Scanner(new File(filename));
         int j= 0;//keeps track of the items added
         while(input.hasNext()){
            int i = input.nextInt();
            Node newitem = new Node();
            Node ano = head;//a new pointer that points to the object that head points
            newitem.data = i;//adding the item to the variable data of the object
            head = newitem; //head points to where the pointer new item is pointing
            Node temp = head;//a new pointer pointing to the node object, head points to
            
            if (j>0){//if the item added is more than 1
               head.next = ano; 
               ano.prev = head;
            }
            for (int k = 1; k <= j ; k++){//loop that run through the number of item added
               temp = temp.next; //makes sure the temp pointer is pointing to the first node object
            }
            temp.next = head; //the link between the first and the last objects 
            head.prev = temp; 
            j++; //increments as number of the item increases
         
         }
         head = head.prev;//assigns head pointer to the first item added
      } 
      catch (IOException theexception) {
         System.out.println("I'm sorry, friend, file wasn't there.");
      }
   
   
   }  
 
/*This method  brings out n numbers from the deck. n is an integer greater than or equal to 1, though it
can be larger than 28. If it is, the deck  starts over from the top and keeps printing.*/
   void print(int n){
      Node point = head; //a new pointer point pointing to the object head points which is the last objedt added
   
      for (int i = 0; i < n; i++) //looping over the entire list
      {System.out.print(point.data + " ");//prints the integers in the obejct's data variable
         point = point.prev; 
         
      }
       System.out.println();
      
   
   }



//This method works just like print, except that it starts at the last number in the deck, and works backwards.
//Like print, n can be larger than 28, in which case it will wrap around.
   void printBackwards(int n){
      Node point = head.next; //a new pointer point pointing to the object head.next points which is the last objedt added
   
      for (int i = 0; i < n; i++)//looping over the entire list
      { System.out.print(point.data + " ");
         point = point.next; //prints from backwards 
         
      }
      System.out.println();
   }

   
//This method implements step 1 of the encryption algorithm, moving a 27 joker down one position.
   void swapJokerA(){
      Node cur = head;
      for (int i =0; i <28;i++){
         if (cur.data == 27)
            break;
         cur = cur.prev;
      }
      if (head.next.data == 27){
         Node temp = head;
         head = cur;
         Node next = head.next;
         Node prev = temp.prev;
         
         next.prev = temp;
         temp.next = next; 
         head.next = temp;
         temp.prev = head;
         head.prev = prev;
         prev.next = head;
         }
      else if(head.data == 27){
         Node last = head.next;
         Node temp = head;
         head = head.prev;
         Node prev = head.prev;
         
         last.prev = head;
         head.next = last;
         temp.next = head;
         head.prev = temp;
         prev.next = temp;
         temp.prev = prev;
         }
     
     else{    
         
      Node prev = cur.prev; 
      Node next = cur.next; 
      Node prev_prev = prev.prev;
    
      prev.next = next;
      next.prev = prev;
      cur.next = prev;   
      prev.prev = cur;
      cur.prev = prev_prev;
      prev_prev.next = cur;
   }
   }
      
 
//This method implements step 2 of the encryption algorithm, moving a 28 joker down two positions.
   void swapJokerB(){
      Node cur = head;
      for (int i =0; i <28;i++){
         if (cur.data == 28)
            break;
         cur = cur.prev;
      }
     if ( head.next.next.data == 28){//if the second last item is the 28 joker
         Node prev = cur.prev;
         Node next = cur.next;
         head = cur ;
         cur = cur.prev.prev;
         Node temp = cur.prev;

         cur.next = prev;
         prev.prev = cur;
         prev.next = next;
         next.prev = prev;
         cur.prev = head;
         head.next = cur;
         head.prev = temp;
         temp.next = head;
         }
         
      else if (head.data == 28){
         Node prev_prev = head.prev.prev;
         Node prev_prev_prev = prev_prev.prev;
         Node last = head.next;
         Node temp = head; 
         head = head.prev;
         
         last.prev = head;
         head.next = last;
         prev_prev.next = head;
         head.prev = prev_prev;
         prev_prev.prev = temp;
         temp.next = prev_prev;
         prev_prev_prev.next= temp;
         temp.prev = prev_prev_prev;
         }
      
      else {  
         
      Node prev = cur.prev; 
      Node next = cur.next; 
      Node prev_prev = prev.prev;
      Node prev_prev_prev = prev_prev.prev;
    
      next.prev = prev;
      prev.next = next;
      cur.prev = prev_prev_prev;
      prev_prev_prev.next = cur;
      cur.next = prev_prev;
      prev_prev.prev = cur;
    
   }
   }

//This method implements step 3 of the encryption algorithm, performing a triple cut.   
   void tripleCut(){
      Node curA = head;  
      for (int i =0; i <28;i++){
         if (curA.data == 27 || curA.data == 28)
            break;
         curA = curA.prev;//moves to the next node
      }
      
      Node curB = curA.prev;//starts from where the curA left of to find the second joker
      for (int i =0; i <28;i++){
         if (curB.data == 27 || curB.data == 28)
            break;
         curB = curB.prev;
      }
      
      if ((head.data == 27 || head.data == 28) && (head.next.data == 27 || head.next.data == 28)){
         }//if the first and the last are jokers, then do nothing
      else if (head.data == 27 || head.data == 28){// if the first is a joker, there is nothing before it
                                                   // only the item numbers below the second joker move
         //creating new pointers
         Node last = head.next;
         head = curB.prev;
        //switching the pointers 
         curA.next = last;
         last.prev = curA;
         head.next = curB;
         curB.prev = head;
         }
      else if (head.next.data == 27 || head.next.data == 28){//if the last is a joker, then nothing is below it
                                                              // only the ones above the first joker move
         //creating new pointers                                                     
         Node temp = head;
         Node last = curA.next;
         head = curA;
         //switching around the pointers
         temp.next = curB;
         curB.prev = temp;
         head.next = last;
         last.prev = head;
       }
       
       else{//this condition occurs when the joker is neither on first node, nor on the last one
      //creating new pointers and directing them 
      Node next = curA.next; 
      Node last = head.next;
      Node temp = head;
      head = curB.prev; 
    // pointers pointing in a way to switch the numbers around
      curA.next = last;
      last.prev = curA;
      temp.next = curB;
      curB.prev = temp;
      head.next = next;
      next.prev = head;
     }   
   }
     

//This method implements step 4 of the encryption algorithm, moving a group of cards to the bottom of the
//deck (above the bottom card).
   void moveToBottom(){
      Node temp = head;
      int bottom = temp.next.data;
      if (bottom == 27 || bottom == 28){//if the bottom is a joker, do nothing
         }
      else{
         for (int i = 1; i < bottom; i++){//loops from the head till the bottom value
            temp = temp.prev;//moves the pointer to the next object
         }
         //creatign new pointers and initialize them 
         Node last = head.next;
         Node first = head;
         head = temp.prev;
         Node next  = last.next;
         //pointers change the objects to which they point
         temp.prev = last;
         last.next = temp;
         head.next = last;
         last.prev = head;
         first.next = next;
         next.prev = first;
      }
   }
   
//This method implements step 5 of the encryption algorithm, counting down from the deck and returning the
//value of a particular card in the deck.
   int countDown(){
      Node temp = head;
      int j = temp.data;
      for (int i = 0; i < j; i++){
         temp = temp.prev;//points to the (j+1)th object
      }
      return temp.data;//retreives the integer stored in the data varible of the object
   }
  

/*This method wraps up the whole process. It calls the above methods representing steps 1-5. If countDown
returns something other than a joker, it returns that value; otherwise, it repeats the whole process until
countDown returns a value which isnt a joker.*/
   int nextKeyValue(){
      int result = 27;//to makes sure the while loop implements onthe first time
      while(result == 27 || result == 28){//runs until the result is not a joker
         swapJokerA();  
         swapJokerB();
         tripleCut();
         moveToBottom();
         result = countDown();
      }
      return result;
   
   }
}