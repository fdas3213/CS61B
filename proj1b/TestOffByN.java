import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offBy5 = new OffByN(5);
    static CharacterComparator offBy1 = new OffByN(1);

    @Test
    public void testequalChars5(){
        //this should return true
        assertTrue(offBy5.equalChars('a','f'));
        //this should return true
        assertTrue(offBy5.equalChars('f','a'));
        //this should return false
        assertFalse(offBy5.equalChars('f','h'));
    }

    @Test
    public void testequalChars1(){
        //this should return true
        assertTrue(offBy1.equalChars('a','b'));
        //this should return false
        assertFalse(offBy1.equalChars('a','a'));
        //this should return true
        assertTrue(offBy1.equalChars('%','&'));
        //this should return false
        assertFalse(offBy1.equalChars('a','B'));
    }
}
