package threads;
import java.io.IOException;

import javafx.application.Platform;
import model.Generator;
import model.Person;
import model.ProgressBarController;
import structures.AVL;
import ui.Generate;

public class FilledThreadGUI extends Thread{
	private Generator random;

	private AVL<String, Person> tree;
	private int cant;
	private Generate gui;
	private ProgressBarController pb;
	
	public FilledThreadGUI(int cant,Generator random, AVL<String, Person> t1, Generate gui, ProgressBarController pb) {
		this.random=random;
		this.tree=t1;
		this.cant=cant;
		this.gui = gui;
		this.pb = pb;
	}

	@Override
	public void run() {
		int max=0;
		int cont=0;
		long init = System.currentTimeMillis();
		while(max<=cant/4) {
			try {
				Person p=random.generate();
				tree.insert(p.getId(), p);
				cont++;
				max++;
				if(cont>=cant/400) {
					cont = 0;
					updateProgressBar();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		long finit = System.currentTimeMillis();
		updateTime(finit-init);
		setEnableButton();
	}
	
	private void  updateProgressBar() {
		pb.increase();
		Platform.runLater(new Thread() {
			public void run() {
				gui.updateProgressBar();
			}
		});
	}
	
	private void updateTime(long millis) {
		pb.increase();
		Platform.runLater(new Thread() {
			public void run() {
				gui.setTime(millis + " milliseconds");
			}
		});
	}
	
	private void setEnableButton() {
		Platform.runLater(new Thread() {
			public void run() {
				gui.setEnableButton();
			}
		});
	}
}