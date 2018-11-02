package application;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Block extends Application {
	private ArrayList<Rectangle> block_list=new ArrayList<Rectangle>(); 
	Ball balls=new Ball();
	Shield shield=new Shield();
	public ArrayList<Rectangle> getlist(){
		return block_list;
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		stage.initStyle(StageStyle.UNDECORATED);
		Pane pane = new Pane();
		drawblocklist(pane,this.block_list);
		Scene scene = new Scene(pane,600,800);
		stage.setScene(scene);
		moveblocks(this.block_list,pane);
		stage.show();
	}
	public void moveblocks(ArrayList<Rectangle> blocklist,Pane pane) {
		ParallelTransition main=new ParallelTransition();
		main.getChildren().add(balls.getBallAnimation());
		for(int i=0;i<blocklist.size();i++) {
			TranslateTransition a=new TranslateTransition(Duration.seconds(3.5),blocklist.get(i));
			a.setByY(1740);
			a.setCycleCount(1);
			main.getChildren().add(a);
		}
		Random num=new Random();
		if(num.nextInt(3)==0) {
			main.getChildren().add(shield.getShieldAnimation());
			main.play();
		}else {
			main.play();
		}
		
		main.setOnFinished(e ->{
			try {
				shield.createShield(pane);
			}catch(Exception fg) {
			}
			balls.createball(pane);
			this.drawblocklist(pane,this.block_list);
			this.moveblocks(this.block_list,pane);
		});
	}public void drawblocklist(Pane root,ArrayList<Rectangle> blocklist) {
		balls.createball(root);
		try {
			shield.createShield(root);
		}catch(Exception e) {
		}
		for(int i=0;i<5;i++) {
			Rectangle rect=new Rectangle((i*120),-800,120,120);
			rect.setArcHeight(15);
			rect.setArcWidth(15);
			rect.setFill(Color.AQUA);
			rect.setStroke(Color.BLACK);
			blocklist.add(i,rect);
			root.getChildren().add(rect);
		}
		
		//System.out.println(this.block_list.size());
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
