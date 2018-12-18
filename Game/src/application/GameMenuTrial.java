import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.*;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
// TODO: Auto-generated Javadoc
class MenuButton extends StackPane{
	private Text text;
	private Rectangle back;
	public MenuButton(String name){
		text = new Text(name);
		text.setFont(text.getFont().font(22));
		back = new Rectangle(200,40);
		back.setOpacity(0.7);
//		back.setFill(Color.DARKKHAKI);
		GaussianBlur b = new GaussianBlur(3.2);
		back.setEffect(b);
		
		setAlignment(Pos.CENTER_LEFT);
		getChildren().addAll(back,text);
		
//		setOnMouseEntered(event -> {
//			back.setTranslateX(10);
//			text.setTranslateX(10);
//			back.setFill(Color.BLACK);
//			text.setFill(Color.GREY);
//		});
//		setOnMouseExited(event -> {
//			back.setTranslateX(0);
//			text.setTranslateX(0);
//			back.setFill(Color.DARKKHAKI);
//			text.setFill(Color.BLACK);
//		});			
	}
	
	public void button_color_mouse_exit(Color text_color ,Color button_color){
		setOnMouseExited(event -> {
			back.setTranslateX(0);
			text.setTranslateX(0);
			back.setFill(button_color);
			text.setFill(text_color);
		});
	}
	
	public void button_color_mouse_enter(Color text_color, Color button_color){
		setOnMouseEntered(event -> {
			back.setTranslateX(10);
			text.setTranslateX(10);
			back.setFill(button_color);
			text.setFill(text_color);
		});
	}
	
	public void set_text_color(Color c){
		text.setFill(c);
	}
	
	public void set_button_color(Color c){
		back.setFill(c);		
	}
	
}

/**
 * The Class GameMenuTrial.
 */
public class GameMenuTrial extends Application  {
	private ArrayList<player_state> pls=new ArrayList<>();
	/** The btn 1. */
	private MenuButton btn1;
	
	/** The player. */
	private int[] player;
	
	/** The noresume. */
	private boolean noresume;
	
	/** The keyr. */
	private boolean keyr=true;
	
	/** The shield. */
	private boolean shield=false;
	
	/** The magnet. */
	private boolean magnet=false;
	
	/** The paused. */
	private boolean paused=false;
	
	/** The root. */
	private Pane root;
	
	/** The pane game. */
	private Pane pane_game = new Pane();
	
	/** The pane game 1. */
	private Pane pane_game_1=new Pane();
	
	/** The pane leaderboard. */
	private Pane pane_leaderboard;
	
	/** The Pause menu. */
	private Pane PauseMenu;
	
	/** The scene resgame. */
	private Scene scene_main, scene_instructions,scene_leaderboard,scene_newgame,scene_resgame;
	
	/** The pause game. */
	private Scene pause_game;
	
	/** The rect. */
	private Block rect = new Block();
	
	/** The ball. */
	private Ball ball = new Ball();
	
	/** The new shield. */
	private Shield new_shield = new Shield();
	
	/** The new destroy. */
	private Destroyer new_destroy = new Destroyer();
	
	/** The new magnet. */
	private Magnet new_magnet = new Magnet();
	
	/** The block list. */
	private ArrayList<Group> block_list ;
	
	/** The ball list. */
	private ArrayList<Group> ball_list;
	
	/** The snake list. */
	private ArrayList<Sprite> snake_list = new ArrayList<>();
	
	/** The block list 2. */
	private ArrayList<Group> block_list_2 = new ArrayList<>();
	
	/** The ball list 2. */
	private ArrayList<Group> ball_list_2 = new ArrayList<>();

/** The shield list 2. */
//	private ArrayList<Group> random_block_list_2 = new ArrayList<>();
	private ArrayList<Group> shield_list_2 = new ArrayList<>();
	
	/** The destroy list 2. */
	private ArrayList<Group> destroy_list_2 = new ArrayList<>();
	
	/** The magnet list 2. */
	private ArrayList<Group> magnet_list_2 = new ArrayList<>();
	
	/** The t. */
	private Timeline t;
	
	/** The t balls 1. */
	private Timeline t_balls_1;
	
	/** The t 2. */
	private Timeline t2;
	
	/** The t balls 2. */
	private Timeline t_balls_2;
	
	/** The t shield 1. */
	private Timeline t_shield_1;
	
	/** The t shield 2. */
	private Timeline t_shield_2;
	
	/** The t destroy 1. */
	private Timeline t_destroy_1;
	
	/** The t destroy 2. */
	private Timeline t_destroy_2;
	
	/** The t magnet 1. */
	private Timeline t_magnet_1;
	
	/** The t magnet 2. */
	private Timeline t_magnet_2;
	
	/** The t magnet 3. */
	private Timeline t_magnet_3;
	
	/** The t shield 3. */
	private Timeline t_shield_3;
	
	/** The t balls 3. */
	private Timeline t_balls_3;
	
	private Timeline t_wall_1;
	private Timeline t_wall_2;
	
	private Wall new_wall = new Wall();
	private ArrayList<Group> wall_list_2 = new ArrayList<>();
	
	private ArrayList<TranslateTransition> walls;
	/** The blocks. */
	private ArrayList<TranslateTransition> blocks;
	
	/** The balls. */
	private ArrayList<TranslateTransition> balls;
	
	/** The shields. */
	private ArrayList<TranslateTransition> shields;
	
	/** The destroys. */
	private ArrayList<TranslateTransition> destroys;
	
	/** The magnets. */
	private ArrayList<TranslateTransition> magnets;
	
	/** The snake. */
	private Snake snake;
	
	/** The Score. */
	private Text Score=new Text("0");
	
	/** The Score 1. */
	private Text Score_1=new Text("0");
	
	/** The num blocks 2. */
	private int num_blocks,num_blocks_2 = 0; 
	
	/** The num balls. */
	private int num_balls = 0;
	
	/** The num shield. */
	private int num_shield = 0;
	
	/** The num destroy. */
	private int num_destroy = 0;
	
	/** The num magnet. */
	private int num_magnet = 0;
	
	private int num_walls = 0;
	
	/** The Score name. */
	private Text Score_name=new Text("Score:");
	
	/** The Score name 1. */
	private Text Score_name_1=new Text("Score:");
	
	/** The Score list. */
	private ArrayList<Integer> Score_list = new ArrayList<>();
	
	/** The inres. */
	private boolean inres=false;
	
	/** The for shield. */
	private ImageView for_shield = null;
	
	/** The for destroy. */
	private ImageView for_destroy = null;
	
	/** The for magnet. */
	private ImageView for_magnet = null;
	
	private ImageView for_wall = null;
	
	/** The check shield. */
	/* if one of the below is true that means one of them has appeared and any of the rest of 
	 * and any of the rest of two cannot appear */
	private boolean check_shield = false;
	
	/** The check destroyer. */
	private boolean check_destroyer = false;
	
	/** The check magnet. */
	private boolean check_magnet = false;
	
	/** The check block type. */
	private boolean check_block_type = false;
	
	/** The token gen. */
	private Random token_gen ;
	
	/** The token gen value. */
	private int token_gen_value;
	
	/** The shield start time. */
	private double shield_start_time = 0;
	
	/** The shield end time. */
	private double shield_end_time = 0;
	
	/** The magnet start time. */
	private double magnet_start_time;
	
	/** The magnet end time. */
	private double magnet_end_time;
	
	/**
	 * Instantiates a new game menu trial.
	 */
	public GameMenuTrial(){
		root = new Pane();
		
	}
	// num_blocks_2, blocks_2,random_block_list_2
	
	// TO Produce Animations of blocks and all of the tokens
	
	/**
	 * The Class Timehandler1.
	 */
	
	private class Timehandler1 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
//			block_list = new ArrayList<>();
//			int num = block_list_2.size();
			check_block_type = true;
			num_blocks = block_list_2.size();
			Random random_num = new Random();
			int block_index = random_num.nextInt(5)+ num_blocks;
			for(int i=0;i<5;i++){
				int final_ = random_num.nextInt(5);
				if(final_==3){
//				block_list.add(rect.generate_blocks(i,snake_list.size()));
					block_list_2.add(rect.generate_blocks((i+final_),snake_list.size()));
				}
				else{
					block_list_2.add(rect.generate_blocks((i),snake_list.size()));
				}
			}
			int block_value = random_num.nextInt(snake_list.size())+1;
			Text j = (Text)block_list_2.get(block_index).getChildren().get(1);
			j.setText(Integer.toString(block_value));
			block_list_2.get(block_index).getChildren().set(1,j);
			blocks=Animate_blocks(block_list_2,num_blocks);
			for(int u=0;u<blocks.size();u++) {
					blocks.get(u).play();
			}
			for(int i=num_blocks;i<block_list_2.size();i++){
				if(!inres) {
					pane_game.getChildren().add(block_list_2.get(i));
				}else {
					pane_game_1.getChildren().add(block_list_2.get(i));
				}
			}
		}
	}
	
	/**
	 * The Class Timehandler_balls_1.
	 */
	private class Timehandler_balls_1 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			ball_list = new ArrayList<>();
			Random r = new Random();
			int num_balls_random = r.nextInt(4)+1;
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
				if(!inres) {
					pane_game.getChildren().add(ball_list_2.get(i));
				}else {
					pane_game_1.getChildren().add(ball_list_2.get(i));
				}
			}
		}
	}
	
	/**
	 * The Class Timehandler_Destroy_1.
	 */
	private class Timehandler_Destroy_1 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			Random r = new Random();
			int chance = r.nextInt(5);
//			num_shield = shield_list_2.size();
			if(chance == 3){
				num_destroy = destroy_list_2.size();
				check_destroyer = true;
				double max = -10;
				double min = 10000;
				for(int i=num_balls;i<ball_list_2.size();i++){
					if(max<ball_list_2.get(i).getTranslateX()){
						max = ball_list_2.get(i).getTranslateX();
					}
					if(min>ball_list_2.get(i).getTranslateX()){
						min = ball_list_2.get(i).getTranslateX();
					}
				}
				for_destroy = new_destroy.generateDestroyer();
				Group a=new Group(for_destroy);
				destroy_list_2.add(a);
				destroys = Animate_destroyer(destroy_list_2,num_destroy);
				for(int u=0;u<destroys.size();u++) {
					destroys.get(u).play();
				}
//				System.out.println(num_shield);
				for(int i=num_destroy;i<destroy_list_2.size();i++){
				if(!inres) {
					pane_game.getChildren().add(destroy_list_2.get(i));
				}else {
					pane_game_1.getChildren().add(destroy_list_2.get(i));
				}
			}
		  }	
		}
	}
	
	/**
	 * The Class Timehandler_Magnet_1.
	 */
	private class Timehandler_Magnet_1 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			Random r = new Random();
//			int chance = r.nextInt(5);
//			num_shield = shield_list_2.size();
			int chance=3;
			if(chance == 3 ){
				num_magnet = magnet_list_2.size();
				check_magnet = true;
				double max = -10;
				double min = 10000;
				for(int i=num_balls;i<ball_list_2.size();i++){
					if(max<ball_list_2.get(i).getTranslateX()){
						max = ball_list_2.get(i).getTranslateX();
					}
					if(min>ball_list_2.get(i).getTranslateX()){
						min = ball_list_2.get(i).getTranslateX();
					}
				}
				for_magnet = new_magnet.generateMagnet();
				Group a=new Group(for_magnet);
				magnet_list_2.add(a);
				magnets = Animate_Magnet(magnet_list_2,num_destroy);
				for(int u=0;u<magnets.size();u++) {
					magnets.get(u).play();
				}
//				System.out.println(num_shield);
				for(int i=num_magnet;i<magnet_list_2.size();i++){
				if(!inres) {
					pane_game.getChildren().add(magnet_list_2.get(i));
				}else {
					pane_game_1.getChildren().add(magnet_list_2.get(i));
				}
			}
		  }	
		}
	}
	
	
	/**
	 * The Class Timehandler_Shield_1.
	 */
	private class Timehandler_Shield_1 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			Random r = new Random();
			int chance = r.nextInt(5);
//			num_shield = shield_list_2.size();
			if(chance == 3 || chance==2 ){
				num_shield = shield_list_2.size();
				check_shield = true;
				double max = -10;
				double min = 10000;
				for(int i=num_balls;i<ball_list_2.size();i++){
					if(max<ball_list_2.get(i).getTranslateX()){
						max = ball_list_2.get(i).getTranslateX();
					}
					if(min>ball_list_2.get(i).getTranslateX()){
						min = ball_list_2.get(i).getTranslateX();
					}
				}
				for_shield = new_shield.generateShield();
				Group a=new Group(for_shield);
				shield_list_2.add(a);
				shields = Animate_shields(shield_list_2,num_shield);
				for(int u=0;u<shields.size();u++) {
					shields.get(u).play();
				}
//				System.out.println(num_shield);
				for(int i=num_shield;i<shield_list_2.size();i++){
				if(!inres) {
					pane_game.getChildren().add(shield_list_2.get(i));
				}else {
					pane_game_1.getChildren().add(shield_list_2.get(i));
				}
			}
			}
			
		}
	}
	
	/**
	 * Animate blocks.
	 *
	 * @param list the list
	 * @param num the num
	 * @return the array list
	 */
	public ArrayList<TranslateTransition> Animate_blocks(ArrayList<Group> list, int num){
		ArrayList<TranslateTransition> l=new ArrayList<>();
		TranslateTransition a;
		for(int i=num;i<list.size();i++) {
			if(check_block_type==true){
				a=new TranslateTransition(Duration.millis(5000));
				a.setByY(1750);
			}else{
				a=new TranslateTransition(Duration.millis(6280));
				a.setByY(2200);
			}
			a.setNode(list.get(i));
			l.add(a);
//			a.play();
		}
		return l;
	}
	
	/**
	 * Animate balls.
	 *
	 * @param list the list
	 * @param num the num
	 * @return the array list
	 */
	public ArrayList<TranslateTransition> Animate_balls(ArrayList<Group> list, int num){
		ArrayList<TranslateTransition> l=new ArrayList<>();
			TranslateTransition a;
			for(int i=num;i<list.size();i++){
				a = new TranslateTransition(Duration.millis(5000));
				a.setByY(1750);
				a.setNode(list.get(i));
				l.add(a);
			}
		return l;
	}
	
	/**
	 * Animate shields.
	 *
	 * @param list the list
	 * @param num the num
	 * @return the array list
	 */
	public ArrayList<TranslateTransition> Animate_shields(ArrayList<Group> list,int num){
		ArrayList<TranslateTransition> l=new ArrayList<>();
		TranslateTransition a;
		for(int i=num;i<list.size();i++) {
			a=new TranslateTransition(Duration.millis(5000));
			a.setByY(1750);
			a.setNode(list.get(i));
			l.add(a);
		}
		return l;
	}
	
	/**
	 * Animate destroyer.
	 *
	 * @param list the list
	 * @param num the num
	 * @return the array list
	 */
	public ArrayList<TranslateTransition> Animate_destroyer(ArrayList<Group> list,int num){
		ArrayList<TranslateTransition> l=new ArrayList<>();
		TranslateTransition a;
		for(int i=num;i<list.size();i++) {
			a=new TranslateTransition(Duration.millis(5000));
			a.setByY(1750);
			a.setNode(list.get(i));
			l.add(a);
		}
		return l;
	}
	
	/**
	 * Animate magnet.
	 *
	 * @param list the list
	 * @param num the num
	 * @return the array list
	 */
	public ArrayList<TranslateTransition> Animate_Magnet(ArrayList<Group> list,int num){
		ArrayList<TranslateTransition> l=new ArrayList<>();
		TranslateTransition a;
		for(int i=num;i<list.size();i++) {
			a=new TranslateTransition(Duration.millis(5000));
			a.setByY(1750);
			a.setNode(list.get(i));
			l.add(a);
		}
		return l;
	}
	
	
	// For Blocks Collission
	
	/**
	 * The Class Timehandler2.
	 */
	private class Timehandler2 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			
			for(int i=0;i<block_list_2.size();i++){
				if(snake_list.get(0).getBoundsInParent().intersects(block_list_2.get(i).getBoundsInParent())){
					if(!inres) {
						pane_game.getChildren().remove(block_list_2.get(i));
					}else {
						pane_game_1.getChildren().remove(block_list_2.get(i));
					}
					Text gh=(Text)block_list_2.get(i).getChildren().get(1);
					int h=Integer.parseInt(gh.getText());
						if((!(inres))&&h>=snake_list.size()&&!shield) {
							player_state g=new player_state(Integer.parseInt(Score.getText()));
							pls.add(g);
							pls.sort(new playercomparator());
							ObjectOutputStream hgf=null;
							try {
								hgf=new ObjectOutputStream(new FileOutputStream("res/text/p.txt"));
								hgf.writeObject(pls);
							}catch(Exception e) {
							}finally {
								try {
									hgf.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							System.exit(0);
						}else if(h>=snake_list.size()&&inres&&!shield) {
							inres=false;
							System.out.println(inres);
							File f=new File("res/text/d.txt");
							FileWriter fw=null;
							try {
								fw=new FileWriter(f,false);
								fw.write("  ");
								fw.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							player_state g=new player_state(Integer.parseInt(Score.getText()));
							pls.add(g);
							pls.sort(new playercomparator());
							ObjectOutputStream hgf=null;
							try {
								hgf=new ObjectOutputStream(new FileOutputStream("D:\\eclipse-workspace\\Game\\src\\application\\p.txt"));
								hgf.writeObject(pls);
							}catch(Exception e) {
							}finally {
								try {
									hgf.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							System.exit(0);
						}
						int index=snake_list.size()-1;
						if(!inres) {
							if(!shield) {
								for(int l=index;l>index-h;l--) {
									pane_game.getChildren().remove(snake_list.get(l));				
								}
							}
						}else {
							if(!shield) {
								for(int l=index;l>index-h;l--) {
									pane_game_1.getChildren().remove(snake_list.get(l));	
								}
							}
						}
						if(!shield) {
							for(int l=index;l>index-h;l--) {
								snake_list.remove(l);
							}	
						}
				if(!shield) {
					snake.getlen().setText(Integer.toString(Integer.parseInt(snake.getlen().getText())-h));
				}
				if(!inres) {
					Score.setText(Integer.toString(Integer.parseInt(Score.getText())+h));
				}else {
					Score_1.setText(Integer.toString(Integer.parseInt(Score_1.getText())+h));
					Score_list.add(Integer.parseInt(Score_1.getText())+h);
				}
			}
			}
		}
	}
	
	// For Shield, Destroy and Magnet Collission
	
	/**
	 * The Class Timehandler_shield_2.
	 */
	private class Timehandler_shield_2 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			for(int i=0;i<shield_list_2.size();i++){
				if(snake_list.get(0).getBoundsInParent().intersects(shield_list_2.get(i).getBoundsInParent())){
					shield=true;
					shield_start_time = System.currentTimeMillis()*0.001;
					shield_list_2.get(i).setVisible(false);
					for(int i1=0;i1<snake_list.size();i1++){
						snake_list.get(i1).setFill(Color.RED);
						pane_game.getChildren().add(snake_list.get(i1));
					}
					pane_game.getChildren().remove(shield_list_2.get(i));
				}
			}
		}
	}
	
	/**
	 * The Class Timehandler_shield_3.
	 */
	private class Timehandler_shield_3 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			shield_end_time = System.currentTimeMillis()*0.001;
			System.out.println(shield_end_time-shield_start_time);
			if((shield_end_time-shield_start_time) > 7.0){
				shield=false;
				for(int i=0;i<snake_list.size();i++){
					snake_list.get(i).setFill(Color.BLUE);
				}
				shield_start_time=0;
				shield_end_time = 0;
			}
		}
	}
	
	/**
	 * The Class Timehandler_magnet_3.
	 */
	private class Timehandler_magnet_3 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			magnet_end_time = System.currentTimeMillis()*0.001;
			if((magnet_end_time-magnet_start_time) > 7){
				magnet=false;
				for(int i=0;i<snake_list.size();i++){
					snake_list.get(i).setFill(Color.BLUE);
				}
			}
		}
	}
	
	/**
	 * The Class Timehandler_destroy_2.
	 */
	private class Timehandler_destroy_2 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			for(int i=0;i<destroy_list_2.size();i++){
				if(snake_list.get(0).getBoundsInParent().intersects(destroy_list_2.get(i).getBoundsInParent())){
					destroy_list_2.get(i).setVisible(false);
					for(int i1 = 0;i1<snake_list.size();i1++){
						snake_list.get(i).setFill(Color.GREEN);
					}
					if(!inres) {
						pane_game.getChildren().remove(destroy_list_2.get(i));
					}else {
						pane_game_1.getChildren().remove(destroy_list_2.get(i));
					}for(int j=num_blocks;j<block_list_2.size();j++) {
						if(!inres) {
							pane_game.getChildren().remove(block_list_2.get(j));
						}else {
							pane_game_1.getChildren().remove(block_list_2.get(j));
						}
					}break;
				}
			}
		}
	}
	
	
	/**
	 * The Class Timehandler_magnet_2.
	 */
	private class Timehandler_magnet_2 implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			for(int i=0;i<magnet_list_2.size();i++){
				if(snake_list.get(0).getBoundsInParent().intersects(magnet_list_2.get(i).getBoundsInParent())){
					magnet=true;
					magnet_start_time=System.currentTimeMillis()*0.001;
					magnet_list_2.get(i).setVisible(false);
//					for(int i1 = 0;i1<snake_list.size();i1++){
//						snake_list.get(i).setFill(Color.BLUE);
//					}
					if(!inres) {
						pane_game.getChildren().remove(magnet_list_2.get(i));
					}else {
						pane_game_1.getChildren().remove(magnet_list_2.get(i));
					}
					
				}
			}
		}
	}
	
	
	// For Balls Collission

	/**
	 * The Class Timehandler_balls_2.
	 */
	private class Timehandler_balls_2  implements EventHandler<ActionEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event){
			for(int i=0;i<ball_list_2.size();i++){
					if(snake_list.get(0).getBoundsInParent().intersects(ball_list_2.get(i).getBoundsInParent())){
						if(!inres) {
							pane_game.getChildren().remove(ball_list_2.get(i));
							Text gh=(Text)ball_list_2.get(i).getChildren().get(1);
							int h=Integer.parseInt(gh.getText());
							snake.getlen().setText(Integer.toString(Integer.parseInt(snake.getlen().getText())+h));
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
					}else {
						pane_game_1.getChildren().remove(ball_list_2.get(i));
						Text gh=(Text)ball_list_2.get(i).getChildren().get(1);
						int h=Integer.parseInt(gh.getText());
						snake.getlen().setText(Integer.toString(Integer.parseInt(snake.getlen().getText())+h));
						int index=snake_list.size()-1;
						for(int j=0;j<h;j++) {
							int x=(int)snake_list.get(index).getTranslateX();
							int y=(int)snake_list.get(index).getTranslateY();
							int y_new=y+30;
							Sprite a=null;
							if(!shield) {
								a=new Sprite(x,y_new,15,"snake",Color.BLUE);
							}else {
								a=new Sprite(x,y_new,15,"snake",Color.RED);
							}
							
							snake_list.add(a);
							pane_game_1.getChildren().add(a);
							index=snake_list.size()-1;
					}
				}
				}
				
			}
		}
	}
/**
 * The Class TimeHandler_balls_3.
 */
private class TimeHandler_balls_3 implements EventHandler<ActionEvent>{

		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(magnet) {
				for(int i=0;i<ball_list_2.size();i++) {
					Circle hgfd=(Circle)ball_list_2.get(i).getChildren().get(0);
					TranslateTransition asdf=new TranslateTransition(Duration.millis(1000),ball_list_2.get(i));
					asdf.setToX(snake_list.get(0).getTranslateX()-hgfd.getCenterX());
					if(hgfd.getCenterY()<snake_list.get(0).getTranslateY()) {
						asdf.setToY(snake_list.get(0).getTranslateY()-hgfd.getCenterY());
					}
					asdf.setCycleCount(1);
					asdf.play();
				}
			}
		}
		
	}
	
	
	/**
	 * Gets the ball list.
	 *
	 * @return the ball list
	 */
	public ArrayList<Group> get_ball_list(){	
		return ball_list_2;
	}
	
	/**
	 * Ball list index.
	 *
	 * @return the int
	 */
	public int ball_list_index(){
		return num_balls;
	}
	
	/**
	 * Gets the block list.
	 *
	 * @return the block list
	 */
	public ArrayList<Group> get_block_list(){
		Random k=new Random();
		int g=k.nextInt(5)+num_blocks;
		for(int i=0;i<5;i++){
			block_list.add(rect.generate_blocks(i,snake_list.size()));
		}
		int u=k.nextInt(snake_list.size())+1;
		Text j=(Text)block_list.get(g).getChildren().get(1);
		j.setText(Integer.toString(u));
		return block_list;
		
	}
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception,InterruptedException{
		stage.initStyle(StageStyle.UNDECORATED);
		root.setPrefSize(800,800);
		btn1 = new MenuButton("RESUME");
		btn1.set_button_color(Color.DARKKHAKI);
		btn1.set_text_color(Color.BLACK);
		btn1.button_color_mouse_enter(Color.YELLOW, Color.BLACK);
		btn1.button_color_mouse_exit(Color.BLACK, Color.DARKKHAKI);
		btn1.setVisible(false);
		ObjectInputStream inp=null;
		ObjectInputStream inp1=null;
		try {
			inp1=new ObjectInputStream(new FileInputStream("res/text/p.txt"));
			pls=(ArrayList<player_state>)inp1.readObject();
		}catch(ClassNotFoundException gfds) {
		}catch(NullPointerException gggg) {
		}catch(EOFException fgsdg) {
		}
		finally {
			try {
				inp1.close();
			}catch(NullPointerException fgsd) {
			}
			
		}
		try {
			inp=new ObjectInputStream(new FileInputStream("res/text/d.txt"));
			player=(int[])inp.readObject();
			btn1.setVisible(true);
		}catch(ClassNotFoundException jiu) {
		}catch(NullPointerException jio) {
		}catch(EOFException hju) {
		}
		finally {
			try {
				inp.close();
			}catch(NullPointerException iou) {
			}
			
		}
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
		view.setFitHeight(800);
		view2.setFitWidth(800);
		view2.setFitHeight(800);
		view3.setFitWidth(800);
		view3.setFitHeight(800);
		
		VBox menu = new VBox(15);
		menu.setTranslateX(250);
		menu.setTranslateY(200);
		MenuButton btn2 = new MenuButton("NEW GAME");
		MenuButton btn3 = new MenuButton("LEADERBOARDS");
		MenuButton btn4 = new MenuButton("HELP");
		MenuButton btn5 = new MenuButton("EXIT");
		btn2.set_button_color(Color.DARKKHAKI); btn3.set_button_color(Color.DARKKHAKI); 
		btn4.set_button_color(Color.DARKKHAKI); btn5.set_button_color(Color.DARKKHAKI);
		
		btn2.set_text_color(Color.BLACK); btn3.set_text_color(Color.BLACK);
		btn4.set_text_color(Color.BLACK); btn5.set_text_color(Color.BLACK);
		
		btn2.button_color_mouse_enter(Color.YELLOW, Color.BLACK); btn3.button_color_mouse_enter(Color.YELLOW, Color.BLACK);
		btn4.button_color_mouse_enter(Color.YELLOW, Color.BLACK); btn5.button_color_mouse_enter(Color.YELLOW, Color.BLACK);
		
		btn2.button_color_mouse_exit(Color.BLACK, Color.DARKKHAKI); btn3.button_color_mouse_exit(Color.BLACK, Color.DARKKHAKI);
		btn4.button_color_mouse_exit(Color.BLACK, Color.DARKKHAKI); btn5.button_color_mouse_exit(Color.BLACK, Color.DARKKHAKI);
		//btn1.setDisable(true);
		
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
		InputStream in2 = null;
		Image img2 = null;
		try{
			in2 = Files.newInputStream(Paths.get("res/images/black.jpg"));	
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
			KeyFrame frame_shield_1 = new KeyFrame(Duration.millis(5000),new Timehandler_Shield_1());
			KeyFrame frame_destroy_1 = new KeyFrame(Duration.millis(5000), new Timehandler_Destroy_1());
			KeyFrame frame_magnet_1 = new KeyFrame(Duration.millis(5000), new Timehandler_Magnet_1());
//			KeyFrame frame_wall_1 = new KeyFrame(Duration.millis(5000), new Timehandler_Wall_1());
			
			
//			KeyFrame frame_rand_blocks = new KeyFrame(Duration.millis(6280),new Timehandler_random_blocks_1());
			
			KeyFrame frame2 = new KeyFrame(Duration.millis(1250),new Timehandler2());
			KeyFrame frame_balls_2  = new KeyFrame(Duration.millis(150),new Timehandler_balls_2());
			KeyFrame frame_shield_2 = new KeyFrame(Duration.millis(150),new Timehandler_shield_2());
			KeyFrame frame_destroy_2 = new KeyFrame(Duration.millis(150), new Timehandler_destroy_2());
			KeyFrame frame_magnet_2 = new KeyFrame(Duration.millis(150), new Timehandler_magnet_2());
			KeyFrame frame_magnet_3=new KeyFrame(Duration.millis(5000),new Timehandler_magnet_3());
			
//			KeyFrame frame_ball_3=new KeyFrame(Duration.millis(1000),new TimeHandler_balls_3());
			KeyFrame frame_time_1 = new KeyFrame(Duration.millis(150),new Timehandler_shield_3());
			
			
			t = new Timeline(frame);
			t_balls_1 = new Timeline(frame_balls_1);
//			Timeline t_rand_blocks = new Timeline(frame_rand_blocks);
			t_shield_1 = new Timeline(frame_shield_1);
			t_destroy_1 = new Timeline(frame_destroy_1);
			t_magnet_1 = new Timeline(frame_magnet_1);
//			t_wall_1 = new Timeline(frame_wall_1);
//			t_balls_3=new Timeline(frame_ball_3);
//			t.setRate(0.8);
//			t_balls_1.setRate(0.8);
//			t.setRate(1.4);
//			t_balls_1.setRate(1.4);
//			t_rand_blocks.setCycleCount(Timeline.INDEFINITE);
			t.setCycleCount(Timeline.INDEFINITE);
			t_balls_1.setCycleCount(Timeline.INDEFINITE);
			t_shield_1.setCycleCount(Timeline.INDEFINITE);
			t_destroy_1.setCycleCount(Timeline.INDEFINITE);
			t_magnet_1.setCycleCount(Timeline.INDEFINITE);
//			t_wall_1.setCycleCount(Timeline.INDEFINITE);
			
			t2 = new Timeline(frame2);
			t_balls_2 = new Timeline(frame_balls_2);
			t_shield_2 = new Timeline(frame_shield_2);
			t_destroy_2 = new Timeline(frame_destroy_2);
			t_magnet_2 = new Timeline(frame_magnet_2);
			
			t_shield_3 = new Timeline(frame_time_1);
			t_magnet_3=new Timeline(frame_magnet_3);
			
//			t_balls_2.setRate(0.5);
			t2.setCycleCount(Timeline.INDEFINITE);
			t_balls_2.setCycleCount(Timeline.INDEFINITE);
//			t_balls_3.setCycleCount(Timeline.INDEFINITE);
			t_shield_2.setCycleCount(Timeline.INDEFINITE);
			t_destroy_2.setCycleCount(Timeline.INDEFINITE);
			t_magnet_2.setCycleCount(Timeline.INDEFINITE);
			t_magnet_3.setCycleCount(Timeline.INDEFINITE);
			t_shield_3.setCycleCount(Timeline.INDEFINITE);
			
			t.play();
			t_balls_1.play();
			t_shield_1.play();
			t_destroy_1.play();
			t_magnet_1.play();
//			t_wall_1.play();
			t2.play();
			t_balls_2.play();
			t_shield_2.play();
			t_shield_3.play();
			t_destroy_2.play();
			t_magnet_2.play();
			t_magnet_3.play();
//			t_balls_3.play();
//			t_rand_blocks.play();
			
			scene_newgame = new Scene(snake.createContent(pane_game));
			scene_newgame.setOnKeyPressed(e -> {
				switch (e.getCode()){
					case LEFT:
						if((!paused)&&snake_list.get(0).getTranslateX()>30) {
							for(int i=0;i<snake_list.size();i++){
								snake_list.get(i).moveLeft();
								snake.getlen().setX(snake_list.get(0).getTranslateX()-8);
							}
//							if(keyr) {
//								snake.moveleft(keyr);
//								snake.getlen().setX(snake_list.get(0).getTranslateX()-35);
//								keyr=false;
//							}
						}
						break;
					case RIGHT:
						if((!paused)&&snake_list.get(0).getTranslateX()<570) {
							for(int i=0;i<snake_list.size();i++){
								snake_list.get(i).moveRight();
								snake.getlen().setX(snake_list.get(0).getTranslateX()-8);
							}
//							if(keyr) {
//								snake.moveright(keyr);
//								snake.getlen().setX(snake_list.get(0).getTranslateX()+25);
//							}
//							keyr=false;
							
						}
						break;		
					case P:
						paused=true;
						for(int i=0;i<blocks.size();i++) {
							blocks.get(i).pause();
						}for(int i=0;i<balls.size();i++) {
							balls.get(i).pause();
						}if(shields!=null) {
							for(int i=0;i<shields.size();i++) {
								shields.get(i).pause();
							}
						}if(magnets!=null) {
							for(int i=0;i<magnets.size();i++) {
								magnets.get(i).pause();
							}
						}if(destroys!=null) {
							for(int i=0;i<destroys.size();i++) {
								destroys.get(i).pause();
							}
						}
						t.pause();
						t_balls_1.pause();
						t_shield_1.pause();
						t_destroy_1.pause();
						t_magnet_1.pause();
						
						t2.pause();
						t_balls_2.pause();
						t_shield_2.pause();
						t_shield_3.pause();
						t_destroy_2.pause();
						t_magnet_2.pause();
						t_magnet_3.pause();
						stage.setScene(pause_game);				
						
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
//					case S:
//						shield=true;
//						break;
//					case N:
//						shield=false;
//						break;
					case K:
						ObjectOutputStream o=null;
						System.out.println(snake_list.size());
						try {
							o=new ObjectOutputStream(new FileOutputStream("res/text/d.txt"));
							int[] p=new int[2];
							p[0]=Integer.parseInt(snake.getlen().getText());
							p[1]=Integer.parseInt(Score.getText());
							o.writeObject(p);
							System.exit(0);
						}catch(Exception jkl) {
						}
						finally {
							try {
								o.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				}
				
			});
			stage.setScene(scene_newgame);	
			stage.show();
		});
		
		
		//Resume Game

//		Pane pane_game = new Pane();
		InputStream in3 = null;
		Image img3 = null;
		try{
			in3 = Files.newInputStream(Paths.get("res/images/black.jpg"));	
			img3 = new Image(in3);
		}finally{	
			in3.close();
		}
		ImageView viet = new ImageView(img3);
		viet.setFitWidth(600);
		viet.setFitHeight(800);
		pane_game_1.getChildren().add(viet);
		Score_name_1.setX(0);
		Score_name_1.setY(100);
		Score_name_1.setFont(new Font(30));
		Score_name_1.setFill(Color.YELLOW);
		pane_game_1.getChildren().add(Score_name_1);
		Score_1.setX(80);
		Score_1.setY(100);
		Score_1.setFont(new Font(30));
		Score_1.setFill(Color.YELLOW);
		if(player!=null) {
			Score_1.setText(Integer.toString(player[1]));
		}
		pane_game_1.getChildren().add(Score_1);
		if(player!=null) {
		btn1.setOnMouseClicked(event -> {
			inres=true;
			KeyFrame frame = new KeyFrame(Duration.millis(5000),new Timehandler1());
			KeyFrame frame_balls_1 = new KeyFrame(Duration.millis(5000),new Timehandler_balls_1());
			KeyFrame frame_shield_1 = new KeyFrame(Duration.millis(5000),new Timehandler_Shield_1());
			KeyFrame frame_destroy_1 = new KeyFrame(Duration.millis(5000), new Timehandler_Destroy_1());
			KeyFrame frame_magnet_1 = new KeyFrame(Duration.millis(5000), new Timehandler_Magnet_1());
//			KeyFrame frame_wall_1 = new KeyFrame(Duration.millis(5000), new Timehandler_Wall_1());
			
			
//			KeyFrame frame_rand_blocks = new KeyFrame(Duration.millis(6280),new Timehandler_random_blocks_1());
			
			KeyFrame frame2 = new KeyFrame(Duration.millis(1250),new Timehandler2());
			KeyFrame frame_balls_2  = new KeyFrame(Duration.millis(150),new Timehandler_balls_2());
			KeyFrame frame_shield_2 = new KeyFrame(Duration.millis(150),new Timehandler_shield_2());
			KeyFrame frame_destroy_2 = new KeyFrame(Duration.millis(150), new Timehandler_destroy_2());
			KeyFrame frame_magnet_2 = new KeyFrame(Duration.millis(5000), new Timehandler_magnet_2());
			
			
			
			t = new Timeline(frame);
			t_balls_1 = new Timeline(frame_balls_1);
//			Timeline t_rand_blocks = new Timeline(frame_rand_blocks);
			t_shield_1 = new Timeline(frame_shield_1);
			t_destroy_1 = new Timeline(frame_destroy_1);
			t_magnet_1 = new Timeline(frame_magnet_1);
//			t_wall_1 = new Timeline(frame_wall_1);
//			t.setRate(0.8);
//			t_balls_1.setRate(0.8);
//			t.setRate(1.4);
//			t_balls_1.setRate(1.4);
//			t_rand_blocks.setCycleCount(Timeline.INDEFINITE);
			t.setCycleCount(Timeline.INDEFINITE);
			t_balls_1.setCycleCount(Timeline.INDEFINITE);
			t_shield_1.setCycleCount(Timeline.INDEFINITE);
			t_destroy_1.setCycleCount(Timeline.INDEFINITE);
			t_magnet_1.setCycleCount(Timeline.INDEFINITE);

			t_wall_1.setCycleCount(Timeline.INDEFINITE);
			t2 = new Timeline(frame2);
			t_balls_2 = new Timeline(frame_balls_2);
			t_shield_2 = new Timeline(frame_shield_2);
			t_destroy_2 = new Timeline(frame_destroy_2);
			t_magnet_2 = new Timeline(frame_magnet_2);
			
//			t_balls_2.setRate(0.5);
			t2.setCycleCount(Timeline.INDEFINITE);
			t_balls_2.setCycleCount(Timeline.INDEFINITE);
			t_shield_2.setCycleCount(Timeline.INDEFINITE);
			t_destroy_2.setCycleCount(Timeline.INDEFINITE);
			t_magnet_2.setCycleCount(Timeline.INDEFINITE);
			
			t.play();
			t_balls_1.play();
			t_shield_1.play();
			t_destroy_1.play();
			t_magnet_1.play();
//			t_wall_1.play();
			t2.play();
			t_balls_2.play();
			t_shield_2.play();
			t_destroy_2.play();
			t_magnet_2.play();
			
			scene_resgame = new Scene(snake.createContentres(pane_game_1,player[0]));
			snake.getlen().setText(Integer.toString(player[0]));
			scene_resgame.setOnKeyPressed(e -> {
				switch (e.getCode()){
					case LEFT:
						if((!paused)&&snake_list.get(0).getTranslateX()>30) {
							for(int i=0;i<snake_list.size();i++){
								snake_list.get(i).moveLeft();
								snake.getlen().setX(snake_list.get(0).getTranslateX()-8);
							}
						}
						break;
					case RIGHT:
						if((!paused)&&snake_list.get(0).getTranslateX()<570) {
							for(int i=0;i<snake_list.size();i++){
								snake_list.get(i).moveRight();
								snake.getlen().setX(snake_list.get(0).getTranslateX()-8);
							}
						}
						break;		
					case P:
						paused=true;
						for(int i=0;i<blocks.size();i++) {
							blocks.get(i).pause();
						}for(int i=0;i<balls.size();i++) {
							balls.get(i).pause();
						}if(shields!=null) {
							for(int i=0;i<shields.size();i++) {
								shields.get(i).pause();
							}
						}if(magnets!=null) {
							for(int i=0;i<magnets.size();i++) {
								magnets.get(i).pause();
							}
						}if(destroys!=null) {
							for(int i=0;i<destroys.size();i++) {
								destroys.get(i).pause();
							}
						}
						
						t.pause();
						t_balls_1.pause();
						t_shield_1.pause();
						t_destroy_1.pause();
						t_magnet_1.pause();
						
						t2.pause();
						t_balls_2.pause();
						t_shield_2.pause();
						t_shield_3.pause();
						t_destroy_2.pause();
						t_magnet_2.pause();
						t_magnet_3.pause();
						t_balls_3.pause();
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
					case S:
						shield=true;
						break;
					case N:
						shield=false;
						break;
					case K:
						ObjectOutputStream o=null;
						try {
							o=new ObjectOutputStream(new FileOutputStream("res/text/d.txt"));
							int[] p=new int[2];
							p[0]=Integer.parseInt(snake.getlen().getText());
							p[1]=Integer.parseInt(Score_1.getText());
							o.writeObject(p);
							inres=false;
							System.exit(0);
						}catch(Exception jkl) {
						}
						finally {
							try {
								o.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						break;
				}
				
			});
			stage.setScene(scene_resgame);	
			stage.show();
		});
	}
		
		// PAUSE GAME
		Pane PauseMenu = new Pane();
		Label pause_text = new Label("PAUSE");
		pause_text.setTranslateX(130);
		pause_text.setTranslateY(40);
		pause_text.setTextFill(Color.YELLOW);
		pause_text.setFont(pause_text.getFont().font("PAUSE",FontWeight.BOLD,70));
		VBox pause_buttons = new VBox(15);
		pause_buttons.setTranslateX(150);
		pause_buttons.setTranslateY(180);
		MenuButton resume_button = new MenuButton("RESUME");
		MenuButton restart = new MenuButton("RESTART");
		MenuButton mainmenu_screen = new MenuButton("GO TO MAIN-MENU");
		resume_button.set_button_color(Color.DARKGRAY);
		resume_button.set_text_color(Color.BLACK);
		resume_button.button_color_mouse_enter(Color.BLACK, Color.DARKORANGE);
		resume_button.button_color_mouse_exit(Color.BLACK, Color.WHITE);
		restart.set_button_color(Color.DARKGRAY);
		restart.set_text_color(Color.BLACK);
		restart.button_color_mouse_enter(Color.BLACK, Color.DARKORANGE);
		restart.button_color_mouse_exit(Color.BLACK, Color.WHITE);
		mainmenu_screen.set_button_color(Color.DARKGRAY);
		mainmenu_screen.set_text_color(Color.BLACK);
		mainmenu_screen.button_color_mouse_enter(Color.BLACK, Color.DARKORANGE);
		mainmenu_screen.button_color_mouse_exit(Color.BLACK, Color.WHITE);
		InputStream in4 = null;
		Image img4 = null;
		try{
			in4 = Files.newInputStream(Paths.get("res/images/black.jpg"));	
			img4 = new Image(in3);
		}finally{	
			in4.close();
		}
		ImageView pause_back = new ImageView(img3);
		pause_back.setFitWidth(600);
		pause_back.setFitHeight(800);
		PauseMenu.getChildren().add(pause_back);
		resume_button.setOnMouseClicked(event -> {
			paused=false;
			for(int i=0;i<blocks.size();i++) {
				blocks.get(i).play();
			}for(int i=0;i<balls.size();i++) {
				balls.get(i).play();
			}if(shields!=null) {
				for(int i=0;i<shields.size();i++) {
					shields.get(i).play();
				}
			}if(magnets!=null) {
				for(int i=0;i<magnets.size();i++) {
					magnets.get(i).play();
				}
			}if(destroys!=null) {
				for(int i=0;i<destroys.size();i++) {
					destroys.get(i).play();
				}
			}
			
			
			t.play();
			t_balls_1.play();
			t_shield_1.play();
			t_destroy_1.play();
			t_magnet_1.play();
			
			t2.play();
			t_balls_2.play();
			t_shield_2.play();
			t_shield_3.play();
			t_destroy_2.play();
			t_magnet_2.play();
			t_magnet_3.play();
			stage.setScene(scene_newgame);
		});
		restart.setOnMouseClicked(event ->{
			stage.setScene(scene_newgame);
				
		});
		mainmenu_screen.setOnMouseClicked(event->{
			stage.close();
			GameMenuTrial hjkl=new GameMenuTrial();
			Stage stage1=new Stage();
			ObjectOutputStream o=null;
			try {
				o=new ObjectOutputStream(new FileOutputStream("res/text/d.txt"));
				int[] p=new int[2];
				p[0]=Integer.parseInt(snake.getlen().getText());
				p[1]=Integer.parseInt(Score.getText());
				o.writeObject(p);
				hjkl.start(stage1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		pause_buttons.getChildren().add(resume_button);
		pause_buttons.getChildren().add(restart);
		pause_buttons.getChildren().add(mainmenu_screen);
		PauseMenu.getChildren().add(pause_text);
		PauseMenu.getChildren().add(pause_buttons);
				
		pause_game = new Scene(PauseMenu,500,500,Color.BLACK);
		
		
		
		// FOR LEADERBOARD
		pane_leaderboard = new Pane();
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
		back1.set_button_color(Color.DARKKHAKI);
		back1.set_text_color(Color.BLACK);
		back1.button_color_mouse_enter(Color.BLACK, Color.YELLOW);
		back1.button_color_mouse_exit(Color.BLACK, Color.DARKKHAKI);
		pane_leaderboard.getChildren().add(view3);
		pane_leaderboard.getChildren().add(l3);
		pane_leaderboard.getChildren().add(back1);
		pane_leaderboard.getChildren().add(sno);
		pane_leaderboard.getChildren().add(score);
		pane_leaderboard.getChildren().add(date);
		for(int i=0;i<pls.size();i++) {
			Label a=new Label(Integer.toString(i));
			Label b=new Label(Integer.toString(pls.get(i).getScore()));
			Label c=new Label(pls.get(i).getDate().toString());
			a.setTranslateX(40);a.setTextFill(Color.BLACK);
			b.setTranslateX(200);b.setTextFill(Color.BLACK);
			c.setTranslateX(400);c.setTextFill(Color.BLACK);
			a.setTranslateY(150+(65*(i+1)));
			b.setTranslateY(150+(65*(i+1)));
			c.setTranslateY(150+(65*(i+1)));
			a.setFont(a.getFont().font(Integer.toString(i),FontWeight.NORMAL,40));
			b.setFont(a.getFont().font(Integer.toString(pls.get(i).getScore()),FontWeight.NORMAL,40));
			c.setFont(a.getFont().font(pls.get(i).getDate().toString(),FontWeight.NORMAL,40));
			pane_leaderboard.getChildren().add(a);
			pane_leaderboard.getChildren().add(b);
			pane_leaderboard.getChildren().add(c);
		}
		scene_leaderboard = new Scene(pane_leaderboard,800,800);
		//
		// FOR INSTRUCTIONS
		
		Instructions inst = new Instructions();
		Pane pane_instructions = inst.get_instructions_pane();
		MenuButton back = inst.get_instructions_button();
		
		scene_instructions = new Scene(pane_instructions,800,800);
				
		back1.setOnMouseClicked(event -> stage.setScene(scene_main));  // getting back from leaderboard to main_menu
		
		back.setOnMouseClicked(event -> stage.setScene(scene_main));   // getting back from instructions to main_menu
		
		stage.show();	
	}
			
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		Media sound=new Media(new File("res/images/music.mp3").toURI().toString());
		MediaPlayer mediaplayer=new MediaPlayer(sound);
		mediaplayer.play();
		launch(args);
	}
}
