
public class ArrayListDequeTest
{
   /**
    * Check isEmpty(), addFirst(), addLast(), removeFirst(), removeLast(),
    *  get(), size();
    */  
   public static void checkEmptyAddRemoveGetSize(){
       ArrayDeque<String> a = new ArrayDeque<>();
       LinkedListDeque<String> exp = new LinkedListDeque<>();
       boolean passed = a.isEmpty();
       boolean checkNull = false;
       boolean checkEqual = true;
       
       a.addFirst("Hello");
       a.addLast("My");
       a.addFirst("Morning");
       a.addLast("Friend");
       a.addFirst("Good");
       a.addLast("Minh");
       a.addLast("!");
       a.addLast("<3");
       //a.addFirst("Miss");
       
       exp.addFirst("Hello");
       exp.addLast("My");
       exp.addFirst("Morning");
       exp.addLast("Friend");
       exp.addFirst("Good");
       exp.addLast("Minh");
       exp.addLast("!");
       exp.addLast("<3");
       //exp.addFirst("Miss");
       
       System.out.println("Expected ");
       exp.printDeque();
       System.out.println("");
       System.out.println("Result ");
       a.printDeque();
       System.out.println("");
       
       System.out.println("Checking Null element: ");
       for (int i = 0;i < a.size();i++){
           if(a.get(i) == null){
               checkNull = true;
               passed = passed && !(checkNull);
               System.out.println("There are null elements in the Deque");
               break;
            }
       }
       printStatus(passed);
        
       if (!checkNull){
         System.out.println("Checking Add and Get: ");
         for (int i = 0; i < a.size(); i++){
             if(!(a.get(i).equals(exp.get(i)))){
                 passed = passed && !(checkEqual);
                 System.out.println("Should get: " + exp.get(i) 
                  + " ,but receives: " + a.get(i));
              }
          }
         printStatus(passed);
         
         a.removeFirst();
         a.removeLast();
         a.removeFirst();
         
         exp.removeFirst();
         exp.removeLast();
         exp.removeFirst();
         
         
         System.out.println("Checking Remove and Get: ");
         for (int i = 0; i < a.size(); i++){
             if(!(a.get(i).equals(exp.get(i)))){
                 passed = passed && !(checkEqual);
                 System.out.println("Should get: " + exp.get(i) 
                  + " ,but receives: " + a.get(i));
              }
          }
         printStatus(passed);
      }

       System.out.println("Check size: ");
       printStatus(passed && (a.size() == exp.size()));
    }
    
   /**
    * Print out the result whether the tested method fails or passes
    */
   public static void printStatus(boolean status){
       if(status){
           System.out.println("Passed the test");
        }
       else
          System.out.println("Failed the test");
       System.out.println("");
    }
    
    public static void main(String[] args){
      checkEmptyAddRemoveGetSize();

   }
   
}
