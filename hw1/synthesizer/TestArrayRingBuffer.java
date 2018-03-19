package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void TestisEmpty() {
        ArrayRingBuffer arb = new ArrayRingBuffer(8);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
    }

    @Test
    public void TestisFull(){
        ArrayRingBuffer<String> test = new ArrayRingBuffer<>(6);
        test.enqueue("dog");
        test.enqueue("cat");
        test.enqueue("X");
        test.enqueue("bird");
        test.enqueue("bird");
        test.enqueue("bird");
        assertTrue(test.isFull());
        assertFalse(test.isEmpty());
    }

    @Test
    public void Testdeque(){
        ArrayRingBuffer<String> test = new ArrayRingBuffer<>(4);
        test.enqueue("Minh");
        test.enqueue("Thao");
        test.enqueue("Nguyen");
        assertEquals(3,test.fillCount());
        assertEquals("Minh",test.dequeue());
        assertEquals("Thao",test.dequeue());
        assertEquals(1,test.fillCount());
        assertEquals("Nguyen",test.dequeue());
        assertTrue(test.isEmpty());
    }
    @Test
    public void TestEnqueue(){
        ArrayRingBuffer<String> test = new ArrayRingBuffer<>(4);
        test.enqueue("Nguyen");
        test.enqueue("Ngoc");
        test.enqueue("Thao");
        test.enqueue("Minh");
        assertTrue(test.isFull());
        assertEquals(4,test.fillCount());
        assertEquals("Nguyen",test.dequeue());
        assertEquals(3,test.fillCount());
        assertEquals("Ngoc",test.peek());

    }
    @Test
    public void Testpeek(){
        ArrayRingBuffer<String> test = new ArrayRingBuffer<>(8);
        test.enqueue("Nguyen");
        test.enqueue("Ngoc");
        test.enqueue("Thao Minh");
        assertEquals("Nguyen",test.peek());
        assertEquals(3,test.fillCount());
        assertEquals("Nguyen",test.peek());
        test.dequeue();
        assertEquals("Ngoc",test.peek());
        assertEquals(2,test.fillCount());


    }
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
