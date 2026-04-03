import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Helper {
  public void addItem(Customer customer, LinkedHashMap<Customer, Cart> cart, Scanner sc) {
    while (true) {
      System.out.print("Enter item (0 to exit): ");
      String item = sc.nextLine();
      if (item.equals("0")) {
        return;
      }
      if (item.length() > 50) {
        System.out.println("\u001B[31m" + "Maximum Length for Item's Name Exceeded!!" + "\u001B[0m");
        return;
      }
      boolean rightPrice = false;
      int price = 0;
      while (!rightPrice) {
        try {
          System.out.print("Enter price: ₹");
          price = sc.nextInt();
          rightPrice = true;
        } catch (InputMismatchException e) {
          System.out.println("\u001B[31m" + "Invalid Input!" + "\u001B[0m");
        }
        sc.nextLine();
      }

      boolean rightQuantity = false;
      int quantity = 0;
      while (!rightQuantity) {
        try {
          System.out.print("Enter quantity: ");
          quantity = sc.nextInt();
          rightQuantity = true;
        } catch (InputMismatchException e) {
          System.out.println("\u001B[31m" + "Invalid Input!" + "\u001B[0m");
        }
        sc.nextLine();
      }

      cart.get(customer).addToCart(item, price, quantity);
    }
  }

  public void removeItem(Customer customer, LinkedHashMap<Customer, Cart> cart, Scanner sc) {
    if (cart.get(customer).getNoOfItems() == 0) {
      System.out.println("\nCart for Mr/Mrs. " + customer.getName() + " is Empty!");
      return;
    }

    System.out.print("Enter item to remove: ");
    String item = sc.nextLine();
    cart.get(customer).removeFromCart(item);
  }

  public void viewItemCount(Customer customer, LinkedHashMap<Customer, Cart> cart) {
    System.out.println("Item Count: " + cart.get(customer).getNoOfItems());
  }

  public void showReceipt(Customer customer, LinkedHashMap<Customer, Cart> cart) {

    if (cart.get(customer).getNoOfItems() == 0) {
      System.out.println("\nCart for Mr/Mrs. " + customer.getName() + " is Empty!");
      return;
    }

    LinkedHashMap<String, Integer[]> items = cart.get(customer).getList();

    System.out.println("\n\t\t\t\t\t\t\t=== " + "\u001B[34m" + "Receipt" + "\u001B[0m" + " === ");

    System.out.println(
        "+--------------------------------------------------+--------------+------------------+---------+\n" +
            "|                      Item                        |   Quantity   |  Price per Item  |  Price  |\n" +
            "+--------------------------------------------------+--------------+------------------+---------+");
    for (Map.Entry<String, Integer[]> e : items.entrySet()) {
      System.out.printf("|%-50s|%-14d|₹%-17d|₹%-8d|", e.getKey(), e.getValue()[0], e.getValue()[1],
          (e.getValue()[0] * e.getValue()[1]));
      System.out.print("\n");
    }

    System.out
        .print("+--------------------------------------------------+--------------+------------------+---------+\n");

    System.out.println("Customer's Credentials:");
    System.out.printf("Name:               %-30s\n", customer.getName());
    System.out.printf("Age:                %-30d\n", customer.getAge());
    System.out.printf("Aadhar Number:      %-30s\n", customer.getAadhar());
    System.out.printf("Contact Number:     +91 %-30s\n", customer.getContact());
    System.out.printf("Total:              ₹%.2f\n\n", cart.get(customer).getTotal());
  }

  public void showHistory(LinkedHashMap<Customer, Cart> cart) {
    if (cart.isEmpty()) {
      System.out.println("No Customer Added Yet!");
      return;
    }
    for (Map.Entry<Customer, Cart> e : cart.entrySet()) {
      showReceipt(e.getKey(), cart);
    }
  }

  public void exit() {
    System.out.println("Thank you for using SuperMarket Cashier System!!!");
    System.out.print("Exiting");
    try {
      for (int i = 1; i <= 3; i++) {
        Thread.sleep(500);
        System.out.print(".");
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println();
  }
}
