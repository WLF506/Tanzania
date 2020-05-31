package com.gg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;



public class MyGdxGame extends Game {
	public static Screen ScreenMenu;
	public static Screen ScreenMenuLevelSelect;
	public static SpriteBatch batch;


	FreeTypeFontGenerator generator ;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	BitmapFont font;


	static Preferences prefs;

	public static OrthographicCamera camera;


	public static FitViewport viewport;
	private Texture texture;

	private static long SPLASH_MINIMUM_MILLIS = 1000L;
	public static TextureRegion[] Animation_pers_blaster_shoot;
	public static TextureRegion[] Animation_pers_hurt;
	public static TextureRegion[] Animation_pers_idle;
	public static TextureRegion[] Animation_pers_jump;
	public static TextureRegion[] Animation_pers_run;
	Music music;
	Sound tab1;
	Sound tab2;
	Sound denied;



	public void create() {
		prefs = Gdx.app.getPreferences("Data");
		denied= Gdx.audio.newSound(Gdx.files.internal("music/denied.mp3"));
		tab1= Gdx.audio.newSound(Gdx.files.internal("music/Tab1.mp3"));
		tab2= Gdx.audio.newSound(Gdx.files.internal("music/Tab2.mp3"));
		music= Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));


		generator = new FreeTypeFontGenerator(Gdx.files.internal("ttf/segoeprb.ttf"));
		batch = new SpriteBatch();

		parameter.characters ="абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		parameter.size = Gdx.graphics.getHeight()/18;
		font = generator.generateFont(parameter);
		generator.dispose();
		font.setColor(0f, 0f, 0f, 1f);

		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

		screen();

		setScreen(new Bootscreen());
		boot();

	}

	public void render() {
		super.render();

	}
	public void dispose() {

		batch.dispose();
		generator.dispose();
		font.dispose();


	}
	private void TextureAtlas(){
		Animation_pers_blaster_shoot=new TextureRegion[8];
		Animation_pers_hurt=new TextureRegion[10];
		Animation_pers_idle=new TextureRegion[14];
		Animation_pers_jump=new TextureRegion[21];
		Animation_pers_run=new TextureRegion[14];
		for(int i=0;i<Animation_pers_blaster_shoot.length;i++){
			texture = new Texture("foxy/animation/blaster shoot/foxy-blaster shoot_"+i+".png");
			Animation_pers_blaster_shoot[i]=new TextureRegion(texture);

		}
		for(int i=0;i<Animation_pers_hurt.length;i++){
			if(i<10){
				texture = new Texture("foxy/animation/hurt/foxy-hurt_0"+i+".png");
				Animation_pers_hurt[i]=new TextureRegion(texture);

			}else {
				texture = new Texture("foxy/animation/hurt/foxy-hurt_"+i+".png");
				Animation_pers_hurt[i]=new TextureRegion(texture);


			}
		}
		for(int i=0;i<Animation_pers_idle.length;i++){
			if(i<10){
				texture = new Texture("foxy/animation/idle/foxy-idle_0"+i+".png");
				Animation_pers_idle[i]=new TextureRegion(texture);

			}else {
				texture = new Texture("foxy/animation/idle/foxy-idle_"+i+".png");
				Animation_pers_idle[i]=new TextureRegion(texture);


			}
		}
		for(int i=0;i<Animation_pers_jump.length;i++){
			if(i<10){
				texture = new Texture("foxy/animation/jump/foxy-jump_0"+i+".png");
				Animation_pers_jump[i]=new TextureRegion(texture);

			}else {
				texture = new Texture("foxy/animation/jump/foxy-jump_"+i+".png");
				Animation_pers_jump[i]=new TextureRegion(texture);


			}
		}
		for(int i=0;i<Animation_pers_run.length;i++){
			if(i<10){
				texture = new Texture("foxy/animation/run/foxy-run_0"+i+".png");
				Animation_pers_run[i]=new TextureRegion(texture);

			}else {
				texture = new Texture("foxy/animation/run/foxy-run_"+i+".png");
				Animation_pers_run[i]=new TextureRegion(texture);


			}
		}
		texture.dispose();
	}

	private void boot(){
	final long splash_start_time = System.currentTimeMillis();
	new Thread(new Runnable() {
		@Override
		public void run() {

			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					TextureAtlas();

					long splash_elapsed_time = System.currentTimeMillis() - splash_start_time;
					if (splash_elapsed_time < MyGdxGame.SPLASH_MINIMUM_MILLIS) {
						Timer.schedule(
								new Timer.Task() {
									@Override
									public void run() {
										menu();
									}
								}, (float)(MyGdxGame.SPLASH_MINIMUM_MILLIS - splash_elapsed_time) / 1000f);
					} else {
						menu();
					}
				}
			});
		}
	}).start();
}
public  void menu(){
	setScreen(ScreenMenu);
}
public void screen(){

	ScreenMenu = new Menu(this);
	ScreenMenuLevelSelect= new MenuLevelSelect(this);

	}



}