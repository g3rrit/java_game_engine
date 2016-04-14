package com.pear.fx.drawables;

import com.pear.core.fx.ShadowType;
import com.pear.geometry.Rectangle;

public class Tile extends Sprite
{
	public Tile(Image image, int x, int y, ShadowType shadowType)
	{	
		super.image = image;
		super.width = image.getWidth();
		super.height = image.getHeight();
		super.rect = new Rectangle(x,y, x + image.getWidth(), y + image.getHeight());
		super.setPosition(x, y);
		super.setShadowType(shadowType);
		//super.init(x,y, shadowType);
	}
}
