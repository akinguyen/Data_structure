
package Flik;
/**  
 *  Steve is wrong because the Integer can only reach the maximum number of 128
 */
public class HorribleSteve {
    public static void main(String [] args) {
        Integer i = 0;
        for (Integer j = 0; i < 128; j++,i++) {

            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
