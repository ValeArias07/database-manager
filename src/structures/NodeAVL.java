package structures;

public class NodeAVL <K extends Comparable<K>,V> extends Node<K,V> implements NodeInterface<K,V>{
	
	private int height;

	public NodeAVL(K k, V v, NodeInterface<K, V> f) {
		super(k, v, f);
		height = 1;
	}

	public NodeAVL(K k, V v) {
		super(k, v);
		height = 1;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public void setKey(K k) {
		key = k;
	}
}
