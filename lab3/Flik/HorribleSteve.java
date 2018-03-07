<<<<<<< HEAD
package Flik;
/**  
 *  Steve is wrong because the Integer can only reach the maximum number of 128
 */
public class HorribleSteve {
    public static void main(String [] args) {
        Integer i = 0;
        for (Integer j = 0; i < 128; j++,i++) {
=======
public class HorribleSteve {
    public static void main(String [] args) {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
>>>>>>> fa1f2c324bdce43c52a611c56669665d0fbd785a
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
