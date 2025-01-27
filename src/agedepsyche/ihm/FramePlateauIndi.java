package agedepsyche.ihm;

import agedepsyche.Controleur;

import javax.swing.*;

import agedepsyche.metier.*;

import java.util.List;

public class FramePlateauIndi extends JFrame
{
	Controleur ctrl  ;
	int        equipe;

	public FramePlateauIndi(Controleur ctrl, int equipe) 
	{
		this.ctrl = ctrl;
		this.equipe = equipe;

		this.setTitle("Plateau Individuel");
		this.setSize(553  + 300, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(992, 500*equipe);
		//this.setResizable(false);

		this.add (new PanelPlateauIndi(ctrl, equipe));
		this.setVisible(true);
	}

}