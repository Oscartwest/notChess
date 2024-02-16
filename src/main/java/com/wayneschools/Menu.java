package com.wayneschools;

import javax.swing.*;
import java.awt.event.*;

public class Menu extends JPanel implements MouseListener
{
	GameFrame main;

	JLabel labelMenu;

	public Menu(GameFrame main)
	{
		this.main = main;

		setLayout(null);



		labelMenu = new JLabel(new ImageIcon("src/main/resources/otherImages/menu.png"));
		labelMenu.setBounds(0, 0, 800, 800);
		
		add(labelMenu);

		addMouseListener(this);




		


	}

	public void mouseClicked(MouseEvent e)
	{
		if(e.getY()>425&&e.getY()<720)
		{
			if(e.getX()<400)
			{
				main.setGame(1);
			}
			else
			{
				main.setGame(2);
			}

		}

	}

	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	





}
