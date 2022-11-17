import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the implementor class for the CardGameInterface
 *
 * @author Christian Wood and Jacob Beeson
 *
 */
public class CardGame extends Thread{
    //Attributes:
    //---------------
    //private as they should only be used by CardGame instance
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static ArrayList<CardDeck> decks = new ArrayList<CardDeck>();
    private static ArrayList<Card> pack = new ArrayList<Card>();
    private static int numberOfPlayers;
    private static String packLocation;
    private static Boolean gameWon;

    //Getters and Setters:
    //---------------
    public static Boolean getGameWon() {
        return gameWon;
    }

    public static void setGameWon(Boolean gameWon) {
        CardGame.gameWon = gameWon;
    }

    // check
    public void setNumberOfPlayers(Integer num){
        this.numberOfPlayers = num;
    }
    public ArrayList getPlayerList(){
        return players;
    }
    public ArrayList getDeckList(){
        return decks;
    }
    //Methods:
    //---------------
    public static void userInputs(){
        Scanner input = new Scanner(System.in);

        //checking number of players input is valid
        boolean validNumOfPlayers = false;
        Integer integerNumberOfPlayers = null;
        while(!validNumOfPlayers) {
            System.out.println("Please enter the number of players: ");
            String numberOfPlayersInput = input.nextLine();
            try {
                integerNumberOfPlayers = Integer.parseInt(numberOfPlayersInput);
                if (integerNumberOfPlayers <= 0){
                    System.out.println("Please enter a non negative number of players. ");
                    validNumOfPlayers = false;
                } else{
                    validNumOfPlayers = true;
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("wrong type of input");
            }
        }
        numberOfPlayers = integerNumberOfPlayers;

        //checking pack input is valid
        boolean isValidPackType = false;
        String packLocationInput = null;
        while(!isValidPackType) {
            System.out.println("Please enter pack location: ");
            packLocationInput = input.nextLine();
            isValidPackType = validatePack(packLocationInput);
        }
        packLocation = packLocationInput;

        input.close();
    }

    public static boolean validatePack(String packLocation){
        try {
            File myObj = new File(packLocation);
            Scanner myReader = new Scanner(myObj);
            int counter = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains(" ")) {
                    System.out.println("One of your lines contains more than a single number");
                    return false;
                } else {
                    try{
                        Integer number = Integer.parseInt(data);
                        if (number < 0){
                            System.out.println("One of the cards was negative");
                            return false;
                        }
                    }
                    catch (NumberFormatException ex){
                        System.out.println("One of the cards was not an integer");
                        return false;
                    }
                }
                counter += 1;
            }
            myReader.close();
            if (counter == (numberOfPlayers * 8)){
                System.out.println("Pack is valid. ");
                return true;
            } else {
                System.out.println("Not the right number of cards for the number of players. ");
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. ");
            return false;
        }
    }

    public static void createPack(String packLocation) throws FileNotFoundException {
        System.out.println("Creating Pack");
        File myObj = new File(packLocation);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            pack.add(new Card(Integer.parseInt(data)));
        }
    }

    public static void distributeCards(){
        for (int i=0;i<4;i++){
            System.out.println("Dealing a card to each player. ");
            for (int j=0;j<numberOfPlayers;j++){
                System.out.println("    Dealing a card to player " + j);
                players.get(j).dealCard(pack.get(i*numberOfPlayers + j));
            }
        }
        for (int i=0;i<4;i++){
            System.out.println("Dealing a card to each deck. ");
            for (int j=0;j<numberOfPlayers;j++){
                System.out.println("    Dealing a card to deck " + j);
                decks.get(j).dealCard(pack.get((i*numberOfPlayers + j) + (4*numberOfPlayers)));
            }
        }
    }

    public static void createDecks(){
        System.out.println("Creating decks");
        for (int i=1;i<=numberOfPlayers;i++){
            System.out.println("    Creating new card deck " + i);
            decks.add(new CardDeck(i));
        }

    }

    public static void createPlayers(){
        System.out.println("Creating players. ");
        for (int i=0;i<numberOfPlayers;i++) {
            System.out.println("    Creating new player " + i);
            if (i==0) {
                players.add(new Player(i, decks.get(numberOfPlayers - 1), decks.get(i)));
            } else {
                players.add(new Player(i, decks.get(i - 1), decks.get(i)));
            }
        }
    }

    public static void startGame(){
        Boolean gameWon=false;
        System.out.println("Starting the game. ");
        for (int i=0;i<numberOfPlayers;i++){
            players.get(i).start();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //getting user inputs
        userInputs();

        //create decks
        createDecks();
        createPlayers();

        //create pack
        createPack(packLocation);

        //distribute cards to players
        distributeCards();

        //start game
        Thread gameThread = new Thread(){
            startGame();

        };
        gameThread.start();
        //we start a thread which runs the game
        //this thread is waiting to end the game when it is interrupted by one of the players (when the player has won)
        //when interrupted it stops the thread group with
    }
}
