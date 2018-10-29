import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class GameMenuTrial extends Application {
	
	private Menu menu;
	
	@Override
	public void start(Stage stage) throws Exception{
		Pane root = new Pane();
		root.setPrefSize(800,600);
		Label l = new Label("SNAKES V/S BLOCKS");
		l.setTranslateX(85);
		l.setTranslateY(50);
		l.setTextFill(Color.BLACK);
		l.setFont(l.getFont().font("SNAKES V/S BLOCKS", FontWeight.BOLD, 70));
		
		Image img = null;
		InputStream in = null;
		try{
			in = Files.newInputStream(Paths.get("res/images/snake2.jpg"));	
			img = new Image(in);
		}finally{	
			in.close();
		}
		ImageView view = new ImageView(img);
		view.setFitWidth(800);
		view.setFitHeight(600);
		menu = new Menu();
		root.getChildren().add(view);
		root.getChildren().add(l);
		root.getChildren().add(menu);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}

	private class Menu extends Parent{
		public Menu(){
			VBox menu = new VBox(15);
			menu.setTranslateX(250);
			menu.setTranslateY(200);
			MenuButton btn1 = new MenuButton("RESUME");
			MenuButton btn2 = new MenuButton("NEW GAME");
			MenuButton btn3 = new MenuButton("LEADERBOARDS");
			MenuButton btn4 = new MenuButton("HELP");
			MenuButton btn5 = new MenuButton("EXIT");
			
			btn5.setOnMouseClicked(event -> {
				System.exit(0);
			});
			menu.getChildren().addAll(btn1,btn2,btn3,btn4,btn5);
			
			getChildren().addAll(menu);
		}
	}
	
	private static class MenuButton extends StackPane{
		private Text text;
		
		public MenuButton(String name){
			text = new Text(name);
			text.setFont(text.getFont().font(22));
			Rectangle back = new Rectangle(200,40);
			back.setOpacity(0.7);
			back.setFill(Color.DARKKHAKI);
			GaussianBlur b = new GaussianBlur(3.2);
			back.setEffect(b);
			
			setAlignment(Pos.CENTER_LEFT);
			getChildren().addAll(back,text);
			
			setOnMouseEntered(event -> {
				back.setTranslateX(10);
				text.setTranslateX(10);
				back.setFill(Color.BLACK);
				text.setFill(Color.GREY);
			});
			setOnMouseExited(event -> {
				back.setTranslateX(0);
				text.setTranslateX(0);
				back.setFill(Color.DARKKHAKI);
				text.setFill(Color.BLACK);
			});			
		}	
	}
	
	public static void main(String[] args){
		launch(args);
	}

}
