package Threads;

import model.Person;
import structures.AVL;
import ui.DataBase;

public class SearchThread extends Thread{
	
	private DataBase data;
	private AVL <String, Person> tree;
	private String id;
	private Person personFound;
	
	public SearchThread(DataBase data, AVL<String, Person> tree, String id) {
		this.data=data;
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
