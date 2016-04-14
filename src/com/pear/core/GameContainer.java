package com.pear.core;

public class GameContainer implements Runnable
{
	private Thread thread;
	private AbstractGame game;
	private Window window;
	private Renderer renderer;
	private Input input;
	
	private int width = 320, height = 240;
	private float scale = 2.0f;
	private String title = "GameEngine";
	private boolean lightEnabled = false;
	private boolean debug = false;
	public int FPS;
	
	private double frameCap = 1.0 / 30.0;
	private boolean isRunning = false;
	
	public GameContainer(AbstractGame game)
	{
		this.game = game;
	}
	
	public void start()
	{
		if(isRunning) return;
		
		//Initialize engine components
		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);
		
		game.init(this);
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop()
	{
		if(!isRunning) return;
		
		isRunning = false;
	}
	
	public void run()
	{
		isRunning = true;
		
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		double frameTime = 0;
		int frames = 0;
		
		boolean render = false;
		
		while(isRunning)
		{	
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= frameCap)
			{
				game.update((float)frameCap);
				input.update();
				unprocessedTime -= frameCap;
				render = true;
				
				if(frameTime >= 1)
				{
					frameTime = 0;
					FPS = frames;
					frames = 0;
				}
			}
			
			if(render)
			{
				//clear screen
				renderer.clear();
				
				//render game
				game.render(renderer);
				
				//combine with lightmap
				if(lightEnabled)renderer.combineMaps();
				
				//update window
				window.update();
				
				frames ++;
				if(!debug)
				{
					render = false; 
				}
				else
				{
					render = true;
				}
			}
			else
			{
				try
				{
					thread.sleep(1);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		cleanUp();
	}
	
	public boolean isLightEnabled()
	{
		return lightEnabled;
	}

	public double getFrameCap()
	{
		return frameCap;
	}

	public void setFrameCap(double frameCap)
	{
		this.frameCap = 1.0 / frameCap;
	}

	public void setLightEnabled(boolean lightenabled)
	{
		this.lightEnabled = lightenabled;
	}

	public boolean isDebug()
	{
		return debug;
	}

	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}

	private void cleanUp()
	{
		window.cleanUp();
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public float getScale()
	{
		return scale;
	}

	public void setScale(float scale)
	{
		this.scale = scale;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Window getWindow()
	{
		return window;
	}

	public Renderer getRenderer()
	{
		return renderer;
	}
	
	public void setClearColor(int clearColor)
	{
		renderer.setClearColor(clearColor);
	}
	
	public int getClearColor()
	{
		return renderer.getClearColor();
	}
}
