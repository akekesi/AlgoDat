import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CustomerTest {

    @Test
    void TestGetName() {
        String name = "Otto";
        int age = 44;
        Queue<String> list = new LinkedList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        Customer customer = new Customer(name, age, list);
        assertEquals(name, customer.getName(), "getName() does not work properly");
    }

    @Test
    void TestGetAge() {
        String name = "Otto";
        int age = 44;
        Queue<String> list = new LinkedList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        Customer customer = new Customer(name, age, list);
        assertEquals(age, customer.getAge(), "getAge() does not work properly");
    }

    @Test
    void TestGetList() {
        String name = "Otto";
        int age = 44;
        Queue<String> list = new LinkedList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        Customer customer = new Customer(name, age, list);
        assertArrayEquals(list.toArray(), customer.getList().toArray(), "getList() does not work properly");
        list.add("D");
        assertFalse(Arrays.equals(list.toArray(), customer.getList().toArray()), "getList() does not work properly");
    }

    @Test
    void TestGetBag() {
        Product product_a = new Product("A");
        Product product_b = new Product("B");
        Product product_c = new Product("C");
        String name = "Otto";
        int age = 44;
        Queue<String> list = new LinkedList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        Customer customer = new Customer(name, age, list);
        System.out.println("ONLY PRINT, NO ASSERT:");
        System.out.println(customer.toString());
        System.out.println(customer.getBag());
        customer.buy(product_a);
        System.out.println(customer.toString());
        System.out.println(customer.getBag());
        customer.buy(product_b);
        customer.buy(product_c);
        System.out.println(customer.toString());
        System.out.println(customer.getBag());
    }

    @Test
    void TestBuy() {
        Product product_a = new Product("A");
        Product product_b = new Product("B");
        Product product_c = new Product("C");
        String name = "Otto";
        int age = 44;
        Queue<String> list = new LinkedList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        Customer customer = new Customer(name, age, list);
        System.out.println("ONLY PRINT, NO ASSERT:");
        System.out.println(customer.toString());
        customer.buy(product_a);
        System.out.println(customer.toString());
        customer.buy(product_b);
        customer.buy(product_c);
        System.out.println(customer.toString());
    }

    @Test
    void TestToString() {
        Product product_a = new Product("A");
        Product product_b = new Product("B");
        Product product_c = new Product("C");
        String name = "Otto";
        int age = 44;
        Queue<String> list = new LinkedList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        Customer customer = new Customer(name, age, list);
        System.out.println(customer.toString());
        customer.buy(product_a);
        System.out.println(customer.toString());
        customer.buy(product_b);
        customer.buy(product_c);
        System.out.println(customer.toString());
    }

    @Test
    void TestCompareTo() {
        String name1 = "Otto";
        String name2 = "Ede";
        int age1 = 44;
        int age2 = 77;
        Queue<String> list1 = new LinkedList<String>();
        Queue<String> list2 = new LinkedList<String>();
        list1.add("A");
        list2.add("A");
        Customer customer1 = new Customer(name1, age1, list1);
        Customer customer2 = new Customer(name2, age2, list2);
        assertEquals(-1, customer1.compareTo(customer2), "compareTo() does not work properly");
        assertEquals(1, customer2.compareTo(customer1), "compareTo() does not work properly");
        assertEquals(0, customer1.compareTo(customer1), "compareTo() does not work properly");
    }
}
