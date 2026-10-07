package lw03.unguided;

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
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        }
    }

    static void problem1() throws FileNotFoundException {
        List<String> registrations = new ArrayList<>();

        Scanner sc = new Scanner(
            new File("src/lw03/unguided/registrations.txt")
        );

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.startsWith("ADD ")) {
                registrations.add(line.substring(4));
            } else if (line.startsWith("INSERT ")) {
                String[] parts = line.split(" ", 3);
                int index = Integer.parseInt(parts[1]);
                registrations.add(index, parts[2]);
            } else if (line.startsWith("REMOVE ")) {
               registrations.remove(line.substring(7));
            }
        }

        sc.close();

        System.out.println("Event Check-In Result");

        for (int i = 0; i < registrations.size(); i++) {
            System.out.println((i + 1) + ": " + registrations.get(i));
        }

        System.out.println();
        }

    static void problem2() throws FileNotFoundException {
        Set<String> checkins = new LinkedHashSet<>();
        int duplicates = 0;

        Scanner sc = new Scanner(
            new File("src/lw03/unguided/checkins.txt")
        );

        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();

            if (!checkins.add(name)) {
                duplicates++;
            }
        }

        sc.close();

        System.out.println("===== Final Event Summary =====");
        System.out.println("registered students:");
        System.out.println("successful check-ins:");
        System.out.println("Absent students:");
        int no = 1;

        for (int  nomor : registrations) {
            System.out.println(no + ". " + name);
            no++;
        }

        System.out.println("Duplicate registrations: " + duplicates);
        System.out.println();
    }

        Scanner sc = new Scanner(
            new File("src/lw03/unguided/checkins.txt")
        );

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");

            String  type = parts[0];
            String registered = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (ID.containsKey(registered)) {
                    ID.put(registered, ID.get(registered) + quantity);
                } else {
                    ID.put(registered, quantity);
                }
            } else if (type.equals("SELL")) {
                if (ID.containsKey(registered)
                        && ID.get(registered) >= quantity) {
                    ID.put(registered, ID.get(registered) - quantity);
                } else {
                    rejectedAttempt++;
                }
              for (int registered : ID.keySet()) {
            System.out.println(registered + ": " + ID.get(registered));
            }
        }

        sc.close();

       
       
            
     }
}




