package structures;

public interface BinarySearchTreeI<K extends Comparable<K>, V> {

	public void insert(K key, V value);
	public V search(K key);
	public NodeInterface<K,V> delete(K key);
	public String inOrder(boolean parameter);
	public String preOrder(boolean parameter);
	public NodeInterface<K,V> getLess();
	public NodeInterface<K,V> getHigher();
	public int getSize();
}
