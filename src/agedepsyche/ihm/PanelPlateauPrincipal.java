package agedepsyche.ihm;

import javax.swing.*;

import agedepsyche.Controleur;
import agedepsyche.metier.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.BorderLayout;

import java.awt.event.*;

public class PanelPlateauPrincipal extends JPanel implements ActionListener, KeyListener
{
	private Graphics2D g2;

	private Controleur ctrl;

	private Image imgFond;

	protected JTextField txt1, txt2;
	private JPanel panelH, panelB;
	private JButton btnValider, btnQuitter;
	

	public PanelPlateauPrincipal ( Controleur ctrl )
	{
		GereSouris gereSouris;

		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.imgFond = getToolkit().getImage(this.ctrl.getCheminTheme() + "/Plateau_vierge.png");

		// Création des composants
		gereSouris = new GereSouris();

		this.panelH = new JPanel();
		this.panelB = new JPanel();
		
		this.panelH.setOpaque(false);
		
		this.txt1 = new JTextField(20);
		this.txt2 = new JTextField(20);
		this.btnValider = new JButton("Valider");
		this.btnQuitter = new JButton("Quitter");
		
		// Positionnement des composants
		this.panelB.add(new JLabel("Noeud 1"));
		this.panelB.add(this.txt1);
		this.panelB.add(new JLabel("Noeud 2"));
		this.panelB.add(this.txt2);
		this.panelB.add(this.btnValider);
		this.panelB.add(this.btnQuitter);

		this.add(this.panelH, BorderLayout.CENTER);
		this.add(this.panelB, BorderLayout.SOUTH);
		// Activation des composants
		
		this.btnValider.addActionListener(this);
		this.btnQuitter.addActionListener(this);
		this.txt1.addActionListener(this);
		this.txt2.addActionListener(this);
		this.btnValider.addKeyListener(this);

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
		int x3;
		int y3;
		
		int debutX;
		int debutY;
		int finX  ;
		int finY  ;

		int rayon;
		double angle;

		Image jetonPossesion;


		super.paintComponent(g);

		g2 = (Graphics2D) g;

		g2.drawImage(this.imgFond, 0, 0, this);

		// Dessiner l'image de fond
		if ( imgFond != null )
		{
			g2.drawImage ( imgFond, 0 , 0 ,this );
		}

		// Dessiner l'ensemble des routes
		for (int cpt = 0 ; cpt < this.ctrl.getNbRoute() ; cpt++)
		{
			route = this.ctrl.getRoute(cpt);

			x1 = (route.getNoeud1().getX()) + Noeud.TAILLE_X / 2;
			y1 = (route.getNoeud1().getY()) + Noeud.TAILLE_Y / 2;
			x2 = (route.getNoeud2().getX()) + Noeud.TAILLE_X / 2;
			y2 = (route.getNoeud2().getY()) + Noeud.TAILLE_Y / 2;

			rayon = 30;

			// calcule l'angle entre les 2 noeuds
			angle = Math.atan2(y2 - y1, x2 - x1);

			debutX = (int) (x1 + rayon * Math.cos(angle));
			debutY = (int) (y1 + rayon * Math.sin(angle));
			finX   = (int) (x2 - rayon * Math.cos(angle));
			finY   = (int) (y2 - rayon * Math.sin(angle));

			g2.setStroke(new BasicStroke(5));
			switch (route.appartientA())
			{
				case 0  -> { jetonPossesion = getToolkit().getImage(this.ctrl.getCheminTheme() + "/pion_joueur_1.png"); 
					g2.setColor(Color.GREEN); }
				case 1  -> { g2.setColor(Color.RED   ); jetonPossesion = getToolkit().getImage(this.ctrl.getCheminTheme() + "/pion_joueur_2.png"); }
				default -> { g2.setColor(Color.BLACK ); jetonPossesion = null;                                                      }
			}

			g2.setColor( new Color( 60, 60, 60 )  );
			
			g2.drawLine(debutX, debutY, finX, finY);

			g2.fillOval(debutX - 5, debutY - 5, 10, 10);
			g2.fillOval(finX   - 5, finY   - 5, 10, 10);


			x3 = (debutX + finX) / 2;
			y3 = (debutY + finY) / 2;

			if (route.getNbTroncons() == 2)
			{
				g2.fillOval(x3 - 5, y3 - 5, 10, 10);
				if (jetonPossesion != null)
				{
					g2.drawImage(jetonPossesion,
					             x3 < debutX?(debutX - ((debutX-x3)/2) - 10):(debutX + ((x3-debutX)/2) - 10), 
					             y3 < debutY?(debutY - ((debutY-y3)/2) - 10):(debutY + ((y3-debutY)/2) - 10),
					             20, 20, this);

					g2.drawImage(jetonPossesion,
					             x3 < debutX?(debutX - ((debutX-x3) + ((debutX-x3)/2)) - 10):(debutX + ((x3-debutX) + ((x3-debutX)/2)) - 10),
					             y3 < debutY?(debutY - ((debutY-y3) + ((debutY-y3)/2)) - 10):(debutY + ((y3-debutY) + ((y3-debutY)/2)) - 10),
					             20, 20, this);
				}
			}
			else
			{
				if (jetonPossesion != null)
					g2.drawImage(jetonPossesion, x3 - 12, y3 - 12, 25, 25, this);
			}
		}

		// Dessiner l'ensemble des Noeuds
		for (int cpt = 0; cpt < this.ctrl.getNbNoeud(); cpt++)
		{
			noeud = this.ctrl.getNoeud(cpt);
			String nomNoeud = noeud.getNom();
			int indspace = nomNoeud.indexOf(' ');
			nomNoeud = nomNoeud.substring(0, indspace);

			Image imgNoeud;

			if (nomNoeud.equals("Noir"))
				imgNoeud = getToolkit().getImage(this.ctrl.getCheminTheme() + "/transparent/NR.png");
			else
			{
				if (!noeud.isEmpty())
					imgNoeud = getToolkit()
							.getImage(this.ctrl.getCheminTheme() + "/transparent/" + nomNoeud + ".png");
				else
					imgNoeud = getToolkit()
							.getImage(this.ctrl.getCheminTheme() + "/transparent/" + nomNoeud + "_clair.png");
			}

			x1 = noeud.getX();
			y1 = noeud.getY();

			g2.drawImage(imgNoeud, x1 - 7, y1 - 7, this);

			g2.setColor(Color.BLACK);
			g2.drawString("" + noeud.getPoint(), x1 + 8, y1 + 6 + 8);

			Image imgJeton = null;
			Jeton jeton = noeud.getJeton();
			if (jeton == null)
				continue;

			if (jeton.getType() instanceof Monnaie)
			{
				Monnaie monnaie = (Monnaie) jeton.getType();
				imgJeton = getToolkit().getImage(this.ctrl.getCheminTheme() + "/ressources/" + monnaie.toString() + ".png");
			}

			if (jeton.getType() instanceof Minerai)
			{
				Minerai minerai = (Minerai) jeton.getType();
				imgJeton = getToolkit().getImage(this.ctrl.getCheminTheme() + "/ressources/" + minerai.getLibCourt() + ".png");
			}

			g2.drawImage(imgJeton, x1 - 1, y1 + 27, 25, 25, this);
		}
	}


	private class GereSouris extends MouseAdapter
	{
		Integer numNoeudActif = null;
		Noeud   noeudActif    = null;

		public void mousePressed (MouseEvent e)
		{
			this.noeudActif    = null;

			this.numNoeudActif = PanelPlateauPrincipal.this.ctrl.getIndiceNoeud ( e.getX(), e.getY() );

			String nomNoeud="";

			if (this.numNoeudActif != null)
			{
				this.noeudActif    = PanelPlateauPrincipal.this.ctrl.getNoeud(numNoeudActif);

				nomNoeud = noeudActif.getNom();
				int indspace = nomNoeud.indexOf(' ');

				nomNoeud = nomNoeud.substring(0, indspace);
			}



			int buttonDown = e.getButton();

			if(noeudActif != null)
			{

				if (buttonDown == MouseEvent.BUTTON1)
				{
					PanelPlateauPrincipal.this.txt1.setText(PanelPlateauPrincipal.this.ctrl.getNomNoeud(nomNoeud) +"_"+ noeudActif.getNumero());
				}
					

				if (buttonDown == MouseEvent.BUTTON3)
				{
					PanelPlateauPrincipal.this.txt2.setText(PanelPlateauPrincipal.this.ctrl.getNomNoeud(nomNoeud) +"_"+ noeudActif.getNumero());
				}

			}
		}
		
	}


	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnValider ||
		    e.getSource() == this.txt1       ||
			e.getSource() == this.txt2          )
		{
			Noeud noeudDep = this.ctrl.getNoeud(this.ctrl.getCouleurNoeud(this.txt1.getText().substring(0, this.txt1.getText().indexOf("_"))) + " " + 
												this.txt1.getText().substring( this.txt1.getText().indexOf("_")+1));


			Noeud noeudArr = this.ctrl.getNoeud(this.ctrl.getCouleurNoeud(this.txt2.getText().substring(0, this.txt2.getText().indexOf("_"))) + " " + 
												this.txt2.getText().substring( this.txt2.getText().indexOf("_")+1));

			if (noeudArr != null && noeudDep != null)
				this.ctrl.AchatNoeud(noeudDep, noeudArr);
		}

		if(e.getSource() == this.btnQuitter) System.exit(0);

	}

	public void keyTyped(KeyEvent e) {}

			
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER) 
		{
			Noeud noeudDep = this.ctrl.getNoeud(this.ctrl.getCouleurNoeud(this.txt1.getText().substring(0, this.txt1.getText().indexOf("_"))) + " " + 
												this.txt1.getText().substring( this.txt1.getText().indexOf("_")+1));


			Noeud noeudArr = this.ctrl.getNoeud(this.ctrl.getCouleurNoeud(this.txt2.getText().substring(0, this.txt2.getText().indexOf("_"))) + " " + 
												this.txt2.getText().substring( this.txt2.getText().indexOf("_")+1));

			if (noeudArr != null && noeudDep != null)
				this.ctrl.AchatNoeud(noeudDep, noeudArr);
		}
	}

	public void keyReleased(KeyEvent e) {}

	public void fin()
	{
		this.txt1.setEnabled(false);
		this.txt2.setEnabled(false);
		this.btnValider.setEnabled(false);

	}

}
