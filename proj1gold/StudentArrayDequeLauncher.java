/** If you project is set up properly, this file should execute. 
* One thing you might consider is to try printing out the sequence of
* operations */
public class StudentArrayDequeLauncher {
    public static void main(String[] args) {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        test.addLast(1);
        sad1.addLast(1);
        sad1.printDeque();
        test.printDeque();
        System.out.println(test.get(test.size()-1).equals(sad1.get(sad1.size()-1)));
    }
} 
