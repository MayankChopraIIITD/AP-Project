package application;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PaneOrganizer  {
	private StackPane root;
	private Circle snakehead;
	private Rectangle r;
	private VBox sub;
	private int move_delta=-10;
	public PaneOrganizer() {
		root=new StackPane();
		sub=new VBox();
		snakehead=new Circle(200,200,10);
		snakehead.setFill(Color.AQUA);
		r=new Rectangle();
		r.setX(0);
		r.setY(100);
		r.setWidth(400);
		r.setHeight(50);		
		sub.getChildren().add(r);
		root.getChildren().add(sub);
		root.getChildren().add(snakehead);
		KeyFrame k=MakeNewFrame();
		this.setupTimeline(k);
	}public void setupTimeline(KeyFrame k) {
		Timeline a=new Timeline(k);
		a.setCycleCount(Animation.INDEFINITE);
		a.play();
	}
	public StackPane getRoot() {
		return root;
	}public Circle getHead() {
		return snakehead;
	}public KeyFrame MakeNewFrame() {
		KeyFrame kf=new KeyFrame(Duration.seconds(2),ev -> {
			TranslateTransition t=new TranslateTransition(Duration.seconds(1),r);
			t.setFromY(0);
			t.setToY(350);
			t.setCycleCount(1);
			t.play();
			t.setOnFinished(h ->{
				root.getChildren().remove(0);
				sub.getChildren().remove(0);
				Random num=new Random();
				r=new Rectangle();
				r.setX(0);
				r.setY(0);
				r.setWidth(num.nextInt(250)+150);
				r.setHeight(50);
				sub.getChildren().add(r);
				root.getChildren().add(0,sub);
			});
		});
		return kf;
	}
	
}
