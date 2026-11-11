package lw04.unguided;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
       Scanner input = new Scanner(new File("src/lw04/prelab/unguided/parcels.txt"));

        int n = input.nextInt();
        int[] parcels = new int[n];

        for (int i = 0; i < n; i++) {
            parcels[i] = input.nextInt();
        }

        int m = input.nextInt();
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = input.nextInt();
        }

        input.close();

        int[] afterTwoPasses = new int[n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (parcels[j] < parcels[j + 1]) {
                    int temp = parcels[j];
                    parcels[j] = parcels[j + 1];
                    parcels[j + 1] = temp;
                }
            }

            if (i == 1) {
                for (int j = 0; j < n; j++) {
                    afterTwoPasses[j] = parcels[j];
                }
            }
        }

        System.out.println("==== After 2 Passes ====");
        for (int i = 0; i < n; i++) {
            System.out.print(afterTwoPasses[i] + (i < n - 1 ? " " : ""));
        }

        System.out.println();
        System.out.println("==== Fully Sorted ====");

        for (int i = 0; i < n; i++) {
            System.out.print(parcels[i] + (i < n - 1 ? " " : ""));
        }

        System.out.println();
        System.out.println("==== Search Results ====");

        for (int i = 0; i < m; i++) {
            int target = targets[i];
            int low = 0;
            int high = n - 1;
            int steps = 0;
            int index = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                steps++;

                if (parcels[mid] == target) {
                    index = mid;
                    break;
                } else if (parcels[mid] > target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            if (index != -1) {
                System.out.println(target + ": FOUND at index " + index
                        + " (steps: " + steps + ")");
            } else {
                System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }
}
