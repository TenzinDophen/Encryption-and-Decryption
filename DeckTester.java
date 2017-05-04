public class DeckTester {
public static void main(String[] args) {
Deck cards = new Deck("deck.txt");
cards.print(3);
cards.print(30);
cards.printBackwards(30);
System.out.println(cards.nextKeyValue());
System.out.println(cards.nextKeyValue());
cards.swapJokerA();
cards.swapJokerB();
cards.tripleCut();
cards.moveToBottom();
System.out.println(cards.countDown());
System.out.println(cards.nextKeyValue());
cards.print(30);
cards.printBackwards(30);
}
}