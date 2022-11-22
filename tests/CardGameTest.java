import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class CardGameTest {
    // userInputs() private ????
    /*
    @BeforeClass
    public static void beforeClass() throws Exception {
        CardGame cardGame = new CardGame();
        cardGame.setNumberOfPlayers(4);
        // no need to validate as we know contents are.
        cardGame.createPack("test_pack_0.txt");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        this.cardGame = null;
    }
*/
    @BeforeClass
    public static void setUp(){
        CardGame.setNumberOfPlayers(4);
        try {
            CardGame.createDecks();
        } catch (Exception e){
            System.out.println("Unable to create decks");
        }

        try {
            CardGame.createPlayers();
        } catch (Exception e){
            System.out.println("Unable to create players");
        }

        try {
            CardGame.createPack("test_pack_0.txt");
        } catch (FileNotFoundException e){
            System.out.println("Unable to create testing pack: FileNotFoundException");
        }

        try {
            CardGame.distributeCards();
        } catch (Exception e){
            System.out.println("Unable to distribute cards");
        }

    }

    @AfterClass
    public static void tearDown(){
        CardGame.setPack();
        CardGame.setPlayerList();
        CardGame.setDeckList();
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
        assertTrue("Valid pack location and contents", CardGame.validatePack("test_pack_0.txt"));
        assertFalse("Invalid pack location", CardGame.validatePack("doesNotExist.txt"));
        assertFalse("Invalid pack contents: Multiple values per line", CardGame.validatePack("test_pack_1.txt"));
        assertFalse("Invalid pack contents: Non integers", CardGame.validatePack("test_pack_2.txt"));
        assertFalse("Invalid pack contents: Negative integers", CardGame.validatePack("test_pack_3.txt"));
        assertFalse("Invalid pack contents: Wrong amount of integers", CardGame.validatePack("test_pack_4.txt"));
    }

    @Test
    public void createPackTest(){
        assertEquals("Wrong length of pack",32, CardGame.getPack().size());
    }

    @Test
    public void createDecksTest(){
        assertEquals("Incorrect amount of decks created",4, CardGame.getDeckList().size());
    }

    @Test
    public void createPlayersTest(){
        assertEquals("Incorrect amount of players created",4, CardGame.getPlayerList().size());
    }
    @Test
    public void distributedCardsTest(){
        Player p1 = (Player) CardGame.getPlayerList().get(0);
        int playerHandSize = p1.getPlayerHandList().size();
        Card playerHandCard = (Card) p1.getPlayerHandList().get(3);
        int cardValue = playerHandCard.getCardValue();

        assertEquals("Incorrect player hand size",4, playerHandSize);
        assertEquals("Incorrect player cards in hand",5, cardValue);
    }
}