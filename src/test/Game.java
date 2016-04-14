package test;

import java.awt.event.KeyEvent;

import com.pear.core.AbstractGame;
import com.pear.core.GameContainer;
import com.pear.core.Input;
import com.pear.core.Renderer;
import com.pear.core.fx.Light;
import com.pear.core.fx.ShadowType;
import com.pear.core.fx.SoundClip;
import com.pear.drawablegeometry.DCircle;
import com.pear.drawablegeometry.DLine;
import com.pear.drawablegeometry.DTriangle;
import com.pear.fx.drawables.AnimatedSprite;
import com.pear.fx.drawables.Button;
import com.pear.fx.drawables.ButtonSprite;
import com.pear.fx.drawables.Image;
import com.pear.fx.drawables.Sprite;
import com.pear.fx.drawables.Text;
import com.pear.geometry.Point;
import com.pear.geometry.Rectangle;

public class Game extends AbstractGame
{	
	private GameContainer gc;
	
	private Sprite bg = new Sprite("/backgroundtest.png", 0, 0, ShadowType.NONE);
	private Sprite testrgb = new Sprite("/imgt.png", 320, 600, 48, 95, 200, 100, ShadowType.NONE);
	private Sprite image;
	private Rectangle imagerect;
	private Sprite shot;
	private Sprite testsh;
	private Button testb;
	private AnimatedSprite animspr = new AnimatedSprite("/testanimation/testanim0.png", 2, 2, 3,3, 20, 20, ShadowType.NONE);
	private Rectangle animatedsprrect = new Rectangle(20,20, 5,5);
	private double deltatimetest = 0;
	
	private Text FPS;
	
	private Light l0;
	private Light l1;
	
	private SoundClip clip;
	
	float x = 0;
	float vol = 1f;
	
	DTriangle tri0 = new DTriangle(new Point(100,0), new Point(0,100), new Point(200,100), 0xff0000ff);
	Rectangle rect = new Rectangle(new Point(0,0), new Point(20,20));
	
	DLine dline = new DLine(new Point(10,10), new Point(100,100), 2, 0xffff0000);
	DCircle dcircle = new DCircle(new Point(100,100), 20, 0xff00ff00);
	
	public static void main(String []args)
	{
		GameContainer gc = new GameContainer(new Game());
		gc.setWidth(400);
		gc.setHeight(300);
		gc.setScale(2);
		
		gc.setFrameCap(3);
		
		gc.setLightEnabled(false);
		gc.setDebug(false);
		
		gc.start();
	}
	
	public Game()
	{	
		bg = new Sprite("/backgroundtest.png", 0, 0, ShadowType.NONE);
		image = new Sprite("/char.png", 4,4,4,4, 0, 0, ShadowType.NONE);
		imagerect = new Rectangle(0,0, image.getWidth(), image.getHeight());
		shot = new Sprite("/shot.png", 0, 0, ShadowType.NONE);
		testsh = new Sprite("/testsh.png", 0, 0, ShadowType.NONE);
		
		FPS = new Text("FPS: 0", 5, 5, ShadowType.NONE);
		
		l0 = new Light(0xff00ffff, 100, 0, 0);
		l1 = new Light(0xffffff00, 200, 0, 0);
		
		clip = new SoundClip("/sophiepew.wav");
		
		testb = new Button("test12", ButtonSprite.STANDART, 100,100, ShadowType.NONE);
		
		
		animspr.addFrame(new Image("/testanimation/testanim1.png"));
		animspr.addFrame(new Image("/testanimation/testanim2.png"));
		animspr.addFrame(new Image("/testanimation/testanim3.png"));
		
		animspr.setSize(30, 30);
		
		x = 0;
		vol = 1f;
	
		testsh.setShadowType(ShadowType.FADE);
		
		FPS.setScale(2,2);
	}
	
	@Override
	public void init(GameContainer gc)
	{
		this.gc = gc;
	}

	@Override
	public void update(float dt)
	{
		if(Input.isKey(KeyEvent.VK_A))
		{
			image.setWidth(200);
			testb.setSize(100, 100);
			x += dt * 500;
			this.testsh.invertX(true);
		}
		if(Input.isKeyPressed(KeyEvent.VK_A))
		{
			System.out.println("hello");
			clip.play();
		}
		if(Input.isKeyPressed(KeyEvent.VK_T))
		{
		}
		
		if(Input.isKeyPressed(KeyEvent.VK_B))
		{
			image.setSize(100, 100);
			x = 0;
			//if(vol > 0.1f)vol -= 0.1f;
			clip.setVolume(-10);
		}
		if(Input.getMouseX() > 300)
		{
			//image.setSize(200, 200);
		}
		
		l0.setPosition(Input.getMouseX(), Input.getMouseY());
		FPS.setText("FPS: " + gc.FPS);
		
		testb.mouseMoved(Input.getMouseX(), Input.getMouseY());
		
		deltatimetest += (double)dt;
		if(deltatimetest >= 0.2f) 
		{
			//animspr.update();
			deltatimetest = 0;
		}
		
		this.rect.moveTo(Input.getMouseX(), Input.getMouseY());
		
		if(tri0.contains(rect)) System.out.println("!!containing");
		
		dline.setP1(new Point(Input.getMouseX(), Input.getMouseY()));
		dcircle.setP0(new Point(Input.getMouseX(), Input.getMouseY()));
		
	}

	@Override
	public void render(Renderer r)
	{
		r.draw(bg);
		r.draw(testsh);
		//r.draw(shot);
		r.draw(FPS);
		r.draw(image);
		r.draw(testb);
		r.draw(animspr);
		//r.drawLight(l1, 50, 50);
		r.addLight(l0);
		//r.drawString("./012456789=>?ABCDEFGHIJK", 0xffffffff, 10, 10);
		
		r.draw(testrgb);
		
		//DRightTriangle tri1 = new DRightTriangle(new Point(100,100) , -50, 30, 0xffff0000);
		//r.draw(tri1);
		//DPrism pri1 = new DPrism(new Point(100,100), new Point(90,130), 0xff00ff00);
		//r.draw(pri1);
		
		//r.draw(tri0);
		
		r.draw(dline);
		
		r.draw(dcircle);
		
		r.setViewPoint(200, 150);
	}

}
