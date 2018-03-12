public class LinkedListDeque<T>{
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
    
    public void addFirst(T item){
        size += 1;
        sendFront.next = new DoubleNode(sendFront,item,sendFront.next);
        sendFront.next.next.prev = sendFront.next;
    }
    
    public void addLast(T item){
        size += 1;
        sendBack.prev = new DoubleNode(sendBack.prev,item,sendBack);
        sendBack.prev.prev.next = sendBack.prev;
    }
    
    
    public boolean isEmpty(){
        return !(size > 0);
    }
    
    public int size(){
        return size;
    }
    
    public void printDeque(){
        System.out.print("{ ");
        for (int i = 0; i < size;i++){
            System.out.print(get(i) + " ");
        }
        System.out.println("}");
    }
    
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
    
     public static void main (String[] args){
        LinkedListDeque<Integer> a = new LinkedListDeque<>(3);
        a.printDeque();
    }
}