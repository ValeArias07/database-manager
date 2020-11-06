package structures;


public class AVL <K extends Comparable <K>, V> extends BinarySearchTree<K,V> implements BinarySearchTreeI<K,V>{

	public AVL() {
		super();
	}

	public AVL(NodeInterface<K, V> r) {
		super(r);
	}

	private int getHeight(NodeAVL<K,V> n) {
		if (n == null) 
            return 0; 
  
        return n.getHeight(); 
	}
	
	private int getBalance(NodeAVL<K,V> n) {
		 if (n == null) 
			 return 0; 
	  
	     return getHeight((NodeAVL<K,V>)n.getLeft()) - getHeight((NodeAVL<K,V>)n.getRight());
	}
	
	private int max(int a, int b) {
		return (a>b)?a:b;
	}
	
	private NodeAVL<K,V> rightRotate(NodeAVL<K,V> y) { 
		NodeAVL<K,V> x = (NodeAVL<K, V>) y.getLeft();
        NodeAVL<K,V> T2 = (NodeAVL<K, V>) x.getRight();
  
        // Perform rotation 
        x.setRight(y); 
        y.setLeft(T2);  
  
        // Update heights 
        y.setHeight(max(getHeight((NodeAVL<K, V>) y.getLeft()), getHeight((NodeAVL<K, V>) y.getRight())) + 1); 
        x.setHeight(max(getHeight((NodeAVL<K, V>) x.getLeft()), getHeight((NodeAVL<K, V>) x.getRight())) + 1); 
        return x;
    } 
  
    private NodeAVL<K,V> leftRotate(NodeAVL<K,V> x) { 
        NodeAVL<K,V> y = (NodeAVL<K, V>) x.getRight(); 
        NodeAVL<K,V> T2 = (NodeAVL<K, V>) y.getLeft(); 
  
        // Perform rotation 
        y.setLeft(x); 
        x.setRight(T2); 
  
        //  Update heights 
        x.setHeight(max(getHeight((NodeAVL<K, V>) x.getLeft()), getHeight((NodeAVL<K, V>) x.getRight())) + 1); 
        y.setHeight(max(getHeight((NodeAVL<K, V>) y.getLeft()), getHeight((NodeAVL<K, V>) y.getRight())) + 1); 
        return y;
    } 
	
    @Override
	public void insert(K key, V value) {
    	root = insert((NodeAVL<K,V>)root, key, value);
    	size++;
    }
    
	private NodeAVL<K,V> insert(NodeAVL<K,V> n, K key, V value) {
		if(n == null)
			return new NodeAVL<K,V>(key, value);
			
		if (key.compareTo(n.getKey())<0) 
            n.setLeft(insert((NodeAVL<K,V>)n.getLeft(), key, value)); 
        else if (key.compareTo(n.getKey())>0) 
        	n.setRight(insert((NodeAVL<K,V>)n.getRight(), key, value));
        else {
        	n.increaseNumberOfCopies();
        	return n;
        }
		
		((NodeAVL<K,V>)n).setHeight(1 + 
				max(getHeight((NodeAVL<K,V>)n.getLeft()), getHeight((NodeAVL<K,V>)n.getRight()) ) );
		
		int balance = getBalance((NodeAVL<K,V>)n);
		
		// If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && key.compareTo(n.getLeft().getKey())<0) 
            return rightRotate((NodeAVL<K, V>) n); 
  
        // Right Right Case 
        if (balance < -1 && key.compareTo(n.getRight().getKey())>0) 
            return leftRotate((NodeAVL<K, V>) n); 
  
        // Left Right Case 
        if (balance > 1 && key.compareTo(n.getLeft().getKey())>0) { 
            n.setLeft(leftRotate((NodeAVL<K, V>) n.getLeft())); 
            return rightRotate((NodeAVL<K, V>) n); 
        }
  
        // Right Left Case 
        if (balance < -1 && key.compareTo(n.getRight().getKey())<0) { 
            n.setRight(rightRotate((NodeAVL<K, V>) n.getRight())); 
            return leftRotate((NodeAVL<K, V>) n); 
        }

        return n;
	}

	@Override
	public NodeInterface<K,V> delete(K key) {
		root = delete((NodeAVL<K, V>) root, key);
		size--;
		return null;
	}
	
	private NodeAVL<K,V> delete(NodeAVL<K,V> n, K key){
        if (n == null)  
            return n;  
  
        // If the key to be deleted is smaller than  
        // the root's key, then it lies in left subtree  
        if (key.compareTo(n.getKey())<0)  
            n.setLeft(delete((NodeAVL<K, V>) n.getLeft(), key));  
  
        // If the key to be deleted is greater than the  
        // root's key, then it lies in right subtree  
        else if (key.compareTo(n.getKey())>0)  
        	n.setRight(delete((NodeAVL<K, V>) n.getRight(), key)); 
  
        // if key is same as root's key, then this is the node  
        // to be deleted  
        else{  
  
            // node with only one child or no child  
            if (n.childrensAmount()==1 || n.childrensAmount()==0){  
                NodeAVL<K,V> temp = null;  
                if (n.getLeft()==null)  
                    temp = (NodeAVL<K, V>)n.getRight();  
                else
                    temp = (NodeAVL<K, V>) n.getLeft();  
  
                // No child case  
                if (temp == null){  
                    temp = n;  
                    n = null;  
                }  
                else // One child case  
                    n = temp; // Copy the contents of  
                                // the non-empty child  
            }
            else{  
  
                // node with two children: Get the inorder  
                // successor (smallest in the right subtree)  
            	NodeAVL<K,V> temp = (NodeAVL<K, V>) getLess(n.getRight());  
  
                // Copy the inorder successor's data to this node  
                n.setKey(temp.getKey());  
  
                // Delete the inorder successor  
                n.setRight(delete((NodeAVL<K, V>) n.getRight(), temp.getKey()));  
            }  
        }  
  
        // If the tree had only one node then return  
        if (n == null)  
            return n;  
  
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE  
        n.setHeight(max(getHeight((NodeAVL<K, V>) n.getLeft()), getHeight((NodeAVL<K, V>) n.getRight()))+1);  
  
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether  
        // this node became unbalanced)  
        int balance = getBalance(n);  
  
        // If this node becomes unbalanced, then there are 4 cases  
        // Left Left Case  
        if (balance > 1 && getBalance((NodeAVL<K, V>) n.getLeft()) >= 0)  
            return rightRotate(n);  
  
        // Left Right Case  
        if (balance > 1 && getBalance((NodeAVL<K, V>) n.getLeft()) < 0){  
            n.setLeft(leftRotate((NodeAVL<K, V>) n.getLeft()));  
            return rightRotate(n);  
        }
  
        // Right Right Case  
        if (balance < -1 && getBalance((NodeAVL<K, V>) n.getRight()) <= 0)  
            return leftRotate(n);  
  
        // Right Left Case  
        if (balance < -1 && getBalance((NodeAVL<K, V>) n.getRight()) > 0){  
            n.setRight(rightRotate((NodeAVL<K, V>) n.getRight()));  
            return leftRotate(n);  
        } 
  
        return n;  
	}
}
