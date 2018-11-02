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

public class Sheild extends Application {
	Pane pane = new Pane();
	
	@Override
	public void start(Stage stage) throws Exception{
		createShield(pane);
		Scene scene = new Scene(pane,600,800);
		stage.setScene(scene);
		stage.show();
		getShieldAnimation();
	}
	
	public void getShieldAnimation(){
		ParallelTransition a = new ParallelTransition();
		TranslateTransition t = new TranslateTransition(Duration.seconds(3.5));
		t.setByY(1900);
		t.setCycleCount(1);
		a.getChildren().add(t);
		a.play();
		a.setOnFinished(e->{
			try{
				createShield(pane);
				getShieldAnimation();
			}catch(Exception er){
				
			}
			
		});
	}
	
	
	public void createShield(Pane pane) throws Exception{
		Random num = new Random();
		int sum_x = 300;
		ArrayList<Integer> arr_x = new ArrayList<>();
		for(int i=-19;i<=19;i++){
			sum_x = 300+(i*15);
			arr_x.add(sum_x);
		}
		Random y = new Random();
		int y_cor = -700-num.nextInt(300);
		Collections.shuffle(arr_x);
		Image img_shield = null;
		InputStream in_shield = null;
		try{
			in_shield = Files.newInputStream(Paths.get("res/images/shield.jpg"));
			img_shield = new Image(in_shield);
		}finally{
			in_shield.close();
		}
		ImageView view = new ImageView(img_shield);
		view.setFitWidth(50);
		view.setFitHeight(50);
		view.setTranslateX(arr_x.get(arr_x.size()/2));
		view.setTranslateY(y_cor);
		pane.getChildren().add(view);
	}
	public static void main(String[] args){
		launch(args);
	}
	
	
	
}
