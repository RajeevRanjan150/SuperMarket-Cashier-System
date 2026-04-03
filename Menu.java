import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;

class Menu {
  public void menu() {
    System.out.println("======= SuperMarket Cashier System =======\n");
    Scanner sc = new Scanner(System.in);
    LinkedHashMap<Customer, Cart> cart = new LinkedHashMap<Customer, Cart>();
    Helper hp = new Helper();
    while (true) {
      System.out.print("\nEnter Customer name: ");
      String name = sc.nextLine();
      int age = 0;
      boolean rightAge = false;
      while (!rightAge) {
        try {
          System.out.print("Enter Customer's age: ");
          age = sc.nextInt();
          rightAge = true;
        } catch (InputMismatchException e) {
          System.out.println("\u001B[31m" + "Invalid Input!" + "\u001B[0m");
        }
        sc.nextLine();
      }
      System.out.print("Enter Customer's Aadhar Number: ");
      String AadharNumber = sc.nextLine();
      System.out.print("Enter Customer's Contact Number: ");
      String contact = sc.nextLine();

      Customer customer = new Customer(name, age, AadharNumber, contact);
      cart.putIfAbsent(customer, new Cart());

      boolean currCustomer = true;
      while (currCustomer) {
        int choice = 0;
        boolean rightChoice = false;
        while (!rightChoice) {
          try {
            System.out.println("\n1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. View item count");
            System.out.println("4. Show Receipt 🧾 ");
            System.out.println("5. Change Customer");
            System.out.println("6. Show History");
            System.out.println("0. Exit");
            System.out.print("\nChoose an Option: ");
            choice = sc.nextInt();
            rightChoice = true;
          } catch (InputMismatchException e) {
            System.out.println("\u001B[31m" + "Please Enter a Valid Number!" + "\u001B[0m");
          }
          sc.nextLine();
        }

        switch (choice) {
          case 1:
            hp.addItem(customer, cart, sc);
            break;
          case 2:
            hp.removeItem(customer, cart, sc);
            break;
          case 3:
            hp.viewItemCount(customer, cart);
            break;
          case 4:
            hp.showReceipt(customer, cart);
            break;
          case 5:
            currCustomer = false;
            break;
          case 6:
            hp.showHistory(cart);
            break;
          case 0:
            hp.exit();
            sc.close();
            return;
          default:
            System.out.println("\u001B[31m" + "Please Enter a Valid Option!" + "\u001B[0m");
        }
      }
    }
  }
}
