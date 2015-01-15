/*
 * Author: Han He
 * Purpose: represents Monster in the game. It implements runnable so that each can
 * 			be handled in a different thread. Makes a "pew pew" sound when attacking
 * 			The monster uses depth first search to hunt you down
 * Date: Dec 2, 2014
 */
package project2;

import java.util.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;

public class Monster implements Runnable
{
	/**
	 * name, String, name of Character health, int, health point of the
	 * character attackCost, int, damage that this character could bring
	 * coolDownTime, int, time between actions
	 */
	protected String name;
	protected int health;
	protected int attackCost;
	protected float coolDownTime;
	
	static int state1 = 0;
	static int state2 = 1;
	private int currentState;
	/**
	 * Data member, String of image file 1
	 */
	private String image1;
	/**
	 * Data member, String of image file 2
	 */
	private String image2;
	/**
	 * Data member, int, x coordinate of the point that monster is to be drawn
	 */
	private int xCord;
	/**
	 * Data member, int, y coordinate of the point that monster is to be drawn
	 */
	private int yCord;
	/**
	 * Data member, float, possibility of a hit when monster attack player
	 */
	private float mTpAttackPossibility;
	/**
	 * Data member, float, possibility of a hit player attack monter
	 */
	private float pTmAttackPossibility;
	
	/**
	 * Data member, the row that monster is at
	 */
	private int row;
	
	/**
	 * Data member, the column that monster is at
	 */
	private int column;
	
	// private Clip clip;
	
	// private AudioInputStream inputStream;
	
	/**
	 * Data member, to add sound affect when running and attacking
	 */
	private AudioClip ac;
	
	/**
	 * Constructor of a Monster
	 * 
	 * @param name
	 *            , String, name of the monster
	 * @param health
	 *            , int, health point of the monster
	 * @param attackCost
	 *            , int, damage that monster would bring
	 * @param coolDownTime
	 *            , float, time between actions
	 * @param image1
	 *            , String, filename of image1
	 * @param image2
	 *            , String, filename of image2
	 * @param xCord
	 *            , int, x coordinate of the point where monster is to be drawn
	 * @param yCord
	 *            , int, y coordinate of the point where monster is to be drawn
	 * @param num1
	 *            , float, possibility of a hit when monster attack player
	 * @param num2
	 *            , float, possibility of a hit player attack monster
	 * @throws Exception
	 */
	public Monster(String name, int health, int attackCost, float coolDownTime,
			String image1, String image2, int xCord, int yCord, float num1,
			float num2, int row, int column) throws Exception
	{
		this.name = name;
		this.health = health;
		this.attackCost = attackCost;
		this.coolDownTime = coolDownTime;
		this.image1 = image1;
		this.image2 = image2;
		this.xCord = xCord;
		this.yCord = yCord;
		this.mTpAttackPossibility = num1;
		this.pTmAttackPossibility = num2;
		this.row = row;
		this.column = column;
		currentState = state1;
		/*
		 * try{ clip = AudioSystem.getClip(); //InputStream stream =
		 * Monster.class.getResourceAsStream("/bite.wav"); inputStream =
		 * AudioSystem
		 * .getAudioInputStream(getClass().getResourceAsStream("/bite.wav"));
		 * clip.open(inputStream);
		 * //clip.open(AudioSystem.getAudioInputStream(stream)); }
		 * catch(Exception fail) { System.out.println(fail.toString()); }
		 */
		
		// ac = Applet.newAudioClip(this.getClass().getResource("/bite.wav"));
		// ac =
		// Applet.newAudioClip(this.getClass().getResource("/dracula.wav"));
		// ac =
		// Applet.newAudioClip(this.getClass().getResource("/evil_laugh.wav"));
		ac = Applet.newAudioClip(this.getClass().getResource("/pew.wav"));
		
	}
	
	public int getCurrentState()
	{
		return currentState;
	}
	
	/**
	 * gets the name of the Character
	 * 
	 * @return String name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * gets health points of the Character
	 * 
	 * @return int health
	 */
	public int getHealth()
	{
		return health;
	}
	
	/**
	 * gets the damage that the character could bring from attacking
	 * 
	 * @return int attackCost
	 */
	public int getAttackCost()
	{
		return attackCost;
	}
	
	/**
	 * gets the time between actions of this character
	 * 
	 * @return float coolDownTime
	 */
	public float getCoolDownTime()
	{
		return coolDownTime;
	}
	
	/**
	 * sets the health point
	 * 
	 * @param newHealth
	 *            int
	 */
	public void setHealth(int newHealth)
	{
		health = newHealth;
	}
	
	/**
	 * checks if the Character is still alive
	 * 
	 * @return true if health is greather than 0 false if not
	 */
	public boolean checkAlive()
	{
		return (health > 0);
	}
	
	/**
	 * gets the filename of image1
	 * 
	 * @return String image1
	 */
	public String getImage1()
	{
		return image1;
	}
	
	/**
	 * gets the filename of image2
	 * 
	 * @return String image2
	 */
	public String getImage2()
	{
		return image2;
	}
	
	/**
	 * gets the x cords
	 * 
	 * @return int xCord
	 */
	public int getXCord()
	{
		return xCord;
	}
	
	/**
	 * gets the yCord
	 * 
	 * @return int yCord
	 */
	public int getYCord()
	{
		return yCord;
	}
	
	/**
	 * gets m To p hit poss Clip clip = AudioSystem.getClip(); AudioInputStream
	 * inputStream = AudioSystem
	 * .getAudioInputStream(getClass().getResourceAsStream("/bite.wav"));
	 * clip.open(inputStream);ibility
	 * 
	 * @return float mTp
	 */
	public float getmTpAttackPossibility()
	{
		return mTpAttackPossibility;
	}
	
	/**
	 * gets p To m hit possibility
	 * 
	 * @return float pTm
	 */
	public float getpTmAttackPossibility()
	{
		return pTmAttackPossibility;
	}
	
	/**
	 * toString, formats a print ready string that shows all values of the
	 * attributes
	 */
	public String toString()
	{
		String s = "Name: " + getName() + "\n";
		s = s + "Health: " + getHealth() + "\n";
		s = s + "Cost of Damage: " + getAttackCost() + "\n";
		s = s + "Image1: " + getImage1() + "\n";
		s = s + "Image2: " + getImage2() + "\n";
		s = s + "x coordinate: " + getXCord() + "\n";
		s = s + "y coordinate: " + getYCord() + "\n";
		s = s + "Cool Down Time: " + getCoolDownTime() + "\n";
		s = s + "Possibility of Hitting Player: " + getmTpAttackPossibility()
				+ "\n";
		s = s + "Possibility of being Hit by Player: "
				+ getpTmAttackPossibility() + "\n";
		return s;
	}
	
	/**
	 * gets the column
	 * 
	 * @return int column
	 */
	public int getColumn()
	{
		return column;
	}
	
	/**
	 * gets the row
	 * 
	 * @return int row
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * moves the monster left/west
	 */
	public void moveLeft()
	{
		column--;
	}
	
	/**
	 * moves the monster right/East
	 */
	public void moveRight()
	{
		column++;
	}
	
	/**
	 * moves the monster up/North
	 */
	public void moveUp()
	{
		row--;
	}
	
	/**
	 * moves the monster down/South
	 */
	public void moveDown()
	{
		row++;
	}
	
	/**
	 * Attempts to attack player, the chance of successful attack depends on the
	 * monster to player attack possibility data
	 */
	public void attack()
	{
		
		// music();
		Random rn = new Random();
		int randomNumber = rn.nextInt(99) + 1;
		if (randomNumber <= this.getmTpAttackPossibility() * 100)
		{
			Player.gotHit(this.getAttackCost());
		}
		// ac.play();
	}
	
	/**
	 * Moves the monster around reasonably. it would try to move towards the
	 * player.
	 */
	public synchronized void move()
	{
		boolean moved = false;
		int playerRow = Player.getRow();
		int playerColumn = Player.getColumn();
		
		Cell[][] maze = Maze.getMap();
		Cell myLocation = maze[row][column];
		
		if ((playerRow > row) && (!moved))
		{
			if (!myLocation.getSouthWall())
			{
				if ((maze[row + 1][column].getMonster() == null)
						&& (!(row + 1 == playerRow) && (playerColumn == column)))
				{
					myLocation.setMonster(null);
					moveDown();
					moved = true;
				}
			}
		}
		
		if ((playerColumn > column) && (!moved))
		{
			if (!myLocation.getEastWall())
			{
				if ((maze[row][column + 1].getMonster() == null)
						&& !((row == playerRow) && (playerColumn == column + 1)))
				{
					myLocation.setMonster(null);
					moveRight();
					moved = true;
				}
			}
		}
		
		if ((playerColumn < column) && (!moved))
		{
			if (!myLocation.getWestWall())
			{
				if ((maze[row][column - 1].getMonster() == null)
						&& !((row == playerRow) && (playerColumn == column - 1)))
				{
					myLocation.setMonster(null);
					moveLeft();
					moved = true;
				}
			}
		}
		
		if ((playerRow < row) && (!moved))
		{
			if (!myLocation.getNorthWall())
			{
				if ((maze[row - 1][column].getMonster() == null)
						&& !((row - 1 == playerRow) && (playerColumn == column)))
				{
					myLocation.setMonster(null);
					moveUp();
					moved = true;
				}
			}
		}
		myLocation = maze[row][column];
		myLocation.setMonster(this);
	}
	
	/**
	 * sets the current state to state
	 * 
	 * @param state
	 *            , either 1 or 0
	 */
	public void setCurrentState(int state)
	{
		currentState = state;
	}
	
	/**
	 * override the run method, either attack or move towards the player waits
	 * for a while before next move
	 */
	@Override
	public void run()
	{
		
		boolean done = false;
		HanNode playerOrigin = new HanNode(Player.getRow(), Player.getColumn());
		ArrayList<HanNode> path = dfs(new HanNode(row, column), new HanNode(
				Player.getRow(), Player.getColumn()));
		while (!done)
		{
			if (canAttack() && checkAlive())
			{
				// ac.play();
				
				ac.loop();
				// System.out.println(getName());
				if (currentState == state1)
				{
					setCurrentState(state2);
				} else
				{
					setCurrentState(state1);
				}
				attack();
				try
				{
					Thread.sleep((long) (getCoolDownTime() * 100));
				} catch (InterruptedException e)
				{ // Do nothing
				}
				ac.stop();
				// System.out.println(gui.getDisplay().getMaze().getPlayer().getHealth());
			} else
			{
				
				HanNode newLocation = new HanNode(Player.getRow(),
						Player.getColumn());
				if (!newLocation.equals(playerOrigin))
				{
					System.out.println("Player moved!!!!!!!!1");
					playerOrigin = new HanNode(Player.getRow(),
							Player.getColumn());
					path = dfs(new HanNode(row, column), playerOrigin);
				}
				path = move2(path);
				// System.out.println(path.size());
				
				try
				{
					Thread.sleep(1000);
				}
				
				catch (InterruptedException e)
				{
					// Do nothing
				}
			}
			
			if (!checkAlive() || !Player.checkAlive())
			{
				done = true;
			}
		}
		
	}
	
	/**
	 * checks if player can be reached for attack
	 * 
	 * @return boolean, true if can start attack
	 */
	public boolean canAttack()
	{
		Cell c = Maze.getMap()[row][column];
		int playerRow = Player.getRow();
		int playerColumn = Player.getColumn();
		int distance = Math.abs(Math.abs(playerRow - row)
				+ Math.abs(playerColumn - column));
		if (distance == 1)
		{
			if (playerRow > row)
			{
				return !c.getSouthWall();
			} else if (playerRow < row)
			{
				return !c.getNorthWall();
			} else if (playerColumn > column)
			{
				return !c.getEastWall();
			} else
			{
				return !c.getWestWall();
			}
		}
		return false;
	}
	
	/**
	 * tester. See if it reads the file right.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Scanner scan = new Scanner(new FileReader("monster.txt"));
		int numM = scan.nextInt();
		for (int i = 0; i < numM; i++)
		{
			scan.nextLine();
			scan.nextLine();
			String name = scan.nextLine();
			String image1 = scan.nextLine();
			String image2 = scan.nextLine();
			int xCord = scan.nextInt();
			int yCord = scan.nextInt();
			int health = scan.nextInt();
			int attackCost = scan.nextInt();
			float coolDownTime = scan.nextFloat();
			float mTp = scan.nextFloat();
			float pTm = scan.nextFloat();
			Monster m = new Monster(name, health, attackCost, coolDownTime,
					image1, image2, xCord, yCord, mTp, pTm, 0, 0);
			// System.out.println(m);
		}
	}
	
	/**
	 * searches a path to get to player
	 * @param origin
	 * @param destination
	 * @return
	 */
	public ArrayList<HanNode> dfs(HanNode origin, HanNode destination)
	{
		ArrayList<HanNode> path = new ArrayList<HanNode>();
		ArrayList<HanNode> visited = new ArrayList<HanNode>();
		HanNode child = null;
		
		path.add(origin);
		if (origin.equals(destination))
		{
			return null;
		}
		while (!path.isEmpty())
		{
			HanNode top = path.remove(path.size() - 1);
			// System.out.println(top);
			if (!visited.contains(top))
			{
				if (top.equals(destination))
				{
					child = top;
					// System.out.println(child);
					break;
				}
				visited.add(top);
				int row = top.getRow();
				int col = top.getColumn();
				if (!Maze.getMap()[row][col].getNorthWall())
				{
					// System.out.println("add north Node");
					HanNode c = top.upNode();
					c.setParent(top);
					path.add(c);
				}
				if (!Maze.getMap()[row][col].getEastWall())
				{
					// System.out.println("add east Node");
					HanNode c = top.rightNode();
					c.setParent(top);
					path.add(c);
				}
				if (!Maze.getMap()[row][col].getSouthWall())
				{
					// System.out.println("add south Node");
					HanNode c = top.downNode();
					c.setParent(top);
					path.add(c);
				}
				if (!Maze.getMap()[row][col].getWestWall())
				{
					// System.out.println("add left Node");
					HanNode c = top.leftNode();
					c.setParent(top);
					path.add(c);
				}
			}
		}
		ArrayList<HanNode> realPath = new ArrayList<HanNode>();
		while (child.getParent() != null)
		{
			realPath.add(child);
			child = child.getParent();
		}
		return realPath;
	}
	
	/**
	 * moves towards user
	 * @param path
	 * @return
	 */
	public synchronized ArrayList<HanNode> move2(ArrayList<HanNode> path)
	{
		
		HanNode next = path.get(path.size() - 1);
		if ((Maze.getMap()[next.getRow()][next.getColumn()].getMonster() == null))
		{
			//System.out.println("Run");
			Maze.getMap()[next.getRow()][next.getColumn()].setMonster(this);
			Maze.getMap()[row][column].setMonster(null);
			if (next.getRow() > row)
			{
				//System.out.println("moved down");
				moveDown();
			} else if (next.getRow() < row)
			{
				//System.out.println("moved up");
				moveUp();
			} else if (next.getColumn() > column)
			{
				//System.out.println("moved right");
				moveRight();
			} else
			{
				//System.out.println("moved left");
				moveLeft();
			}
			path.remove(path.size() - 1);
			return path;
		}
		return path;
	}
	
}

/**
 * Helper class used to represent a node
 * @author han.he
 *
 */
class HanNode
{
	/**
	 * data members
	 */
	private int row;
	private int column;
	private boolean visited;
	private HanNode parent;
	
	/**
	 * initialize node data 
	 * @param row
	 * @param column
	 */
	public HanNode(int row, int column)
	{
		this.row = row;
		this.column = column;
		visited = false;
		parent = null;
	}
	/**
	 * gets row
	 * @return
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * sets parent
	 * @param aparent
	 */
	public void setParent(HanNode aparent)
	{
		this.parent = aparent;
	}
	
	/**
	 * gets parent 
	 * @return
	 */
	public HanNode getParent()
	{
		return parent;
	}
	
	/**
	 * gets col
	 * @return
	 */
	public int getColumn()
	{
		return column;
	}
	/**
	 * set visited 
	 */
	public void setVisited()
	{
		visited = false;
	}
	/**
	 * gets visited
	 * @return
	 */
	public boolean visited()
	{
		return visited;
	}
	/**
	 * gets node above
	 * @return
	 */
	public HanNode upNode()
	{
		return new HanNode(row - 1, column);
	}
	
	/**
	 * gets down node
	 * @return
	 */
	public HanNode downNode()
	{
		return new HanNode((row + 1), column);
	}
	/**
	 * gets left node
	 * @return
	 */
	public HanNode leftNode()
	{
		return new HanNode(row, column - 1);
	}
	
	/**
	 * gets right node
	 * @return
	 */
	public HanNode rightNode()
	{
		return new HanNode(row, column + 1);
	}
	
	/**
	 * equals
	 */
	@Override
	public boolean equals(Object node)
	{
		if (node instanceof HanNode)
		{
			return (((HanNode) node).getRow() == row)
					&& (((HanNode) node).getColumn() == column);
		}
		return false;
	}
	
	/**
	 * compares if two nodes are the same
	 * @param node
	 * @return
	 */
	public int compareTo(Object node)
	{
		if (node instanceof HanNode)
		{
			HanNode n = (HanNode) node;
			if (n.getColumn() > column)
			{
				return 1;
			} else if (n.getColumn() < column)
			{
				return -1;
			} else
			{
				if (n.getRow() > row)
				{
					return 1;
				} else if (n.getRow() < row)
				{
					return -1;
				} else
				{
					return 0;
				}
			}
		}
		return 2;
	}
	
	/**
	 * tostring
	 */
	public String toString()
	{
		return "Location: " + row + ":" + column;
	}
}