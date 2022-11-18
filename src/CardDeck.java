import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

public class CardDeck {
    //Attributes:
    //---------------
    //the deck number
    private final Integer deckNumber;
    //queue of the cards in a deck
    private ArrayList<Card> deckHand = new ArrayList<Card>();

    //Constructor:
    //---------------
    public CardDeck(Integer deckNumber) {
        this.deckNumber = deckNumber;
    }

    //Getters and Setters:
    //---------------
    public Integer getDeckNumber() {
        return deckNumber;
    }

    //Methods
    //---------------
    public void dealCard(Card card){
        deckHand.add(card);
    }

    public Card drawCard(){
        return deckHand.remove(0);
    }

    public String getDeck(){
        String deck = "";
        for (int i=0;i<deckHand.size();i++) {
            deck = deck + String.valueOf(deckHand.get(i).getCardValue())+" ";
        }
        return deck;
    }

    public void writeHand(){
        try {
            File myObj = new File("deck" + deckNumber + "_output.txt");
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred creating a file.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("deck" + deckNumber + "_output.txt");
            myWriter.write("deck" + deckNumber + " contents: " + getDeck() + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing deck to a file.");
            e.printStackTrace();
        }
    }
}