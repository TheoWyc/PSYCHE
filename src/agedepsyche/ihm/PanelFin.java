package agedepsyche.ihm;

import javax.swing.*;
import agedepsyche.Controleur;
import java.awt.event.*;
import java.awt.BorderLayout;

public class PanelFin extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnQuitter;
	private JLabel lblWin;
	
	public PanelFin(Controleur ctrl)
	{
		JPanel panelBas, panelHaut;

		this.ctrl = ctrl;
		
		this.setLayout(new BorderLayout());

		panelBas        = new JPanel();
		panelHaut       = new JPanel();
		this.btnQuitter = new JButton("Quitter");
		this.gagnant();

		panelHaut.add(lblWin);

		panelBas.add(btnQuitter);

		this.add(panelBas, BorderLayout.SOUTH);
		this.add(panelHaut, BorderLayout.CENTER);

		this.btnQuitter.addActionListener(this);

		this.setVisible(true);
	}

	public void gagnant()
	{
		String s = "";
		boolean victoire = false;
		if(this.ctrl.getFinal(0)>this.ctrl.getFinal(1) && !victoire) { s="Le Joueur 1 à Gagné"; victoire = true;}

		if(this.ctrl.getFinal(0)<this.ctrl.getFinal(1) && !victoire) { s="Le Joueur 2 à Gagné"; victoire = true;}

	
		//if(this.ctrl.getMinesDiff(0)>this.ctrl.getMinesDiff(1) && !victoire) { s="Le Joueur 1 à Gagné"; victoire = true;}

		//if(this.ctrl.getMinesDiff(0)<this.ctrl.getMinesDiff(1) && !victoire) { s="LE Joueur 2 à Gagné"; victoire = true;}

			
		if(this.ctrl.getJetonPossession(0)>this.ctrl.getJetonPossession(1) && !victoire) { s="Le Joueur 1 à gagné"; victoire = true;}

		if(this.ctrl.getJetonPossession(0)<this.ctrl.getJetonPossession(1) && !victoire) { s="Le Joueur 2 à gagné"; victoire = true;}


		if(this.ctrl.getNbMonnaie(0)>this.ctrl.getNbMonnaie(1) && !victoire) { s="Le Joueur 1 à gagné"; victoire = true;}

		if(this.ctrl.getNbMonnaie(0)<this.ctrl.getNbMonnaie(1) && !victoire) { s="Le Joueur 2 à gagné"; victoire = true;}

		
		if ( !victoire ){ s="Égalité entre les 2 Joueurs"; }
		
		this.lblWin = new JLabel(s);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnQuitter) System.exit(0);
	}
}