package com.pear.core;

public abstract class AbstractGame
{	
	public abstract void init(GameContainer gc);
	
	public abstract void update(float dt);
	
	public abstract void render(Renderer r);
}
