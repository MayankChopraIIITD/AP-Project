import java.util.*;
import java.io.*;


public class token {
	private ArrayList<Integer> arr_x;
	private int y_cor;
	
	public token(){
		arr_x = new ArrayList<>();
	}
	public void generate_random_list(){
		int sum_x = 300;
		for(int i=-19;i<=19;i++){
			sum_x = 300 + (i*15);
			arr_x.add(sum_x);
		}
	}
	public ArrayList<Integer> get_shuffled_list(){
		Collections.shuffle(arr_x);
		return arr_x;
	}
	
	public void random_y_cordinate(){
		Random num = new Random();
		y_cor = -700-num.nextInt(300);
	}
	public int get_y_coordinate(){
		return y_cor;
	}
	
	
	
	
}
