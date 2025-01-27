package ConstructeurDeMap.ihm;

import javax.swing.*;

import ConstructeurDeMap.Controleur;
import ConstructeurDeMap.metier.Noeud;
import ConstructeurDeMap.metier.Route;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.*;

/** PanelReseau
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class PanelReseau extends JPanel
{
	private Graphics2D g2;

	private Controleur ctrl;

	private Image imgFond;

	public PanelReseau ( Controleur ctrl )
	{
		GereSouris gereSouris;


		this.ctrl = ctrl;
		this.changerImage(this.ctrl.getCheminTheme());
		gereSouris = new GereSouris();
		
		this.addMouseListener( gereSouris );
		this.addMouseMotionListener(gereSouris);


		
	}

	public void paintComponent(Graphics g)
	{
		Noeud noeud;
		Route route;

		int x1;
		int y1;
		int x2;
		int y2;


		super.paintComponent(g);

		g2 = (Graphics2D) g;

		// Dessiner l'image de fond
		if ( this.imgFond != null )
		{
			g2.drawImage ( this.imgFond, 0 , 0 , 1000, 1000, this );
		}

		// Dessiner l'ensemble des routes
		for (int cpt = 0 ; cpt < this.ctrl.getLstRoute().size() ; cpt++)
		{
			g2.setColor(Color.WHITE);
			route = this.ctrl.getRoute(cpt);

			x1 = (route.getNoeudDepart().getCoordX()) + Noeud.TAILLE_X / 2;
			y1 = (route.getNoeudDepart().getCoordY()) + Noeud.TAILLE_Y / 2;
			x2 = (route.getNoeudArrive().getCoordX()) + Noeud.TAILLE_X / 2;
			y2 = (route.getNoeudArrive().getCoordY()) + Noeud.TAILLE_Y / 2;

			int rayon = 25;
			double angle = Math.atan2(y2 - y1, x2 - x1);

			int debutX = (int) (x1 + rayon * Math.cos(angle));
			int debutY = (int) (y1 + rayon * Math.sin(angle));
			int finX   = (int) (x2 - rayon * Math.cos(angle));
			int finY   = (int) (y2 - rayon * Math.sin(angle));


			g2.setStroke(new BasicStroke(2));
			g2.drawLine(debutX, debutY, finX, finY);
			//g2.setColor( new Color( 60, 60, 60 ) );
			g2.fillOval(debutX - 5, debutY - 5, 10, 10);
			g2.fillOval(finX - 5, finY - 5, 10, 10);

			if (route.getNbTroncons() == 2)
			{
				int x3 = (debutX + finX) / 2;
				int y3 = (debutY + finY) / 2;
				g2.fillOval(x3 - 5, y3 - 5, 10, 10);
			}
		}

		// Dessin de la route temporaire
		if (this.ctrl.getRouteTemp() != null)
		{
			g2.setColor(Color.BLUE);
			route = this.ctrl.getRouteTemp();
	
			x1 = (route.getNoeudDepart().getCoordX()) + Noeud.TAILLE_X / 2;
			y1 = (route.getNoeudDepart().getCoordY()) + Noeud.TAILLE_Y / 2;
			x2 = (route.getNoeudArrive().getCoordX()) + Noeud.TAILLE_X / 2;
			y2 = (route.getNoeudArrive().getCoordY()) + Noeud.TAILLE_Y / 2;
	
			int rayon = 25;
			double angle = Math.atan2(y2 - y1, x2 - x1);
	
			int debutX = (int) (x1 + rayon * Math.cos(angle));
			int debutY = (int) (y1 + rayon * Math.sin(angle));
			int finX   = (int) (x2 - rayon * Math.cos(angle));
			int finY   = (int) (y2 - rayon * Math.sin(angle));
	
	
			g2.drawLine(debutX, debutY, finX, finY);
			g2.fillOval(debutX - 5, debutY - 5, 10, 10);
			g2.fillOval(finX - 5, finY - 5, 10, 10);
	
			if (route.getNbTroncons() == 2)
			{
				int x3 = (debutX + finX) / 2;
				int y3 = (debutY + finY) / 2;
				g2.fillOval(x3 - 5, y3 - 5, 10, 10);
			}
		}


		// Dessiner l'ensemble des Noeuds
		for (int cpt = 0 ; cpt < this.ctrl.getNbNoeud() ; cpt++)
		{
			noeud = this.ctrl.getNoeud(cpt);

			x1 = noeud.getCoordX();
			y1 = noeud.getCoordY();
			
			if (noeud.getTypeNoeud().equals("Noir 1"))
			{
				g2.drawImage ( getToolkit().getImage ( this.ctrl.getCheminTheme() + "/transparent/NR.png" ), noeud.getCoordX() , noeud.getCoordY() ,35, 40, this );
			}
				
		
			else
				g2.drawImage ( getToolkit().getImage ( this.ctrl.getCheminTheme() + "/transparent/" + noeud.getTypeNoeud().substring(0, noeud.getTypeNoeud().indexOf(" ")) + ".png" ), noeud.getCoordX() , noeud.getCoordY() ,33, 60, this );
			

			g2.setColor( Color.WHITE );

			if (noeud.getTypeNoeud().equals("Jaune"))
				g2.drawString("" + noeud.getpoints(), x1 + 12, y1 + 22);
			else
				if (! noeud.getTypeNoeud().equals("Noir 1"))
					g2.drawString("" + noeud.getpoints(), x1 + 12, y1 + 20);
		}

	}

	public void changerImage(String image)
	{
		this.imgFond = getToolkit().getImage ( image + "/Plateau_vierge.png" );
		this.repaint();
	}

	private class GereSouris extends MouseAdapter
	{
		Integer numNoeudActive = null;

		public void mousePressed (MouseEvent e)
		{
			this.numNoeudActive = PanelReseau.this.ctrl.getIndiceNoeud ( e.getX(), e.getY() );
		}

		public void mouseDragged (MouseEvent e)
		{
			if ( this.numNoeudActive != null )
			{
				PanelReseau.this.ctrl.deplacerNoeud ( this.numNoeudActive, e.getX(), e.getY());

				PanelReseau.this.repaint();
			}
		}
	}
}
