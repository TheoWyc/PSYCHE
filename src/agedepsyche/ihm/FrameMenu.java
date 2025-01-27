package agedepsyche.ihm;
import javax.swing.*;
import agedepsyche.Controleur;

public class FrameMenu extends JFrame
{

	public FrameMenu(Controleur ctrlPsyche, ConstructeurDeMap.Controleur ctrlMap)
	{
		this.setTitle("Menu");
		this.setLocation(200,30);
		this.setSize(200, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(new PanelMenu(ctrlPsyche, ctrlMap ,this));

		this.setVisible(true);
	}
}