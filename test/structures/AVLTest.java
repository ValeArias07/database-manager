package structures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AVLTest {

	private AVL<Integer, String> avl;
	
	void setup1() {
		avl = new AVL<Integer, String>();
		avl.insert(10, "TEN");
		avl.insert(20, "TWENTY");
		avl.insert(30, "THIRTY");
		avl.insert(40, "FOURTY");
		avl.insert(50, "FIVETY");
		avl.insert(25, "TWENTY-FIVE");
	}
	
	void setup2() {
		avl = new AVL<Integer, String>();
		avl.insert(9, "NINE");
		avl.insert(5, "FIVE");
		avl.insert(10, "TEN");
		avl.insert(0, "ZERO");
		avl.insert(6, "SIX");
		avl.insert(11, "ELEVEN");
		avl.insert(-1, "MINUS-ONE");
		avl.insert(1, "ONE");
		avl.insert(2, "TWO");
	}
	
	void setup3() {
		avl = new AVL<Integer, String>();
		avl.insert(5, "FIVE");
		avl.insert(2, "TWO");
		avl.insert(10, "TEN");
		avl.insert(1, "ONE");
		avl.insert(4, "FOUR");
		avl.insert(6, "SIX");
		avl.insert(11, "ELEVEN");
		avl.insert(9, "NINE");
	}
	
	void setup4() {
		avl = new AVL<Integer, String>();
		avl.insert(5, "FIVE");
	}
	
	@Test
	void insertTest() {
		setup1();
		String s = avl.preOrder(true);
		assertEquals("30 20 10 25 40 50", s);
		s = avl.preOrder(false);
		assertEquals("THIRTY TWENTY TEN TWENTY-FIVE FOURTY FIVETY", s);
		
		setup2();
		s = avl.preOrder(true);
		assertEquals("9 1 0 -1 5 2 6 10 11", s);
	}
	
	@Test
	void deleteTest() {
		setup2();
		avl.delete(10);
		String s = avl.preOrder(true);
		assertEquals("1 0 -1 9 5 2 6 11", s);
	}
	
	@Test
	void searchTest() {
		setup3();
		assertEquals("FIVE", avl.search(5));
		assertEquals("TWO", avl.search(2));
		assertEquals("FOUR", avl.search(4));
		assertEquals("ONE", avl.search(1));
		assertEquals("TEN", avl.search(10));
		assertEquals("SIX", avl.search(6));
		assertEquals("NINE", avl.search(9));
		assertEquals("ELEVEN", avl.search(11));
		assertEquals(null, avl.search(0));
		assertEquals(null, avl.search(3));
		
		setup4();
		assertEquals("FIVE", avl.search(5));
	}
}
