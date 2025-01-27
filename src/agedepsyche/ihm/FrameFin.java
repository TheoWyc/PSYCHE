package agedepsyche.ihm;
import javax.swing.*;
import agedepsyche.Controleur;

public class FrameFin extends JFrame
{
	private PanelFin   panelFin;

	public FrameFin(Controleur ctrl)
	{
		this.setTitle("Fin De Jeu");
		this.setLocation(300, 150);

		this.setSize(200, 150);

		this.panelFin = new PanelFin(ctrl);
		this.add(panelFin);

		this.setVisible(true);
	}
}