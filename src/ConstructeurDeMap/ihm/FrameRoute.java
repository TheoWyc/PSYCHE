package ConstructeurDeMap.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ConstructeurDeMap.Controleur;

/** FrameRoute
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class FrameRoute extends JFrame
{
	private Controleur ctrl;

	private PanelTableRoute panelTableRoute;
	private PanelRoute panelRoute;

	public FrameRoute(Controleur ctrl)
	{
		this.setTitle("Route");
		this.setSize(800, 200);
		this.setLocation(400,600);
		this.setLayout(new BorderLayout());

		this.ctrl = ctrl;
		
		/* ----------------------------------  */
		/*       CRÉATION DES COMPOSANTS       */
		/* ----------------------------------  */
		this.panelTableRoute = new PanelTableRoute(this.ctrl);
		this.panelRoute      = new PanelRoute     (this.ctrl);

		/* ----------------------------------  */
		/*    POSITIONNEMENT DES COMPOSANTS    */
		/* ----------------------------------  */
		this.add(this.panelTableRoute, BorderLayout.CENTER);
		this.add(this.panelRoute, BorderLayout.EAST);
		

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	
		this.setVisible(false);
	}

	public void majTableau()
	{
		this.panelTableRoute.majTableau();
		this.panelRoute.majNoeud();
	}

	public void supprimerRoute()
	{
		this.panelTableRoute.supprimerRoute();
	}
}
