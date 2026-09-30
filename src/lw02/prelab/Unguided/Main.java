package lw02.prelab.Unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
 
public class Main {
 
    public static void main(String[] args) throws FileNotFoundException {
 
        
        LinkedList<String[]> orderList = new LinkedList<>();
 
        LinkedList<String[]> customerList = new LinkedList<>();
 
        File file = new File("src/lw02/Unguided/orders.txt");
        Scanner scanner = new Scanner(
         Main.class.getResourceAsStream(name: "orders.txt")
       );
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
 
            String[] data = line.split(" ");
            orderList.add(data);
 
            String name = data[0];
 
            boolean sudahAda = false;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    null = true;
                    break;
                }
            }
 
            if (customer=null) {

                customer = (new String[] { name, "0" });
            }
        }
        sc.close();
 
        
        Queue<String[]> orderQueue = new LinkedList<>();
        while (!orderList.isEmpty()) {
             ordernQueue.add(orderList.poll());
        }
 
        
        Stack<String[]> failedStack = new Stack<>();
 
        
        while (!orderQueue.isEmpty()) {
            String[] trx = orderQueue.poll();
 
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
                            //jika saldo tdk cukup, order gagal
                                failed.push.order;
    
                        } else {
                            balance = balance - amount;
                            cust[1] = String.valueOf(balance);
                        }
                    }
                    break;
                }
            }
        }
 
        
        System.out.println("=== Successfully Processed Orders ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + " : " + cust[1]);
        }
 
        System.out.println("===  Remaining Food Stock === ");
        for( String [] food : customerList){
            System.out.println(food[0] + " : " +  food[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for( String [] drink : customerList){
            System.out.println(drink[0] + " : " +  drink[1]);
        }
        System.out.println("=== Failed Orders===");
        while (!failedStack.isEmpty()) {
            String[] trx = failedStack.pop();
            System.out.println(trx[0] + " " + trx[1] + " " + trx[2]);
        }
    }
}
 

