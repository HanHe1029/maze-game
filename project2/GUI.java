/*
 * This class is the game class, currently player can move around and attack monsters, once the attack is clicked, the monster 
 * would be killed.
 */

package project2;

import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame // implements MouseListener
{
	private JPanel mainPan, upPan, bottomPan;
	private Display display;
	
	public GUI() throws Exception
	{
		super("Maze Game");
		display = new Display();
		mainPan = new JPanel();
		setLayout(mainPan);
		this.add(mainPan);
		this.pack();
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public void setLayout(JPanel mainPan)
	{
		upPan = new JPanel();
		upPan.setPreferredSize(new Dimension(800, 600));
		
		display.setPreferredSize(new Dimension(800, 800));
		mainPan.setLayout(new FlowLayout());
		mainPan.add(display);
	}
	
	public Display getDisplay()
	{
		return display;
	}
	
	//public static void upDatePaint()
	//{
	//	display.revalidate();
	//	display.repaint();
	//}
	
	public static void main(String[] args) throws Exception
	{
		GUI gui = new GUI();
		System.out.println("CHECK RUN");
		System.out.println(gui.getDisplay().getMaze().getPlayer().getHealth());
		System.out.println(gui.getDisplay().getMaze().getMonsterList().size());
		while ((gui.getDisplay().getMaze().getPlayer().getHealth() >= 0)
				&& (gui.getDisplay().getMaze().getMonsterList().size() > 0))
		{
			// Do nothing
			System.out.print("");
		}
		System.out.println("END");
		gui.setVisible(false);
		gui.dispose();
	}
}
