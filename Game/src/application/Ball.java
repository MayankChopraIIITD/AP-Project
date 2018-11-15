package application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;



public class Ball extends token{
//	private ArrayList<Circle> ball_list;
//	private Pane pane=new Pane();
	private ArrayList<Integer> check_x = new ArrayList<>();
	private ArrayList<Integer> check_y = new ArrayList<>();
	
	public Group createball(){
		int x_cor = this.get_random_x_coordinate();
		int y_cor = this.get_random_y_coordinate();
		check_x.add(x_cor);
		check_y.add(y_cor);
		if(search_pointlist(x_cor,y_cor)!=0){
			check_x.remove(search_pointlist(x_cor,y_cor));
			check_y.remove(search_pointlist(x_cor,y_cor));
		}
		Circle ball = new Circle(check_x.get(check_x.size()-1),check_y.get(check_y.size()-1),15);
		Random num=new Random();
		int numb=num.nextInt(1)+1;
		Text n=new Text(Integer.toString(numb));
		n.setX(ball.getCenterX()-10);
		n.setY(ball.getCenterY()+5);
		n.setFont(new Font(15));
		ball.setFill(Color.YELLOW);
		ball.setStroke(Color.BLACK);
		Group grp=new Group(ball,n);
		return grp;
	}
	public int search_pointlist(int x, int y){
		int index=0;
		boolean var=true;
		int n = check_x.size();
		double distance = 0;
		for(int i=0;i<n-1;i++){
			for(int j=i+1;j<n;j++){
				double x1 = (double)(check_x.get(i));
				double x2 = (double)(check_x.get(j));
				double y1 = (double)(check_x.get(i));
				double y2 = (double)(check_x.get(j));
				distance = Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
				if(distance<=32){
					var=true;
					index=j;
					break;
				}
			}
			if(var==true){
				break;
			}
		}
		return index;
		
	}
	
}