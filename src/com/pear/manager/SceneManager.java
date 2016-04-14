package com.pear.manager;

import com.pear.core.AbstractGame;
import com.pear.core.Renderer;

public class SceneManager
{
	private AbstractGame game;
	
	private Scene activeScene;
	
	public SceneManager(AbstractGame game, Scene scene)
	{
		this.game = game;
		this.activeScene = scene;
	}
	
	public void pushBackScene(Scene scene)
	{
		this.activeScene = scene;
	}
	
	public void update(float dt)
	{
		this.activeScene.update(dt);
	}
	
	public void render(Renderer r)
	{
		this.activeScene.render(r);
	}
}
