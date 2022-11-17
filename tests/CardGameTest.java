import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;

import java.util.ArrayList;

public class CardGameTest {
    // userInputs() private ????
    private CardGame cardGame;
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
    @Before
    public void setUp() throws Exception {
        CardGame cardGame = new CardGame();
        cardGame.setNumberOfPlayers(4);
        cardGame.createDecks();
        cardGame.createPlayers();
        cardGame.createPack("test_pack_0.txt");
    }

    @After
    public void tearDown() throws Exception {
        cardGame = null;
    }


    @Test
    public void validatePackTest(){
        assertTrue("Valid pack location and contents", cardGame.validatePack("test_pack_0.txt"));
        assertFalse("Invalid pack location", cardGame.validatePack("doesNotExist.txt"));
        assertFalse("Invalid pack contents: Multiple values per line", cardGame.validatePack("test_pack_1.txt"));
        assertFalse("Invalid pack contents: Non integers", cardGame.validatePack("test_pack_2.txt"));
        assertFalse("Invalid pack contents: Negative integers", cardGame.validatePack("test_pack_3.txt"));
        assertFalse("Invalid pack contents: Wrong amount of integers", cardGame.validatePack("test_pack_4.txt"));
    }
    /*
    @Test
    public void createPlayersTest(){

        cardGame.createPlayers();
        ArrayList playerList = cardGame.getPlayerList();
        assertEquals("condition",playerList.size() == 4 );
    }

    @Test
    public void createDecksTest(){
        cardGame.createDecks();
        ArrayList deckList = cardGame.getDeckList();
        assertTrue("All decks present",deckList.size() == 4 );

    }

     */
    @Test
    public void createPackTest(){
        // assert(pack.length() == 'test location')
    }


}