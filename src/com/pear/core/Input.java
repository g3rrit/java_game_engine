package com.pear.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.pear.geometry.Point;

public class Input implements KeyListener, MouseListener, MouseMotionListener
{
	private GameContainer gc;
	private Point viewPoint;
	
	private static boolean[] keys = new boolean[256];
	private static boolean[] keysLast = new boolean[256];
	
	private static boolean[] buttons = new boolean[5];
	private static boolean[] buttonsLast = new boolean[5];
	
	private static int mouseX, mouseY;
	
	public Input(GameContainer gc)
	{
		this.gc = gc;
		this.viewPoint = gc.getRenderer().getViewPointRef();
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
	}
	
	public void update()
	{
		keysLast = keys.clone();
		buttonsLast = buttons.clone();
	}
	
	public static boolean isKey(int keyCode)
	{
		return keys[keyCode];
	}
	
	public static boolean isKeyPressed(int keyCode)
	{
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	public static boolean isKeyReleased(int keyCode)
	{
		return !keys[keyCode] && keysLast[keyCode];
	}
	
	public static boolean isButton(int button)
	{
		return buttons[button];
	}
	
	public static boolean isButtonPressed(int button)
	{
		return buttons[button] && !buttonsLast[button];
	}
	
	public static boolean isButtonReleased(int button)
	{
		return !buttons[button] && buttonsLast[button];
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		mouseX = (int)(e.getX() / gc.getScale()) + viewPoint.x;
		mouseY = (int)(e.getY() / gc.getScale()) + viewPoint.y;
	}

	public static int getMouseX()
	{
		return mouseX;
	}

	public static int getMouseY()
	{
		return mouseY;
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mouseX = (int)(e.getX() / gc.getScale()) + viewPoint.x;
		mouseY = (int)(e.getY() / gc.getScale()) + viewPoint.y;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		buttons[e.getButton()] = false;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}

}
