/*
 * Author: Han He
 * Purpose: represents the maze.The maze is constructed according to the rooms.txt file.
 * 			it also create a player, and creates the monsters and starts the threads
 * Date: Dec 3, 2014
 */
package project2;

import java.util.*;
import java.io.*;

public class Maze
{
	/**
	 * Data member, monster map, maps the name of the monster to all the other
	 * values of the attributes of this monster
	 */
	public Hashtable<String, MonsterStamp> monsterMap;
	/**
	 * Data member, treasure map, maps the name of the treasure to all the other
	 * values of the attributes of this treasure
	 */
	public Hashtable<String, TreasureStamp> treasureMap;
	
	/**
	 * Data member, 2D array of Cells/rooms. Each room is constructed according
	 * to the file
	 */
	public static Cell[][] map;
	
	/**
	 * Data member, number of rooms in the maze, number of rows, number of
	 * columns
	 */
	public int numCells;
	public static int rows;
	public static int cols;

	/**
	 * Data member, holds all the monsters
	 */
	public ArrayList<Monster> monsterList;
	/**
	 * Data member, holds all the monster threads
	 */
	public ArrayList<Thread> threadList;

	/**
	 * Data member, the number of monster that are alive
	 */
	public int numAliveMonster;

	/**
	 * Data mamber, player
	 */
	public Player player;
	
	/**
	 * constructor of Maze, initialize data members 
	 * @throws Exception FileNotFound Exception
	 */
	public Maze() throws Exception
	{
		player = new Player(10000, (float) 0.1);

		monsterList = new ArrayList<Monster>();
		threadList = new ArrayList<Thread>();
		monsterMap = setUpMonster();
		treasureMap = setUpTreasure();
	
		Scanner scan = new Scanner(new InputStreamReader(getClass().getResourceAsStream("/rooms.txt")));		
		numCells = scan.nextInt();
		rows = scan.nextInt();
		cols = scan.nextInt();

		map = new Cell[rows][cols];
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				scan.nextLine();
				scan.nextLine();
				String monster = scan.nextLine();
				String treasure = scan.nextLine();
				boolean wn = scan.nextBoolean();
				boolean we = scan.nextBoolean();
				boolean ws = scan.nextBoolean();
				boolean ww = scan.nextBoolean();
				String texture = scan.next();
				boolean ms = scan.nextBoolean();

				Monster m;
				if (monsterMap.containsKey(monster))
				{
					MonsterStamp mstamp = monsterMap.get(monster);
					m = new Monster(mstamp.getName(), mstamp.getHealth(), mstamp.getAttackCost(), mstamp.getCDT(), mstamp.getImage1(),
							mstamp.getImage2(), mstamp.getXCord(), mstamp.getYCord(), mstamp.getmTp(), mstamp.getpTm(), i, j);
					monsterList.add(m);
				} else
					m = null;

				Treasure t;
				if (treasureMap.containsKey(treasure))
				{
					TreasureStamp tstamp = treasureMap.get(treasure);
					t = new Treasure(tstamp.getName(), tstamp.getImage(), tstamp.getGoldValue(), tstamp.getHealthValue(), tstamp.getWidth(),
							tstamp.getHeight(), tstamp.getXCord(), tstamp.getYCord());
				} else
					t = null;

				map[i][j] = new Cell(m, t, wn, we, ws, ww, texture, ms);
			}
		}
		numAliveMonster = monsterList.size();

		for (Monster a : monsterList)
		{
			Thread b = new Thread(a);
			threadList.add(b);
			b.start();
		}
	}

	
	public static int getrows()
	{
		return rows;
	}
	
	public static int getcols()
	{
		return cols;
	}
	

	
	/**
	 * gets the list of threads
	 * @return ArrayList<Thread>
	 */
	public ArrayList<Thread> getThreadList()
	{
		return threadList;
	}
	/**
	 * helper function to set up the monster mapping
	 * 
	 * @return Hashtable<String, MonsterStamp>
	 * @throws Exception
	 *             FileReaderException
	 */
	public Hashtable<String, MonsterStamp> setUpMonster() throws Exception
	{
		Scanner scan1 = new Scanner(new InputStreamReader(getClass().getResourceAsStream("/monster.txt")));
		Hashtable<String, MonsterStamp> mMap = new Hashtable<String, MonsterStamp>();
		int numM = scan1.nextInt();
		for (int i = 0; i < numM; i++)
		{
			scan1.nextLine();
			scan1.nextLine();
			String name = scan1.nextLine();
			String image1 = scan1.nextLine();
			String image2 = scan1.nextLine();
			int xCord = scan1.nextInt();
			int yCord = scan1.nextInt();
			int health = scan1.nextInt();
			int attackCost = scan1.nextInt();
			float coolDownTime = scan1.nextFloat();
			float mTp = scan1.nextFloat();
			float pTm = scan1.nextFloat();
			MonsterStamp ms = new MonsterStamp(name, health, attackCost, coolDownTime, image1, image2, xCord, yCord, mTp, pTm);
			mMap.put(name, ms);
		}
		return mMap;
	}

	/**
	 * helper function to set up the treasure mapping
	 * 
	 * @return Hashtable<String, TreasureStamp>
	 * @throws Exception
	 *             FileReaderException
	 */
	public Hashtable<String, TreasureStamp> setUpTreasure() throws Exception
	{
		Scanner scan = new Scanner(new InputStreamReader(getClass().getResourceAsStream("/treasure.txt")));
		Hashtable<String, TreasureStamp> tMap = new Hashtable<String, TreasureStamp>();
		int numT = scan.nextInt();
		for (int i = 0; i < numT; i++)
		{
			scan.nextLine();
			scan.nextLine();
			String name = scan.nextLine();
			String image = scan.nextLine();
			int goldValue = scan.nextInt();
			int healthValue = scan.nextInt();
			int width = scan.nextInt();
			int height = scan.nextInt();
			int xCord = scan.nextInt();
			int yCord = scan.nextInt();
			TreasureStamp st = new TreasureStamp(name, image, goldValue, healthValue, width, height, xCord, yCord);
			tMap.put(name, st);
		}
		return tMap;
	}

	/**
	 * gets the map
	 * 
	 * @return Cell[][] map
	 */
	public static Cell[][] getMap()
	{
		return map;
	}

	/**
	 * gets the list of monsters
	 * @return ArrayList<Monster>
	 */
	public ArrayList<Monster> getMonsterList()
	{
		return monsterList;
	}

	/**
	 * toString function that return nicely formated string of cells
	 */
	public String toString()
	{
		String s = "";
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				s = s + map[i][j].toString();
			}
		}
		return s;
	}

	/**
	 * gets the player
	 * @return Player
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * tester
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Maze amaze = new Maze();
		System.out.println(amaze);
	}
}
