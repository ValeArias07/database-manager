package model;

import threads.ControllerThread;
import java.io.Serializable;
import java.time.LocalDate;
import structures.AVL;

@SuppressWarnings("serial")
public class DataBase implements Serializable{

	private AVL<String, Person> t1;
	private AVL<String, Person> t2;
	private AVL<String, Person> t3;
	private AVL<String, Person> t4;
	private int actualTree;
	private int addAmount;
	
	public DataBase() {
		 actualTree=1;
		 addAmount=0;
		 t1= new AVL<String, Person>();
		 t2= new AVL<String, Person>();
		 t3= new AVL<String, Person>();
		 t4= new AVL<String, Person>();
	}
	
	public void add(String name, boolean gender, LocalDate bornDate, double height, String nationality) {
		Person p= new Person(Generator.IDS[5]+addAmount, name, gender, bornDate, height, nationality);
		int r=getActualTree();

		if(r==1) {
			t1.insert(p.getId(), p);
		}else if(r==2) {
			t2.insert(p.getId(), p);
		}else if(r==3) {
			t3.insert(p.getId(), p);
		}else if(r==4) {
			t4.insert(p.getId(), p);
		}
		
		addAmount++;
	}
	
	private int getActualTree() {
		int r;
		r=actualTree;
			if(actualTree<4) 
				actualTree++;
			else 
				actualTree=1;	
		return r;
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
	
	public void update(Person p, String name, boolean gender, LocalDate bornDate, double height, String nationality) {
		p.setName(name);
		p.setGender(gender);
		p.setBornDate(bornDate);
		p.setHeight(height);
		p.setNationality(nationality);
	}
	
	public Person remove(String id) {
		ControllerThread controller= new ControllerThread(this, ControllerThread.REMOVE_OPTION, id);
		controller.start();
		Person p=null;
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