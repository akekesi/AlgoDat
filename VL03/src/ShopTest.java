import java.util.Queue;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ShopTest {

    @Test
    void TestGetName() {
        String name = "Otto's Shop";
        String owner = "Otto";
        int size = 3;

        Shop shop = new Shop(name, owner, size);
        assertEquals(name, shop.getName(), "getName() does not work properly");
    }

    @Test
    void TestGetOwner() {
        String name = "Otto's Shop";
        String owner = "Otto";
        int size = 3;

        Shop shop = new Shop(name, owner, size);
        assertEquals(owner, shop.getOwner(), "getOwner() does not work properly");
    }

    @Test
    void TestDelivery() {
        String name = "Otto's Shop";
        String owner = "Otto";
        int size = 3;

        Shop shop = new Shop(name, owner, size);

        Product product1 = new Product("A");
        Product product2 = new Product("B");
        Product product3 = new Product("C");
        Product product4 = new Product("D");
        Product product5 = new Product("E");

        System.out.println("ONLY PRINT, NO ASSERT:");
        shop.delivery(product1);
        shop.delivery(product2);
        shop.delivery(product3);
        shop.delivery(product4);
        shop.delivery(product5);
    }

    @Test
    void TestDeliveryLinkedList() {
        String name = "Otto's Shop";
        String owner = "Otto";
        int size = 3;

        Shop shop = new Shop(name, owner, size);

        Product product1 = new Product("A");
        Product product2 = new Product("B");
        Product product3 = new Product("C");
        Product product4 = new Product("D");
        Product product5 = new Product("E");

        System.out.println("ONLY PRINT, NO ASSERT:");
        LinkedList<Product> productLinkedList = new LinkedList<Product>();
        productLinkedList.addLast(product1);
        productLinkedList.addLast(product2);
        productLinkedList.addLast(product3);
        productLinkedList.addLast(product4);
        productLinkedList.addLast(product5);

        shop.delivery(productLinkedList);
    }

    @Test
    void TestBuy() {
        String name = "Otto's Shop";
        String owner = "Otto";
        int size = 3;

        Shop shop = new Shop(name, owner, size);

        Product product1 = new Product("A");
        Product product2 = new Product("B");
        Product product3 = new Product("C");
        Product product4 = new Product("D");

        System.out.println("ONLY PRINT, NO ASSERT:");
        shop.delivery(product1);
        shop.delivery(product2);
        shop.delivery(product3);
        Product product6 = shop.buy("B");
        Product product7 = shop.buy("B");
        Product product8 = shop.buy("D");
        shop.delivery(product4);
        Product product9 = shop.buy("D");
    }

    @Test
    void TestService() {
        String name1 = "Otto";
        String name2 = "Ede";
        String name3 = "Aladar";
        int age1 = 44;
        int age2 = 33;
        int age3 = 77;
        Queue<String> list1 = new LinkedList<String>();
        Queue<String> list2 = new LinkedList<String>();
        Queue<String> list3 = new LinkedList<String>();
        list1.add("A");
        list2.add("X");
        list2.add("B");
        list2.add("C");
        list3.add("A");
        Customer customer1 = new Customer(name1, age1, list1);
        Customer customer2 = new Customer(name2, age2, list2);
        Customer customer3 = new Customer(name3, age3, list3);

        String name = "Otto's Shop";
        String owner = "Otto";
        int size = 3;
        Shop shop = new Shop(name, owner, size);
        shop.queue.add(customer1);
        shop.queue.add(customer2);
        shop.queue.add(customer3);

        Product product1 = new Product("A");
        Product product2 = new Product("B");
        Product product3 = new Product("C");
        Product product4 = new Product("D");

        System.out.println("ONLY PRINT, NO ASSERT:");
        shop.delivery(product1);
        shop.delivery(product2);
        shop.delivery(product3);
        shop.delivery(product4);

        System.out.println("before shop.service():");
        System.out.println("shop.queue: " + shop.queue);
        System.out.println("customer2:\n" + customer2);

        shop.service();
        System.out.println("after first shop.service():");
        System.out.println("shop.queue: " + shop.queue);
        System.out.println("customer2:\n" + customer2);

        shop.service();
        System.out.println("after second shop.service():");
        System.out.println("shop.queue: " + shop.queue);
        System.out.println("customer2:\n" + customer1);
    }
}
