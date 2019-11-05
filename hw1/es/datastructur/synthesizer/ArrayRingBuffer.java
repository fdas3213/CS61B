package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    /* Capacity of the array */
    private int capacity;

    public Iterator<T> iterator(){
        return new ArrayBufferIterator();
    }

    private class ArrayBufferIterator implements Iterator<T>{
        private int count;

        public ArrayBufferIterator(){
            count = 0;
        }

        public boolean hasNext(){
            return count < capacity;
        }

        public T next(){
            T x = rb[count];
            count ++;
            return x;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        rb = (T[]) new Object[this.capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    public int capacity(){
        return capacity;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    private int plusone(int index){
        return (index + 1) % capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = plusone(last);
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T x = rb[first];
        first = plusone(first);
        fillCount--;
        return x;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    public boolean equals(ArrayRingBuffer o){
        return this.first==o.first && this.last==o.last && this.capacity==o.capacity
                && this.fillCount==o.fillCount && this.rb.equals(o.rb);
    }

}

