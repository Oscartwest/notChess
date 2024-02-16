package com.wayneschools;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameFrame extends JFrame
{
	private GameBoard board;
	private Menu menu;
	private WinScreen winScreen;

	public GameFrame()
	{
		board = new GameBoard(this);
		menu = new Menu(this);
		winScreen = new WinScreen(this);
		

		setTitle("Not Chess");
		setForeground(Color.BLACK);
		try{setIconImage(ImageIO.read(new File("src/main/resources/blackPieces/queen.png")));}catch (IOException e) {}
		setVisible(true);
		setContentPane(menu);
		setSize(816, 815);


	}


	public void setMenu()
	{
		setContentPane(menu);
		revalidate(); 
		repaint();
	}
	public void setGame(int whichStart)
	{
		board.reset();
		switch(whichStart)
		{
			case 0:
			board.ezWinStart();
			break;
			case 1:
			board.regChessStart();
			break;
			case 2:
			board.altChessStart();
			break;
			case 3:
			board.blankStart();
			break;
		}
		
		setContentPane(board);
		revalidate();
		repaint();
	}
	public void setWin(boolean isBlackWin)
	{
		
		winScreen.setWinTeam(isBlackWin);
		setContentPane(winScreen);
		revalidate();

	}



}
