package ConstructeurDeMap.ihm;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import ConstructeurDeMap.Controleur;

import javax.swing.table.TableColumn;

import java.awt.BorderLayout;

/** PanelTableVille
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class PanelTableNoeud extends JPanel
{
	private JTable tblNoeud;

	private Controleur ctrl;

	private GrilleDonneesModelNoeud grd;

	public PanelTableNoeud(Controleur ctrl)
	{
		JScrollPane      spGrilleDonnees;
		TableColumnModel colonneModel;
		TableColumn      colonne;

		this.setLayout(new BorderLayout());

		this.ctrl = ctrl;

		/* ---------------------------------- */
		/*      CRÉATION DES COMPOSANTS       */
		/* ---------------------------------- */
		this.grd = new GrilleDonneesModelNoeud(ctrl);

		this.tblNoeud = new JTable(this.grd);
		this.tblNoeud.setFillsViewportHeight(true);

		colonneModel = this.tblNoeud.getColumnModel();
		colonne = colonneModel.getColumn(0);
		colonne.setPreferredWidth(40);
		colonne = colonneModel.getColumn(1);
		colonne.setPreferredWidth(100);
		colonne = colonneModel.getColumn(2);
		colonne.setPreferredWidth(40);
		colonne = colonneModel.getColumn(3);
		colonne.setPreferredWidth(40);
		colonne = colonneModel.getColumn(4);
		colonne.setPreferredWidth(40);
		

		

		spGrilleDonnees = new JScrollPane(this.tblNoeud);

		/* ---------------------------------- */
		/*    POSITIONNEMENT DES COMPOSANTS   */
		/* ---------------------------------- */
		this.add(spGrilleDonnees);
	}

	public void supprimer()
{
	this.ctrl.supprimerNoeud(this.tblNoeud.getSelectedRow());
}

	public void majTableau()
	{
		this.grd = new GrilleDonneesModelNoeud(ctrl);
		this.tblNoeud.setModel(this.grd);
	}
}
