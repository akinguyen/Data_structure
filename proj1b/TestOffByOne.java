import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    CharacterComparator a = new OffByOne();
    @Test
    public void testEqualChars(){
        assertFalse(a.equalChars('c','a'));
        assertTrue(a.equalChars('a','b'));
        assertTrue(a.equalChars('c','b'));
        assertTrue(a.equalChars('y','z'));
        assertFalse(a.equalChars('z','d'));
        assertFalse(a.equalChars('d','f'));
        assertFalse(a.equalChars('t','m'));
        assertTrue(a.equalChars('u','v'));
        assertTrue(a.equalChars('p','q'));
        assertTrue(a.equalChars('a','b'));
        
    }
}
