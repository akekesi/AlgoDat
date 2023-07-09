import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class GenomicsTest {
	String strang;
	String[] dictionary;
	long answer;

	public void initialize() {
		this.strang = "CAGTCCAGTCAGTC";
		this.dictionary = new String[]{
				"AGT",
				"CA",
				"CAG",
				"GTC",
				"TC",
				"TCA",
				"TCC"
		};
		this.answer = 4;
	}

	@Test
	void testOptBottomUp() {
		initialize();
		assertEquals(this.answer, Genomics.optBottomUp(strang, dictionary), "optBottomUp() does not work properly");
	}

	@Test
	void testOptTopDown() {
		fail("Not implemented yet");
	}
}
