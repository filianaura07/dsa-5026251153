package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            problem1();
            problem2();
            problem3();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        }
    }

    static void problem1() throws FileNotFoundException {
        List<String> playlist = new ArrayList<>();

        Scanner sc = new Scanner(
            new File("src/lw03/prelab/playlist.txt")
        );

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.startsWith("ADD ")) {
                playlist.add(line.substring(4));
            } else if (line.startsWith("INSERT ")) {
                String[] parts = line.split(" ", 3);
                int index = Integer.parseInt(parts[1]);
                playlist.add(index, parts[2]);
            } else if (line.startsWith("REMOVE ")) {
                playlist.remove(line.substring(7));
            }
        }

        sc.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println();
    }

    static void problem2() throws FileNotFoundException {
        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;

        Scanner sc = new Scanner(
            new File("src/lw03/prelab/participants.txt")
        );

        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();

            if (!participants.add(name)) {
                duplicates++;
            }
        }

        sc.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int no = 1;

        for (String name : participants) {
            System.out.println(no + ". " + name);
            no++;
        }

        System.out.println("Duplicate registrations: " + duplicates);
        System.out.println();
    }

    static void problem3() throws FileNotFoundException {
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner sc = new Scanner(
            new File("src/lw03/prelab/inventory.txt")
        );

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");

            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (stock.containsKey(product)) {
                    stock.put(product, stock.get(product) + quantity);
                } else {
                    stock.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (stock.containsKey(product)
                        && stock.get(product) >= quantity) {
                    stock.put(product, stock.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }

        sc.close();

        System.out.println("===== Problem 3 =====");

        for (String product : stock.keySet()) {
            System.out.println(product + ": " + stock.get(product));
        }

        System.out.println("Failed sales: " + failedSales);
    }
}


