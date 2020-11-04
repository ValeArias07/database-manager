package structures;

public interface NodeInterface<K extends Comparable<K>, V> {
	
	public void setFather(NodeInterface<K,V> f);
	public void setLeft(NodeInterface<K,V> l);
	public void setRight(NodeInterface<K,V> r);
	public NodeInterface<K,V> getFather();
	public NodeInterface<K,V> getLeft();
	public NodeInterface<K,V> getRight();
	public K getKey();
	public V getValue();
	public int childrensAmount();
	public int getNumberOfCopies();
	public void increaseNumberOfCopies();
	public void decreaseNumberOfCopies();
}
