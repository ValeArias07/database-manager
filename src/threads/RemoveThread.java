package threads;

import model.Person;
import structures.AVL;


public class RemoveThread extends Thread{
	

	private AVL <String, Person> tree;
	private String id;
	
	public RemoveThread(AVL<String, Person> tree, String id) {
		this.tree=tree;
		this.id=id;
	}
	
	@Override
	public void run() {
		tree.delete(id);
	}
}
