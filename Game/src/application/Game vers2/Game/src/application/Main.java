package application;
	

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class Main extends Application {
	Scene scene2;
	Rectangle r;
	ArrayList<Circle> Snakelist;
	Pane root;
	PaneOrganizer u;
	private boolean keyr=true;
	@Override
	public void start(Stage primaryStage) {
			Font.loadFont(this.getClass().getResource("ARCADECLASSIC.TTF").toExternalForm(),10);
			VBox b=new VBox();
			StackPane c=new StackPane();
			StackPane d=new StackPane();
			StackPane e=new StackPane();
			StackPane f=new StackPane();
			StackPane g=new StackPane();
			BorderPane a=new BorderPane();
			primaryStage.initStyle(StageStyle.UNDECORATED);
			Hyperlink b1=new Hyperlink("Play");
			Hyperlink b2=new Hyperlink("Resume");
			Hyperlink b3=new Hyperlink("Exit");
			Hyperlink b4=new Hyperlink("LeaderBoard");
			Label l1=new Label("Snake vs Block");
			Label l2=new Label("\n"+"\n"+"\n"+"\n"+"\n");
			l1.setFont(Font.font("ARCADECLASSIC",40));
			l1.setTextFill(Color.GREENYELLOW);
			b1.setBorder(Border.EMPTY);
			b2.setBorder(Border.EMPTY);
			b3.setBorder(Border.EMPTY);
			b4.setBorder(Border.EMPTY);
			b1.setUnderline(false);
			b2.setUnderline(false);
			b3.setUnderline(false);
			b4.setUnderline(false);
			b1.setFont(Font.font("ARCADECLASSIC",30));
			b2.setFont(Font.font("ARCADECLASSIC",30));
			b3.setFont(Font.font("ARCADECLASSIC",30));
			b4.setFont(Font.font("ARCADECLASSIC",30));
			b1.setTextFill(Color.AQUAMARINE);
			b2.setTextFill(Color.AQUAMARINE);
			b3.setTextFill(Color.AQUAMARINE);
			b4.setTextFill(Color.AQUAMARINE);
			a.setId("pane");
			d.getChildren().add(b1);
			e.getChildren().add(b2);
			f.getChildren().add(b3);
			g.getChildren().add(b4);
			b.getChildren().add(l2);
			b.getChildren().add(d);
			b.getChildren().add(e);
			b.getChildren().add(g);
			b.getChildren().add(f);
			c.getChildren().add(l1);
			a.setTop(c);
			a.setCenter(b);
			Scene scene1 = new Scene(a,400,400);
			scene1.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene1);
			primaryStage.show();
			b4.setOnAction(w ->{
				Date date=new Date();
				Random num=new Random();
				StackPane pl2=new StackPane();
				StackPane pl3=new StackPane();
				StackPane pl4=new StackPane();
				StackPane pl5=new StackPane();
				StackPane pl6=new StackPane();
				StackPane last=new StackPane();
				VBox pl=new VBox();
				BorderPane pl1=new BorderPane();
				pl2.getChildren().add(new Label("Score"+"			"+"Date&Time"));
				pl3.getChildren().add(new Label(num.nextInt(50)+50+"				"+date.toString()));
				pl4.getChildren().add(new Label(num.nextInt(50)+50+"				"+date.toString()));
				pl5.getChildren().add(new Label(num.nextInt(50)+50+"				"+date.toString()));
				pl6.getChildren().add(new Label(num.nextInt(50)+50+"				"+date.toString()));
				pl.getChildren().add(pl2);
				pl.getChildren().add(pl3);
				pl.getChildren().add(pl4);
				pl.getChildren().add(pl5);
				pl.getChildren().add(pl6);
				last.getChildren().add(pl);
				pl1.setCenter(last);
				pl1.setTop(null);
				pl1.setBottom(null);
				pl1.setRight(null);
				pl1.setLeft(null);
				Scene scene3=new Scene(pl1,400,400);
				primaryStage.setScene(scene3);
			});
			b3.setOnAction(u -> {
				System.exit(0);
			});
			b1.setOnAction(q ->{
				Stage stage2=new Stage();
				stage2.initStyle(StageStyle.UNDECORATED);
				primaryStage.close();
				u=new PaneOrganizer();
				root=u.getRoot();
				scene2=new Scene(root,400,400);
				boolean f1=true;
				int direction;
				scene2.setOnKeyReleased(tyu ->{
					switch(tyu.getCode()) {
						default:
							keyr=true;
							break;
					}
					
				});
				scene2.setOnKeyPressed(new abcdef());
//						y ->{
//						Snakelist=u.getSnakeList();
//						r=u.getrect();
//						int snakelength=u.getLength();
//						Circle SnakeHead=Snakelist.get(Snakelist.size()-1);
//					TranslateTransition op=null;
//					TranslateTransition op2=null;
//						
//						switch(y.getCode()) {
//							case LEFT:
//								//Timeline opi=new Timeline();
//								//opi.setCycleCount(1);
//								//KeyFrame apq=new KeyFrame(Duration.seconds(0.025),new collisions(),new KeyValue(Snakelist.get(0).centerXProperty(),Snakelist.get(0).centerXProperty().add(-20).doubleValue()),new KeyValue(Snakelist.get(1).centerXProperty(),Snakelist.get(1).centerXProperty().add(-20).doubleValue()),new KeyValue(r.yProperty(),r.yProperty().add(40).doubleValue()));
//								//opi.getKeyFrames().add(apq);
//								SequentialTransition ui=new SequentialTransition();
//								for(int i=0;i<4;i++) {	
//									//Timeline opi=new Timeline();
//									//opi.getKeyFrames().add(new KeyFrame(Duration.seconds(0.025),new KeyValue(Snakelist.get(i).centerXProperty(),Snakelist.get(i).centerXProperty().add(-20).doubleValue())));
//									//opi.setCycleCount(1);
//									//opi.play();
//									op=new TranslateTransition(Duration.seconds(0.025),Snakelist.get(i));
//									op.setByX(-20);
//									op.setCycleCount(1);
//									ui.getChildren().add(op);
//								}
//								playtrans pl=new playtrans(ui);
//								Thread h=new Thread(pl);
//								h.start();
//							try {
//								h.join();
//							} catch (InterruptedException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//								//opi.play();
////								opi.setOnFinished(uiopy ->{
////									if(r.getY()+50>Snakelist.get(0).getCenterY()-10&&r.getX()+200>Snakelist.get(0).getCenterX()) {
////										u.getRoot().getChildren().add(new Circle(200,280,10));
////										u.getRoot().getChildren().remove(r);
////									}
////									System.out.println(Snakelist.get(0).centerXProperty().doubleValue());
////									});
//								
//								break;
//							case RIGHT:
//								SequentialTransition ui2=new SequentialTransition();
//								//Timeline opia=new Timeline();
//								//opia.setCycleCount(1);
//								//KeyFrame apqg=new KeyFrame(Duration.seconds(0.025),new KeyValue(Snakelist.get(0).centerXProperty(),Snakelist.get(0).centerXProperty().add(20).doubleValue()),new KeyValue(Snakelist.get(1).centerXProperty(),Snakelist.get(1).centerXProperty().add(20).doubleValue()));
//								//opia.getKeyFrames().add(apqg);
//								for(int i=0;i<4;i++) {
//									//opia.getKeyFrames().get(0).getValues().add(new KeyValue(Snakelist.get(i).centerXProperty(),Snakelist.get(i).centerXProperty().add(20).doubleValue()));
//									//opia.setCycleCount(1);
//									//opia.play();
//									op2=new TranslateTransition(Duration.seconds(0.025),Snakelist.get(i));
//									op2.setByX(20);
//									op2.setCycleCount(1);
//									ui2.getChildren().add(op2);
//								}
//								//opia.play();
//								//opia.setOnFinished(uio ->{
//									//System.out.println(Snakelist.get(0).centerXProperty().doubleValue());
//							//});
//								playtrans pl1=new playtrans(ui2);
//								Thread j=new Thread(pl1);
//								j.start();
//							try {
//								j.join();
//							} catch (InterruptedException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//								//ui2.play();
//								break;
//						}
//						
//							//ShiftSnake(Snakelist,snakelength,(int)SnakeHead.getCenterX()-20,(int)SnakeHead.getCenterY());
//						
//							//SnakeHead.setCenterX(SnakeHead.getCenterX()-10);
//							//ShiftSnake(Snakelist,snakelength,(int)SnakeHead.getCenterX(),(int)SnakeHead.getCenterY()-20);
//							//Canvas New=new Canvas(400,400);
//							//GraphicsContext gc=New.getGraphicsContext2D();
//							//for(int i=0;i<Snakelist.size();i++) {
//								//gc.fillOval(Snakelist.get(i).getCenterX(),Snakelist.get(i).getCenterY(),20,20);
//								
//							//}
//							//h.getChildren().remove(0);
//							//h.getChildren().add(New);
//							//SnakeHead=Snakelist.get(Snakelist.size()-1);
//							//Canvas New2=new Canvas(400,400);
//							//GraphicsContext gc2=New2.getGraphicsContext2D();
//							//ShiftSnake(Snakelist,snakelength,(int)SnakeHead.getCenterX()+20,(int)SnakeHead.getCenterY());
//							//ShiftRestSnake(Snakelist);
//							//SnakeHead=Snakelist.get(Snakelist.size()-1);
//							//ShiftSnake(Snakelist,snakelength,(int)SnakeHead.getCenterX(),(int)SnakeHead.getCenterY()-20);
//							//for(int i=0;i<Snakelist.size();i++) {
//								//gc2.fillOval(Snakelist.get(i).getCenterX(),Snakelist.get(i).getCenterY(),20,20);
//							//}
//							//h.getChildren().remove(0);
//							//h.getChildren().add(New2);
//							//SnakeHead=Snakelist.get(Snakelist.size()-1);
//					
//					
//						//primaryStage.show();
//				
//				});
					//stage2.setOnShowing(k->{
						//ArrayList<Circle> snl=u.getSnakeList();
						//Circle snh=snl.get(snl.size()-1);
						//ShiftSnake(snl,5,(int)snh.getCenterX(),(int)snh.getCenterY()-20);
						//Canvas jnc=new Canvas(400,400);
						//GraphicsContext jnh=jnc.getGraphicsContext2D();
						//for(int i=0;i<snl.size();i++) {
							//jnh.fillOval(snl.get(i).getCenterX(),snl.get(i).getCenterY(),20,20);
						//}
						//h.getChildren().remove(0);
						//h.getChildren().add(jnc);
					//});
						stage2.setScene(scene2);
						stage2.show();
				
				});
			}
	public void ShiftSnake(ArrayList<Circle> snakelist,int snakelength,int x,int y) {
		Circle NewHead=new Circle(x,y,10);
		snakelist.add(NewHead);
		if(snakelist.size()>snakelength) {
			snakelist.remove(0);
		}
	}public void ShiftRestSnake(ArrayList<Circle> snakelist) {
		for(int i=snakelist.size()-2;i>=0;i++) {
			//snakelist.get(i)=new Circle((int)snakelist.get(i+1).getCenterX(),(int)snakelist.get(i+1).getCenterY()+20,10);
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	class collisions implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(r.getY()+50>Snakelist.get(0).getCenterY()-10) {
				root.getChildren().remove(r);
			}
		}
	}class abcdef implements EventHandler<KeyEvent>{

		@Override
		public synchronized void handle(KeyEvent arg0) {
			// TODO Auto-generated method stub
			Snakelist=u.getSnakeList();
			r=u.getrect();
			int snakelength=u.getLength();
			Circle SnakeHead=Snakelist.get(Snakelist.size()-1);
		TranslateTransition op=null;
		TranslateTransition op2=null;
			
			switch(arg0.getCode()) {
				case LEFT:
					//Timeline opi=new Timeline();
					//opi.setCycleCount(1);
					//KeyFrame apq=new KeyFrame(Duration.seconds(0.025),new collisions(),new KeyValue(Snakelist.get(0).centerXProperty(),Snakelist.get(0).centerXProperty().add(-20).doubleValue()),new KeyValue(Snakelist.get(1).centerXProperty(),Snakelist.get(1).centerXProperty().add(-20).doubleValue()),new KeyValue(r.yProperty(),r.yProperty().add(40).doubleValue()));
					//opi.getKeyFrames().add(apq);
					if(keyr) {
						SequentialTransition ui=new SequentialTransition();
						for(int i=0;i<4;i++) {	
							//Timeline opi=new Timeline();
							//opi.getKeyFrames().add(new KeyFrame(Duration.seconds(0.025),new KeyValue(Snakelist.get(i).centerXProperty(),Snakelist.get(i).centerXProperty().add(-20).doubleValue())));
							//opi.setCycleCount(1);
							//opi.play();
							op=new TranslateTransition(Duration.seconds(0.025),Snakelist.get(i));
							op.setByX(-20);
							op.setCycleCount(1);
							ui.getChildren().add(op);
						}
						ui.play();
						keyr=false;
					}
					//opi.play();
//					opi.setOnFinished(uiopy ->{
//						if(r.getY()+50>Snakelist.get(0).getCenterY()-10&&r.getX()+200>Snakelist.get(0).getCenterX()) {
//							u.getRoot().getChildren().add(new Circle(200,280,10));
//							u.getRoot().getChildren().remove(r);
//						}
//						System.out.println(Snakelist.get(0).centerXProperty().doubleValue());
//						});
					
					break;
				case RIGHT:
					if(keyr) {
						SequentialTransition ui2=new SequentialTransition();
						//Timeline opia=new Timeline();
						//opia.setCycleCount(1);
						//KeyFrame apqg=new KeyFrame(Duration.seconds(0.025),new KeyValue(Snakelist.get(0).centerXProperty(),Snakelist.get(0).centerXProperty().add(20).doubleValue()),new KeyValue(Snakelist.get(1).centerXProperty(),Snakelist.get(1).centerXProperty().add(20).doubleValue()));
						//opia.getKeyFrames().add(apqg);
						for(int i=0;i<4;i++) {
							//opia.getKeyFrames().get(0).getValues().add(new KeyValue(Snakelist.get(i).centerXProperty(),Snakelist.get(i).centerXProperty().add(20).doubleValue()));
							//opia.setCycleCount(1);
							//opia.play();
							op2=new TranslateTransition(Duration.seconds(0.025),Snakelist.get(i));
							op2.setByX(20);
							op2.setCycleCount(1);
							ui2.getChildren().add(op2);
						}
						//opia.play();
						//opia.setOnFinished(uio ->{
							//System.out.println(Snakelist.get(0).centerXProperty().doubleValue());
					//});
						ui2.play();
						keyr=false;
					}
					
					break;
			}
			
				//ShiftSnake(Snakelist,snakelength,(int)SnakeHead.getCenterX()-20,(int)SnakeHead.getCenterY());
			
				//SnakeHead.setCenterX(SnakeHead.getCenterX()-10);
				//ShiftSnake(Snakelist,snakelength,(int)SnakeHead.getCenterX(),(int)SnakeHead.getCenterY()-20);
				//Canvas New=new Canvas(400,400);
				//GraphicsContext gc=New.getGraphicsContext2D();
				//for(int i=0;i<Snakelist.size();i++) {
					//gc.fillOval(Snakelist.get(i).getCenterX(),Snakelist.get(i).getCenterY(),20,20);
					
				//}
				//h.getChildren().remove(0);
				//h.getChildren().add(New);
				//SnakeHead=Snakelist.get(Snakelist.size()-1);
				//Canvas New2=new Canvas(400,400);
				//GraphicsContext gc2=New2.getGraphicsContext2D();
				//ShiftSnake(Snakelist,snakelength,(int)SnakeHead.getCenterX()+20,(int)SnakeHead.getCenterY());
				//ShiftRestSnake(Snakelist);
				//SnakeHead=Snakelist.get(Snakelist.size()-1);
				//ShiftSnake(Snakelist,snakelength,(int)SnakeHead.getCenterX(),(int)SnakeHead.getCenterY()-20);
				//for(int i=0;i<Snakelist.size();i++) {
					//gc2.fillOval(Snakelist.get(i).getCenterX(),Snakelist.get(i).getCenterY(),20,20);
				//}
				//h.getChildren().remove(0);
				//h.getChildren().add(New2);
				//SnakeHead=Snakelist.get(Snakelist.size()-1);
		
		
			//primaryStage.show();
	
		}
		
	}

}
