import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Circle;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Shield extends token {
	Pane pane = new Pane();
	ImageView view;

	public ParallelTransition getShieldAnimation(){
		ParallelTransition a=new ParallelTransition();
		TranslateTransition t = new TranslateTransition(Duration.seconds(7),this.view);
		t.setByY(1750);
		t.setCycleCount(1);
		a.getChildren().add(t);
		return a;
	}
	
	
	public void createShield(Pane pane) throws Exception{
		Random num = new Random();
		int x_cor = this.get_random_x_coordinate();
		int y_cor = this.get_random_y_coordinate();
		Image img_shield = null;
		InputStream in_shield = null;
		try{
			in_shield = Files.newInputStream(Paths.get("res/images/shield.jpg"));
			img_shield = new Image(in_shield);
		}finally{
			in_shield.close();
		}
		view = new ImageView(img_shield);
		view.setFitWidth(50);
		view.setFitHeight(50);
		view.setTranslateX(x_cor);
		view.setTranslateY(y_cor);
		pane.getChildren().add(view);
	}
}
