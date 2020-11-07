package threads;
import java.io.IOException;

import model.Generator;
import model.Person;
import structures.AVL;

public class FilledThread extends Thread{
	private Generator random;

	private AVL<String, Person> tree;
	private int cant;
	
	public FilledThread(int cant,Generator random, AVL<String, Person> t1) {
		this.random=random;
		this.tree=t1;
		this.cant=cant;
	}

	@Override
	public void run() {	
		int max=0;
		long init = System.currentTimeMillis();
		while(max<=cant/4) {
			try {
				Person p=random.generate();
				tree.insert(p.getId(), p);
				max++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long finish = System.currentTimeMillis();
		System.out.println(finish-init);
	}
}
