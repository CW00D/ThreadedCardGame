import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Christian Wood and Jacob Beeson
 *
 */

public class Player extends Thread{
    //Attributes:
    //---------------
    //the player number (also their preferred card)
    private final Integer playerNumber;
    //the cards in the player's hand
    private ArrayList<Card> playerHand = new ArrayList<>();
    //the deck the player pick cards up from
    private final CardDeck leftCardDeck;
    //the deck the player places cards to
    private final CardDeck rightCardDeck;
    //randomizer instance for use in selecting cards
    Random random = new Random();

    //Constructor:
    //---------------
    public Player(Integer playerNumber, CardDeck leftCardDeck, CardDeck rightCardDeck) {
        this.playerNumber = playerNumber;
        this.leftCardDeck = leftCardDeck;
        this.rightCardDeck = rightCardDeck;
    }

    //Getters and Setters:
    //---------------
    public Integer getPlayerNumber() {
        return playerNumber;
    }

    //run thread:
    //---------------
    @Override
    public void run(){
        while (!CardGame.getGameWon()){
            if (checkWin()){
                setVictoryAttributes();
            } else {
                if (leftCardDeck.getDeckHand().size()!=0){
                    playMove();
                    writeMove();
                }
            }
        }
        writeFinalHand();
        rightCardDeck.writeHand();
    }

    //Methods
    //---------------
    public void dealCard(Card card){
        playerHand.add(card);
    }

    private void playMove(){
        Card cardToPlace;
        Card cardDrawn;
        synchronized (Player.class) {
            cardDrawn = drawCard();
            playerHand.add(cardDrawn);
            cardToPlace = selectCardToDiscard();
            placeCard(cardToPlace);
        }
        try {
            FileWriter myWriter = new FileWriter("player" + playerNumber + "_output.txt", true);
            myWriter.write("\nplayer " + playerNumber + " draws a " + cardDrawn.getCardValue() + " from deck " + leftCardDeck.getDeckNumber());
            myWriter.write("\nplayer " + playerNumber + " discards a " + cardToPlace.getCardValue() + " to deck " + rightCardDeck.getDeckNumber() );
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing a move to the file.");
            e.printStackTrace();
        }
    }

    private Card drawCard(){
        Card cardToAdd = leftCardDeck.drawCard();
        return cardToAdd;
    }

    private void placeCard(Card cardToPlace){
        rightCardDeck.dealCard(cardToPlace);
    }

    private Boolean checkWin(){
        return (playerHand.get(0).getCardValue().equals(playerHand.get(1).getCardValue()) && playerHand.get(0).getCardValue().equals(playerHand.get(2).getCardValue()) && playerHand.get(0).getCardValue().equals(playerHand.get(3).getCardValue()));
    }

    private Card selectCardToDiscard(){
        ArrayList<Card> possibleDiscardCards = new ArrayList<>();
        for (int i=0;i<playerHand.size();i++){
            if (!(playerHand.get(i).getCardValue().equals(playerNumber))) {
                possibleDiscardCards.add(playerHand.get(i));
            }
        }
        int randomIndex = -1;
        if (possibleDiscardCards.size()==1){
            randomIndex = 0;
        } else {
            randomIndex = random.nextInt(possibleDiscardCards.size() - 1);
        }
        Card cardToDiscard = possibleDiscardCards.get(randomIndex);
        playerHand.remove(cardToDiscard);
        return cardToDiscard;
    }

    private String getPlayerHand(){
        String hand = "";
        for (int i=0;i<playerHand.size();i++) {
            hand = hand + String.valueOf(playerHand.get(i).getCardValue())+" ";
        }
        return hand;
    }

    private synchronized void setVictoryAttributes() {
        CardGame.setVictorNumber(playerNumber);
        CardGame.setGameWon(true);
    }

    public void writeInitialHand(){
        try {
            File myObj = new File("player" + playerNumber + "_output.txt");
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred creating a file.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("player" + playerNumber + "_output.txt");
            myWriter.write("player " + playerNumber + " initial hand " + getPlayerHand() + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing initial hand to a file.");
            e.printStackTrace();
        }
    }

    public void writeMove(){
        try {
            FileWriter myWriter = new FileWriter("player"+this.playerNumber+"_output.txt", true);
            myWriter.write("\nplayer "+this.playerNumber+" current hand is "+getPlayerHand()+ "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing a move to a file.");
            e.printStackTrace();
        }
    }

    public void writeFinalHand(){
        Integer playerNumberOfVictor = CardGame.getVictorNumber();
        if (playerNumberOfVictor == playerNumber){
            try {
                FileWriter myWriter = new FileWriter("player"+this.playerNumber+"_output.txt", true);
                myWriter.write("\nplayer " + playerNumber + " wins\n");
                myWriter.write("player " + playerNumber + " exits\n");
                myWriter.write("player " + playerNumber + " final hand " + getPlayerHand());
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred writing this player's win to a file.");
                e.printStackTrace();
            }
        } else {
            try {
                FileWriter myWriter = new FileWriter("player"+this.playerNumber+"_output.txt", true);
                myWriter.write("\nplayer " + CardGame.getVictorNumber() + " has informed player " + playerNumber + " that player " + CardGame.getVictorNumber() + " has won\n");
                myWriter.write("player " + playerNumber + " exits\n");
                myWriter.write("player " + playerNumber + " final hand " + getPlayerHand());
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred writing another player's win to a file.");
                e.printStackTrace();
            }
        }
    }
}
