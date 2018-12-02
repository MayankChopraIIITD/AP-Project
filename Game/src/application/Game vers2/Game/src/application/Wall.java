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

public class Wall extends token {
//	Pane pane = new Pane();
	ImageView view;

	
	public ImageView generateDestroyer() {
		Random num = new Random();
		int x_cor = this.get_random_x_coordinate();
		int y_cor = this.get_random_y_coordinate();
		y_cor = y_cor+20;
		Image img_wall = null;
		InputStream in_wall = null;
		try{
			in_wall = Files.newInputStream(Paths.get("D:\\eclipse-workspace\\Game\\src\\application\\wall2.jpg"));
			img_wall = new Image(in_wall);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in_wall.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		view = new ImageView(img_wall);
		view.setFitWidth(40);
		view.setFitHeight(num.nextInt(40)+80);
		view.setX(x_cor);
		view.setY(y_cor);
		return view;
//		pane.getChildren().add(view);
	}
}