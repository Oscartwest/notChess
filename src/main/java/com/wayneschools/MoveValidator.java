package com.wayneschools;

import java.util.Random;

public class MoveValidator
{
	int originX, originY, targetX, targetY;
	ChessSpace origin, target;
	boolean originIsBlack, targetIsBlack;
	private GameBoard board;
	Random r;
	public MoveValidator(GameBoard board)
	{
		this.board = board;
		r = new Random();
	}

	public boolean isValid(ChessSpace selection)
	{
		origin = board.getSelected();
		target = selection;
		originX = origin.getGridX();
		originY = origin.getGridY();
		originIsBlack = origin.getIsBlack();
		targetX = target.getGridX();
		targetY = target.getGridY();
		targetIsBlack = target.getIsBlack();
		//System.out.println("\nOrigin: ("+originX+", "+originY+")\nTarget: ("+targetX+", "+targetY+")");



		switch (origin.getID())
		{
			case 0: break;
			case 1: //pawn
				//System.out.println("pawn");

				if(target.getID()==0)
				{
					if(originIsBlack && originY+1==targetY && targetX==originX)
					{
						pawnSwap(origin);
						return true;
					}
					else if(!originIsBlack && originY-1==targetY && targetX==originX)
					{
						pawnSwap(origin);
						return true;
					}
				}
				else if(originIsBlack && !targetIsBlack && originY+1==targetY && (originX+1 == targetX || originX-1 ==targetX))
				{
					pawnSwap(origin);
					return true;

				}
				else if(!originIsBlack && targetIsBlack && originY-1==targetY && (originX+1 == targetX || originX-1 ==targetX))
				{
					pawnSwap(origin);
					return true;
				}
				break;
			case 2: //rook
				//System.out.println("rook");
				if (originIsBlack != targetIsBlack || target.getID() == 0)
				{
					if(originY == targetY && targetX - originX > 0)
					{
						for(int i = originX + 1; i < targetX; i++)
						{
							if(board.getBoard()[i][originY].getID() != 0)
							{
								return false;
							}
						}
						return true;
					}
					else if(originX == targetX && targetY - originY > 0)
					{
						for(int i = originY + 1; i < targetY; i++)
						{
							
							if(board.getBoard()[originX][i].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
					else if(originX == targetX && targetY - originY < 0)
					{
						for(int i = originY - 1; i > targetY; i--)
						{
							if(board.getBoard()[originX][i].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
					else if(originY == targetY && targetX - originX < 0)
					{
						for(int i = originX - 1; i > targetX; i--)
						{
							if(board.getBoard()[i][originY].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
				}
				break;
			case 3: //knight
				//System.out.println("knight");
				if((originIsBlack != targetIsBlack || target.getID()==0)&&((Math.abs(originX-targetX)==1 && Math.abs(originY-targetY)==2)||(Math.abs(targetX-originX)==2&&Math.abs(targetY-originY)==1)))
				{
					return true;
				}
				break;
			case 4: //bishop
				if(originIsBlack!=targetIsBlack||target.getID()==0)
				{
					if(Math.abs(originX-targetX)==Math.abs(originY-targetY))
					{
						if(targetY - originY > 0 && targetX - originX > 0)
						{
							for(int xCheck = originX + 1; xCheck < targetX; xCheck++)
							{
								for(int yCheck = originY + 1; yCheck < targetY; yCheck++)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY < 0 && targetX - originX < 0)
						{
							for(int xCheck = originX - 1; xCheck > targetX; xCheck--)
							{
								for(int yCheck = originY - 1; yCheck > targetY; yCheck--)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY > 0 && targetX - originX < 0)
						{
							for(int xCheck = originX - 1; xCheck > targetX; xCheck--)
							{
								for(int yCheck = originY + 1; yCheck < targetY; yCheck++)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY < 0 && targetX - originX > 0)
						{
							for(int xCheck = originX + 1; xCheck < targetX; xCheck++)
							{
								for(int yCheck = originY - 1; yCheck > targetY; yCheck--)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
					}
				}
				break;



			case 5: //queen
				if(originIsBlack!=targetIsBlack||target.getID()==0)
				{
					if(originY == targetY && targetX - originX > 0)
					{
						for(int i = originX + 1; i < targetX; i++)
						{
							if(board.getBoard()[i][originY].getID() != 0)
							{
								return false;
							}
						}
						return true;
					}
					else if(originX == targetX && targetY - originY > 0)
					{
						for(int i = originY + 1; i < targetY; i++)
						{
							
							if(board.getBoard()[originX][i].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
					else if(originX == targetX && targetY - originY < 0)
					{
						for(int i = originY - 1; i > targetY; i--)
						{
							if(board.getBoard()[originX][i].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
					else if(originY == targetY && targetX - originX < 0)
					{
						for(int i = originX - 1; i > targetX; i--)
						{
							if(board.getBoard()[i][originY].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
					//^ rook movement for queen

										if(Math.abs(originX-targetX)==Math.abs(originY-targetY))
					{
						if(targetY - originY > 0 && targetX - originX > 0)
						{
							for(int xCheck = originX + 1; xCheck < targetX; xCheck++)
							{
								for(int yCheck = originY + 1; yCheck < targetY; yCheck++)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY < 0 && targetX - originX < 0)
						{
							for(int xCheck = originX - 1; xCheck > targetX; xCheck--)
							{
								for(int yCheck = originY - 1; yCheck > targetY; yCheck--)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY > 0 && targetX - originX < 0)
						{
							for(int xCheck = originX - 1; xCheck > targetX; xCheck--)
							{
								for(int yCheck = originY + 1; yCheck < targetY; yCheck++)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY < 0 && targetX - originX > 0)
						{
							for(int xCheck = originX + 1; xCheck < targetX; xCheck++)
							{
								for(int yCheck = originY - 1; yCheck > targetY; yCheck--)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
					}
				}
				break;
			case 6: //king
				if(originIsBlack!=targetIsBlack||target.getID()==0)
				{
					if(Math.abs(originX-targetX)<=1&&Math.abs(originY-targetY)<=1)
					{
						return true;
					}
				}
		}
		return false;
	}

public boolean isValid(ChessSpace selection, ChessSpace origin)
	{
		target = selection;
		originX = origin.getGridX();
		originY = origin.getGridY();
		originIsBlack = origin.getIsBlack();
		targetX = target.getGridX();
		targetY = target.getGridY();
		targetIsBlack = target.getIsBlack();
		//System.out.println("\nOrigin: ("+originX+", "+originY+")\nTarget: ("+targetX+", "+targetY+")");



		switch (origin.getID())
		{
			case 0: break;
			case 1: //pawn
				//System.out.println("pawn");

				if(target.getID()==0)
				{
					if(originIsBlack && originY+1==targetY && targetX==originX)
					{
						
						return true;
					}
					else if(!originIsBlack && originY-1==targetY && targetX==originX)
					{
						
						return true;
					}
				}
				else if(originIsBlack && !targetIsBlack && originY+1==targetY && (originX+1 == targetX || originX-1 ==targetX))
				{
					
					return true;

				}
				else if(!originIsBlack && targetIsBlack && originY-1==targetY && (originX+1 == targetX || originX-1 ==targetX))
				{
					
					return true;
				}
				break;
			case 2: //rook
				//System.out.println("rook");
				if (originIsBlack != targetIsBlack || target.getID() == 0)
				{
					if(originY == targetY && targetX - originX > 0)
					{
						for(int i = originX + 1; i < targetX; i++)
						{
							if(board.getBoard()[i][originY].getID() != 0)
							{
								return false;
							}
						}
						return true;
					}
					else if(originX == targetX && targetY - originY > 0)
					{
						for(int i = originY + 1; i < targetY; i++)
						{
							
							if(board.getBoard()[originX][i].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
					else if(originX == targetX && targetY - originY < 0)
					{
						for(int i = originY - 1; i > targetY; i--)
						{
							if(board.getBoard()[originX][i].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
					else if(originY == targetY && targetX - originX < 0)
					{
						for(int i = originX - 1; i > targetX; i--)
						{
							if(board.getBoard()[i][originY].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
				}
				break;
			case 3: //knight
				//System.out.println("knight");
				if((originIsBlack != targetIsBlack || target.getID()==0)&&((Math.abs(originX-targetX)==1 && Math.abs(originY-targetY)==2)||(Math.abs(targetX-originX)==2&&Math.abs(targetY-originY)==1)))
				{
					return true;
				}
				break;
			case 4: //bishop
				if(originIsBlack!=targetIsBlack||target.getID()==0)
				{
					if(Math.abs(originX-targetX)==Math.abs(originY-targetY))
					{
						if(targetY - originY > 0 && targetX - originX > 0)
						{
							for(int xCheck = originX + 1; xCheck < targetX; xCheck++)
							{
								for(int yCheck = originY + 1; yCheck < targetY; yCheck++)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY < 0 && targetX - originX < 0)
						{
							for(int xCheck = originX - 1; xCheck > targetX; xCheck--)
							{
								for(int yCheck = originY - 1; yCheck > targetY; yCheck--)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY > 0 && targetX - originX < 0)
						{
							for(int xCheck = originX - 1; xCheck > targetX; xCheck--)
							{
								for(int yCheck = originY + 1; yCheck < targetY; yCheck++)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY < 0 && targetX - originX > 0)
						{
							for(int xCheck = originX + 1; xCheck < targetX; xCheck++)
							{
								for(int yCheck = originY - 1; yCheck > targetY; yCheck--)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
					}
				}
				break;



			case 5: //queen
				if(originIsBlack!=targetIsBlack||target.getID()==0)
				{
					if(originY == targetY && targetX - originX > 0)
					{
						for(int i = originX + 1; i < targetX; i++)
						{
							if(board.getBoard()[i][originY].getID() != 0)
							{
								return false;
							}
						}
						return true;
					}
					else if(originX == targetX && targetY - originY > 0)
					{
						for(int i = originY + 1; i < targetY; i++)
						{
							
							if(board.getBoard()[originX][i].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
					else if(originX == targetX && targetY - originY < 0)
					{
						for(int i = originY - 1; i > targetY; i--)
						{
							if(board.getBoard()[originX][i].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
					else if(originY == targetY && targetX - originX < 0)
					{
						for(int i = originX - 1; i > targetX; i--)
						{
							if(board.getBoard()[i][originY].getID() != 0)
							{
								return false;
							}
							
						}
						return true;
					}
					//^ rook movement for queen

										if(Math.abs(originX-targetX)==Math.abs(originY-targetY))
					{
						if(targetY - originY > 0 && targetX - originX > 0)
						{
							for(int xCheck = originX + 1; xCheck < targetX; xCheck++)
							{
								for(int yCheck = originY + 1; yCheck < targetY; yCheck++)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY < 0 && targetX - originX < 0)
						{
							for(int xCheck = originX - 1; xCheck > targetX; xCheck--)
							{
								for(int yCheck = originY - 1; yCheck > targetY; yCheck--)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY > 0 && targetX - originX < 0)
						{
							for(int xCheck = originX - 1; xCheck > targetX; xCheck--)
							{
								for(int yCheck = originY + 1; yCheck < targetY; yCheck++)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
						else if(targetY - originY < 0 && targetX - originX > 0)
						{
							for(int xCheck = originX + 1; xCheck < targetX; xCheck++)
							{
								for(int yCheck = originY - 1; yCheck > targetY; yCheck--)
								{
									if(Math.abs(originX-xCheck)==Math.abs(originY-yCheck))
									{									
										if(board.getBoard()[xCheck][yCheck].getID() != 0)
										{
											return false;
										}
									}
								}	
							}
							return true;
						}
					}
				}
				break;
			case 6: //king
				if(originIsBlack!=targetIsBlack||target.getID()==0)
				{
					if(Math.abs(originX-targetX)<=1&&Math.abs(originY-targetY)<=1)
					{
						return true;
					}
				}
		}
		return false;
	}


	public void pawnSwap(ChessSpace pawnToSwap)
	{

		int pawnChance = 15;
		int rookChance = 20;
		int knightChance = 25;
		int bishopChance = 25;
		int queenChance = 8;
		int newPiece = r.nextInt(pawnChance+rookChance+knightChance+bishopChance+queenChance);

		if(newPiece<pawnChance)//pawn
		{
			pawnToSwap.setPiece(1, pawnToSwap.getIsBlack());
		}
		else if(newPiece<(pawnChance+rookChance))//rook
		{
			pawnToSwap.setPiece(2, pawnToSwap.getIsBlack());
		}
		else if(newPiece<(pawnChance+rookChance+knightChance))//knight
		{
			pawnToSwap.setPiece(3, pawnToSwap.getIsBlack());
		}
		else if(newPiece<(pawnChance+rookChance+knightChance+bishopChance))//bishop
		{
			pawnToSwap.setPiece(4, pawnToSwap.getIsBlack());
		}
		else if(newPiece<(pawnChance+rookChance+knightChance+bishopChance+queenChance))//queen
		{
			pawnToSwap.setPiece(5, pawnToSwap.getIsBlack());
		}





	}




}
