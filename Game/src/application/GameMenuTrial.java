package application;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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

class playTimeline implements Runnable{
	private Timeline t;
	
	public playTimeline(Timeline t){
		this.t = t;
	}
	
	@Override
	public void run(){
		t.play();
	}
	
}

public class GameMenuTrial extends Application  {
	private boolean paused=false;
	private Pane root;
	private Pane pane_game = new Pane();
	private Scene scene_main, scene_instructions,scene_leaderboard,scene_newgame;
	private Block rect = new Block();
	private Ball ball = new Ball();
	private ArrayList<Group> block_list ;
	private ArrayList<Group> ball_list;
	private ArrayList<Sprite> snake_list = new ArrayList<>();
	private ArrayList<Group> block_list_2 = new ArrayList<>();
	private ArrayList<Group> ball_list_2 = new ArrayList<>();
	private ArrayList<TranslateTransition> blocks;
	private ArrayList<TranslateTransition> balls;
	private Snake snake;
	private Text Score=new Text("0");
	private int num_blocks = 0;
	private int num_balls = 0;
	private Text Score_name=new Text("Score:");
	public GameMenuTrial(){
		root = new Pane();
	}
	
	private class Timehandler1 implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event){
			block_list = new ArrayList<>();
//			int num = block_list_2.size();
			num_blocks = block_list_2.size();
			for(int i=0;i<5;i++){
				block_list.add(rect.generate_blocks(i));
				block_list_2.add(rect.generate_blocks(i));
			}
			blocks=Animate_blocks(block_list_2,num_blocks);
			if(!paused) {
				for(int u=0;u<blocks.size();u++) {
					blocks.get(u).play();
				}
			}else {
				for(int u=0;u<blocks.size();u++) {
					blocks.get(u).pause();
				}
			}
			
			for(int i=num_blocks;i<block_list_2.size();i++){
				pane_game.getChildren().add(block_list_2.get(i));
			}
		}
	}
	private class Timehandler_balls_1 implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event){
			ball_list = new ArrayList<>();
			Random r = new Random();
			int num_balls_random = r.nextInt(4)+1;
//			int num = ball_list_2.size();
			num_balls = ball_list_2.size();
			for(int i=0;i<num_balls_random;i++){
				ball_list.add(ball.createball());
				ball_list_2.add(ball.createball());
			}
			balls=Animate_balls(ball_list_2,num_balls);
			for(int o=0;o<balls.size();o++) {
				balls.get(o).play();
			}
			for(int i=num_balls;i<ball_list_2.size();i++){
				pane_game.getChildren().add(ball_list_2.get(i));
			}
		}
	}
	
	private class Timehandler2 implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event){
			
			for(int i=0;i<block_list_2.size();i++){
				if(snake_list.get(0).getBoundsInParent().intersects(block_list_2.get(i).getBoundsInParent())){
					block_list_2.get(i).setVisible(false);
					Text gh=(Text)block_list_2.get(i).getChildren().get(1);
					int h=Integer.parseInt(gh.getText());
					if(h>=snake_list.size()) {
						System.exit(0);
					}
					int index=snake_list.size()-1;
					for(int l=index;l>index-h;l--) {
						pane_game.getChildren().remove(snake_list.get(l));
						
				}	for(int l=index;l>index-h;l--) {
						snake_list.remove(l);
				}	
				snake.getlen().setText(Integer.toString(Integer.parseInt(snake.getlen().getText())-h));
				Score.setText(Integer.toString(Integer.parseInt(Score.getText())+h));
				}
			}
		}
	}
	private class Timehandler_balls_2  implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event){
			for(int i=0;i<ball_list_2.size();i++){
				if(snake_list.get(0).getBoundsInParent().intersects(ball_list_2.get(i).getBoundsInParent())){
					ball_list_2.get(i).setVisible(false);
					Text gh=(Text)ball_list_2.get(i).getChildren().get(1);
					int h=Integer.parseInt(gh.getText());
					int index=snake_list.size()-1;
					for(int j=0;j<h;j++) {
						int x=(int)snake_list.get(index).getTranslateX();
						int y=(int)snake_list.get(index).getTranslateY();
						int y_new=y+30;
						Sprite a=new Sprite(x,y_new,15,"snake",Color.BLUE);
						snake_list.add(a);
						pane_game.getChildren().add(a);
						index=snake_list.size()-1;
				}
					snake.getlen().setText(Integer.toString(Integer.parseInt(snake.getlen().getText())+h));
					
				}
			}
		}
	}
	
	public ArrayList<TranslateTransition> Animate_blocks(ArrayList<Group> list, int num){
		ArrayList<TranslateTransition> l=new ArrayList<>();
		TranslateTransition a;
		for(int i=num;i<list.size();i++) {
			a=new TranslateTransition(Duration.millis(5000));
			a.setByY(1750);
			a.setNode(list.get(i));
			l.add(a);
//			a.play();
		}
		return l;
	}
	
	public ArrayList<TranslateTransition> Animate_balls(ArrayList<Group> list, int num){
		ArrayList<TranslateTransition> l=new ArrayList<>();
		TranslateTransition a;
		for(int i=num;i<list.size();i++){
			a = new TranslateTransition(Duration.millis(5000));
			a.setByY(1750);
			a.setNode(list.get(i));
			l.add(a);
//			a.play();
		}
		return l;
	}
	
	public ArrayList<Group> get_ball_list(int num){
		for(int i=0;i<num;i++){
			ball_list.add(ball.createball());
		}
		return ball_list;
		
	}
	
	public ArrayList<Group> get_block_list(){
		Random k=new Random();
		int g=k.nextInt(5)+num_blocks;
		for(int i=0;i<5;i++){
			block_list.add(rect.generate_blocks(i));
		}
		int u=k.nextInt(snake_list.size())+1;
		Text j=(Text)block_list.get(g).getChildren().get(1);
		j.setText(Integer.toString(u));
		return block_list;
		
	}
	
	@Override
	public void start(Stage stage) throws Exception,InterruptedException{
		stage.initStyle(StageStyle.UNDECORATED);
		root.setPrefSize(800,800);
		
		// For MAIN MENU
		Label l = new Label("SNAKES V/S BLOCKS");
		l.setTranslateX(75);
		l.setTranslateY(50);
		l.setTextFill(Color.BLACK);
		l.setFont(l.getFont().font("SNAKES V/S BLOCKS", FontWeight.BOLD, 70));
		Image img = null;
		InputStream in = null;
		try{
			in = Files.newInputStream(Paths.get("D:\\eclipse-workspace\\Game\\src\\application\\snake2.jpg"));	
			img = new Image(in);
		}finally{	
			in.close();
		}
		ImageView view = new ImageView(img);
		ImageView view2 = new ImageView(img);
		ImageView view3 = new ImageView(img);
		view.setFitWidth(800);
		view.setFitHeight(800);
		view2.setFitWidth(800);
		view2.setFitHeight(800);
		view3.setFitWidth(800);
		view3.setFitHeight(800);
		
		VBox menu = new VBox(15);
		menu.setTranslateX(250);
		menu.setTranslateY(200);
		MenuButton btn1 = new MenuButton("RESUME");
		MenuButton btn2 = new MenuButton("NEW GAME");
		MenuButton btn3 = new MenuButton("LEADERBOARDS");
		MenuButton btn4 = new MenuButton("HELP");
		MenuButton btn5 = new MenuButton("EXIT");
		
		btn1.setDisable(true);
		
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
		
//		Pane pane_game = new Pane();
		Image img2 = null;
		InputStream in2 = null;
		try{
			in2 = Files.newInputStream(Paths.get("D:\\eclipse-workspace\\Game\\src\\application\\black.jpg"));	
			img2 = new Image(in2);
		}finally{	
			in2.close();
		}
		ImageView vie = new ImageView(img2);
		vie.setFitWidth(600);
		vie.setFitHeight(800);
		pane_game.getChildren().add(vie);
		Score_name.setX(0);
		Score_name.setY(100);
		Score_name.setFont(new Font(30));
		Score_name.setFill(Color.YELLOW);
		pane_game.getChildren().add(Score_name);
		Score.setX(80);
		Score.setY(100);
		Score.setFont(new Font(30));
		Score.setFill(Color.YELLOW);
		pane_game.getChildren().add(Score);
		snake = new Snake();
		snake_list = snake.getSnake();

		btn2.setOnMouseClicked(event -> {
			KeyFrame frame = new KeyFrame(Duration.millis(5000),new Timehandler1());
			KeyFrame frame_balls_1 = new KeyFrame(Duration.millis(5000),new Timehandler_balls_1());
			KeyFrame frame2 = new KeyFrame(Duration.millis(1250),new Timehandler2());
			KeyFrame frame_balls_2  = new KeyFrame(Duration.millis(100),new Timehandler_balls_2());
			
			Timeline t = new Timeline(frame);
			Timeline t_balls_1 = new Timeline(frame_balls_1);
			t.setCycleCount(Timeline.INDEFINITE);
			t_balls_1.setCycleCount(Timeline.INDEFINITE);
	
			Timeline t2 = new Timeline(frame2);
			Timeline t_balls_2 = new Timeline(frame_balls_2);
			t2.setCycleCount(Timeline.INDEFINITE);
			t_balls_2.setCycleCount(Timeline.INDEFINITE);
			
//			playTimeline p_block = new playTimeline(t);
//			playTimeline p_balls = new playTimeline(t_balls_1);
//			playTimeline p_block_collide = new playTimeline(t2);
//			playTimeline p_balls_collide = new playTimeline(t_balls_2);
//			
//			Thread thread1 = new Thread(p_block);
//			Thread thread2 = new Thread(p_balls);
//			Thread thread3 = new Thread(p_block_collide);
//			Thread thread4 = new Thread(p_balls_collide);
//			
//			thread1.start();
//			thread2.start();
//			thread3.start();
//			thread4.start();
//			
//			try {
//				thread1.join();
//				thread2.join();
//				thread3.join();
//				thread4.join();
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			
			t.play();
			t_balls_1.play();
			t2.play();
			t_balls_2.play();
			
			scene_newgame = new Scene(snake.createContent(pane_game));
			scene_newgame.setOnKeyPressed(e -> {
				switch (e.getCode()){
					case A:
						if(!paused) {
							for(int i=0;i<snake_list.size();i++){
								snake_list.get(i).moveLeft();
								snake.getlen().setX(snake_list.get(0).getTranslateX()-8);
							}
						}
						break;
					case D:
						if(!paused) {
							for(int i=0;i<snake_list.size();i++){
								snake_list.get(i).moveRight();
								snake.getlen().setX(snake_list.get(0).getTranslateX()-5);
							}
						}
						break;		
					case P:
						paused=true;
						for(int i=0;i<blocks.size();i++) {
							blocks.get(i).pause();
						}
						for(int i=0;i<balls.size();i++) {
							balls.get(i).pause();
						}
						t.pause();
						t_balls_1.pause();
						t2.pause();
						t_balls_2.pause();
							break;
					case R:
						paused=false;
						
						for(int i=0;i<blocks.size();i++) {
							blocks.get(i).play();
						}
						for(int i=0;i<balls.size();i++) {
							balls.get(i).play();
						}
						t.play();
						t_balls_1.play();
						t2.play();
						t_balls_2.play();
							break;
//					default:
////						t.stop();
////						t_balls_1.stop();
////						t2.stop();
////						t_balls_2.stop();
//						t.pause();
//						t_balls_1.pause();
//						t2.pause();
//						t_balls_2.pause();
//						Stage paused=new Stage();
//						paused.initModality(Modality.APPLICATION_MODAL);
//						paused.initStyle(StageStyle.UNDECORATED);
//						paused.initOwner(stage);
//						VBox h=new VBox();
//						StackPane st=new StackPane();
//						StackPane st2=new StackPane();
//						StackPane st3=new StackPane();
//						StackPane st4=new StackPane();
//						Button res=new Button("Resume");
//						Button back=new Button("Back");
//						Button exit=new Button("Exit");
//						st.getChildren().add(res);
//						st2.getChildren().add(back);
//						st3.getChildren().add(exit);
//						h.getChildren().add(st);
//						h.getChildren().add(st2);
//						h.getChildren().add(st3);
//						st4.getChildren().add(h);
//						Scene pauseds=new Scene(st4,200,300);
//						paused.setX(500);
//						paused.setY(200);
//						res.setOnAction(resume ->{
//							t.play();
//							t_balls_1.play();
//							t2.play();
//							t_balls_2.play();
//							paused.close();
//						});
//						back.setOnAction(backed ->{
//							t.stop();
//							t_balls_1.stop();
//							t2.stop();
//							t_balls_2.stop();
//							stage.setScene(scene_main);
//							paused.close();
//						});
//						exit.setOnAction(exited ->{
//							System.exit(0);
//							paused.close();
//						});
//						paused.setScene(pauseds);
//						paused.show();
//						break;
				}
				
			});
			stage.setScene(scene_newgame);	
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
		
		scene_leaderboard = new Scene(pane_leaderboard,800,800);
		//
		
		
		// FOR INSTRUCTIONS
		Pane pane_instructions = new Pane();
		Label l2 = new Label("INSTRUCTIONS");
		l2.setTranslateX(150);
		l2.setTranslateY(40);
		l2.setTextFill(Color.BLACK);
		l2.setFont(l2.getFont().font("INSTRUCTIONS",FontWeight.BOLD,70));
		Image img_shield = null; Image img_magnet = null; Image img_destroy = null;
		InputStream in_shield = null; InputStream in_magnet = null; InputStream in_destroy = null; 
		try{
			in_shield = Files.newInputStream(Paths.get("D:\\eclipse-workspace\\Game\\src\\application\\shield.jpg"));
			in_magnet  = Files.newInputStream(Paths.get("D:\\eclipse-workspace\\Game\\src\\application\\Magnet.png"));
			in_destroy = Files.newInputStream(Paths.get("D:\\eclipse-workspace\\Game\\src\\application\\destroy.jpg"));
			img_shield = new Image(in_shield); img_magnet = new Image(in_magnet); img_destroy = new Image(in_destroy);
		}finally{	
			in_shield.close();
			in_magnet.close();
			in_destroy.close();
		}
		ImageView s_view = new ImageView(img_shield);
		ImageView m_view = new ImageView(img_magnet);
		ImageView d_view = new ImageView(img_destroy);
		s_view.setFitWidth(40); s_view.setFitHeight(40);  s_view.setTranslateX(30); s_view.setTranslateY(580);
		m_view.setFitWidth(40); m_view.setFitHeight(40);  m_view.setTranslateX(30); m_view.setTranslateY(680); 		
		d_view.setFitWidth(40); d_view.setFitHeight(40);  d_view.setTranslateX(30); d_view.setTranslateY(510);	
		String s2 = " Each block has a value, which represents the points made by the"; 
		String s2_1=" player when the snake eats it. After eating the block the length";
		String s2_2=" of the snake decreases by the value of the block";
		String s3 = " The game ends when the snake is not able to eat a ";
		String s3_ = " block completely. ";
		String s4 = " The length of the snake increases by the value of ";
		String s4_ = " the ball if it eats the ball. ";
		String s5 = " Destroy all the blocks present on the screen. ";
		String s6 = " Lets you to eat any block without decreasing the ";
		String s6_= " snake's length. ";
		String s7 = " Lets the snake to collect coins which are within a ";
		String s7_ = " certain distance from the head of the snake. ";
		Text t3 = new Text(s3); Text t3_ = new Text(s3_);
		Text t4 = new Text(s4); Text t5 = new Text(s5); Text t6 = new Text(s6); Text t6_ = new Text(s6_);
		Text t7 = new Text(s7); Text t7_ = new Text(s7_);
		Text t2 = new Text(s2); Text t2_1 = new Text(s2_1); Text t2_2 = new Text(s2_2);
		Text t4_ = new Text(s4_);
		t2.setTranslateX(75); t2.setTranslateY(170); t2_1.setTranslateX(75); t2_1.setTranslateY(210); 
		t2_2.setTranslateX(75); t2_2.setTranslateY(250); 
		t3.setTranslateX(75); t3.setTranslateY(325); t3_.setTranslateX(75); t3_.setTranslateY(362); 
		t4.setTranslateX(75); t4.setTranslateY(430); t4_.setTranslateX(75); t4_.setTranslateY(470);  
		t5.setTranslateX(75); t5.setTranslateY(540);
		t6.setTranslateX(75); t6.setTranslateY(600); t6_.setTranslateX(75); t6_.setTranslateY(640);
		t7.setTranslateX(75); t7.setTranslateY(700); t7_.setTranslateX(75); t7_.setTranslateY(740);	
		t4.setFill(Color.BLACK); t5.setFill(Color.BLACK); 
		t6.setFill(Color.BLACK); t7.setFill(Color.BLACK);
		t2.setFont(t2.getFont().font(s3, FontWeight.BOLD,20)); t2_1.setFont(t2_1.getFont().font(s3, FontWeight.BOLD,20));
		t2_2.setFont(t2_2.getFont().font(s3, FontWeight.BOLD,20));
		t3.setFont(t3.getFont().font(s3, FontWeight.BOLD,20)); t3_.setFont(t3_.getFont().font(s3, FontWeight.BOLD,20));
		t4.setFont(t4.getFont().font(s3, FontWeight.BOLD,20));
		t4_.setFont(t4_.getFont().font(s3, FontWeight.BOLD,20));
		t5.setFont(t5.getFont().font(s3, FontWeight.BOLD,20));
		t6.setFont(t6.getFont().font(s3, FontWeight.BOLD,20)); t6_.setFont(t6_.getFont().font(s3, FontWeight.BOLD,20));
		t7.setFont(t7.getFont().font(s3, FontWeight.BOLD,20)); t7_.setFont(t7_.getFont().font(s3, FontWeight.BOLD,20));
			
		MenuButton back = new MenuButton("BACK");
		pane_instructions.getChildren().add(view2);
		pane_instructions.getChildren().add(l2);
		pane_instructions.getChildren().add(back);
		pane_instructions.getChildren().addAll(t2,t2_1,t2_2,t3,t3_,t4,t4_,t5,t6,t6_,t7_,t7,s_view,m_view,d_view);
		
		scene_instructions = new Scene(pane_instructions,800,800);
		
		back1.setOnMouseClicked(event -> stage.setScene(scene_main));
		back.setOnMouseClicked(event -> stage.setScene(scene_main));
		stage.show();	
	}
			
	public static void main(String[] args){
		launch(args);
	}

}
