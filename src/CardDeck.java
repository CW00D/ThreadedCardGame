import java.util.ArrayList;
import java.util.Queue;

public class CardDeck {
    //Attributes:
    //---------------
    //the deck number
    private Integer deckNumber;
    //queue of the cards in a deck
    private Queue<Card> deckHand;

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