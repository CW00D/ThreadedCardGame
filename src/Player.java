import java.util.ArrayList;

public class Player {
    //Attributes:
    //---------------
    //the player number (also their preferred card)
    private Integer playerNumber;
    //the cards in the player's hand
    private ArrayList<Card> playerHand;
    //the deck the player pick cards up from
    private CardDeck leftCardDeck;
    //the deck the player places cards to
    private CardDeck rightCardDeck;

    //Constructor:
    //---------------
    public Player(String playerName, CardDeck leftCardDeck, CardDeck rightCardDeck) {
        this.playerNumber = playerNumber;
        this.leftCardDeck = leftCardDeck;
        this.rightCardDeck = rightCardDeck;
    }

    //Getters and Setters:
    //---------------
    public Integer getPlayerNumber() {
        return playerNumber;
    }

    //Methods
    //---------------
    public Card playMove(){
        //implements
        return null;
    }

    private Card drawCard(){
        //implements
        return null;
    }

    private void placeCard(Card cardToPlace){
        //implement
    }

    private Boolean checkWin(){
        //implement
        return null;
    }

    public String getPlayerHand(){
        //implement
        return null;
    }

    private void writeHand(){
        //implement
    }

    private void writeToFile(){
        //implement
    }
}
