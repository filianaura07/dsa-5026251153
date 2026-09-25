package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
 
public class Main {
 
    public static void main(String[] args) throws FileNotFoundException {
 
        
        LinkedList<String[]> transactionList = new LinkedList<>();
 
        LinkedList<String[]> customerList = new LinkedList<>();
 
        File file = new File("src/lw02/prelab/transactions.txt");
        Scanner sc = new Scanner(file);
 
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
 
            String[] data = line.split(" ");
            transactionList.add(data);
 
            String name = data[0];
 
            boolean sudahAda = false;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    sudahAda = true;
                    break;
                }
            }
 
            if (!sudahAda) {
                customerList.add(new String[] { name, "0" });
            }
        }
        sc.close();
 
        
        Queue<String[]> transactionQueue = new LinkedList<>();
        while (!transactionList.isEmpty()) {
            transactionQueue.add(transactionList.poll());
        }
 
        
        Stack<String[]> failedStack = new Stack<>();
 
        
        while (!transactionQueue.isEmpty()) {
            String[] trx = transactionQueue.poll();
 
            String name = trx[0];
            String type = trx[1];
            int amount = Integer.parseInt(trx[2]);
 
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    int balance = Integer.parseInt(cust[1]);
 
                    if (type.equals("DEPOSIT")) {
                        balance = balance + amount;
                        cust[1] = String.valueOf(balance);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > balance) {
                            // saldo gak cukup, transaksi gagal
                            failedStack.push(trx);
                        } else {
                            balance = balance - amount;
                            cust[1] = String.valueOf(balance);
                        }
                    }
                    break;
                }
            }
        }
 
        // 6. tampilin hasil akhir
        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + " : " + cust[1]);
        }
 
        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] trx = failedStack.pop();
            System.out.println(trx[0] + " " + trx[1] + " " + trx[2]);
        }
    }
}
 