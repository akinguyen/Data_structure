import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome a = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = a.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } 
    
    @Test
    public void testIsPalindrome(){
        assertFalse(a.isPalindrome("cat"));
        assertTrue(a.isPalindrome("racecar"));
        assertTrue(a.isPalindrome("LeveL"));
        assertTrue(a.isPalindrome("CiviC"));
        assertFalse(a.isPalindrome("yahoo"));
        assertFalse(a.isPalindrome("yahoo2"));
        assertFalse(a.isPalindrome("yahaY"));
        assertTrue(a.isPalindrome("oo2oo"));
        assertTrue(a.isPalindrome("kjha2catac2ahjk"));
        assertTrue(a.isPalindrome("hnimoahtthaominh"));
        
    }
}
