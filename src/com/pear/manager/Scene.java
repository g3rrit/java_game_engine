package com.pear.manager;

import com.pear.core.Renderer;

public abstract class Scene
{
	public abstract void update(float dt);
	
	public abstract void render(Renderer r);
}
