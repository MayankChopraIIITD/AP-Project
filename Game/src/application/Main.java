package application;
	

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
			Font.loadFont(this.getClass().getResource("ARCADECLASSIC.TTF").toExternalForm(),10);
			VBox a=new VBox();
			primaryStage.initStyle(StageStyle.UNDECORATED);
			a.setPadding(new Insets(130,0,0,120));
			a.setSpacing(10);
			Hyperlink b1=new Hyperlink("Play");
			Hyperlink b2=new Hyperlink("Resume");
			Hyperlink b3=new Hyperlink("Exit");
			Label l1=new Label("Snake vs Block");
			l1.setFont(Font.font("ARCADECLASSIC",40));
			l1.setTextFill(Color.CRIMSON);
			b1.setBorder(Border.EMPTY);
			b2.setBorder(Border.EMPTY);
			b3.setBorder(Border.EMPTY);
			b1.setUnderline(false);
			b2.setUnderline(false);
			b3.setUnderline(false);
			b1.setFont(Font.font("ARCADECLASSIC",30));
			b2.setFont(Font.font("ARCADECLASSIC",30));
			b3.setFont(Font.font("ARCADECLASSIC",30));
			b1.setTextFill(Color.GREENYELLOW);
			b2.setTextFill(Color.GREENYELLOW);
			b3.setTextFill(Color.GREENYELLOW);
			a.setId("pane");
			a.getChildren().add(l1);
			a.getChildren().add(b1);
			a.getChildren().add(b2);
			a.getChildren().add(b3);
			Scene scene1 = new Scene(a,400,400);
			scene1.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene1);
			primaryStage.show();
			
			b3.setOnAction(e -> {
				System.exit(0);
			});
			b1.setOnAction(e -> {
				PaneOrganizer u=new PaneOrganizer();
				Scene scene2=new Scene(u.getRoot(),400,400);
				Circle circle=u.getHead();
				primaryStage.setScene(scene2);			
	});
	}public static void main(String[] args) {
		launch(args);
	}   

}
