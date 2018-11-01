import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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

class MenuButton extends StackPane{
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

public class GameMenuTrial extends Application {
	
	private Pane root;
	private Scene scene_main, scene_instructions,scene_leaderboard,scene_newgame;
	public GameMenuTrial(){
		root = new Pane();
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		root.setPrefSize(800,600);
		
		// For MAIN MENU
		Label l = new Label("SNAKES V/S BLOCKS");
		l.setTranslateX(75);
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
		ImageView view2 = new ImageView(img);
		ImageView view3 = new ImageView(img);
		view.setFitWidth(800);
		view.setFitHeight(600);
		view2.setFitWidth(800);
		view2.setFitHeight(600);
		view3.setFitWidth(800);
		view3.setFitHeight(600);
		

		VBox menu = new VBox(15);
		menu.setTranslateX(250);
		menu.setTranslateY(200);
		MenuButton btn1 = new MenuButton("RESUME");
		MenuButton btn2 = new MenuButton("NEW GAME");
		MenuButton btn3 = new MenuButton("LEADERBOARDS");
		MenuButton btn4 = new MenuButton("HELP");
		MenuButton btn5 = new MenuButton("EXIT");
		
		btn3.setOnMouseClicked(event -> stage.setScene(scene_leaderboard));
		
		btn4.setOnMouseClicked(event -> stage.setScene(scene_instructions));	
	
		btn5.setOnMouseClicked(event -> {
			System.exit(0);
		});
		menu.getChildren().addAll(btn1,btn2,btn3,btn4,btn5);
	
		root.getChildren().add(view);
		root.getChildren().add(l);
		root.getChildren().add(menu);
		scene_main = new Scene(root);
		stage.setScene(scene_main);
		
		// NEW GAME
		
		Pane pane_game = new Pane();
		Snake snake = new Snake();
		ArrayList<Sprite> snak = snake.getSnake();
		btn2.setOnMouseClicked(event -> {
			Scene scene = new Scene(snake.createContent());
			scene.setOnKeyPressed(e -> {
				switch (e.getCode()){
					case A:
						for(int i=0;i<snak.size();i++){
							snak.get(i).moveLeft();
						}
						break;
					case D:
						for(int i=0;i<snak.size();i++){
							snak.get(i).moveRight();
						}
						break;
						
				}
			});
			stage.setScene(scene);	
			stage.show();
		});
		
		
		// FOR LEADERBOARD
		Pane pane_leaderboard = new Pane();
		Label l3 = new Label("LEADERBOARD");
		Label sno = new Label("Sno.");
		Label score = new Label("SCORE");
		Label date = new Label("DATE");
		l3.setTranslateX(150);
		l3.setTranslateY(40);
		l3.setTextFill(Color.BLACK);
		l3.setFont(l3.getFont().font("LEADERBOARD",FontWeight.BOLD,70));

		sno.setTranslateX(40); 	  sno.setTranslateY(150);  sno.setTextFill(Color.BLACK);
		score.setTranslateX(200); score.setTranslateY(150); score.setTextFill(Color.BLACK);
		date.setTranslateX(400);  date.setTranslateY(150);  date.setTextFill(Color.BLACK);	
		sno.setFont(sno.getFont().font("Sno.",FontWeight.BOLD,40));
		score.setFont(score.getFont().font("SCORE",FontWeight.BOLD,40));
		date.setFont(date.getFont().font("DATE",FontWeight.BOLD,40));
		
		MenuButton back1 = new MenuButton("BACK");
		pane_leaderboard.getChildren().add(view3);
		pane_leaderboard.getChildren().add(l3);
		pane_leaderboard.getChildren().add(back1);
		pane_leaderboard.getChildren().add(sno);
		pane_leaderboard.getChildren().add(score);
		pane_leaderboard.getChildren().add(date);
		
		scene_leaderboard = new Scene(pane_leaderboard,800,600);
		//
		
		
		// FOR INSTRUCTIONS
		Pane pane_instructions = new Pane();
		Label l2 = new Label("INSTRUCTIONS");
		l2.setTranslateX(150);
		l2.setTranslateY(40);
		l2.setTextFill(Color.BLACK);
		l2.setFont(l2.getFont().font("INSTRUCTIONS",FontWeight.BOLD,70));
		
		MenuButton back = new MenuButton("BACK");
		pane_instructions.getChildren().add(view2);
		pane_instructions.getChildren().add(l2);
		pane_instructions.getChildren().add(back);
		scene_instructions = new Scene(pane_instructions,800,600);
		
		
		back1.setOnMouseClicked(event -> stage.setScene(scene_main));
		back.setOnMouseClicked(event -> stage.setScene(scene_main));
		stage.show();	
	}
			
	public static void main(String[] args){
		launch(args);
	}

}
