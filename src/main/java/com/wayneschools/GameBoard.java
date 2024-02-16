package com.wayneschools;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel
{
	private ChessSpace board[][] = new ChessSpace[8][8];
	private MoveValidator validator = new MoveValidator(this);
	private ChessSpace nullSquare = new ChessSpace();
	private ChessSpace selectedSquare = nullSquare;
	private boolean isBlackTurn = false;
	private int kingInCheckXWhite;
	private int kingInCheckYWhite;
	private int kingInCheckXBlack;
	private int kingInCheckYBlack;

	public GameBoard(GameFrame main)
	{
		setLayout(new GridLayout(8, 8));

		
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 8; x++)
			{
				if (x % 2 == 0 && y % 2 != 0 || x % 2 != 0 && y % 2 == 0)
				{
					board[x][y] = new ChessSpace(main, this, validator, new Color(113, 130, 227), x, y);
				}
				else
				{
					board[x][y] = new ChessSpace(main, this, validator, new Color(50, 60, 169), x, y);
				}
				add(board[x][y]);
			}

		}
		//resetGame();


	}

	public void resetGame()
	{

		for(int iy = 0; iy<8;iy++)
		{
			for(int i = 0; i<8;i++)
			{
				board[i][iy].setPiece(0, false);
			}
		}
	}


	public void blankStart()
	{
		for (int y = 0; y < 8; y++)
		{
			for (int x = 0; x < 8; x++)
			{
				board[x][y].setPiece(0, false);
			}	
		}
	}

	public void ezWinStart()
	{
		for (int y = 0; y < 8; y++)
		{
			for (int x = 0; x < 8; x++)
			{
				board[x][y].setPiece(0, false);
			}	
		}
		board[4][3].setPiece(6, false);
		board[4][4].setPiece(6, true);	
	}

	public void regChessStart()
	{
		for(int i = 0; i<8;i++)
		{
			board[i][1].setPiece(1, true);
			board[i][2].setPiece(0, false);
			board[i][3].setPiece(0, false);
			board[i][4].setPiece(0, false);
			board[i][5].setPiece(0, false);
			board[i][6].setPiece(1, false);
		}
		board[0][0].setPiece(2, true);
		board[1][0].setPiece(3, true);
		board[2][0].setPiece(4, true);
		board[3][0].setPiece(5, true);
		board[4][0].setPiece(6, true);
		board[5][0].setPiece(4, true);
		board[6][0].setPiece(3, true);
		board[7][0].setPiece(2, true);
		board[0][7].setPiece(2, false);
		board[1][7].setPiece(3, false);
		board[2][7].setPiece(4, false);
		board[3][7].setPiece(5, false);
		board[4][7].setPiece(6, false);
		board[5][7].setPiece(4, false);
		board[6][7].setPiece(3, false);
		board[7][7].setPiece(2, false);
		revalidate();



	}


	public void altChessStart()
	{
		for(int i = 0; i<8; i++)
		{
			board[i][0].setPiece(1, true);
			board[i][1].setPiece(1, true);
			board[i][2].setPiece(0, false);
			board[i][3].setPiece(0, false);
			board[i][4].setPiece(0, false);
			board[i][5].setPiece(0, false);
			board[i][6].setPiece(1, false);
			board[i][7].setPiece(1, false);
			board[4][0].setPiece(6, true);
			board[4][7].setPiece(6, false);
			

			
		}





	}




	public void selectSquare(ChessSpace selection)
	{
		selectedSquare.deselect();
		if(selectedSquare == selection)
		{
			selectedSquare = nullSquare;
			isCheckWhite();
			isCheckBlack();
		}
		else if (selectedSquare != selection && selection.getID() != 6)
		{
			selectedSquare = selection;
			isCheckBlack();
			isCheckWhite();
		}
		else
		{
			selectedSquare = selection;
		}


	}

public boolean isCheckWhite()
	{		
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 8; x++)
			{
				if(board[x][y].getID() == 6 && board[x][y].getIsBlack() == false)
				{
					boolean isBlackKing = board[x][y].getIsBlack();
					
					for(int yTarget = 0; yTarget < 8; yTarget++)
					{
						for(int xTarget = 0; xTarget < 8; xTarget++)
						{
							if(board[xTarget][yTarget].getID() != 0 && board[xTarget][yTarget].getIsBlack() != isBlackKing)
							{
								if(validator.isValid(board[x][y], board[xTarget][yTarget]))
								{
									setCheckRed(x, y);
									kingInCheckXWhite = x;
									kingInCheckYWhite = y;
									return true;
								}
							}
							else
							{
								
							}
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isCheckBlack()
	{
		for(int y = 0; y < 8; y++)
		{
			for(int x = 0; x < 8; x++)
			{
				if(board[x][y].getID() == 6 && board[x][y].getIsBlack() == true)
				{
					boolean isBlackKing = board[x][y].getIsBlack();
					
					for(int yTarget = 0; yTarget < 8; yTarget++)
					{
						for(int xTarget = 0; xTarget < 8; xTarget++)
						{
							if(board[xTarget][yTarget].getID() != 0 && board[xTarget][yTarget].getIsBlack() != isBlackKing)
							{
								if(validator.isValid(board[x][y], board[xTarget][yTarget]))
								{
									setCheckRed(x, y);
									kingInCheckXBlack = x;
									kingInCheckYBlack = y;
									return true;
								}
							}
							else
							{
								
							}
						}
					}
				}
			}
		}
		return false;
	}
	public int getKingInCheckXWhite()
	{
		return kingInCheckXWhite;
	}

	public int getKingInCheckYWhite()
	{
		return kingInCheckYWhite;
	}

	public int getKingInCheckXBlack()
	{
		return kingInCheckXBlack;
	}

	public int getKingInCheckYBlack()
	{
		return kingInCheckYBlack;
	}

	public void setCheckRed(int x, int y)
	{
		board[x][y].setBackground(Color.RED);
	}

	public void setCheckClear(int x, int y)
	{
		if(board[x][y].getBackground() == Color.RED)
		{
			if (x % 2 == 0 && y % 2 != 0 || x % 2 != 0 && y % 2 == 0)
			{
				board[x][y].setBackground(new Color(113, 130, 227));
			}
			else
			{
				board[x][y].setBackground(new Color(50, 60, 169));
			}
		}
	}

	public ChessSpace[][] getBoard()
	{
		return board;
	}

	public ChessSpace getSelected()
	{
		return selectedSquare;
	}

	public boolean isSelectedNull()
	{
		return selectedSquare==nullSquare;
	}
	public void deselect()
	{
		selectedSquare.deselect();
		selectedSquare = nullSquare;
	}
	public boolean isBlacksTurn()
	{
		return isBlackTurn;
	}
	public void swapActiveTeam()
	{
		if(isBlackTurn)
		{
			isBlackTurn = false;
		}
		else
		{
			isBlackTurn = true;
		}
	}
	public void reset()
	{
		deselect();
		if(isBlackTurn)
		{
			swapActiveTeam();
		}
	}





}
