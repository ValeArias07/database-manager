package Threads;

import model.Person;
import structures.AVL;
import ui.DataBase;

public class RemoveThread extends Thread{
	
	private DataBase data;
	private AVL <String, Person> tree;
	private String id;
	private Person personFound;
	
	public RemoveThread(DataBase data, AVL<String, Person> tree, String id) {
		this.data=data;
		this.tree=tree;
		this.id=id;
	}
	
	@Override
	public void run() {
		Person p= (Person) tree.delete(id);
		if(p!=null) {
			personFound=p;
		}
	}
	
	public Person getPersonFound() {
		return personFound;
	}
}
