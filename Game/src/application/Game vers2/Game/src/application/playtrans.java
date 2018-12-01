package application;

import javafx.animation.SequentialTransition;

public class playtrans implements Runnable {
	private SequentialTransition st;
	public playtrans(SequentialTransition st) {
		this.st=st;
	}
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		st.play();
	}
	
}
