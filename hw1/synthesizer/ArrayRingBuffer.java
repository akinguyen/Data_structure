// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    private int first;
    private int last;
    private T[] rb;
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    public void enqueue(T x) {
        if (isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last += 1;
        if (last == capacity()){
            last = 0;
        }
        fillCount += 1;

    }

    public T dequeue() {
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }

        T store = rb[first];
        rb[first] = null;
        first += 1;
        if (first == capacity()){
            first = 0;
        }
        fillCount -= 1;
        return store;
    }

    public T peek() {
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }


    @Override
    public Iterator<T> iterator() {
        return new RBIterator();
    }

    private class RBIterator implements Iterator<T> {
        private int pointer;
        private int iterated;
        private RBIterator(){
            pointer = first;
            iterated = fillCount();
        }

        @Override
        public boolean hasNext() {
            return iterated > 0;
        }

        @Override
        public T next(){
            T next = rb[pointer];
            pointer += 1;
            iterated -= 1;
            if (pointer == capacity()){
                pointer = 0;
            }
            return next;
        }
    }
}
