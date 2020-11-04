package structures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	private BinarySearchTree<Integer, String> bst;
	
	void setup1() {
		bst = new BinarySearchTree<Integer, String>();
		bst.insert(5, "FIVE");
		bst.insert(2, "TWO");
		bst.insert(10, "TEN");
		bst.insert(1, "ONE");
		bst.insert(4, "FOUR");
		bst.insert(6, "SIX");
		bst.insert(11, "ELEVEN");
		bst.insert(9, "NINE");
	}
	
	void setup2() {
		bst = new BinarySearchTree<Integer, String>();
		bst.insert(5, "FIVE");
	}
	
	void setup3() {
		bst = new BinarySearchTree<Integer, String>();
		bst.insert(30, "THIRTY");
		bst.insert(20, "TWENTY");
		bst.insert(10, "TEN");
		bst.insert(25, "TWENTY FIVE");
		bst.insert(40, "FOURTY");
		bst.insert(50, "FIVETY");
	}
	
	@Test
	void insertTest() {
		setup1();
		NodeInterface<Integer, String> root = bst.getRoot();
		assertEquals(5, root.getKey());
		root = root.getLeft();
		assertEquals(2, root.getKey());
		root = root.getRight();
		assertEquals(4, root.getKey());
		root = root.getFather().getLeft();
		assertEquals(1, root.getKey());
		root = root.getFather().getFather().getRight();
		assertEquals(10, root.getKey());
		root = root.getLeft();
		assertEquals(6, root.getKey());
		root = root.getRight();
		assertEquals(9, root.getKey());
		root = root.getFather().getFather().getRight();
		assertEquals(11, root.getKey());
		assertEquals(8, bst.getSize());
		setup2();
		assertEquals(5, bst.getRoot().getKey());
		assertEquals(1, bst.getSize());
	}
	
	@Test
	void searchTest() {
		setup1();
		assertEquals("FIVE", bst.search(5));
		assertEquals("TWO", bst.search(2));
		assertEquals("FOUR", bst.search(4));
		assertEquals("ONE", bst.search(1));
		assertEquals("TEN", bst.search(10));
		assertEquals("SIX", bst.search(6));
		assertEquals("NINE", bst.search(9));
		assertEquals("ELEVEN", bst.search(11));
		assertEquals(null, bst.search(0));
		assertEquals(null, bst.search(3));
		
		setup2();
		assertEquals("FIVE", bst.search(5));
	}
	
	@Test
	void deleteTest() {
		setup1();
		bst.delete(5);
		assertEquals(7, bst.getSize());
		NodeInterface<Integer, String> root = bst.getRoot();
		assertEquals(6, root.getKey());
		assertEquals(6, root.getRight().getFather().getKey());
		assertEquals(6, root.getLeft().getFather().getKey());
		root = root.getLeft();
		assertEquals(2, root.getKey());
		root = root.getRight();
		assertEquals(4, root.getKey());
		root = root.getFather().getLeft();
		assertEquals(1, root.getKey());
		root = root.getFather().getFather().getRight();
		assertEquals(10, root.getKey());
		root = root.getLeft();
		assertEquals(9, root.getKey());
		root = root.getFather().getRight();
		assertEquals(11, root.getKey());
		
		setup1();
		bst.delete(6);
		assertEquals(7, bst.getSize());
		root = bst.getRoot();
		assertEquals(5, root.getKey());
		root = root.getLeft();
		assertEquals(2, root.getKey());
		root = root.getRight();
		assertEquals(4, root.getKey());
		root = root.getFather().getLeft();
		assertEquals(1, root.getKey());
		root = root.getFather().getFather().getRight();
		assertEquals(10, root.getKey());
		root = root.getLeft();
		assertEquals(9, root.getKey());
		assertEquals(10, root.getFather().getKey());
		root = root.getFather().getRight();
		assertEquals(11, root.getKey());
		
		setup1();
		bst.delete(9);
		assertEquals(7, bst.getSize());
		root = bst.getRoot();
		assertEquals(5, root.getKey());
		root = root.getLeft();
		assertEquals(2, root.getKey());
		root = root.getRight();
		assertEquals(4, root.getKey());
		root = root.getFather().getLeft();
		assertEquals(1, root.getKey());
		root = root.getFather().getFather().getRight();
		assertEquals(10, root.getKey());
		root = root.getRight();
		assertEquals(11, root.getKey());
		root = root.getFather().getLeft();
		assertEquals(6, root.getKey());
		root = root.getRight();
		assertEquals(null, root);
	}
	
	@Test
	void inOrderTest() {
		setup1();
		String s = bst.inOrder(true);
		assertEquals("1 2 4 5 6 9 10 11", s);
		s = bst.inOrder(false);
		assertEquals("ONE TWO FOUR FIVE SIX NINE TEN ELEVEN", s);
	}
	
	@Test
	void preOrderTest() {
		setup3();
		String s = bst.preOrder(true);
		assertEquals("30 20 10 25 40 50", s);
		//s = bst.inOrder(false);
		//assertEquals("ONE TWO FOUR FIVE SIX NINE TEN ELEVEN", s);
	}
	
	@Test
	void getLessTest() {
		setup1();
		NodeInterface<Integer, String> aux = bst.getLess();
		assertEquals(1, aux.getKey());
		assertEquals("ONE", aux.getValue());
		
		setup2();
		aux = bst.getLess();
		assertEquals(5, aux.getKey());
		assertEquals("FIVE", aux.getValue());
	}
	
	@Test
	void getHigherTest() {
		setup1();
		NodeInterface<Integer, String> aux = bst.getHigher();
		assertEquals(11, aux.getKey());
		assertEquals("ELEVEN", aux.getValue());
		
		setup2();
		aux = bst.getLess();
		assertEquals(5, aux.getKey());
		assertEquals("FIVE", aux.getValue());
	}
}
