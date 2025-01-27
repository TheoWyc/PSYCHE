package agedepsyche.ihm;

import agedepsyche.Controleur;
import javax.swing.*;

import java.awt.BorderLayout;

public class PanelScore extends JPanel
{
	private JPanel panelHaut;

	private JPanel panelBas;

	private JTable tblGrilleDonnees;

	private Controleur ctrl;

	public PanelScore(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setVisible(true);
		this.setLayout(new BorderLayout());

		// Creation des composants

		this.panelHaut = new JPanel();
		this.panelBas = new JPanel(new BorderLayout());
		this.panelHaut.setBackground(java.awt.Color.LIGHT_GRAY);

		JScrollPane spGrilleDonnees;

		// Creation des composants
		this.tblGrilleDonnees = new JTable(new ScoreGrilleDonneesModel(ctrl));
		this.tblGrilleDonnees.setFillsViewportHeight(true);

		spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);

		// Positionnement des composants
		this.panelHaut.add(new JLabel("Fiche de Score"));
		this.panelBas.add(spGrilleDonnees, BorderLayout.CENTER);

		this.add(this.panelHaut, BorderLayout.NORTH);
		this.add(this.panelBas, BorderLayout.CENTER);
	}
}