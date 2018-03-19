
package DebugPractice;

import edu.princeton.cs.introcs.In;

/**
 * Created by jug on 1/22/18.
 */
public class DebugExercise3 {
    public static int countTurnips(In in) {
        int totalTurnips = 0;
        while (!in.isEmpty()) {
            String vendor = in.readString();
            String foodType = in.readString();
            double cost = in.readDouble();
            int numAvailable = in.readInt();
            if (foodType.equals("turnip")) {
                int newTotal = totalTurnips + numAvailable;
                totalTurnips = newTotal;
            }
            in.readLine();
        }
        return totalTurnips;
    }

    public static void main(String[] args) {
        In in = new In("C:\\Users\\Nqk\\61B\\Data_structure\\lab2\\DebugPractice\\foods.csv");
        System.out.println(countTurnips(in));
        }
        }
