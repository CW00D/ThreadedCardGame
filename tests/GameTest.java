import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.File;  // Import the File class


public class GameTest {
    public Game game = new Game();

    @Before
    public void setUp(){
        game.setNumberOfPlayers(4);
        try {
            game.createDecks();
        } catch (Exception e){
            System.out.println("Unable to create decks");
        }

        try {
            game.createPlayers();
        } catch (Exception e){
            System.out.println("Unable to create players");
        }

        try {
            game.createPack("test_pack_0.txt");
        } catch (FileNotFoundException e){
            System.out.println("Unable to create testing pack: FileNotFoundException");
        }

        try {
            game.distributeCards();
        } catch (Exception e){
            System.out.println("Unable to distribute cards");
        }

    }

    @After
    public  void tearDown(){
        game = null;

        for (int i=0;i<4;i++) {
            File playerFile = new File("player" + i + "_output.txt");
            try {
                playerFile.delete();
            } catch (Exception e) {
            }
        }

        for (int j=0;j<4;j++) {
            File deckFile = new File("deck" + j + "_output.txt");
            try {
                deckFile.delete();
            } catch (Exception e) {
            }
        }
    }

    @Test
    public void validatePackTest(){
        assertTrue("Valid pack location and contents", game.validatePack("test_pack_0.txt"));
        assertFalse("Invalid pack location", game.validatePack("doesNotExist.txt"));
        assertFalse("Invalid pack contents: Multiple values per line", game.validatePack("test_pack_1.txt"));
        assertFalse("Invalid pack contents: Non integers", game.validatePack("test_pack_2.txt"));
        assertFalse("Invalid pack contents: Negative integers", game.validatePack("test_pack_3.txt"));
        assertFalse("Invalid pack contents: Wrong amount of integers", game.validatePack("test_pack_4.txt"));
    }

    @Test
    public void createPackTest(){
        assertEquals("Wrong length of pack",32, game.getPack().size());
    }

    @Test
    public void createDecksTest(){
        assertEquals("Incorrect amount of decks created",4, game.getDeckList().size());
    }

    @Test
    public void createPlayersTest(){
        assertEquals("Incorrect amount of players created",4, game.getPlayerList().size());
    }
    @Test
    public void distributedCardsTest(){
        Player p1 = (Player) game.getPlayerList().get(0);
        int playerHandSize = p1.getPlayerHandList().size();
        Card playerHandCard = (Card) p1.getPlayerHandList().get(3);
        int cardValue = playerHandCard.getCardValue();

        assertEquals("Incorrect player hand size",4, playerHandSize);
        assertEquals("Incorrect player cards in hand",5, cardValue);
    }
}