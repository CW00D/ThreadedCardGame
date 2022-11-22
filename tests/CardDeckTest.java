import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class CardDeckTest {
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
    public void getDeckHandTest() {
        ArrayList deckList = CardGame.getDeckList();
        CardDeck d1 = (CardDeck) deckList.get(0);
        String deckHand = d1.getDeck();
        assertEquals("Incorrect player hand or test pack format", "5 5 5 5 ", deckHand);
    }

    @Test
    public void outputDeckFileTest(){
        CardDeck d1 = (CardDeck) CardGame.getDeckList().get(0);
        d1.writeHand();
        //assert(file created / read correct hand
        try {
            File myObj = new File("deck1_output.txt");
            Scanner myReader = new Scanner(myObj);
            int count = 0;
            while (myReader.hasNextLine()) {
                count +=1;
                String data = myReader.nextLine();
                switch(count){
                    case 1:
                        assertEquals("Wrong final deck output", "deck1 contents: 5 5 5 5 ", data);
                        break;
                    case 2:
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
}