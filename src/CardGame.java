import java.io.FileNotFoundException;

/**
 *
 * @author Christian Wood and Jacob Beeson
 *
 */

public class CardGame {

    public static void main(String[] args) throws FileNotFoundException {
        Game game = new Game();
        //getting user inputs
        game.userInputs();

        //create decks
        game.createDecks();
        game.createPlayers();

        //create pack
        game.createPack(game.getPackLocation());

        //distribute cards to players
        game.distributeCards();

        //start game
        game.startGame();
        //we start a thread which runs the game
        //this thread is waiting to end the game when it is interrupted by one of the players (when the player has won)
        //when interrupted it stops the thread group with
    }
}
