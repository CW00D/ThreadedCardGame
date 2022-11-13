import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
    //Attributes:
    //---------------
    //private as they should only be used by CardGame instance
    private ArrayList<Player> players;
    private ArrayList<CardDeck> decks;
    private ArrayList<Integer> pack;

    //Methods:
    //---------------
    private static Integer getNumberOfPlayers(){
        //take number of players from a terminal input
        Scanner input = new Scanner(System.in);
        //add input handling
        System.out.print("Please enter the number of players: ");
        Integer numberOfPlayers = input.nextInt();
        // closing the scanner object
        input.close();
        return numberOfPlayers;
    }

    private static String getPackLocation(){
        //take number of players from a terminal input
        Scanner input = new Scanner(System.in);
        //add input handling
        System.out.println("Please enter location of pack to load: ");

        String packLocation = input.nextLine();
        // closing the scanner object
        input.close();
        return packLocation;
    }

    public static void takeInputs(){
        //take number of players from a terminal input
        Scanner input = new Scanner(System.in);
        //add input handling
        System.out.print("Please enter the number of players: ");
        Integer numberOfPlayers = input.nextInt();

        //take number of players from a terminal input
        //add input handling
        System.out.print("Please enter location of pack to load: ");
        String packLocation = input.next();
        System.out.println(numberOfPlayers);
        System.out.println(packLocation);

        // closing the scanner object
        input.close();
    }

    private void setPack(String pack){
        //implement
    }

    private Boolean validatePack(String packLocation){
        //implement
        return null;
    }

    private void distributeCards(){
        //implement
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //checking number of players input is valid
        boolean validNumOfPlayers = false;
        while(!validNumOfPlayers) {
            System.out.println("Please enter the number of players: ");
            String numberOfPlayersInput = input.nextLine();
            try {
                int numberOfPlayers = Integer.parseInt(numberOfPlayersInput);
                validNumOfPlayers = true;
            }
            catch (NumberFormatException nfe) {
                System.out.println("wrong type of input");
            }
        }

        //getting pack location input
        System.out.println("Please enter pack location: ");
        String packLocationInput = input.nextLine();
        input.close();

        //validating pack inputted


        //create players
        //create decks
        //create pack
        //distribute cards to players
        //distribute remaining cards to decks
        //start game

    }
}
