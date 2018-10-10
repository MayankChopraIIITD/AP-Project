package application;
	

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;


public class Main extends Application {
	protected static final double KEYBOARD_MOVEMENT_DELTA = 5;
	@Override
	public void start(Stage primaryStage) {
			VBox a=new VBox();
			primaryStage.initStyle(StageStyle.UNDECORATED);
			a.setPadding(new Insets(170,0,0,180));
			a.setSpacing(10);
			Button b1=new Button("Play");
			Button b2=new Button("Resume");
			Button b3=new Button("Exit");
			a.setId("pane");
			a.getChildren().add(b1);
			a.getChildren().add(b2);
			a.getChildren().add(b3);
			Scene scene1 = new Scene(a,400,400);
			scene1.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene1);
			primaryStage.show();
			Button b4=new Button("Yup");
			Circle snakehead=new Circle(200,200,10);
			Group h=new Group(snakehead);
			Scene scene2 = new Scene(h,400,400);
			moveCircleOnKeyPress(scene2,snakehead);
			b3.setOnAction(e -> {
				System.exit(0);
			});
			b1.setOnAction(e -> {
				primaryStage.setScene(scene2);
			});
			b4.setOnAction(e -> {
				primaryStage.setScene(scene1);
			});
	} 
	public static void main(String[] args) {
		launch(args);
	} private void moveCircleOnKeyPress(Scene scene, final Circle circle) {
	    scene.setOnKeyPressed(e ->{
	    	switch (e.getCode()) {
	    case UP:    circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;
        case RIGHT: circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
        case LEFT:  circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
	    	}
	    	if(circle.getCenterY()-circle.getRadius()<0||circle.getCenterX()+circle.getRadius()>400||circle.getCenterX()-circle.getRadius()<0) {
	    		System.exit(0);
	    	}
	    });
	    }
}
