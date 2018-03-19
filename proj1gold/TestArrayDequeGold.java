import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold
{
   LinkedListDeque<Integer> exp = new LinkedListDeque<>();
   ArrayDeque<Integer> act = new ArrayDeque<>();
   String operation = "";
   boolean check = true;
   
   @Test
   public void AutoGrader(){
       int rdm; int rdn; int rdi;
       while (check){
       rdm = StdRandom.uniform(6);
       rdn = StdRandom.uniform(12);
       if (rdm == 0){
           //Check addFirst
           exp.addFirst(rdn);
           act.addFirst(rdn);
           operation += "addFirst("+rdn+")\n";
           check = exp.get(0).equals(act.get(0));
        }
       else if (rdm == 1){
           //Check addLast
           exp.addLast(rdn);
           act.addLast(rdn);
           if (exp.size() > 0){
             check = exp.get(exp.size()-1).equals(act.get(act.size()-1));
           }
           operation += "addLast("+rdn+")\n";
       }
        else if (rdm == 2){
           //Check get
           if (exp.size() > 1){
             rdi = StdRandom.uniform(exp.size()-1);
             check = (exp.get(rdi).equals(act.get(rdi)));
             operation += "get("+rdi+")\n";
            }
            else if (exp.size() == 1){
             check = (exp.get(0).equals(act.get(0)));
             operation += "get("+0+")\n";
           }
       }
        else if (rdm == 3){
           //Check isEmpty
           if (exp.size() == 0){
              check = (exp.isEmpty() && act.isEmpty());
           }
           operation += "isEmpty()\n";
       }
        else if (rdm == 4){
           //Check removeFirst
           if (exp.size() > 0 && act.size() > 0){
             check =  (exp.removeFirst().equals(act.removeFirst()));
           }
           operation += "removeFirst()\n";
       }
        else if (rdm == 5){
           //Check removeLast
           if (exp.size() > 0 && act.size() > 0){
              check = (exp.removeLast().equals(act.removeLast()));
           }
           operation += "removeLast()\n";
       }
        else if (rdm == 6){
           //Check size
           check = (exp.size() == act.size());
           operation += "size()\n";
       }
      }
      assertEquals(operation,check,true);
    }
}
