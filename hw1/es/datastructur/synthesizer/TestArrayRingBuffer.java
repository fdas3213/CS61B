package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        /* Test capacity is set properly */
        assertEquals(arb.capacity(), 10);
        /* Test enqueue() works properly */
        arb.enqueue(5);
        assertEquals(arb.peek(),5);
        arb.enqueue(3);
        arb.enqueue(6);
        /* Test fillCount() */
        assertEquals(arb.fillCount(),3);
        /* Test dequeue() and fillCount() */
        arb.dequeue();
        assertEquals(arb.fillCount(),2);
    }
}
