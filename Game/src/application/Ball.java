import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class Ball extends token{
	private ArrayList<Circle> ball_list=new ArrayList<Circle>();
	private Pane pane=new Pane();
	private ArrayList<Integer> check_x = new ArrayList<>();
	private ArrayList<Integer> check_y = new ArrayList<>();
	
	public void getBallAnimation(ArrayList<Circle> list,Pane pane){
		ParallelTransition a=new ParallelTransition();
		for(int i=0;i<list.size();i++){
			TranslateTransition t=new TranslateTransition(Duration.seconds(7),list.get(i));
			t.setByY(1750);
			t.setCycleCount(1);
			a.getChildren().add(t);
		}
		a.play();
		a.setOnFinished(event ->{
			this.createball(pane,this.ball_list);
			this.getBallAnimation(this.ball_list, pane);
		});
		
	}
	public ArrayList<Circle> getballs(){
		return ball_list;
	}
	public void createball(Pane pane,ArrayList<Circle> list){
		Random num=new Random();
		int x_cor=0;
		int y_cor=0;
		int number=num.nextInt(4)+1;
		for(int i=0;i<number;i++){
			x_cor = this.get_random_x_coordinate();
			y_cor = this.get_random_y_coordinate();
			check_x.add(x_cor);
			check_y.add(y_cor);
			if(search_pointlist(x_cor,y_cor)!=0){
				check_x.remove(search_pointlist(x_cor,y_cor));
				check_y.remove(search_pointlist(x_cor,y_cor));
			}
			Circle ball = new Circle(x_cor,y_cor,15);
			ball.setFill(Color.YELLOW);
			ball.setStroke(Color.BLACK);
			list.add(i,ball);
			pane.getChildren().add(ball);
		}
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
