package ConstructeurDeMap.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ConstructeurDeMap.Controleur;

public class PanelChoisirTheme extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton btnSelectionnerDossier;
	private JButton btnRetirerTheme;

	private String repertoireSelectionner;

	public PanelChoisirTheme(Controleur ctrl)
	{
		this.ctrl = ctrl;

		/* ----------------------------------  */
		/*       CRÉATION DES COMPOSANTS       */
		/* ----------------------------------  */
		this.btnSelectionnerDossier = new JButton("Selectionner un dossier");
		this.btnRetirerTheme = new JButton("Retirer le theme");


		/* ----------------------------------  */
		/*    POSITIONNEMENT DES COMPOSANTS    */
		/* ----------------------------------  */
		this.add(new JLabel("Dossier du theme : "));
		this.add(this.btnSelectionnerDossier);
		this.add(new JLabel("Retirer le theme : "));
		this.add(this.btnRetirerTheme);

		/* ----------------------------------  */
		/*      ACTIVATION DES COMPOSANTS      */
		/* ----------------------------------  */
		this.btnSelectionnerDossier.addActionListener(this);
		this.btnRetirerTheme.addActionListener(this);

		
	}

	public void selectionnerDossier()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choisir un dossier");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
	
		int valeurDeRetour = fileChooser.showOpenDialog(null);
		if (valeurDeRetour == JFileChooser.APPROVE_OPTION)
		{
			this.repertoireSelectionner = fileChooser.getSelectedFile().getPath();
		}

		if (this.repertoireSelectionner == null) this.repertoireSelectionner = "../images/Mine";
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnSelectionnerDossier)
		{
			this.selectionnerDossier();
			this.ctrl.imageSelectionner(this.repertoireSelectionner);
			this.ctrl.setCheminTheme(this.repertoireSelectionner);
			this.ctrl.majTableau();
			this.ctrl.majListeType();
		}
		if (e.getSource() == this.btnRetirerTheme)
		{
			this.repertoireSelectionner= "../images/Mine";
			this.ctrl.setCheminTheme("../images/Mine");
			this.ctrl.imageSelectionner("../images/Mine");
			this.ctrl.majTableau();
			this.ctrl.majListeType();
		}
	}
}