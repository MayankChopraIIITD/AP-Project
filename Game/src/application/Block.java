import java.util.ArrayList;
import java.util.Collections;
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

public class Block {
	private ArrayList<Rectangle> block_list=new ArrayList<Rectangle>(); 
	Shield shield=new Shield();
	public ArrayList<Rectangle> getlist(){
		return block_list;
	}
	public void moveblocks(ArrayList<Rectangle> blocklist,Pane pane) {
		ParallelTransition main=new ParallelTransition();
		for(int i=0;i<blocklist.size();i++) {
			TranslateTransition a=new TranslateTransition(Duration.seconds(7),blocklist.get(i));
			a.setByY(1750);
			a.setCycleCount(1);
			main.getChildren().add(a);
		}
		Random num=new Random();
		if(num.nextInt(6)==2) {
			main.getChildren().add(shield.getShieldAnimation());
		}
		main.play();
		main.setOnFinished(e ->{
			try {
				shield.createShield(pane);
			}catch(Exception fg) {
			}
			this.drawblocklist(pane,this.block_list);
			this.moveblocks(this.block_list,pane);
		});
		
	}public void drawblocklist(Pane root,ArrayList<Rectangle> blocklist) {
		try {
			shield.createShield(root);
		}catch(Exception e) {
		}
		Random r = new Random();
		int type_list = r.nextInt(12)+1;
		for(int i=0;i<5;i++){
			Rectangle rect = new Rectangle((i*120),-900,120,120);
			rect.setArcHeight(15);
			rect.setArcWidth(15);
			rect.setFill(Color.AQUA);
			rect.setStroke(Color.BLACK);
			blocklist.add(rect);
			if(type_list==1 || type_list==12){
				if(i>2){
					rect.setTranslateX(1000);
				}
			}
			else if(type_list==2 || type_list==11){
				if(i>3){
					rect.setTranslateX(1000);
				}
			}
			else if(type_list==3){
				if(i==3){
					rect.setTranslateX(1000);
				}
			}
			else if(type_list==4 || type_list==9){
				if(i<2){
					rect.setTranslateX(-900);
				}
			}
			else if(type_list==5){
				if(i==1){
					rect.setTranslateX(-900);
				}
			}
			else if(type_list==6 || type_list==7){
				if(i==0){
					rect.setTranslateX(-900);
				}
			}
			root.getChildren().add(rect);
		}
		block_list = blocklist;
	}
}
