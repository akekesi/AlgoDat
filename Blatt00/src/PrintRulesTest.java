import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

class PrintRulesTest {

    @Test
    void testPrint() throws FileNotFoundException {
        PrintRules printRules = new PrintRules();
        printRules.print();
    }
}
