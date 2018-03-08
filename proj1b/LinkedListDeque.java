public class LinkedListDeque<T> implements Deque<T>{
    private DoubleNode sendFront;
    private int size;
    private DoubleNode sendBack;
    
    /** Double Node class */
    public class DoubleNode{
        public DoubleNode prev;
        public DoubleNode next;
        public T item;
        
        public DoubleNode(DoubleNode prev, T item, DoubleNode next){
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
    
    
    
    public LinkedListDeque(T item){
        size = 1;
        sendFront = new DoubleNode(null,null,null);
        sendBack = new DoubleNode(null,null,null);
        sendFront.next = sendBack.prev = new DoubleNode(sendFront,item,sendBack);
    }
    
    public LinkedListDeque(){
        size = 0;
        sendFront = new DoubleNode(null,null,null);
        sendBack = new DoubleNode(null,null,null);
        sendFront.next = sendBack;
        sendBack.prev = sendFront;
        
    }
    
    @Override
    public void addFirst(T item){
        size += 1;
        sendFront.next = new DoubleNode(sendFront,item,sendFront.next);
        sendFront.next.next.prev = sendFront.next;
    }
    
    @Override
    public void addLast(T item){
        size += 1;
        sendBack.prev = new DoubleNode(sendBack.prev,item,sendBack);
        sendBack.prev.prev.next = sendBack.prev;
    }
    
    @Override
    public boolean isEmpty(){
        return !(size > 0);
    }
    
    @Override
    public int size(){
        return size;
    }
    
    @Override
    public void printDeque(){
        System.out.print("{ ");
        for (int i = 0; i < size;i++){
            System.out.print(get(i) + " ");
        }
        System.out.println("}");
    }
    
    @Override
    public T removeFirst(){
        if (sendFront.next != sendBack){
        size -= 1;
        T first = sendFront.next.item;
        sendFront.next = sendFront.next.next;
        sendFront.next.prev = sendFront;
        return first;
       }
       return null;
    }
    
    @Override
    public T removeLast(){
        if (sendBack.prev != sendFront){
          size -= 1;
          T last = sendBack.prev.item;
          sendBack.prev = sendBack.prev.prev;
          sendBack.prev.next = sendBack;
          return last;
        }
        return null;
    }
    
    @Override
    public T get(int index){
       DoubleNode a = sendFront;
       int i = 0;
        while (a.next != null){
           a = a.next;
            if ( i == index){
               return a.item;
           }
           i++;
       }
       return null;
    }
}