package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import java.util.*;

class Sprite extends Circle {
	boolean dead = false;
	final String type;
	Sprite(int x,int y,int r, String type,Color color){
		super(r,color);
		this.type = type;
		setTranslateX(x);
		setTranslateY(y);
	}
	void moveLeft(){
		setTranslateX(getTranslateX()-15);			
	}
	void moveRight(){
		setTranslateX(getTranslateX()+15);
	}
	
}





public class Snake extends Application{
	private Pane root = new Pane();
	private Sprite player;
	private ArrayList<Sprite> snake = new ArrayList<>();

	
	public Pane createContent(Pane root){
		root.setPrefSize(600,800);
		int x=15;
		for(int i=0;i<5;i++){
			player = new Sprite(300,600+(2*x),15,"snake",Color.BLUE);
			root.getChildren().add(player);
			x = x+15;
			snake.add(player);
		}
		return root;
	}
	
	
	
	@Override
	public void start(Stage stage) throws Exception{
		Scene scene = new Scene(createContent(root));
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()){
				case A:
					for(int i=0;i<snake.size();i++){
						snake.get(i).moveLeft();
					}
					break;
				case D:
					for(int i=0;i<snake.size();i++){
						snake.get(i).moveRight();
					}
					break;
					
			}
		});
		stage.setScene(scene);	
		stage.show();
	}
	
	public ArrayList<Sprite> getSnake(){
		return snake;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
