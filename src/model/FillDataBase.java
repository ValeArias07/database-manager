package model;

import threads.ControllerThread;
import ui.ControllerGUI;
import ui.Generate;

public class FillDataBase {

	private Generate guiProgress;
	private ProgressBarController pb;
	
	public FillDataBase(Generate guiProgress, ProgressBarController pb) {
		this.guiProgress = guiProgress;
		this.pb = pb;
	}
	
	public void create(int amount) {
		ControllerThread controller= new ControllerThread(ControllerGUI.data, ControllerThread.CREATE_OPTION, amount+"", guiProgress, pb);
		controller.start();
	}
}
