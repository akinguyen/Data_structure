public class ArrayDeque<T>{
   public T[] items;
   public int size;
   public int nextFirst;
   public int nextLast;
    
   public ArrayDeque(int size, int nextFirst, int nextLast){
       this.items = items;
       this.size = size;
       this.nextFirst = nextFirst;
       this.nextLast = nextLast;
    }
   
    public ArrayDeque(){
        items = new T[8];
        size = 0;
    }
    
   public void addFirst(T item){
        size += 1;
        
    }
    
    public void addLast(T item){
        size += 1;
       
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
        System.out.print("}");
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
        a.addFirst(2);
        a.addLast(4);
        a.addLast(5);
        a.addFirst(1);
        //a.removeFirst();
        //a.removeLast();
        a.printDeque();
   
    }
}