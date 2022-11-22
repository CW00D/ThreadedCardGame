import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class PlayerTest {
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
    public static void tearDown() {
        CardGame.setPack();
        CardGame.setPlayerList();
        CardGame.setDeckList();
        for (int i = 0; i < 4; i++) {
            File playerFile = new File("player" + i + "_output.txt");
            try {
                playerFile.delete();
            } catch (Exception e) {
            }
        }

        for (int j = 0; j < 4; j++) {
            File deckFile = new File("deck" + j + "_output.txt");
            try {
                deckFile.delete();
            } catch (Exception e) {
            }
        }
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
    public void outputPlayerFileTest(){
        Player p1 = (Player) CardGame.getPlayerList().get(0);
        System.out.println(p1.getPlayerNumber());
        CardGame.setVictorNumber(2);
        p1.writeInitialHand();
        p1.writeFinalHand();
        //assert(file created / read correct hand
        try {
            File myObj = new File("player1_output.txt");
            Scanner myReader = new Scanner(myObj);
            int count = 0;
            while (myReader.hasNextLine()) {
                count +=1;
                String data = myReader.nextLine();
                switch(count){
                    case 1:
                        assertEquals("Wrong initial hand output", "player 1 initial hand 1 1 1 5 ", data);
                        break;
                    case 2:
                        assertEquals("No data should be present", "", data);
                        break;
                    case 3:
                        assertEquals("Wrong win output", "player 2 has informed player 1 that player 2 has won", data);
                        break;
                    case 4:
                        assertEquals("Wrong exit output", "player 1 exits", data);
                        break;
                    case 5:
                        assertEquals("Wrong final hand output", "player 1 final hand 1 1 1 5 ", data);
                        break;
                    case 6:
                        System.out.println("Should not have data here");
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    @Test
    public void playerWinTest(){
        CardGame.setGameWon(false);
        Player p1 = (Player) CardGame.getPlayerList().get(1);
        p1.run();
        assertEquals("Wrong test pack used or error in run()", 2, (int) CardGame.getVictorNumber());
        assertTrue("Wrong test pack used or error in run()",CardGame.getGameWon());


    }

    @Test
    public void playMoveTest(){
        CardGame.setGameWon(false);
        Player p1 = (Player) CardGame.getPlayerList().get(2);
        p1.run();

    }
}
