import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void TestGetName() {
        String name = "A";
        Product product = new Product(name);
        assertEquals(name, product.getName(), "getName() does not work properly");
    }
}
