package application;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
		setTranslateX(getTranslateX()-30);			
	}
	void moveRight(){
		setTranslateX(getTranslateX()+30);
	}
}

public class Snake {
	private Pane root = new Pane();
	private Sprite player;
	private ArrayList<Sprite> snake = new ArrayList<>();
	private Text len;
	public Text getlen() {
		return len;
	}public void setlen(int length) {
		len.setText(Integer.toString(length));
	}
	public Pane createContent(Pane root){
		root.setPrefSize(600,800);
		int x=15;
		if(snake.size()!=0) {
			for(int i=0;i<snake.size();i++){
				player = new Sprite(300,600+(2*x),15,"snake",Color.BLUE);
				root.getChildren().add(player);
				x = x+15;
				snake.add(player);
			}
		}else {
			for(int i=0;i<5;i++){
				player = new Sprite(300,600+(2*x),15,"snake",Color.BLUE);
				if(i==4) {
					len=new Text(Integer.toString(snake.size()));
					len.setFont(new Font(20));
					len.setX(snake.get(0).getTranslateX()-5);
					len.setY(snake.get(0).getTranslateY());
					root.getChildren().add(len);
				}
				root.getChildren().add(player);
				x = x+15;
				snake.add(player);
			}
		}
		
		
		
		return root;
	}
	
	
	public ArrayList<Sprite> getSnake(){
		return snake;
	}

}