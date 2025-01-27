package ConstructeurDeMap.ihm;

import java.awt.GridLayout;

import javax.swing.JFrame;

import ConstructeurDeMap.Controleur;

public class FrameChoisirTheme extends JFrame
{
	private PanelChoisirTheme panelChoisirImage;
	private Controleur ctrl;

	public FrameChoisirTheme(Controleur ctrl)
	{
		this.setTitle("Choisir thème");
		this.setSize(200,250);
		this.setLocation(400, 100);
		this.setLayout(new GridLayout(2,1));
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		/*---------------------------*/
		/*  Creation des Composants  */
		/*---------------------------*/
		this.ctrl = ctrl;
		this.panelChoisirImage = new PanelChoisirTheme(this.ctrl);
		

		/*--------------------------*/
		/*   Ajout des Composants   */
		/*--------------------------*/
		this.add(this.panelChoisirImage);

		/*--------------------------*/
		/* Activation des Compsants */
		/*--------------------------*/

		this.setVisible(false);
	}



}