import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;

public class CardDeck {
    //Attributes:
    //---------------
    //the deck number
    private Integer deckNumber;
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
        //implement
        return null;
    }

    public void addCard(Card cardToAdd){
        //implement
    }

    public void writeHand(){
        //implement
    }
}