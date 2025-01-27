package ConstructeurDeMap.ihm;


import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ConstructeurDeMap.Controleur;

import javax.swing.JMenuBar;

import java.awt.event.*;

public class BarreMenu extends JMenuBar implements ActionListener
{
	private Controleur ctrl;

	private JMenuItem  menuiFichierEnregistrer;
	private JMenuItem  menuiFichierQuitter;

	private JMenuItem  menuiEditionNoeud;
	private JMenuItem  menuiEditionRoute;
	private JMenuItem  menuiEditionChoisirTheme;

	public BarreMenu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		// Création des composants
		JMenu    menuFichier = new JMenu("Fichier");
		JMenu    menuEdition = new JMenu("Edition");

		this.menuiFichierEnregistrer = new JMenuItem("Enregistrer");
		this.menuiFichierQuitter     = new JMenuItem("Quitter");

		this.menuiEditionNoeud        = new JMenuItem("Editer les Noeuds");
		this.menuiEditionRoute        = new JMenuItem("Editer les routes");
		this.menuiEditionChoisirTheme = new JMenuItem("Editer le thème");

		menuFichier.setMnemonic('F');
		menuEdition.setMnemonic('E');

		this.menuiFichierEnregistrer.setMnemonic('s');
		this.menuiFichierQuitter    .setMnemonic('Q');

		this.menuiEditionNoeud.setMnemonic('v');
		this.menuiEditionRoute.setMnemonic('r');
		
		
		// Positionnement des composants
		menuFichier.add(this.menuiFichierEnregistrer);
		menuFichier.add(this.menuiFichierQuitter);

		menuEdition.add(this.menuiEditionNoeud);
		menuEdition.add(this.menuiEditionRoute);
		menuEdition.add(this.menuiEditionChoisirTheme);

		this.add(menuFichier);
		this.add(menuEdition);


		// Activation des composants
		this.menuiFichierEnregistrer.addActionListener(this);
		this.menuiFichierQuitter    .addActionListener(this);

		this.menuiEditionNoeud       .addActionListener(this);
		this.menuiEditionRoute       .addActionListener(this);
		this.menuiEditionChoisirTheme.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.menuiFichierEnregistrer)
		{
			this.ctrl.enregistrer();
		}

		if (e.getSource() == this.menuiFichierQuitter)
		{
			System.exit(0);
		}

		if (e.getSource() == this.menuiEditionNoeud)
		{
			this.ctrl.setVisibleIHMNoeud();
		}

		if (e.getSource() == this.menuiEditionRoute)
		{
			this.ctrl.setVisibleIHMRoute();
		}

		if (e.getSource() == this.menuiEditionChoisirTheme)
		{
			this.ctrl.setVisibleIHMimage();
		}
	}
}