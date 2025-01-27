package ConstructeurDeMap.ihm;

import javax.swing.*;

import ConstructeurDeMap.Controleur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** FrameNoeud
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class FrameNoeud extends JFrame implements ActionListener
{
	private PanelTableNoeud tabNoeud;

	private PanelNoeud      panelAjout;
	private JPanel          panelFlow;

	private JButton         btnSupprimer;

	private Controleur      ctrl;


	public FrameNoeud( Controleur ctrl )
	{
		this.setTitle("Noeud");
		this.setLayout(new GridLayout(2,2));
		this.setSize(750,250);
		this.setLocation(400, 100);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		/*---------------------------*/
		/*  Creation des Composants  */
		/*---------------------------*/

		this.ctrl = ctrl;

		this.tabNoeud  = new PanelTableNoeud( this.ctrl );

		this.panelAjout  = new PanelNoeud( this.ctrl );

		this.panelFlow = new JPanel(new FlowLayout(0, 100, 50));

		this.btnSupprimer  = new JButton("Supprimer");

		this.btnSupprimer.setPreferredSize(new Dimension(180, 20));
		/*--------------------------*/
		/*   Ajout des Composants   */
		/*--------------------------*/

		this.add(this.tabNoeud);

		this.add(this.panelAjout);
		this.add(this.panelFlow);
		
		this.panelFlow.add(this.btnSupprimer);

		/*--------------------------*/
		/* Activation des Composants */
		/*--------------------------*/

		this.btnSupprimer.addActionListener(this);

		this.setVisible(false);

		
	}

	public void majTableau()
	{
		this.tabNoeud.majTableau();
	}

	public void majListeType()
	{
		this.panelAjout.majListeType();
	}

	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() == this.btnSupprimer )
		{
			this.tabNoeud.supprimer();
		}
	}
	

}