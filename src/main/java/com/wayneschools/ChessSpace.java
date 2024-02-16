package com.wayneschools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessSpace extends JButton implements ActionListener
{

	private ImageIcon[] blackPieces;
	private ImageIcon[] whitePieces;
	private int xPos, yPos;
	private int iconID;
	private boolean iconIsBlack;

	private Color selectedColor;
	private Color baseColor;

	private GameFrame main;
	private GameBoard board;
	private MoveValidator validator;

	//private int inCheckX;
	//private int inCheckY;


	public ChessSpace(){}
	public ChessSpace(GameFrame main, GameBoard board, MoveValidator validator, Color color, int x, int y)
	{

		addActionListener(this);
		this.main = main;
		this.xPos = x;
		this.yPos = y;
		selectedColor = new Color(255, 151, 41);
		baseColor = color;
		setBackground(baseColor);
		this.board = board;
		this.validator = validator;
		iconID = 0;


		blackPieces = new ImageIcon[7];
		blackPieces[1] = new ImageIcon("src/main/resources/blackPieces/pawn.png");
		blackPieces[2] = new ImageIcon("src/main/resources/blackPieces/rook.png");
		blackPieces[3] = new ImageIcon("src/main/resources/blackPieces/knight.png");
		blackPieces[4] = new ImageIcon("src/main/resources/blackPieces/bishop.png");
		blackPieces[5] = new ImageIcon("src/main/resources/blackPieces/queen.png");
		blackPieces[6] = new ImageIcon("src/main/resources/blackPieces/king.png");


		whitePieces = new ImageIcon[7];
		whitePieces[1] = new ImageIcon("src/main/resources/whitePieces/pawn.png");
		whitePieces[2] = new ImageIcon("src/main/resources/whitePieces/rook.png");
		whitePieces[3] = new ImageIcon("src/main/resources/whitePieces/knight.png");
		whitePieces[4] = new ImageIcon("src/main/resources/whitePieces/bishop.png");
		whitePieces[5] = new ImageIcon("src/main/resources/whitePieces/queen.png");
		whitePieces[6] = new ImageIcon("src/main/resources/whitePieces/king.png");



	}



	public void setPiece(int pieceID, boolean isBlack)
	{
		iconID = pieceID;
		iconIsBlack = isBlack;

		if(isBlack)
		{
			setIcon(blackPieces[pieceID]);
		}
		else
		{
			setIcon(whitePieces[pieceID]);
		}

	}

	public int getID()
	{
		return iconID;
	}
	public boolean getIsBlack()
	{
		return iconIsBlack;
	}


	public void deselect()
	{
		setBackground(baseColor);
	}

	public int getGridX()
	{
		return xPos;
	}

	public int getGridY()
	{
		return yPos;
	}



	public void actionPerformed(ActionEvent e)
	{
		//if selecting a piece and clicking valid move
		if(!board.isSelectedNull()&&validator.isValid(this))
		{
			//move piece
			if(iconID==6)
			{
				if(iconIsBlack)
				{
					//System.out.println("WHITE WIN");
					main.setWin(false);
				}
				else
				{
					//System.out.println("BLACK WIN");
					main.setWin(true);
				}

			}
			setPiece(board.getSelected().getID(), board.getSelected().getIsBlack());
			board.getSelected().setPiece(0, true);
			board.deselect();
			board.isCheckWhite();
			board.isCheckBlack();
			if(!board.isCheckWhite())
			{
				board.setCheckClear(board.getKingInCheckXWhite(), board.getKingInCheckYWhite());
			}
			if(!board.isCheckBlack())
			{
				board.setCheckClear(board.getKingInCheckXBlack(), board.getKingInCheckYBlack());
			}

			board.swapActiveTeam();




		}

		else if(iconID != 0 && board.isBlacksTurn()==iconIsBlack)
		{
			setBackground(selectedColor);
			board.selectSquare(this);
		}


	}





}


