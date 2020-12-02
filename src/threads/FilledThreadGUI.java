package threads;
import java.io.IOException;

import javafx.application.Platform;
import model.Generator;
import model.Person;
import model.ProgressBarController;
import structures.AVL;
import ui.ControllerGUI;
import ui.Generate;

public class FilledThreadGUI extends Thread{
	private Generator random;

	private AVL<String, Person> tree;
	private int index;
	private int cant;
	private Generate gui;
	private ProgressBarController pb;
	
	public FilledThreadGUI(int cant,Generator random, int index, Generate gui, ProgressBarController pb) {
		this.index = index;
		this.random=random;
		this.cant=cant;
		this.gui = gui;
		this.pb = pb;
		tree= new AVL<String, Person>();
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
		setTree();
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
	
	private void setTree() {
		if(index==1) {
			Platform.runLater(new Thread() {
				public void run() {
					ControllerGUI.data.setT1(tree);
				}
			});
		}else if(index==2) {
			Platform.runLater(new Thread() {
				public void run() {
					ControllerGUI.data.setT1(tree);
				}
			});
		}else if(index==3) {
			Platform.runLater(new Thread() {
				public void run() {
					ControllerGUI.data.setT1(tree);
				}
			});
		}else if(index==4) {
			Platform.runLater(new Thread() {
				public void run() {
					ControllerGUI.data.setT1(tree);
				}
			});
		}
	}
}