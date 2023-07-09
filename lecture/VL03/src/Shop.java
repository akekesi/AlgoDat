import java.util.LinkedList;
import java.util.PriorityQueue;


public class Shop {

    private String name;
    private String owner;
    private Product[] shelf;
    public PriorityQueue<Customer> queue;

    public Shop(String name, String owner, int size) {
        this.name = name;
        this.owner = owner;
        this.shelf = new Product[size];
        this.queue = new PriorityQueue<Customer>();
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public void delivery(Product product) {
        for (int i = 0; i < this.shelf.length; i++) {
            if (this.shelf[i] == null) {
                this.shelf[i] = product;
                return;
            }
        }
        System.out.println("The product '" + product.getName() + "' could not be delivered.");
    }

    public void delivery(LinkedList<Product> productLinkedListL) {
        for (Product product : productLinkedListL) {
            this.delivery(product);
        }
    }
    public Product buy(String name) {
        for (int i = 0; i < this.shelf.length; i++) {
            if (this.shelf[i] == null) {
                continue;
            }
            else if (this.shelf[i].getName().equals(name)) {
                Product product = this.shelf[i];
                this.shelf[i] = null;
                return product;
            }
        }
        System.out.println("The product '" + name + "' could not be bought.");
        return null;
    }

    public void service() {
        Customer customer = queue.remove(); // remove element with the highest priority
        while (!customer.getList().isEmpty()) {
            String productName = customer.getList().poll();
            Product product = this.buy(productName);
            if (product != null) {
                customer.buy(product);
            }
        }
    }
}
