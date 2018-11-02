package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PaneOrganizer  {
	private BorderPane root;
	//private BorderPane root;
	private Circle snakehead;
	private Rectangle r;
	private VBox sub;
	private GraphicsContext gc;
	//private StackPane sub2;
	//private StackPane s1;
	//private Timeline a;
	//private TranslateTransition t;
	//private int move_delta=-10;
	private ArrayList<Circle> snakelist=new ArrayList<Circle>();
	private int snakelength=5;
	//private VBox snake;
	public PaneOrganizer() {
		//StackPane l1=new StackPane();
		//root=new BorderPane();
		//s1=new StackPane();
		//sub=new VBox();
		//sub2=new StackPane();
		snakehead=new Circle(200,200,10);
		Circle tail1=new Circle(200,220,10);
		tail1.setLayoutX(0);
		tail1.setLayoutY(0);
		Circle tail2=new Circle(200,240,10);
		tail2.setLayoutX(0);
		tail2.setLayoutY(0);
		Circle tail3=new Circle(200,260,10);
		tail3.setLayoutX(0);
		tail3.setLayoutY(0);
		snakelist.add(snakehead);
		snakelist.add(tail1);
		snakelist.add(tail2);
		snakelist.add(tail3);
		//snake=new VBox();
		//for(int i=0;i<5;i++) {
			//snake.getChildren().add(snakelist.get(i));
		//}
		snakehead.setLayoutX(0);
		snakehead.setLayoutY(0);
		root=new BorderPane();
		root.getChildren().add(snakehead);
		root.getChildren().add(tail1);
		root.getChildren().add(tail2);
		root.getChildren().add(tail3);
		//root=new Canvas(400,400);
		//gc=root.getGraphicsContext2D();
		//gc.fillOval(snakehead.getCenterX(),snakehead.getCenterY(), 20, 20);
		
		
		//Group l=new Group(snake);
		//s1.getChildren().add(l);
		r=new Rectangle(); 
		r.setY(0);
		r.setWidth(400);
		r.setHeight(50);
		r.setOpacity(0);
		//sub2.getChildren().add(r);
		//sub.getChildren().add(sub2);
		//l1.getChildren().add(sub);
		//root.setTop(l1);
		//root.setCenter(s1);
		//KeyFrame k=MakeNewFrame();
		//this.setupTimeline(k);
	}//public void setupTimeline(KeyFrame k) {
		//a=new Timeline(k);
		//a.setCycleCount(Animation.INDEFINITE);
		//a.play();
	//}
	public BorderPane getRoot() {
		return root;
	}public Circle getHead() {
		return snakehead;
	}//public TranslateTransition getTransition() {
		//return t;}
	//public Timeline getTimeLine() {
		//return a;}
	public ArrayList<Circle> getSnakeList(){
		return snakelist;
	}
	public GraphicsContext getGC() {
		return gc;
	}public int getLength() {
		return snakelength;
	}
	
	public KeyFrame MakeNewFrame() {
		KeyFrame kf=new KeyFrame(Duration.seconds(3.5),ev -> {
			TranslateTransition t=new TranslateTransition(Duration.seconds(3),r);
			t.setFromY(-100);		
			FadeTransition ft=new FadeTransition(Duration.seconds(1),r);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.play();
			t.setToY(500);
			t.setCycleCount(1);
			t.play();
			t.setOnFinished(h ->{
				//sub2.getChildren().remove(0);
				sub.getChildren().remove(0);
				//root.getChildren().remove(0);
				Random num=new Random();
				r=new Rectangle();
				r.setX(-100);
				r.setY(-100);
				r.setWidth(num.nextInt(250)+150);
				r.setHeight(50);
				r.setOpacity(0);
				//sub2.getChildren().add(r);
				//sub.getChildren().add(sub2);
				//root.getChildren().add(0,sub);
			});
		});
		return kf;
	}
	
}
