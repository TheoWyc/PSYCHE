package ConstructeurDeMap.ihm;

import javax.swing.JFrame;

import ConstructeurDeMap.Controleur;


/** FrameConstructeurDeMap
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class FrameConstructeurDeMap extends JFrame
{
	private PanelReseau panelReseau;


	public FrameConstructeurDeMap(Controleur ctrl)
	{
		/*-------------------------------*/
		/*   Configuration de la frame   */
		/*-------------------------------*/

		this.setTitle   ("Creation de map");
		this.setSize    (1000,1000);
		this.setLocation(300, 20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Quand on ferme la fenêtre ça arrête la console

		/*-------------------------------*/
		/*    Création des composants    */
		/*-------------------------------*/

		this.panelReseau = new PanelReseau(ctrl);


		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.add(this.panelReseau);

		this.setJMenuBar( new BarreMenu(ctrl) );

		this.setVisible(true);
	}

	public void changerImage(String image)
	{
		this.panelReseau.changerImage(image);
	}


	public void repaint()
	{
		this.panelReseau.repaint();
	}
}
