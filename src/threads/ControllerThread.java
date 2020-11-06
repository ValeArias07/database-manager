package Threads;

import java.io.IOException;

import model.Generator;
import model.Loader;
import model.Person;
import ui.DataBase;
public class ControllerThread extends Thread {

	private DataBase data;
	private String option;
	private Person personFound;
	private String aux;
	private boolean finish;
	public static final String SEARCH_OPTION="Search";
	public static final String REMOVE_OPTION="Remove";
	public static final String CREATE_OPTION="Create";
	
	public ControllerThread(DataBase data, String option, String aux) {
		this.data=data;
		this.option=option;
		this.aux=aux;
		this.finish=false;
	}
	
	@Override
	public void run() {
		try {
			if(option.equals(SEARCH_OPTION)) {
				search();
			}else if(option.equals(REMOVE_OPTION)) {
				remove();
			}else if(option.equals(CREATE_OPTION)){
					create();
			}
		}catch(IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void search() throws InterruptedException {
		SearchThread s1= new SearchThread(data,data.getT1(),aux);
		SearchThread s2= new SearchThread(data,data.getT2(),aux);
		SearchThread s3= new SearchThread(data,data.getT3(),aux);
		SearchThread s4= new SearchThread(data,data.getT4(),aux);
		s1.start();
		s2.start();
		s3.start();
		s4.start();

		while(s1.isAlive() || s2.isAlive() || s3.isAlive() || s4.isAlive())
		if(s1.getPersonFound()!=null) {
			personFound=s1.getPersonFound();
		}else if(s2.getPersonFound()!=null) {
			personFound=s2.getPersonFound();
		}else if(s3.getPersonFound()!=null) {
			personFound=s3.getPersonFound();
		}else if(s4.getPersonFound()!=null) {
			personFound=s4.getPersonFound();
		}
		
		/**
		 * s1.join();
		s2.join();
		s3.join();
		s4.join();
		 */
	}

	public void create() throws IOException {
		int cant=Integer.parseInt(aux);
		Loader loader=new Loader(cant);
		Generator g1= new Generator(loader);
		Generator g2= new Generator(loader);
		Generator g3= new Generator(loader);
		Generator g4= new Generator(loader);
		FilledThread thread1= new FilledThread(cant,g1,data.getT1(),1);
		FilledThread thread2=new FilledThread(cant,g2,data.getT2(),2);
		FilledThread thread3= new FilledThread(cant,g3,data.getT3(),3);
		FilledThread thread4= new FilledThread(cant,g4,data.getT4(),4);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			finish=true;
		}catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void remove() throws InterruptedException {
		RemoveThread r1= new RemoveThread(data,data.getT1(),aux);
		RemoveThread r2= new RemoveThread(data,data.getT2(),aux);
		RemoveThread r3= new RemoveThread(data,data.getT3(),aux);
		RemoveThread r4= new RemoveThread(data,data.getT4(),aux);
		r1.start();
		r2.start();
		r3.start();
		r4.start();
		
		while(r1.isAlive() || r2.isAlive() || r3.isAlive() || r4.isAlive())
			if(r1.getPersonFound()!=null) {
				personFound=r1.getPersonFound();
			}else if(r2.getPersonFound()!=null) {
				personFound=r2.getPersonFound();
			}else if(r3.getPersonFound()!=null) {
				personFound=r3.getPersonFound();
			}else if(r4.getPersonFound()!=null) {
				personFound=r4.getPersonFound();
			}
		
		r1.join();
		r2.join();
		r3.join();
		r4.join();
	}
	
	public Person getPersonFound() {
		return personFound;
	}
	
	public boolean getFinish() {
		return finish;
	}
}
