import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testequalChars(){
        //this should return true
        assertTrue(offByOne.equalChars('a','b'));
        //this should return false
        assertFalse(offByOne.equalChars('a','a'));
        //this should return true
        assertTrue(offByOne.equalChars('%','&'));
        //this should return false
        assertFalse(offByOne.equalChars('a','B'));
    }
}