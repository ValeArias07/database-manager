package structures;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BinarySearchTree <K extends Comparable<K>, V> implements BinarySearchTreeI<K, V>, Serializable{

	protected NodeInterface<K, V> root;
	protected int size;
	
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	public BinarySearchTree(NodeInterface<K,V> r) {
		root = r;
		size = 1;
	}
	
	@Override
	public void insert(K key, V value) {
		insert(new Node<K,V>(key, value));
	}
	
	protected void insert(NodeInterface<K,V> n) {
		if(root==null) {
			root = n;
		}else {
			NodeInterface<K,V> aux = root;
			
			boolean add = false;
			while(!add) {
				if(n.getKey().compareTo(aux.getKey())<0) {
					if(aux.getLeft()!=null) {
						aux = aux.getLeft();
					}else {
						n.setFather(aux);
						aux.setLeft(n);
						add = true;
					}		
				}else if(n.getKey().compareTo(aux.getKey())>0){
					if(aux.getRight()!=null) {
						aux = aux.getRight();
					}else {
						n.setFather(aux);
						aux.setRight(n);
						add = true;
					}
				}else {
					aux.increaseNumberOfCopies();
					add = true;
				}
			}
		}
		
		size++;
	}

	@Override
	public V search(K key) {
		if(root!=null) {
			if(root.getKey().equals(key))
				return root.getValue();
			
			NodeInterface<K, V> aux = (key.compareTo(root.getKey())<0)?root.getLeft():root.getRight();
			while(aux!=null) {
				if(key.compareTo(aux.getKey())<0) {
					if(aux.getLeft()!=null) {
						aux = aux.getLeft();
					}else {
						return null;
					}
				}else if(key.compareTo(aux.getKey())>0) {
					if(aux.getRight()!=null) {
						aux = aux.getRight();
					}else {
						return null;
					}
				}else
					return aux.getValue();
			}	
			
		}
		return null;
	}

	private NodeInterface<K,V> searchNode(K key) {
		if(root!=null) {
			if(root.getKey()==key)
				return root;
			
			NodeInterface<K, V> aux = (key.compareTo(root.getKey())<0)?root.getLeft():root.getRight();
			while(aux!=null) {
				if(key.compareTo(aux.getKey())<0) {
					if(aux.getLeft()!=null) {
						aux = aux.getLeft();
					}else {
						return null;
					}
				}else if(key.compareTo(aux.getKey())>0) {
					if(aux.getRight()!=null) {
						aux = aux.getRight();
					}else {
						return null;
					}
				}else
					return aux;
			}	
		}
		
		return null;
	}

	@Override
	public NodeInterface<K,V> delete(K key) {
		NodeInterface<K,V> nodeToDelete = searchNode(key);
		if(nodeToDelete==null)
			return null;
		
		if(nodeToDelete.getNumberOfCopies()>1) {
			nodeToDelete.decreaseNumberOfCopies();
			size--;
			return nodeToDelete;
		}
		
		NodeInterface<K,V> aux = nodeToDelete.getFather();
		
		if(nodeToDelete.childrensAmount() == 0) {
			if(aux==null)
				root = null;
			else if(nodeToDelete.getKey().compareTo(aux.getKey())>0)
				aux.setRight((null));
			else
				aux.setLeft((null));
		}else if(nodeToDelete.childrensAmount() == 1) {
			NodeInterface<K,V> aux2 = (nodeToDelete.getLeft()!=null)?nodeToDelete.getLeft():nodeToDelete.getRight(); 
			aux2.setFather(aux);
			if(aux==null)
				root = aux2;
			else if(nodeToDelete.getKey().compareTo(aux.getKey())>0)
				aux.setRight(aux2);
			else 
				aux.setLeft(aux2);

		}else if(nodeToDelete.childrensAmount() == 2) {
			K s = getSussesor(nodeToDelete.getRight());
			NodeInterface<K,V> delete = delete(s);
			size++;
			NodeInterface<K,V> replace = new Node<K,V>(delete.getKey(), delete.getValue(),
					aux, nodeToDelete.getLeft(),nodeToDelete.getRight());
			
			nodeToDelete.getLeft().setFather(replace);
			nodeToDelete.getRight().setFather(replace);
			if(aux!=null) {
				if(nodeToDelete.getKey().compareTo(aux.getKey())>0)
					aux.setRight(replace);
				else 
					aux.setLeft(replace);
			}else
				root = replace;
		}
		
		size--;
		return nodeToDelete;
	}

	private K getSussesor(NodeInterface<K,V> aux){
		if(aux==null)
			return null;
		 
		while(aux.getLeft()!=null) {
			aux = aux.getLeft();
		}
		return aux.getKey();
	}
	
	@Override //true to key, false to value.
	public String inOrder(boolean parameter) {
		return (parameter)?inOrderK("", root).trim():inOrderV("", root).trim();
	}
	
	private String inOrderK(String n, NodeInterface<K,V> aux) {
		if(aux==null)
			return n;
		else {
			n = inOrderK(n,aux.getLeft());
			n += (aux.getNumberOfCopies()>1)?aux.getKey() + "(" + aux.getNumberOfCopies() + ") ":aux.getKey() +" ";
			n = inOrderK(n,aux.getRight());
			return n;
		}
	}
	
	private String inOrderV(String n, NodeInterface<K,V> aux) {
		if(aux==null)
			return n;
		else {
			n = inOrderV(n,aux.getLeft());
			n += (aux.getNumberOfCopies()>1)?aux.getValue() + "(" + aux.getNumberOfCopies() + ") ":aux.getValue() +" ";
			n = inOrderV(n,aux.getRight());
			return n;
		}
	}

	@Override //true to key, false to value.
	public String preOrder(boolean parameter) {
		return (parameter)?preOrderK("", root).trim():preOrderV("", root).trim();
	}
	
	private String preOrderK(String n, NodeInterface<K,V> aux) {
		if (aux != null) { 
            n += (aux.getKey() + " "); 
            n = preOrderK(n, aux.getLeft()); 
            n = preOrderK(n, aux.getRight()); 
            return n;
        }else
        	return n;
	}
	
	private String preOrderV(String n, NodeInterface<K,V> aux) {
		if (aux != null) { 
            n += (aux.getValue() + " "); 
            n = preOrderV(n, aux.getLeft()); 
            n = preOrderV(n, aux.getRight()); 
            return n;
        }else
        	return n;
	}
	
	@Override
	public NodeInterface<K,V> getLess() {
		NodeInterface<K,V> aux = root;
		if(aux==null)
			return null;
		 
		while(aux.getLeft()!=null) {
			aux = aux.getLeft();
		}
		
		return aux;
	}
	
	public NodeInterface<K,V> getLess(NodeInterface<K,V> n) {
		NodeInterface<K,V> aux = n;
		if(aux==null)
			return null;
		 
		while(aux.getLeft()!=null) {
			aux = aux.getLeft();
		}
		
		return aux;
	}

	@Override
	public NodeInterface<K,V> getHigher() {
		NodeInterface<K,V> aux = root;
		if(aux==null)
			return null;
		 
		while(aux.getRight()!=null) {
			aux = aux.getRight();
		}
		
		return aux;
	}
	
	public NodeInterface<K,V> getHigher(NodeInterface<K,V> n) {
		NodeInterface<K,V> aux = n;
		if(aux==null)
			return null;
		 
		while(aux.getRight()!=null) {
			aux = aux.getRight();
		}
		
		return aux;
	}
	
	@Override
	public int getSize() {
		return size;
	}
	
	public NodeInterface<K, V> getRoot(){
		return root;
	}
}
