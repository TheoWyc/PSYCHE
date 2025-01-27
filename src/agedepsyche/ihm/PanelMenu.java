package agedepsyche.ihm;
import agedepsyche.Controleur;
import javax.swing.*;

import java.awt.event.*;
import java.awt.GridLayout;
public class PanelMenu extends JPanel implements ActionListener
{
	private JButton btnCreer, btnJouer;
	private Controleur ctrlPsyche;
	private ConstructeurDeMap.Controleur ctrlMap;
	private FrameMenu frameMenu;

	public PanelMenu(Controleur ctrlPsyche, ConstructeurDeMap.Controleur ctrlMap, FrameMenu fm)
	{
		JPanel panelH, panelB;
		this.ctrlPsyche = ctrlPsyche;
		this.ctrlMap = ctrlMap;
		this.frameMenu = fm;

		this.setLayout(new GridLayout(1,2));

		panelH = new JPanel();
		panelB = new JPanel();

		this.btnCreer  = new JButton("Créer la Map");
		this.btnJouer  = new JButton("Jouer"       );

		panelH.add(this.btnCreer );
		panelB.add(this.btnJouer );

		this.add(panelH);
		this.add(panelB);

		this.btnCreer .addActionListener(this);
		this.btnJouer .addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnCreer)
		{
			this.frameMenu.setVisible(false);
			this.ctrlMap.initMap();
		}

		if(e.getSource() == this.btnJouer)
		{
			this.frameMenu.setVisible(false);
			this.ctrlPsyche.initJeu();
		}
	}
}