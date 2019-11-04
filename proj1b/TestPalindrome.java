import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome(){
        //this part tests isPalindrome(String word)
        //this should return true
        assertTrue(palindrome.isPalindrome("abba"));
        //this should return false
        assertFalse(palindrome.isPalindrome("cat"));
        //this should return false as well
        assertFalse(palindrome.isPalindrome("abbA"));

    }

    @Test
    public void testisPalindromeCC(){
        //this part tests isPalindrome(String word, CharacterComparator cc)
        //this should return true
        assertTrue(palindrome.isPalindrome("abab", offByOne));
        //this should return false
        assertFalse(palindrome.isPalindrome("abaB",offByOne));
        //this should return true
        assertTrue(palindrome.isPalindrome("%ab&", offByOne));
    }
}