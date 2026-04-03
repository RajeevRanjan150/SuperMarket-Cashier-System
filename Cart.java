import java.util.LinkedHashMap;
import java.util.Map;

class Cart {
  private LinkedHashMap<String, Integer[]> items = new LinkedHashMap<String, Integer[]>();
  private int itemcount = 0;

  public void addToCart(String item, int price, int quantity) {
    if (!items.containsKey(item)) {
      itemcount++;
    }
    items.put(item, new Integer[] { quantity, price });
  }

  public void removeFromCart(String item) {
    if (!items.containsKey(item)) {
      System.out.println("No such Item found!");
      return;
    }
    items.remove(item);
    itemcount--;
  }

  public int getNoOfItems() {
    return itemcount;
  }

  public LinkedHashMap<String, Integer[]> getList() {
    return items;
  }

  public double getTotal() {
    double total = 0;
    for (Map.Entry<String, Integer[]> e : items.entrySet()) {
      total += (e.getValue()[0] * e.getValue()[1]);
    }

    return total;
  }
}
