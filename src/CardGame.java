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
public class CardGame{
    //Attributes:
    //---------------
    //private as they should only be used by CardGame instance
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static ArrayList<CardDeck> decks = new ArrayList<CardDeck>();
    private static ArrayList<Card> pack = new ArrayList<Card>();
    private static int numberOfPlayers;
    private static String packLocation;

    //Methods:
    //---------------
    private static void createPack(String packLocation) throws FileNotFoundException {
        System.out.println("Creating Pack");
        File myObj = new File(packLocation);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            pack.add(new Card(Integer.parseInt(data)));
        }
    }

    private static boolean validatePack(String packLocation){
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

    private void distributeCards(){
        //implement
    }

    public static void main(String[] args) throws FileNotFoundException {
        //getting user inputs
        Scanner input = new Scanner(System.in);

        //checking number of players input is valid
        boolean validNumOfPlayers = false;
        while(!validNumOfPlayers) {
            System.out.println("Please enter the number of players: ");
            String numberOfPlayersInput = input.nextLine();
            try {
                numberOfPlayers = Integer.parseInt(numberOfPlayersInput);
                validNumOfPlayers = true;
            }
            catch (NumberFormatException nfe) {
                System.out.println("wrong type of input");
            }
        }

        //getting pack location input and checking if pack is valid
        boolean isValidPackType = false;
        String packLocationInput = null;
        while(!isValidPackType) {
            System.out.println("Please enter pack location: ");
            packLocationInput = input.nextLine();
            isValidPackType = validatePack(packLocationInput);
        }
        input.close();

        //create decks
        for (int i=1;i<=numberOfPlayers;i++){
            System.out.println("Creating new card deck " + i);
            decks.add(new CardDeck(i));
        }

        //create players
        for (int i=1;i<=numberOfPlayers;i++) {
            System.out.println("Creating new player " + i);
            if (i==1) {
                players.add(new Player(i, decks.get(numberOfPlayers - 1), decks.get(i-1)));
            } else {
                players.add(new Player(i, decks.get(i - 2), decks.get(i-1)));
            }
        }

        //create pack
        createPack(packLocationInput);

        //distribute cards to players
        //distribute remaining cards to decks
        //start game

    }
}
