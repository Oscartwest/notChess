package com.wayneschools;

import javax.swing.*;
import java.awt.event.*;

public class WinScreen extends JPanel implements MouseListener
{

	private GameFrame main;

	private JLabel labelBlackWin;
	private JLabel labelWhiteWin;
	private ImageIcon imageBlackWin;
	private ImageIcon imageWhiteWin;


	public WinScreen(GameFrame main)
	{
		this.main = main;

		setLayout(null);

		imageBlackWin = new ImageIcon("src/main/resources//blackWin.png");
		labelBlackWin = new JLabel(imageBlackWin);
		labelBlackWin.setBounds(0, 0, 800, 800);


		imageWhiteWin = new ImageIcon("src/main/resources/otherImages/whiteWin.png");
		labelWhiteWin = new JLabel(imageWhiteWin);
		labelWhiteWin.setBounds(0, 0, 800, 800);

		add(labelBlackWin);
		add(labelWhiteWin);


		addMouseListener(this);
		

	}


	public void setWinTeam(boolean blackWin)
	{
		if(blackWin)
		{
			labelBlackWin.setVisible(true);
			labelWhiteWin.setVisible(false);
		}
		else
		{
			labelWhiteWin.setVisible(true);
			labelBlackWin.setVisible(false);
		}


	}

	public void mouseClicked(MouseEvent e)
	{
		if(e.getX()>270&&e.getX()<530&&e.getY()>630&&e.getY()<680)
		{
			main.setMenu();
		}
	}

	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}




}