package application;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class token {
	private ArrayList<Integer> arr_x;
	private int y_cor;
	
	public token(){
		arr_x = new ArrayList<>();
	}
	public int get_random_x_coordinate(){
		int sum_x = 300;
		for(int i=-9;i<=9;i++){
			sum_x = 300 + (i*30);
			arr_x.add(sum_x);
		}
		Collections.shuffle(arr_x);
		return arr_x.get(arr_x.size()/2);
	}
	
	public int get_random_y_coordinate(){
		Random num = new Random();
		y_cor = -700+num.nextInt(300);
		return y_cor;
	}
	
	
	
	
}
