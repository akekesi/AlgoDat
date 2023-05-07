import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

class PrintRulesTest {

    @Test
    void testPrint() throws FileNotFoundException {
        System.out.println("ONLY PRINT, NO ASSERT:");
        PrintRules printRules = new PrintRules();
        printRules.print();
    }
}
