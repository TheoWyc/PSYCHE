package agedepsyche.ihm;

import javax.swing.*;
import agedepsyche.Controleur;

public class FrameScore extends JFrame {

	public FrameScore(Controleur ctrl) {
		this.setTitle("Score");
		this.setSize(553, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(new PanelScore(ctrl));

		this.setVisible(true);
	}
}