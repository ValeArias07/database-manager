package structures;

public class Node<K extends Comparable<K>,V> implements NodeInterface<K, V>{

	protected NodeInterface<K,V> father;
	protected NodeInterface<K,V> left;
	protected NodeInterface<K,V> right;
	protected K key;
	protected V value;
	protected int numberOfCopies;
	
	public Node(K k, V v, NodeInterface<K,V>f, NodeInterface<K,V>l, NodeInterface<K,V>r) {
		key = k;
		value = v;
		father = f;
		left = l;
		right = r;
		numberOfCopies = 1;
	}
	
	public Node(K k, V v, NodeInterface<K,V>f) {
		key = k;
		value = v;
		father = f;
		left = null;
		right = null;
		numberOfCopies = 1;
	}
	
	public Node(K k, V v) {
		key = k;
		value = v;
		father = null;
		left = null;
		right = null;
		numberOfCopies = 1;
	}
	
	@Override
	public void setFather(NodeInterface<K, V> f) {
		father = f;
	}

	@Override
	public void setLeft(NodeInterface<K, V> l) {
		left = l;
	}

	@Override
	public void setRight(NodeInterface<K, V> r) {
		right = r;
	}

	@Override
	public NodeInterface<K, V> getFather() {
		return father;
	}

	@Override
	public NodeInterface<K, V> getLeft() {
		return left;
	}

	@Override
	public NodeInterface<K, V> getRight() {
		return right;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	@Override
	public int childrensAmount() {
		return ((left!=null)&&(right!=null))?2:((left!=null)||(right!=null))?1:0;
	}
	
	@Override
	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	@Override
	public void increaseNumberOfCopies() {
		numberOfCopies++;
	}

	@Override
	public void decreaseNumberOfCopies() {
		numberOfCopies--;
	}
	
}
