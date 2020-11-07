package threads;
import java.io.IOException;

import javafx.application.Platform;
import model.Generator;
import model.Person;
import structures.AVL;
import ui.ControllerGUI;

public class FilledThread extends Thread{
	
	private int cant;
	private Generator random;
	private AVL<String, Person> avl;
	private int index;
	
	
	public FilledThread(int cant,Generator random, int index) {
		this.random=random;
		this.cant=cant;
		avl = new AVL<String, Person>();
		this.index = index;
	}

	@Override
	public void run() {	
		int max=0;
		long init = System.currentTimeMillis();
		while(max<=cant/4) {
			try {
				Person p=random.generate();
				avl.insert(p.getId(), p);
				max++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setTree();
		long finish = System.currentTimeMillis();
		System.out.println(finish-init);
	}
	
	private void setTree() {
		if(index==1) {
			Platform.runLater(new Thread() {
				public void run() {
					ControllerGUI.data.setT1(avl);
				}
			});
		}else if(index==2) {
			Platform.runLater(new Thread() {
				public void run() {
					ControllerGUI.data.setT1(avl);
				}
			});
		}else if(index==3) {
			Platform.runLater(new Thread() {
				public void run() {
					ControllerGUI.data.setT1(avl);
				}
			});
		}else if(index==4) {
			Platform.runLater(new Thread() {
				public void run() {
					ControllerGUI.data.setT1(avl);
				}
			});
		}
	}
}
