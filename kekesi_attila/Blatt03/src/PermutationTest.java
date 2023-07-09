import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PermutationTest {
	PermutationVariation p1;
	PermutationVariation p2;
	public int n1;
	public int n2;
	int cases = 1;
	
	void initialize() {
		n1 = 4;
		n2 = 6;
		Cases c = new Cases();
		p1 = c.switchforTesting(cases, n1);
		p2 = c.switchforTesting(cases, n2);
	}
	

	@Test
	void testPermutation() {
		initialize();
		// TODO
		// p1
		assertNotNull(this.p1.original, "p1 is null");
		assertEquals(this.n1, this.p1.original.length, String.format("p1.original does not have a length %d", this.n1));
		for (int i = 0; i < this.p1.original.length; i++) {
			for (int j = i + 1; j < this.p1.original.length; j++) {
				assertNotEquals(this.p1.original[i], this.p1.original[j], String.format("%d occures twice", this.p1.original[i]));
			}
		}
		assertNotNull(this.p1.allDerangements, "p1.allDerangements is null");
		assertEquals(this.p1.allDerangements.getClass().getName(), "java.util.LinkedList", "p1.allDerangements is not LinkedList");
		// https://javahungry.blogspot.com/2021/06/check-if-linkedlist-is-empty-java.html
		assertEquals(this.p1.allDerangements.size(), 0, "p1.allDerangements does not have a length 0");
		assertTrue(this.p1.allDerangements.isEmpty(), "p1.allDerangement is not empty");

		// p2
		assertNotNull(this.p2.original, "p2 is null");
		assertEquals(this.n2, this.p2.original.length, String.format("p2.original does not have a length %d", this.n2));
		for (int i = 0; i < this.p2.original.length; i++) {
			for (int j = i + 1; j < this.p2.original.length; j++) {
				assertNotEquals(this.p2.original[i], this.p2.original[j], String.format("%d occures twice", this.p2.original[i]));
			}
		}
		assertNotNull(this.p2.allDerangements, "p2.allDerangements is null");
		assertEquals(this.p2.allDerangements.getClass().getName(), "java.util.LinkedList", "p2.allDerangements is not LinkedList");
		// https://javahungry.blogspot.com/2021/06/check-if-linkedlist-is-empty-java.html
		assertEquals(this.p2.allDerangements.size(), 0, "p2.allDerangements does not have a length 0");
		assertTrue(this.p2.allDerangements.isEmpty(), "p2.allDerangement is not empty");
	}

	@Test
	void testDerangements() {
		initialize();
		// in case there is something wrong with the constructor
		fixConstructor();
		// TODO
		// p1
		this.p1.derangements();
		// https://www.1728.org/derange.htm
		assertEquals(this.p1.allDerangements.size(), 9, "size of allDerangements is not 9");
		for (int[] derangement : this.p1.allDerangements) {
			for (int i = 0; i < this.p1.original.length; i++) {
				assertNotEquals(this.p1.original[i], derangement[i], String.format("not derangement because of %d in position %d", this.p1.original[i], i));
			}
		}

		// p2
		this.p2.derangements();
		// https://www.1728.org/derange.htm
		assertEquals(this.p2.allDerangements.size(), 265, "size of allDerangements is not 265");
		for (int[] derangement : this.p2.allDerangements) {
			for (int i = 0; i < this.p2.original.length; i++) {
				assertNotEquals(this.p2.original[i], derangement[i], String.format("not derangement because of %d in position %d", this.p2.original[i], i));
			}
		}
	}

	@Test
	void testsameElements() {
		initialize();
		// in case there is something wrong with the constructor
		fixConstructor();
		// TODO
		// p1
		this.p1.derangements();
		// https://javahungry.blogspot.com/2021/06/check-if-linkedlist-is-empty-java.html
		assertNotEquals(this.p1.allDerangements.size(), 0, "p1.allDerangements has a length 0");
		assertFalse(this.p1.allDerangements.isEmpty(), "p1.allDerangement is empty");
		int[] originalSorted1 = this.p1.original.clone();
		Arrays.sort(originalSorted1);
		for (int[] derangement : this.p1.allDerangements) {
			int[] derangementSorted = derangement.clone();
			Arrays.sort(derangementSorted);
			assertArrayEquals(derangementSorted, originalSorted1, "derangement is not permutation");
		}

		// p2
		this.p2.derangements();
		// https://javahungry.blogspot.com/2021/06/check-if-linkedlist-is-empty-java.html
		assertNotEquals(this.p2.allDerangements.size(), 0, "p2.allDerangements has a length 0");
		assertFalse(this.p2.allDerangements.isEmpty(), "p2.allDerangement is empty");
		int[] originalSorted2 = this.p2.original.clone();
		Arrays.sort(originalSorted2);
		for (int[] derangement : this.p2.allDerangements) {
			int[] derangementSorted = derangement.clone();
			Arrays.sort(derangementSorted);
			assertArrayEquals(derangementSorted, originalSorted2, "derangement is not permutation");
		}
	}

	void setCases(int c) {
		this.cases = c;
	}

	public void fixConstructor() {
		// in case there is something wrong with the constructor
		p1.allDerangements = new LinkedList<int[]>();
		for(int i = 0; i < n1; i++)
			p1.original[i] = 2 * i + 1;

		p2.allDerangements = new LinkedList<int[]>();
		for(int i = 0; i < n2; i++)
			p2.original[i] = i + 1;
	}
}
