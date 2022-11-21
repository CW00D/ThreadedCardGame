import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
    public void distributeCardsTest(){
        Player p1 = (Player) CardGame.getPlayerList().get(0);
        int playerHandSize = p1.getPlayerHandList().size();
        assertEquals("Incorrect player hand size",4, playerHandSize);
    }

    @Test
    public void selectCardToDiscardTest(){
        Player p1 = (Player) CardGame.getPlayerList().get(0);
        Card card = p1.selectCardToDiscard();

        assertTrue("Discards players preferred card",p1.getPlayerNumber() != card.getCardValue());
        assertTrue("Incorrect test pack format used",card.getCardValue() == 5);
    }

    @Test
    public void getPlayerHandTest(){
        Player p1 = (Player) CardGame.getPlayerList().get(0);
        String hand = p1.getPlayerHand();
        assertEquals("Incorrect player hand or test pack format","1 1 1 5 ", hand);
    }
    @Test
    public void getDeckHandTest() {
        ArrayList deckList = CardGame.getDeckList();
        CardDeck d1 = (CardDeck) deckList.get(0);
        String deckHand = d1.getDeck();
        assertEquals("Incorrect player hand or test pack format", "5 5 5 5 ", deckHand);
    }
    @Test
    public void outputFileTest(){

    }
    @Test
    public void playerWinTest(){

    }
}