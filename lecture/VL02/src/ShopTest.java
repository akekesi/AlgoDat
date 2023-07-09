import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    @Test
    void TestGetName() {
        String name = "Otto's Shop";
        String besitzer = "Otto";
        int size = 3;

        Shop shop = new Shop(name, besitzer, size);
        assertEquals(name, shop.getName(), "getName() does not work properly");
    }

    @Test
    void TestGetBesitzer() {
        String name = "Otto's Shop";
        String besitzer = "Otto";
        int size = 3;

        Shop shop = new Shop(name, besitzer, size);
        assertEquals(besitzer, shop.getBesitzer(), "getBesitzer() does not work properly");
    }

    @Test
    void TestLieferung() {
        String name = "Otto's Shop";
        String besitzer = "Otto";
        int size = 3;

        Shop shop = new Shop(name, besitzer, size);

        Product product1 = new Product("A");
        Product product2 = new Product("B");
        Product product3 = new Product("C");
        Product product4 = new Product("D");
        Product product5 = new Product("E");

        System.out.println("ONLY PRINT, NO ASSERT:");
        shop.lieferung(product1);
        shop.lieferung(product2);
        shop.lieferung(product3);
        shop.lieferung(product4);
        shop.lieferung(product5);
    }

    @Test
    void TestLieferungLinkedList() {
        String name = "Otto's Shop";
        String besitzer = "Otto";
        int size = 3;

        Shop shop = new Shop(name, besitzer, size);

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

        shop.lieferung(productLinkedList);
    }

    @Test
    void TestKauf() {
        String name = "Otto's Shop";
        String besitzer = "Otto";
        int size = 3;

        Shop shop = new Shop(name, besitzer, size);

        Product product1 = new Product("A");
        Product product2 = new Product("B");
        Product product3 = new Product("C");
        Product product4 = new Product("D");

        System.out.println("ONLY PRINT, NO ASSERT:");
        shop.lieferung(product1);
        shop.lieferung(product2);
        shop.lieferung(product3);
        Product product6 = shop.kauf("B");
        Product product7 = shop.kauf("B");
        Product product8 = shop.kauf("D");
        shop.lieferung(product4);
        Product product9 = shop.kauf("D");
    }
}
