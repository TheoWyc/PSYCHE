package agedepsyche.ihm;

import agedepsyche.Controleur;

import javax.swing.*;

import java.awt.BorderLayout;

import agedepsyche.metier.Noeud;

public class FramePlateauPrincipal extends JFrame
{
	private Controleur ctrl;
	private PanelPlateauPrincipal panelPlateauPrincipal;

	public FramePlateauPrincipal(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.setTitle   ("Plateau Principal");

		
		this.setSize    ( Noeud.MAX_X + 100, Noeud.MAX_Y + 150);
		this.setLocation( 50, 50 );

		this.panelPlateauPrincipal = new PanelPlateauPrincipal(this.ctrl);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.add(this.panelPlateauPrincipal, BorderLayout.CENTER);
	}

	public void rafraichir() { this.panelPlateauPrincipal.repaint();}

	public void fin() {this.panelPlateauPrincipal.fin();}

}