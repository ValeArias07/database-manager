package threads;

import java.io.IOException;
import model.Generator;
import model.Loader;
import model.Person;
import model.ProgressBarController;
import ui.Generate;
import model.DataBase;
public class ControllerThread extends Thread {

	public static final String SEARCH_OPTION="Search";
	public static final String REMOVE_OPTION="Remove";
	public static final String CREATE_OPTION="Create";
	
	private DataBase data;
	private String option;
	private Person personFound;
	private String aux;
	private boolean finish;
	private Generate gui;
	private ProgressBarController pb;
	
	public ControllerThread(DataBase data, String option, String aux) {
		this.data=data;
		this.option=option;
		this.aux=aux;
		this.finish=false;
	}
	
	public ControllerThread(DataBase data, String option, String aux, Generate gui, ProgressBarController pb) {
		this.data=data;
		this.option=option;
		this.aux=aux;
		this.finish=false;
		this.gui = gui;
		this.pb = pb;
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
		SearchThread s1= new SearchThread(data.getT1(),aux);
		SearchThread s2= new SearchThread(data.getT2(),aux);
		SearchThread s3= new SearchThread(data.getT3(),aux);
		SearchThread s4= new SearchThread(data.getT4(),aux);
		s1.start();
		s2.start();
		s3.start();
		s4.start();
		boolean found=false;
		while((s1.isAlive() || s2.isAlive() || s3.isAlive() || s4.isAlive())&& !found) {
			if(s1.getPersonFound()!=null) {
				personFound=s1.getPersonFound();
				System.out.println("hilo 1 found"+personFound.getName());
				found=true;
			}else if(s2.getPersonFound()!=null) {
				personFound=s2.getPersonFound();
				System.out.println("hilo 2 found"+personFound.getName());
				found=true;
			}else if(s3.getPersonFound()!=null) {
				personFound=s3.getPersonFound();
				System.out.println("hilo 3 found"+personFound.getName());
				found=true;
			}else if(s4.getPersonFound()!=null) {
				personFound=s4.getPersonFound();
				System.out.println("hilo 4 found"+personFound.getName());
				found=true;
			}
		}
		s1.join();
		s2.join();
		s3.join();
		s4.join();
	}

	public void create() throws IOException {
		int cant=Integer.parseInt(aux);
		Loader loader=new Loader(cant);
		Generator g1= new Generator(loader,1);
		Generator g2= new Generator(loader,2);
		Generator g3= new Generator(loader,3);
		Generator g4= new Generator(loader,4);
		FilledThread thread1= new FilledThread(cant,g1,1);
		FilledThread thread2=new FilledThread(cant,g2,1);
		FilledThread thread3= new FilledThread(cant,g3,1);
		FilledThreadGUI thread4= new FilledThreadGUI(cant,g4,1, gui, pb);
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
		RemoveThread r1= new RemoveThread(data.getT1(),aux);
		RemoveThread r2= new RemoveThread(data.getT2(),aux);
		RemoveThread r3= new RemoveThread(data.getT3(),aux);
		RemoveThread r4= new RemoveThread(data.getT4(),aux);
		r1.start();
		r2.start();
		r3.start();
		r4.start();
		
		r1.join();
		r2.join();
		r3.join();
		r4.join();
	}
	
	public void setPersonFound(Person pf) {
		personFound = pf;
	}
	
	public Person getPersonFound() {
		return personFound;
	}
	
	public boolean getFinish() {
		return finish;
	}
}
