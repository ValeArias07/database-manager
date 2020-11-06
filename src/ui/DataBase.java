package ui;

import Threads.ControllerThread;
import model.Generator;
import model.Loader;
import model.Person;
import structures.AVL;

public class DataBase {

	private AVL<String, Person> t1;
	private AVL<String, Person> t2;
	private AVL<String, Person> t3;
	private AVL<String, Person> t4;
	
	public DataBase() {
		
		 t1= new AVL<String, Person>();
		 t2= new AVL<String, Person>();
		 t3= new AVL<String, Person>();
		 t4= new AVL<String, Person>();
		 
	}
	
	public void create(String amount) {
		ControllerThread controller= new ControllerThread(this, ControllerThread.CREATE_OPTION, amount);
		controller.start();
		while(controller.isAlive()) {

		}
	}
	
	public Person search(String id) {
		ControllerThread controller= new ControllerThread(this, ControllerThread.SEARCH_OPTION, id);
		controller.start();
		Person p=null;
		while(controller.isAlive()) {
			p=controller.getPersonFound();
		}
		
		return p;
	}
	
	public void add() {
		
	}
	
	public void update(String id) {
		Person p=search(id);
	}
	
	public Person remove(String id) {
		ControllerThread controller= new ControllerThread(this, ControllerThread.REMOVE_OPTION, id);
		controller.start();
		Person p=null;
		while(controller.isAlive()) {
			p=controller.getPersonFound();
		}
		return p;
	}

	public AVL<String, Person> getT1() {
		return t1;
	}

	public AVL<String, Person> getT2() {
		return t2;
	}

	public AVL<String, Person> getT3() {
		return t3;
	}

	public AVL<String, Person> getT4() {
		return t4;
	}
	
}
