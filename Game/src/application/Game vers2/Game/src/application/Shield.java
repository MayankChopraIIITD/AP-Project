package application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Shield extends token {
//	Pane pane = new Pane();
	ImageView view;

	
	public ImageView generateShield() {
		Random num = new Random();
		int x_cor = this.get_random_x_coordinate();
		int y_cor = this.get_random_y_coordinate();
		Image img_shield = null;
		InputStream in_shield = null;
		try{
			in_shield = Files.newInputStream(Paths.get("D:\\eclipse-workspace\\Game\\src\\application\\shield.jpg"));
			img_shield = new Image(in_shield);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in_shield.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		view = new ImageView(img_shield);
		view.setFitWidth(40);
		view.setFitHeight(40);
		view.setX(x_cor);
		view.setY(y_cor);
		return view;
//		pane.getChildren().add(view);
	}
}