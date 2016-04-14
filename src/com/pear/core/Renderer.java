package com.pear.core;

import java.awt.image.DataBufferInt;

import com.pear.core.fx.Font;
import com.pear.core.fx.Light;
import com.pear.core.fx.Pixel;
import com.pear.core.fx.ShadowType;
import com.pear.fx.drawables.Drawable;
import com.pear.geometry.Point;

public class Renderer
{
	private int width, height;
	private int[] pixels;
	private int[] lightmap;
	private ShadowType[] lightblock;
	private Font font = Font.STANDART;
	private int ambientLight = Pixel.getColor(1,  0.1f, 0.2f, 0.3f);
	private int clearColor = 0xffffffff;
	
	private Point viewPoint;
	
	public Renderer(GameContainer gc)
	{
		width = gc.getWidth();
		height = gc.getHeight();
		pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		lightmap = new int[pixels.length];
		lightblock = new ShadowType[pixels.length];
		viewPoint = new Point(0, 0);
	}
	
	public void draw(Drawable d)
	{
		d.draw(this);
	}
	
	public void setPixel(int x, int y, int color, ShadowType lb)
	{
		x -= viewPoint.x;
		y -= viewPoint.y;
		
		if(x < 0 || x >= width || y < 0 || y >= height || color == 0xffff00ff) return;
		
		pixels[x + y * width] = color;
		lightblock[x + y * width] = lb;
	}
	
	public ShadowType getLightBlock(int x, int y)
	{
		x -= viewPoint.x;
		y -= viewPoint.y;
		
		if(x < 0 || x >= width || y < 0 || y >= height) return ShadowType.TOTAL;
		
		return lightblock[x + y * width];
	}
	
	public void setLightMap(int x, int y, int color)
	{
		x -= viewPoint.x;
		y -= viewPoint.y;
		
		if(x < 0 || x >= width || y < 0 || y >= height) return;
		
		lightmap[x + y * width] = Pixel.getMax(color, lightmap[x + y * width]);
	}
	
	public void clear()
	{
		for(int i = 0; i < width * height; i++)
		{
			pixels[i] = clearColor;
			lightmap[i] = ambientLight;
		}
	}
	
	public void combineMaps()
	{
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				setPixel(x,y, Pixel.getLightBlend(pixels[x + y * width], lightmap[x + y * width], ambientLight), lightblock[x + y *width]);
			}
		}
	}
	
	public void addLight(Light light)
	{	
		for(int i = 0; i <= light.getDiameter(); i++)
		{
			drawLightLine(light.getRadius(), light.getRadius(), i, 0, light, light.getXpos(), light.getYpos());
			drawLightLine(light.getRadius(), light.getRadius(), i, light.getDiameter(), light, light.getXpos(), light.getYpos());
			drawLightLine(light.getRadius(), light.getRadius(), 0, i, light, light.getXpos(), light.getYpos());
			drawLightLine(light.getRadius(), light.getRadius(), light.getDiameter(), i, light, light.getXpos(), light.getYpos());
		}
	}
	
	private void drawLightLine(int x0, int y0, int x1, int y1, Light light, int offX, int offY)
	{
		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);
		
		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;
		
		int err = dx - dy;
		int e2;
		
		float power = 1.0f;
		
		int screenX;
		int screenY;
		
		boolean hit = false;
		
		while(true)
		{
			if(light.getLightValue(x0, y0) == 0xff000000) break;
			
			screenX = x0 - light.getRadius() + offX;
			screenY = y0 - light.getRadius() + offY;
			
			if(power == 1)
			{
				setLightMap(screenX, screenY, light.getLightValue(x0, y0));
			}
			else
			{
				setLightMap(screenX, screenY, Pixel.getColorPower(light.getLightValue(x0, y0), power));
			}
			
			if(x0 == x1 && y0 == y1) break;
			if(getLightBlock(screenX, screenY) == ShadowType.TOTAL) break;
			if(getLightBlock(screenX, screenY) == ShadowType.FADE) power -= 0.1f;
			if(getLightBlock(screenX, screenY) == ShadowType.HALF && !hit) {hit = true; power /= 2;}
			if(getLightBlock(screenX, screenY) == ShadowType.HALF && hit) hit = false;
			if(power <= 0.1f) break;
			
			e2 = 2* err;
			
			if(e2 > -1 * dy)
			{
				err -= dy;
				x0 += sx;
			}
			
			if(e2 < dx)
			{
				err += dx;
				y0 += sy;
			}
		}
	}
	
	public void setViewPoint(Point p)
	{
		viewPoint.x = p.x - width/2;
		viewPoint.y = p.y - height/2;
	}
	
	public void setViewPoint(int x , int y)
	{
		viewPoint.x = x - width/2;
		viewPoint.y = y - height/2;
	}
	
	public void setViewPointX(int x)
	{
		viewPoint.x = x - width/2;
	}
	
	public void setViewPointY(int y)
	{
		viewPoint.y = y - height/2;
	}
	
	public Point getViewPoint()
	{
		return new Point(viewPoint.x + width/2, viewPoint.y + height/2);
	}
	
	public Point getViewPointRef()
	{
		return this.viewPoint;
	}
	
	public void addViewPoint(int x, int y)
	{
		viewPoint.x += x;
		viewPoint.y += y;
	}

	public int getClearColor()
	{
		return clearColor;
	}

	public void setClearColor(int clearColor)
	{
		this.clearColor = clearColor;
	}
}







