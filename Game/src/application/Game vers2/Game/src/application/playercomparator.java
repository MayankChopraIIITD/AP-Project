package application;

import java.util.Comparator;

public class playercomparator implements Comparator<player_state> {

	@Override
	public int compare(player_state arg0, player_state arg1) {
		// TODO Auto-generated method stub
		return arg1.getScore()-arg0.getScore();
	}

}
