import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Thread{
    //Attributes:
    //---------------
    //the player number (also their preferred card)
    private Integer playerNumber;
    //the cards in the player's hand
    private ArrayList<Card> playerHand = new ArrayList<Card>();
    //the deck the player pick cards up from
    private CardDeck leftCardDeck;
    //the deck the player places cards to
    private CardDeck rightCardDeck;
    private Integer numberOfPlays = 0;

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
    public void run(){
        while (!CardGame.getGameWon()){
            //if (checkWin()){
            System.out.println("Player " + playerNumber + " has played " + numberOfPlays + " times. ");
            if (numberOfPlays >= 5){
                System.out.println("Player " + playerNumber + " has won");
                CardGame.setVictorNumber(playerNumber);
                CardGame.setGameWon(true);
                //notify thread group
            }
            System.out.println("Player " + playerNumber + " plays move. ");
            playMove();
            writeMove();
            numberOfPlays += 1;
        }
    }

    //Methods
    //---------------
    public void dealCard(Card card){
        playerHand.add(card);
    }

    private Card playMove(){
        try {
            FileWriter myWriter = new FileWriter("player"+this.playerNumber+"_output.txt", true);
            myWriter.write("\nplayer "+this.playerNumber+" draws a n from deck " + leftCardDeck.getDeckNumber());
            myWriter.write("\nplayer "+this.playerNumber+" discards a n to deck " + rightCardDeck.getDeckNumber() );
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    private synchronized Card drawCard(){
        //implements
        return null;
    }

    private synchronized void placeCard(Card cardToPlace){
        //implement
    }

    private Boolean checkWin(){
        if (playerHand.get(0)==playerHand.get(1) && playerHand.get(0)==playerHand.get(2) && playerHand.get(0)==playerHand.get(3)){
            System.out.println("Player " + this.playerNumber + " declares win. ");
            return true;
        } else {
            return false;
        }
    }

    private String getPlayerHand(){
        String hand = "";
        for (int i=0;i<4;i++) {
            hand = hand + String.valueOf(playerHand.get(i).getCardValue())+" ";
        }
        return hand;
    }

    public void writeInitialHand(){
        try {
            File myObj = new File("player"+this.playerNumber+"_output.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("player"+this.playerNumber+"_output.txt");
            myWriter.write("player "+this.playerNumber+" initial hand "+getPlayerHand()+ "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeMove(){
        try {
            FileWriter myWriter = new FileWriter("player"+this.playerNumber+"_output.txt", true);
            myWriter.write("\nplayer "+this.playerNumber+" current hand is "+getPlayerHand()+ "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
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
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            try {
                FileWriter myWriter = new FileWriter("player"+this.playerNumber+"_output.txt", true);
                myWriter.write("\nplayer " + CardGame.getVictorNumber() + " has informed player " + playerNumber + " that player " + CardGame.getVictorNumber() + " has won\n");
                myWriter.write("player " + playerNumber + " exits\n");
                myWriter.write("player " + playerNumber + " final hand " + getPlayerHand());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
            //"player " + playerNumber + " wins"
            //"player " + playerNumber + " exits"
            //"player " + playerNumber + " final hand " + getPlayerHand()



        //else output:
            //"player " + playerNumberOfVictor + " has informed player " + playerNumber + " that player " + playerNumeberOfVictor + " has won"
            //"player " + playerNumber + " exits"
            //"player " + playerNumber + " final hand " + getPlayerHand()
    }

}
