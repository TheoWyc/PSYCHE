package ConstructeurDeMap.ihm;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ConstructeurDeMap.Controleur;

import java.awt.BorderLayout;

/** PanelTableRoute
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class PanelTableRoute extends JPanel
{
	private JTable tblRoute;

	private Controleur ctrl;

	private GrilleDonneesModelRoute grd;

	public PanelTableRoute(Controleur ctrl)
	{
		JScrollPane spGrilleDonnees;

		this.setLayout ( new BorderLayout() );

		this.ctrl = ctrl;

		/* ----------------------------------  */
		/*       CRÉATION DES COMPOSANTS       */
		/* ----------------------------------  */
		this.grd = new GrilleDonneesModelRoute(ctrl);

		this.tblRoute = new JTable ( this.grd );
		this.tblRoute.setFillsViewportHeight(true);

		spGrilleDonnees = new JScrollPane( this.tblRoute );

		/* ----------------------------------  */
		/*    POSITIONNEMENT DES COMPOSANTS    */
		/* ----------------------------------  */
		this.add ( spGrilleDonnees );
	}

	public void majTableau()
	{
		this.grd = new GrilleDonneesModelRoute(ctrl);
		this.tblRoute.setModel(this.grd);
	}

	public void supprimerRoute()
	{
		int ligneSelec = this.tblRoute.getSelectedRow();

		if (ligneSelec != -1)
		{
			this.ctrl.supprimerRoute(ligneSelec);
		}
	}
}
