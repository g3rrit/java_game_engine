package com.pear.fx.drawables;

public enum ButtonSprite
{
	STANDART("/testbutton.png", "/testbutton2.png");
	
	public Image button;
	public Image buttonPressed;
	
	ButtonSprite(String path1, String path2)
	{
		button = new Image(path1);
		buttonPressed = new Image(path2);
	}
}
