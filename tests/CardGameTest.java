import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;

public class CardGameTest {
    private CardGame cardGame;
    @Before
    public void setUp(){
        CardGame cardGame = new CardGame();


    }
    @Test
    public void TestValidatePack(){
        assertTrue("valid pack", cardGame.validatePack("test_pack.txt"));

        // assert !CardGame.validatePack("testPackWithMultipleValues.txt");
        // assert !CardGame.validatePack("testPackWithNonIntegerValue.txt");
        //assert !CardGame.validatePack("testPackWithNegativeInteger.txt");
    }


}