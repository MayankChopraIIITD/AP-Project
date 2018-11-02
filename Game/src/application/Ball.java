package application;
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


public class Ball extends Application{
	ArrayList<Circle> ball_list1=new ArrayList<Circle>();
	ArrayList<Circle> ball_list2=new ArrayList<Circle>();
	ArrayList<Circle> ball_list3=new ArrayList<Circle>();
	Pane pane=new Pane();
	private int numballs=0;
	@Override
	public void start(Stage stage){
		createball(pane);
		Scene scene=new Scene(pane,600,800);
		stage.setScene(scene);
		stage.show();
		getBallAnimation();
		
	}
	public ParallelTransition getBallAnimation(){
		ParallelTransition a=new ParallelTransition();
		if(numballs==1){
			for(int i=0;i<numballs;i++){
				TranslateTransition t=new TranslateTransition(Duration.seconds(3.5),this.ball_list1.get(i));
				t.setByY(1900);
				t.setCycleCount(1);
				a.getChildren().add(t);
			}
		}else if(numballs==2){
			for(int i=0;i<numballs;i++){
				TranslateTransition t=new TranslateTransition(Duration.seconds(3.5),this.ball_list2.get(i));
				t.setByY(1900);
				t.setCycleCount(1);
				a.getChildren().add(t);
			}
		}else{
			for(int i=0;i<numballs;i++){
				TranslateTransition t=new TranslateTransition(Duration.seconds(3.5),this.ball_list3.get(i));
				t.setByY(1900);
				t.setCycleCount(1);
				a.getChildren().add(t);
			}
		}
		return a;
		
	}
	public void createball(Pane pane){
		Random num=new Random();
		int sum_x = 300;
		ArrayList<Integer> arr_x = new ArrayList<>();
		for(int i=-19;i<=19;i++){
			sum_x = 300+(i*15);
			arr_x.add(sum_x);
		}
		Random y = new Random();
		int y_cor = -700+num.nextInt(300);
		Collections.shuffle(arr_x);
		int number=num.nextInt(3)+1;
		if(number==1){
			Circle ball = new Circle(arr_x.get(arr_x.size()/2),y_cor,15);
			ball.setFill(Color.YELLOW);
			ball.setStroke(Color.BLACK);
			ball_list1.add(0,ball);
			pane.getChildren().add(ball);
			numballs=1;
		}
		else if(number == 2){
			Circle ball1  = new Circle(arr_x.get(arr_x.size()/2),y_cor,15);
			y_cor = -700+num.nextInt(300);
			Collections.shuffle(arr_x);
			Circle ball2  = new Circle(arr_x.get(arr_x.size()/2),y_cor,15);
			ball1.setFill(Color.YELLOW);
			ball2.setFill(Color.YELLOW);
			ball1.setStroke(Color.BLACK);
			ball2.setStroke(Color.BLACK);
			ball_list2.add(0,ball1);
			ball_list2.add(1,ball2);
			pane.getChildren().addAll(ball1,ball2);
			numballs=2;
		}
		else{
			Circle ball1  = new Circle(arr_x.get(arr_x.size()/2),y_cor,15);
			y_cor = -700+num.nextInt(300);
			Collections.shuffle(arr_x);
			Circle ball2  = new Circle(arr_x.get(arr_x.size()/2),y_cor,15);
			y_cor = -700+num.nextInt(300);
			Collections.shuffle(arr_x);
			Circle ball3 = new Circle(arr_x.get(arr_x.size()/2),y_cor,15);
			ball1.setFill(Color.YELLOW);
			ball2.setFill(Color.YELLOW);
			ball3.setFill(Color.YELLOW);
			ball1.setStroke(Color.BLACK);
			ball2.setStroke(Color.BLACK);
			ball3.setStroke(Color.BLACK);
			ball_list3.add(0,ball1);
			ball_list3.add(1,ball2);
			ball_list3.add(2,ball3);
			pane.getChildren().addAll(ball1,ball2,ball3);
			numballs=3;
		}
	}
	public static void main(String[] args){
		launch(args);
	}

}
