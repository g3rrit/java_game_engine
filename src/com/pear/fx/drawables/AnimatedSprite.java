package com.pear.fx.drawables;

import java.util.ArrayList;

import com.pear.core.fx.ShadowType;

public class AnimatedSprite extends Sprite
{
	//super.image is the image that is to be drawn
	ArrayList<Image> aimage;
	
	private int currentPosition;
	
	private float updateTime = 0;
	private float updateAnimationTime = 0.5f; 

	public AnimatedSprite(String path, int x, int y, ShadowType shadowType)
	{
		super(path, x, y, shadowType);
		
		aimage = new ArrayList<Image>();
		
		aimage.add(new Image(path));
		super.image = aimage.get(0);
	}
	
	public AnimatedSprite(String path, int scanx, int scany, int scanw, int scanh, int x, int y, ShadowType shadowType)
	{
		super(path, scanx, scany, scanw, scanh, x, y, shadowType);
		
		aimage = new ArrayList<Image>();
		
		aimage.add(new Image(path, scanx, scany, scanw, scanh));
		super.image = aimage.get(0);
	}
	
	public boolean update(float dt)
	{
		if(aimage.size() == 1)return false;
		
		updateTime += dt;
		if(updateTime >= updateAnimationTime)
		{
			currentPosition ++;
			if(currentPosition == aimage.size()) currentPosition = 0;
			super.image = aimage.get(currentPosition);
			updateTime = 0;
			return true;
		}
		return false;
	}
	
	public void update()
	{
		if(aimage.size() == 1)return;
		
		currentPosition ++;
		if(currentPosition == aimage.size()) currentPosition = 0;
		super.image = aimage.get(currentPosition);
		updateTime = 0;
	}
	
	public void addFrame(Image i)
	{
		aimage.add(i);
	}
	
	public void removeFrameAt(int i)
	{
		if(i < 0 || i >= aimage.size()) return;
		
		aimage.remove(i);
	}
	
	public void removeLastFrame()
	{
		if(aimage.size() <= 1) return;
		
		aimage.remove(aimage.size() - 1);
	}
	
	public void removeAllFrames()
	{
		aimage.clear();
		aimage.add(super.image);
	}

	public void setSize(int width, int height)
	{
		super.setSize(width, height);
		
		for(int i = 0; i < aimage.size(); i++)
		{
			aimage.get(i).setSize(width, height);
		}
	}

	public void setWidth(int width)
	{		
		super.setWidth(width);
		
		for(int i = 0; i < aimage.size(); i++)
		{
			aimage.get(i).setWidth(width);
		}
	}


	public void setHeight(int height)
	{
		super.setHeight(height);
		
		for(int i = 0; i < aimage.size(); i++)
		{
			aimage.get(i).setHeight(height);
		}
	}
	
	public void invertX()
	{
		for(int i = 0; i < aimage.size(); i++)
		{
			aimage.get(i).invertX();
		}
	}
	
	public void invertX(boolean isInverted)
	{
		for(int i = 0; i < aimage.size(); i++)
		{
			aimage.get(i).invertX(isInverted);
		}
	}
	
	public void invertY()
	{
		for(int i = 0; i < aimage.size(); i++)
		{
			aimage.get(i).invertY();
		}
	}
	
	public void invertY(boolean isInverted)
	{
		for(int i = 0; i < aimage.size(); i++)
		{
			aimage.get(i).invertY(isInverted);
		}
	}

	public float getUpdateAnimationTime()
	{
		return updateAnimationTime;
	}

	public void setUpdateAnimationTime(float updateAnimationTime)
	{
		this.updateAnimationTime = updateAnimationTime;
	}
}
