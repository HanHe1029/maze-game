/*
 * Author: Han He
 * Purpose: 2D representation of the Maze. This class extends the JComponent class and
 * override the paint method
 * Date: Nov 6, 2014
 */
package project2;

import java.awt.*;
import javax.swing.*;

public class HanComponent extends JComponent
{
	/**
	 * Data member maze based on which the 2D is dwawn
	 */
	private Maze maze;
	
	public HanComponent() throws Exception
	{
		super();
		maze = new Maze();
	}
	
	/**
	 * overrides the paint method to have it draw a 2D representation of maze
	 */
	public void paint(Graphics g)
	{
		int row = maze.rows;
		int column = maze.cols;
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < column; j++)
			{
				
				Cell c = maze.getMap()[i][j];
				
				String texture = c.getTexture();
				if (texture.equalsIgnoreCase("texture1.png"))
					g.setColor(Color.black);
				if (texture.equalsIgnoreCase("texture2.png"))
					g.setColor(Color.blue);
				if (texture.equalsIgnoreCase("texture3.png"))
					g.setColor(Color.GREEN);
				if (texture.equalsIgnoreCase("texture4.png"))
					g.setColor(Color.MAGENTA);
				
				if (c.getNorthWall())
				{
					g.drawLine(j * 35, i * 35, j * 35 + 30, i * 35);
				}
				if (c.getEastWall())
				{
					g.drawLine(j * 35 + 30, i * 35, j * 35 + 30, i * 35 + 30);
				}
				if (c.getWestWall())
				{
					g.drawLine(j * 35, i * 35, j * 35, i * 35 + 30);
				}
				if (c.getSouthWall())
				{
					g.drawLine(j * 35, i * 35 + 30, j * 35 + 30, i * 35 + 30);
				}
				
				g.setColor(Color.BLACK);
				if (c.getMonster() != null)
				{
					String name = c.getMonster().getName().substring(0, 1);
					g.drawString(name, j * 35 + 5, i * 35 + 12);
				}
				
				if (c.getTreasure() != null)
				{
					if (c.getTreasure().getName().equals("chalice"))
					{
						g.setColor(Color.yellow);
					}
					if (c.getTreasure().getName().equals("gem"))
					{
						g.setColor(Color.green);
					}
					if (c.getTreasure().getName().equals("gold bag"))
					{
						g.setColor(Color.black);
					}
					if (c.getTreasure().getName().equals("health potion"))
					{
						g.setColor(Color.CYAN);
					}
					g.fillOval(j * 35 + 20, i * 35 + 10, 8, 8);
				}
				
				if (c.getMonsterSpawn())
				{
					g.setColor(Color.BLACK);
					g.fillRect(j * 35 + 5, i * 35 + 20, 8, 8);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		JFrame f = new JFrame();
		HanComponent h = new HanComponent();
		h.setPreferredSize(new Dimension(300, 300));
		JPanel mainP = new JPanel();
		mainP.setLayout(new FlowLayout());
		mainP.add(h);
		f.add(mainP);
		f.pack();
		f.setVisible(true);
	}
}
