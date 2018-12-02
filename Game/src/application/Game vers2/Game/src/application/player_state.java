package application;

import java.io.Serializable;
import java.util.Date;

public class player_state implements Serializable {
	private int Score;
	private Date date;
	public player_state(int s) {
		Score=s;
		date=new Date();
	}public int getScore() {
		return Score;
	}public Date getDate() {
		return date;
	}
}
