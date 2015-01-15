/*
 * Author: Han He
 * Purpose: 3D representation of the first person view of the game, player can move around by clicking in the window
 * 			or by pressing the arrow keys.
 * Date: Nov 12, 2014
 */
package project2;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

public class Display extends JComponent implements KeyListener, MouseListener
{
	/**
	 * data member, Maze, the maze is goint to be created here
	 */
	private Maze maze;
	
	/**
	 * constructor, initialize maze and sets the JComponent
	 * 
	 * @throws Exception
	 */
	public Display() throws Exception
	{
		super();
		maze = new Maze();
		// monsterState = 0;
		this.setFocusable(true);
		this.addMouseListener(this);
		this.addKeyListener(this);
	}
	
	/**
	 * gets the maze
	 * 
	 * @return Maze maze
	 */
	public Maze getMaze()
	{
		return maze;
	}
	

	
	/**
	 * overrides the paint method for the custom-painted JComponent
	 */
	public void paint(Graphics g)
	{
		
		Cell c1 = Maze.getMap()[Player.getRow()][Player.getColumn()];
		String texture1 = c1.getTexture();
		String textNum1 = texture1.substring(0, 8);
		
		// initialize some variables for easy tracking of existence of walls
		String leftWall = "";
		String rightWall = "";
		String midWall = "";
		Monster monster = null;
		Treasure treasure = null;
		boolean myfrontWall = false;
		boolean myleftWall = false;
		boolean myrightWall = false;
		
		char direction = maze.getPlayer().getDirectionFacing();
		if (direction == 'N')
		{
			myfrontWall = c1.getNorthWall();
			myleftWall = c1.getWestWall();
			myrightWall = c1.getEastWall();
		} else
		{
			if (direction == 'E')
			{
				myfrontWall = c1.getEastWall();
				myleftWall = c1.getNorthWall();
				myrightWall = c1.getSouthWall();
			} else
			{
				if (direction == 'S')
				{
					myfrontWall = c1.getSouthWall();
					myleftWall = c1.getEastWall();
					myrightWall = c1.getWestWall();
				} else
				{
					myfrontWall = c1.getWestWall();
					myleftWall = c1.getSouthWall();
					myrightWall = c1.getNorthWall();
				}
			}
		}
		
		// Deals with the situation that there is no wall right in front of the
		// nose
		if (!myfrontWall)
		{
			Cell c2;
			if (direction == 'N')
			{
				c2 = Maze.getMap()[Player.getRow() - 1][Player.getColumn()];
				if (!c2.getNorthWall())
					midWall = "_center_no_wall_back.png";
				else
					midWall = "_center.png";
				if (!c2.getWestWall())
				{
					if (!myleftWall)
						leftWall = "_left_no_wall_both.png";
					else
						leftWall = "_left_no_wall_front.png";
				} else
				{
					if (!myleftWall)
						leftWall = "_left_no_wall_back.png";
					else
						leftWall = "_left.png";
				}
				if (!c2.getEastWall())
				{
					if (!myrightWall)
						rightWall = "_right_no_wall_both.png";
					else
						rightWall = "_right_no_wall_front.png";
				} else
				{
					if (!myrightWall)
						rightWall = "_right_no_wall_back.png";
					else
						rightWall = "_right.png";
				}
			} else if (direction == 'E')
			{
				c2 = Maze.getMap()[Player.getRow()][Player.getColumn() + 1];
				if (!c2.getEastWall())
					midWall = "_center_no_wall_back.png";
				else
					midWall = "_center.png";
				if (!c2.getNorthWall())
				{
					if (!myleftWall)
						leftWall = "_left_no_wall_both.png";
					else
						leftWall = "_left_no_wall_front.png";
				} else
				{
					if (!myleftWall)
						leftWall = "_left_no_wall_back.png";
					else
						leftWall = "_left.png";
				}
				if (!c2.getSouthWall())
				{
					if (!myrightWall)
						rightWall = "_right_no_wall_both.png";
					else
						rightWall = "_right_no_wall_front.png";
				} else
				{
					if (!myrightWall)
						rightWall = "_right_no_wall_back.png";
					else
						rightWall = "_right.png";
				}
			} else if (direction == 'S')
			{
				c2 = Maze.getMap()[Player.getRow() + 1][Player.getColumn()];
				if (!c2.getSouthWall())
					midWall = "_center_no_wall_back.png";
				else
					midWall = "_center.png";
				if (!c2.getEastWall())
				{
					if (!myleftWall)
						leftWall = "_left_no_wall_both.png";
					else
						leftWall = "_left_no_wall_front.png";
				} else
				{
					if (!myleftWall)
						leftWall = "_left_no_wall_back.png";
					else
						leftWall = "_left.png";
				}
				if (!c2.getWestWall())
				{
					if (!myrightWall)
						rightWall = "_right_no_wall_both.png";
					else
						rightWall = "_right_no_wall_front.png";
				} else
				{
					if (!myrightWall)
						rightWall = "_right_no_wall_back.png";
					else
						rightWall = "_right.png";
				}
			} else
			{
				c2 = Maze.getMap()[Player.getRow()][Player.getColumn() - 1];
				if (!c2.getWestWall())
					midWall = "_center_no_wall_back.png";
				else
					midWall = "_center.png";
				if (!c2.getSouthWall())
				{
					if (!myleftWall)
						leftWall = "_left_no_wall_both.png";
					else
						leftWall = "_left_no_wall_front.png";
				} else
				{
					if (!myleftWall)
						leftWall = "_left_no_wall_back.png";
					else
						leftWall = "_left.png";
				}
				if (!c2.getNorthWall())
				{
					if (!myrightWall)
						rightWall = "_right_no_wall_both.png";
					else
						rightWall = "_right_no_wall_front.png";
				} else
				{
					if (!myrightWall)
						rightWall = "_right_no_wall_back.png";
					else
						rightWall = "_right.png";
				}
			}
			String texture2 = c2.getTexture();
			String textNum2 = texture2.substring(0, 8);
			midWall = textNum2 + midWall;
			leftWall = textNum2 + leftWall;
			rightWall = textNum2 + rightWall;
			monster = c2.getMonster();
			treasure = c2.getTreasure();
		}
		// ///////////////////////////////////////////////////////////////
		// Deals with the situation that there is a wall in face
		else
		{
			midWall = textNum1 + "_center_wall_in_face.png";
			if (myleftWall)
			{
				leftWall = "_left.png";
			} else
				leftWall = "_left_no_wall_back.png";
			if (myrightWall)
				rightWall = "_right.png";
			else
				rightWall = "_right_no_wall_back.png";
			
			leftWall = textNum1 + leftWall;
			rightWall = textNum1 + rightWall;
		}
		
		ImageIcon image1 = new ImageIcon(getClass().getResource("/" + leftWall));
		ImageIcon image2 = new ImageIcon(getClass().getResource("/" + midWall));
		ImageIcon image3 = new ImageIcon(getClass()
				.getResource("/" + rightWall));
		
		g.drawImage(image1.getImage(), 0, 0, null);
		g.drawImage(image2.getImage(), 228, 0, null);
		g.drawImage(image3.getImage(), 571, 0, null);
		
		// Draws the monster and treasure if there is any
		if (monster != null)
		{
			// System.out.println("There is a monster!!");
			// System.out.println(monster.getImage1());
			// System.out.println(monster.getCurrentState());
			
			String monsterimage = "/";
			if (monster.getCurrentState() == Monster.state1)
			{
				monsterimage = monsterimage + monster.getImage1();
				// System.out.println(monster.getImage1());
			} else
			{
				monsterimage = monsterimage + monster.getImage2();
				// System.out.println(monster.getImage2());
			}
			// ImageIcon image4 = new ImageIcon(monsterimage);
			
			ImageIcon image4 = new ImageIcon(getClass().getResource(
					monsterimage));
			g.drawImage(image4.getImage(), monster.getXCord(),
					monster.getYCord(), null);
			
		}
		if (treasure != null)
		{
			// System.out.println("There is a treasure!!");
			// System.out.println(treasure.getImage());
			g.drawImage(
					(new ImageIcon(getClass().getResource(
							"/" + treasure.getImage()))).getImage(),
					treasure.getXCord(), treasure.getYCord(), null);
		}
		
		// ////////////////////////////////////////////////////////////
		// Deals with the HUD
		Font myFont = new Font("TlwgMono", Font.BOLD, 26);
		g.setColor(new Color(199, 191, 230));
		g.fillRect(0, 600, 800, 200);
		g.setColor(Color.BLACK);
		g.setFont(myFont);
		g.drawString("Health", 28, 670);
		g.setColor(Color.BLACK);
		g.fillRect(23, 690, 210, 30);
		int health = Player.getHealth();
		// System.out.println(health);
		if (health > 0.67 * Player.getDefault())
		{
			g.setColor(new Color(34, 177, 72));
		} else if (health > 0.33 * Player.getDefault()
				&& health <= 0.67 * Player.getDefault())
		{
			g.setColor(Color.YELLOW);
		} else
		{
			g.setColor(Color.RED);
		}
		
		maze.getPlayer();
		g.fillRect(28, 695, (200 * health) / Player.getDefault(), 20);
		
		g.setColor(Color.BLACK);
		g.fillRect(270, 700, 25, 5);
		g.fillRect(280, 690, 5, 25);
		
		int x = 0;
		int y = 0;
		direction = maze.getPlayer().getDirectionFacing();
		if (direction == 'N')
		{
			x = 275;
			y = 680;
		} else if (direction == 'S')
		{
			x = 275;
			y = 740;
		} else if (direction == 'E')
		{
			x = 248;
			y = 710;
		} else
		{
			x = 300;
			y = 710;
		}
		g.drawString("N", x, y);
		
		// Draws the direction "buttons"
		g.fillRect(340, 640, 120, 120);
		g.setColor(new Color(199, 191, 230));
		g.fillRect(347, 647, 30, 30);
		g.fillRect(385, 647, 30, 30);
		g.fillRect(423, 647, 30, 30);
		g.fillRect(347, 685, 30, 30);
		g.fillRect(423, 685, 30, 30);
		g.fillRect(385, 723, 30, 30);
		
		// Draws the turn-left arrow
		g.setColor(Color.BLACK);
		g.fillArc(352, 656, 20, 38, 0, 90);
		g.setColor(new Color(199, 191, 230));
		g.fillArc(357, 658, 10, 38, 0, 90);
		g.setColor(Color.BLACK);
		int[] xPoints = { 362, 362, 350 };
		int[] yPoints = { 650, 664, 657 };
		g.fillPolygon(xPoints, yPoints, 3);
		
		// Draws the turn-right arrow
		g.setColor(Color.BLACK);
		g.fillArc(428, 656, 20, 38, 180, -90);
		g.setColor(new Color(199, 191, 230));
		g.fillArc(433, 658, 10, 38, 180, -90);
		g.setColor(Color.BLACK);
		int[] xPoints1 = { 438, 438, 450 };
		int[] yPoints1 = { 650, 664, 657 };
		g.fillPolygon(xPoints1, yPoints1, 3);
		
		// Draws the arrows of the four directions
		int[] xPoints2 = { 400, 390, 395, 395, 405, 405, 410 };
		int[] yPoints2 = { 647, 662, 662, 675, 675, 662, 662 };
		g.fillPolygon(xPoints2, yPoints2, 7);
		
		int[] xPoints3 = { 400, 390, 395, 395, 405, 405, 410 };
		int[] yPoints3 = { 753, 738, 738, 725, 725, 738, 738 };
		g.fillPolygon(xPoints3, yPoints3, 7);
		
		int[] xPoints4 = { 347, 362, 362, 375, 375, 362, 362 };
		int[] yPoints4 = { 700, 710, 705, 705, 695, 695, 690 };
		g.fillPolygon(xPoints4, yPoints4, 7);
		
		int[] xPoints5 = { 453, 438, 438, 425, 425, 438, 438 };
		int[] yPoints5 = { 700, 710, 705, 705, 695, 695, 690 };
		g.fillPolygon(xPoints5, yPoints5, 7);
		
		// Draws the attack "button"
		g.fillRect(480, 675, 100, 50);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(485, 680, 90, 40);
		
		g.setFont(myFont.deriveFont(Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString("Attack", 495, 705);
		
		g.setFont(myFont.deriveFont(Font.BOLD, 24));
		g.drawString("Treasure:", 595, 670);
		g.drawString("" + maze.getPlayer().getWealth() + " Gold", 595, 700);
	}
	
	/**
	 * Handles the mouse clicked situations. Picks up the treasure when it is
	 * clicked
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		Point p = new Point(e.getX(), e.getY());
		int px = (int) p.getX();
		int py = (int) p.getY();
		
		// -----------------------------------------------
		// ------Sets up to be ready for the handling-----
		// -----------------------------------------------
		Cell c1 = Maze.getMap()[Player.getRow()][Player.getColumn()];
		// FrontRoom
		Cell c2 = null;
		// BackRoom
		Cell c3 = null;
		// LeftRoom
		Cell c4 = null;
		// RightRoom
		Cell c5 = null;
		
		Monster monster = null;
		Treasure treasure = null;
		boolean myfrontWall = false;
		boolean myleftWall = false;
		boolean myrightWall = false;
		boolean mybackWall = false;
		char direction = maze.getPlayer().getDirectionFacing();
		if (direction == 'N')
		{
			myfrontWall = c1.getNorthWall();
			myleftWall = c1.getWestWall();
			myrightWall = c1.getEastWall();
			mybackWall = c1.getSouthWall();
		} else
		{
			if (direction == 'E')
			{
				myfrontWall = c1.getEastWall();
				myleftWall = c1.getNorthWall();
				myrightWall = c1.getSouthWall();
				mybackWall = c1.getWestWall();
			} else
			{
				if (direction == 'S')
				{
					myfrontWall = c1.getSouthWall();
					myleftWall = c1.getEastWall();
					myrightWall = c1.getWestWall();
					mybackWall = c1.getNorthWall();
				} else
				{
					myfrontWall = c1.getWestWall();
					myleftWall = c1.getSouthWall();
					myrightWall = c1.getNorthWall();
					mybackWall = c1.getEastWall();
				}
			}
		}
		if (!myfrontWall)
		{
			if (direction == 'N')
			{
				c2 = Maze.getMap()[Player.getRow() - 1][Player.getColumn()];
			} else if (direction == 'E')
			{
				c2 = Maze.getMap()[Player.getRow()][Player.getColumn() + 1];
			} else if (direction == 'S')
			{
				c2 = Maze.getMap()[Player.getRow() + 1][Player.getColumn()];
			} else
			{
				c2 = Maze.getMap()[Player.getRow()][Player.getColumn() - 1];
			}
			monster = c2.getMonster();
			treasure = c2.getTreasure();
		}
		if (!mybackWall)
		{
			if (direction == 'N')
			{
				c3 = Maze.getMap()[Player.getRow() + 1][Player.getColumn()];
			} else if (direction == 'E')
			{
				c3 = Maze.getMap()[Player.getRow()][Player.getColumn() - 1];
			} else if (direction == 'S')
			{
				c3 = Maze.getMap()[Player.getRow() - 1][Player.getColumn()];
			} else
			{
				c3 = Maze.getMap()[Player.getRow()][Player.getColumn() + 1];
			}
		}
		if (!myleftWall)
		{
			if (direction == 'N')
			{
				c4 = Maze.getMap()[Player.getRow()][Player.getColumn() - 1];
			} else if (direction == 'E')
			{
				c4 = Maze.getMap()[Player.getRow() - 1][Player.getColumn()];
			} else if (direction == 'S')
			{
				c4 = Maze.getMap()[Player.getRow()][Player.getColumn() + 1];
			} else
			{
				c4 = Maze.getMap()[Player.getRow() + 1][Player.getColumn()];
			}
		}
		if (!myrightWall)
		{
			if (direction == 'N')
			{
				c5 = Maze.getMap()[Player.getRow()][Player.getColumn() + 1];
			} else if (direction == 'E')
			{
				c5 = Maze.getMap()[Player.getRow() + 1][Player.getColumn()];
			} else if (direction == 'S')
			{
				c5 = Maze.getMap()[Player.getRow()][Player.getColumn() - 1];
			} else
			{
				c5 = Maze.getMap()[Player.getRow() - 1][Player.getColumn()];
			}
		}
		
		// --------------Handles the pickup Treasure---------------------
		if (treasure != null)
		{
			int x = treasure.getXCord();
			int y = treasure.getYCord();
			int width = treasure.getWidth();
			int height = treasure.getHeight();
			if ((x <= px) && (x + width >= px) && (y <= py)
					&& (y + height >= py))
			{
				maze.getPlayer().pickUpTreasure(treasure);
				c2.setTreasure(null);
			}
		}
		// -------------Handles a click of turn left arrow----------------
		if ((347 <= px) && (347 + 30 >= px) && (647 <= py) && (647 + 30 >= py))
		{
			maze.getPlayer().turnLeft();
		}
		// -------------Handles a click of turn right arrow----------------
		if ((423 <= px) && (423 + 30 >= px) && (647 <= py) && (647 + 30 >= py))
		{
			maze.getPlayer().turnRight();
		}
		// -------------Handles a click of left arrow----------------
		if ((347 <= px) && (347 + 30 >= px) && (685 <= py) && (685 + 30 >= py))
		{
			if (!myleftWall)
			{
				if (c4.getMonster() == null)
				{
					maze.getPlayer().moveLeft();
				}
			}
		}
		// -------------Handles a click of right arrow----------------
		if ((423 <= px) && (423 + 30 >= px) && (685 <= py) && (685 + 30 >= py))
		{
			if (!myrightWall)
			{
				if (c5.getMonster() == null)
				{
					maze.getPlayer().moveRight();
				}
			}
		}
		// -------------Handles a click of up arrow----------------
		if ((385 <= px) && (385 + 30 >= px) && (647 <= py) && (647 + 30 >= py))
		{
			if (!myfrontWall)
			{
				if (monster == null)
				{
					maze.getPlayer().moveFront();
				}
			}
		}
		// -------------Handles a click of down arrow----------------
		if ((385 <= px) && (385 + 30 >= px) && (723 <= py) && (723 + 30 >= py))
		{
			if (!mybackWall)
			{
				if (c3.getMonster() == null)
				{
					maze.getPlayer().moveBack();
				}
			}
		}
		// -------------Handles a click of attack----------------
		if ((485 <= px) && (485 + 90 >= px) && (680 <= py) && (680 + 40 >= py))
		{
			if (monster != null)
			{
				maze.getPlayer().attack(monster);
				if (!monster.checkAlive())
				{
					c2.setMonster(null);
					maze.numAliveMonster--;
				}
			}
		}
		this.repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		// Do not need to do anything
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// Do not need to do anything
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// Do not need to do anything
		
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		// Do not need to do anything
		
	}
	
	@Override
	public void keyTyped(KeyEvent ke)
	{
		// Do not need to do anything
		
	}
	
	/**
	 * Handles what happens when a key is pressed.
	 */
	@Override
	public void keyPressed(KeyEvent ke)
	{
		// ----------------------------------------------------------
		// -----------------Sets up for events-----------------------
		// ----------------------------------------------------------
		Cell c1 = Maze.getMap()[Player.getRow()][Player.getColumn()];
		// FrontRoom
		Cell c2 = null;
		// BackRoom
		Cell c3 = null;
		// LeftRoom
		Cell c4 = null;
		// RightRoom
		Cell c5 = null;
		
		Monster monster = null;
		Treasure treasure = null;
		boolean myfrontWall = false;
		boolean myleftWall = false;
		boolean myrightWall = false;
		boolean mybackWall = false;
		char direction = maze.getPlayer().getDirectionFacing();
		if (direction == 'N')
		{
			myfrontWall = c1.getNorthWall();
			myleftWall = c1.getWestWall();
			myrightWall = c1.getEastWall();
			mybackWall = c1.getSouthWall();
		} else
		{
			if (direction == 'E')
			{
				myfrontWall = c1.getEastWall();
				myleftWall = c1.getNorthWall();
				myrightWall = c1.getSouthWall();
				mybackWall = c1.getWestWall();
			} else
			{
				if (direction == 'S')
				{
					myfrontWall = c1.getSouthWall();
					myleftWall = c1.getEastWall();
					myrightWall = c1.getWestWall();
					mybackWall = c1.getNorthWall();
				} else
				{
					myfrontWall = c1.getWestWall();
					myleftWall = c1.getSouthWall();
					myrightWall = c1.getNorthWall();
					mybackWall = c1.getEastWall();
				}
			}
		}
		if (!myfrontWall)
		{
			if (direction == 'N')
			{
				c2 = Maze.getMap()[Player.getRow() - 1][Player.getColumn()];
			} else if (direction == 'E')
			{
				c2 = Maze.getMap()[Player.getRow()][Player.getColumn() + 1];
			} else if (direction == 'S')
			{
				c2 = Maze.getMap()[Player.getRow() + 1][Player.getColumn()];
			} else
			{
				c2 = Maze.getMap()[Player.getRow()][Player.getColumn() - 1];
			}
			monster = c2.getMonster();
			treasure = c2.getTreasure();
		}
		if (!mybackWall)
		{
			if (direction == 'N')
			{
				c3 = Maze.getMap()[Player.getRow() + 1][Player.getColumn()];
			} else if (direction == 'E')
			{
				c3 = Maze.getMap()[Player.getRow()][Player.getColumn() - 1];
			} else if (direction == 'S')
			{
				c3 = Maze.getMap()[Player.getRow() - 1][Player.getColumn()];
			} else
			{
				c3 = Maze.getMap()[Player.getRow()][Player.getColumn() + 1];
			}
		}
		if (!myleftWall)
		{
			if (direction == 'N')
			{
				c4 = Maze.getMap()[Player.getRow()][Player.getColumn() - 1];
			} else if (direction == 'E')
			{
				c4 = Maze.getMap()[Player.getRow() - 1][Player.getColumn()];
			} else if (direction == 'S')
			{
				c4 = Maze.getMap()[Player.getRow()][Player.getColumn() + 1];
			} else
			{
				c4 = Maze.getMap()[Player.getRow() + 1][Player.getColumn()];
			}
		}
		if (!myrightWall)
		{
			if (direction == 'N')
			{
				c5 = Maze.getMap()[Player.getRow()][Player.getColumn() + 1];
			} else if (direction == 'E')
			{
				c5 = Maze.getMap()[Player.getRow() + 1][Player.getColumn()];
			} else if (direction == 'S')
			{
				c5 = Maze.getMap()[Player.getRow()][Player.getColumn() - 1];
			} else
			{
				c5 = Maze.getMap()[Player.getRow() - 1][Player.getColumn()];
			}
		}
		
		// ----------------------Handles the keyEvent------------------------
		int keyCode = ke.getKeyCode();
		switch (keyCode)
		{
		// ------------------Handles key up------------------------------
			case KeyEvent.VK_UP:
				if (!myfrontWall)
				{
					if (monster == null)
					{
						maze.getPlayer().moveFront();
					}
				}
				break;
			// ------------------Handles key down------------------------------
			case KeyEvent.VK_DOWN:
				if (!mybackWall)
				{
					if (c3.getMonster() == null)
					{
						maze.getPlayer().moveBack();
					}
				}
				break;
			// ------------------Handles key left------------------------------
			case KeyEvent.VK_LEFT:
				if (!myleftWall)
				{
					if (c4.getMonster() == null)
					{
						maze.getPlayer().moveLeft();
					}
				}
				break;
			// ------------------Handles key right------------------------------
			case KeyEvent.VK_RIGHT:
				if (!myrightWall)
				{
					if (c5.getMonster() == null)
					{
						maze.getPlayer().moveRight();
					}
				}
				break;
			// ------------------Handles key space------------------------------
			case KeyEvent.VK_SPACE:
				if (monster != null)
				{
					maze.getPlayer().attack(monster);
					if (!monster.checkAlive())
					{
						c2.setMonster(null);
						maze.numAliveMonster--;
					}
				}
				break;
		}
		
		this.repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent ke)
	{
		// Do not need to do anything
	}
	
	/**
	 * tester, create the JComponent and put it in a frame
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		JFrame f = new JFrame();
		Display h = new Display();
		h.setPreferredSize(new Dimension(800, 800));
		JPanel mainP = new JPanel();
		mainP.setLayout(new FlowLayout());
		mainP.add(h);
		f.add(mainP);
		f.pack();
		f.setVisible(true);
	}
}
