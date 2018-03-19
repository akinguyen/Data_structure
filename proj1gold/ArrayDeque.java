public class ArrayDeque<Type> {
    /** Check the index that needs to be added to the front of the Deque */
    private int nextFirst;
  
    /** Check the index that needs to be added to the back of the Deque */
    private int nextLast;
    
    /**
     * An array that stores Type element and is a core data structure of this Deque
     */
    private Type [] items;
    
    /** Update the number of elements of the Deque */
    private int size;
    
    /** The factor of the capacity that needs to be increased */
    private static final int factor = 2;
    
    
    /**
     * Create an empty ArrayDeque
     */
   public ArrayDeque()
    {
        // initialise instance variables
        items =  (Type[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }
  
   /**
    * Resize the Deque when RFactor is less then 0.25
    */
   private void resize(){
      Type [] a =  (Type[]) new Object[size+2];
      if (nextFirst > nextLast){
       System.arraycopy(items,0,a,0,nextLast);
       System.arraycopy(items,nextFirst+1,a,a.length-size+nextLast,size-nextLast);
       nextFirst = a.length - size + nextLast-1;
     }
     else {
         System.arraycopy(items,nextFirst+1,a,0,size);
         nextFirst = a.length - 1;
         nextLast = a.length -2;
      }
     items = a;
  }
  
  /**
   * Resize the Deque when size == items.length
   */
   private void resize(int cap){
        Type [] a =  (Type[]) new Object[cap];
        System.arraycopy(items,0,a,0,nextLast);
        System.arraycopy(items,nextLast,a,cap-items.length+nextLast,items.length-nextLast);
        nextFirst += cap-items.length;
        items = a;
  }

  /**
   * Add new item to the front of the Deque
   */
    public void addFirst(Type item){
        size += 1;
        if(items[nextFirst] != null){
            resize(size*factor);
        }
        
        items[nextFirst] = item;
        if (nextFirst == 0){
            nextFirst = items.length-1;
        }
        else{
            nextFirst -= 1;
        }
        
    }
    
  /**
   * Add new item to the back of the Deque
   */
  public void addLast(Type item){
        size += 1;
        if (items[nextLast] != null){
            resize(size*factor);
        }
        
        items[nextLast] = item;
        if (nextLast == items.length-1){
            nextLast = 0;
        }
        else{
            nextLast += 1;
        }
        
    }
    
    
    /**
     *  Check whether the Deque is Empty
     *  @return 
     *  Empty is True/False
     */
    public boolean isEmpty(){
        return !(size > 0);
    }
    
    /**
     *  Return the size or number of elements in the Deque
     *  @return
     *  The size of the Deque
     */
    public int size(){
        return size;
    }
    
    /**
     * Print out all of the element in the Deque 
     */
    public void printDeque(){
        if ((nextFirst >= nextLast) || (size == items.length)){
        System.out.print("{ ");
        for (int i = nextFirst+1 ; i < items.length;i++){
            if (items[i] != null ){
            System.out.print(items[i] +" ");
           }
           else
             System.out.print("");
            }
        for (int j = 0; j < nextLast;j++){
               if (items[j] != null ){
               System.out.print(items[j] +" ");
            }
            else
               System.out.print("");
         }
        System.out.println("}");
      }
      else{
        System.out.print("{ ");
        for (int i = nextFirst+1 ; i < nextLast;i++){
            if (items[i] != null){
              System.out.print(items[i] +" ");
           }
           else 
              System.out.print("");
          }
        System.out.println("}");
      }
     
    }
  
   /**
    *  Remove the front item of the Deque
    *  @return
    *  The first item of the Deque
    */
   public Type removeFirst(){
      
      Type remove;
      if (nextFirst == items.length - 1){
          if (items[0] == null){
            return null;
        }
         remove = items[0];
         nextFirst = 0;
      }
      else{
        if (items[nextFirst+1] == null){
            return null;
        }
        remove = items[nextFirst+1];
        nextFirst += 1;
      }
      size -= 1;
      items[nextFirst] = null;
      if ((double) size/items.length < 0.25 && items.length > 8){
          resize();
        }
        
      if (isEmpty()){
          items = (Type[] ) new Object[8];
          nextFirst = 0;
          nextLast = 1;
       }
      return remove;
   }
   
   /**
    * Remove the back item of the Deque
    * @return
    * The last item of the Deque
    */
   public Type removeLast(){
      Type remove;
      if (nextLast == 0){
         if (items[items.length-1] == null){
            return null;
        }
        
         remove = items[items.length-1];
         nextLast = items.length-1;
      }
      else{
        if (items[nextLast-1] == null){
            return null;
        }
        remove = items[nextLast-1];
        nextLast -= 1;
      }
      size -= 1;
      items[nextLast] = null;
      if ((double) size/items.length < 0.25 && items.length > 8){
          resize();
       }
      if (isEmpty()){
          items = (Type[]) new Object[8];
          nextFirst = 0;
          nextLast = 1;
      }
      return remove;
   }
   
   /** Print the items array */
   public void printItems(){
    System.out.print("{ ");
     for (int i = 0; i < items.length; i++){
       System.out.print(items[i]+ " ");
    }
    System.out.println("}");
    }
   
    /** 
     *  The Helper method that converts the given user's index into a corresponding 
     *  index in the items array
     */
   private int realIndex(int index){
       int nextIndex = nextFirst + index + 1;
       int lastIndex = index - items.length + nextFirst + 1; 
       if (nextFirst >= nextLast || (size == items.length)){
            if (nextFirst < nextIndex && nextIndex < items.length ){
                return nextIndex;
            }
            else if (lastIndex < nextLast){
                return lastIndex;
            }
        }
        else if (nextFirst < nextLast){
           if (nextFirst < nextIndex && nextIndex < nextLast ){
                return nextIndex;
            }
        }
        return -1;
    }
    
   /**
    * Return the element on the given index ( user's index )
    * @return
    * The item at the given index
    */
   public Type get(int index){
       int newIndex = realIndex(index);
       if (newIndex >= 0)
         return items[realIndex(index)];
       return null;
    }
}
