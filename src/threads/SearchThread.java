package threads;

import model.Person;
import structures.AVL;

public class SearchThread extends Thread{

	private AVL <String, Person> tree;
	private String id;
	private Person personFound;
	
	public SearchThread(AVL<String, Person> tree, String id) {
		this.tree=tree;
		this.id=id;
		personFound=null;
	}
	
	@Override
	public void run() {
		Person p= tree.search(id);
		if(p!=null) {
			personFound=p;
		}
	}
	
	public Person getPersonFound() {
		return personFound;
	}
}
