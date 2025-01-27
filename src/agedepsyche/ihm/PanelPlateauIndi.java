package agedepsyche.ihm;

import javax.swing.*;

import agedepsyche.Controleur;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Font;

import java.util.List;
import agedepsyche.metier.*;
import java.awt.Color;

public class PanelPlateauIndi extends JPanel 
{
	private Image imgFond;
	private int   equipe;

	private Controleur ctrl;

	public PanelPlateauIndi(Controleur ctrl, int equipe) 
	{
		this.ctrl = ctrl;
		this.equipe = equipe;

		this.imgFond = getToolkit().getImage(this.ctrl.getCheminTheme() + "/plateau_joueur_"+this.equipe+".png");

		this.setLayout(null);

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(this.imgFond, 0, 0, this);

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				g2.drawImage(getToolkit().getImage(ctrl.getCheminAcces(i, j, this.equipe)), 67+ 53*j, 55 + 55 *i ,45,45, this);
			}
		}

		if (ctrl.getNbMonnaie(this.equipe) > 0)
			for (int i = 0; i < ctrl.getNbMonnaie(this.equipe); i++)
			{
				g2.drawImage(getToolkit().getImage(this.ctrl.getCheminTheme() + "/ressources/NR.png"), 67 + 53  *i , 328,45,45, this);
			}

		if (this.equipe == 0)
			g2.drawImage(getToolkit().getImage(this.ctrl.getCheminTheme() + "/pion_joueur_1.png"), 20, 410, 30, 30, this);
		else
			g2.drawImage(getToolkit().getImage(this.ctrl.getCheminTheme() + "/pion_joueur_2.png"), 20, 410, 30, 30, this);

		Font fontChiffre = new Font("Arial", Font.BOLD, 30);
		g2.setFont(fontChiffre);

		g2.drawString(this.ctrl.getJetonPossession(this.equipe)+"", 55, 435);

		List<Noeud> lstNoeuds = ctrl.getListNoeuds(this.equipe);

		int i =0;
		int j =0;

		for (Noeud n : lstNoeuds) {
			String nomNoeud = n.getNom();
			int indspace = nomNoeud.indexOf(' ');

			Image imgNoeud = getToolkit().getImage(this.ctrl.getCheminTheme() + "/transparent/" + nomNoeud.substring(0, indspace) + ".png");
			g2.drawImage(imgNoeud, 560 +50 * i, 10+65*j, this);

			UIDefaults defaults = UIManager.getLookAndFeelDefaults();
			Font font = defaults.getFont("TextField.font");
			g2.setFont(font);

			g2.setColor(Color.BLACK);
			g2.drawString("" + n.getPoint(), 560 +50 * i++ + 15 , 10+65*j + 20);

			if (i == 5)
			{
				i=0;
				j++;
			}
		}
		

		
	}
}