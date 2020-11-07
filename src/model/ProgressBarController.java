package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProgressBarController implements Serializable{
	
	private final double INCREASE_VALUE = 0.01;
	
	private double progress; 
	
	public ProgressBarController() {
		progress = 0;
	}
	
	public void increase() {
		progress += INCREASE_VALUE;
	}
	
	public double getProgress() {
		return progress;
	}
	
	public void resetProgress() {
		progress = 0;
	}
}
