package ConstructeurDeMap.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ConstructeurDeMap.Controleur;
import ConstructeurDeMap.metier.Noeud;


/** PanelNoeud
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class PanelNoeud extends JPanel implements ActionListener
{
	private JButton btnAjouter;

	private JPanel panelGridAjout1;
	private JPanel panelGridAjout2;

	private JLabel lblPoints;

	private JComboBox<String>  ddlstType;
	private JTextField xTextField;
	private JTextField yTextField;
	private JTextField txtPoints;

	private Controleur ctrl;


	public PanelNoeud( Controleur ctrl)
	{
		this.setLayout( new BorderLayout() );
		this.ctrl = ctrl;

		/*---------------------------*/
		/*  Creation des Composants  */
		/*---------------------------*/

		this.btnAjouter = new JButton("Ajouter");

		this.lblPoints = new JLabel("Points :", JLabel.RIGHT);
		this.panelGridAjout1 = new JPanel(new GridLayout(5, 1));
		this.panelGridAjout2 = new JPanel(new GridLayout(5, 1));

		this.xTextField = new JTextField();
		this.yTextField = new JTextField();
		this.ddlstType = new JComboBox<String>();


		this.majListeType();

		this.txtPoints = new JTextField();

		this.ctrl = ctrl;
		/*--------------------------*/
		/*   Ajout des Composants   */
		/*--------------------------*/

		this.panelGridAjout1.add(new JLabel("Nom :", JLabel.RIGHT));
		this.panelGridAjout1.add(this.lblPoints);
		this.panelGridAjout1.add(new JLabel("X :", JLabel.RIGHT));
		this.panelGridAjout1.add(new JLabel("Y :", JLabel.RIGHT));
		this.panelGridAjout1.add(new JLabel());

		this.panelGridAjout2.add(this.ddlstType);
		this.panelGridAjout2.add(this.txtPoints);
		this.panelGridAjout2.add(this.xTextField);
		this.panelGridAjout2.add(this.yTextField);
		this.panelGridAjout2.add(this.btnAjouter);
		

		this.add(this.panelGridAjout1, BorderLayout.WEST);
		this.add(this.panelGridAjout2);

		/*---------------------------*/
		/* Activation des Composants */
		/*---------------------------*/

		this.btnAjouter.addActionListener(this);
		this.ddlstType.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnAjouter)
		{
			try
			{
				if (this.ctrl.getCouleurNoeud((String)this.ddlstType.getSelectedItem()).equals("Noir"))
				{
					this.ctrl.ajouterNoeud("Noir", 0, Integer.parseInt(this.xTextField.getText()),
					Integer.parseInt(this.yTextField.getText()));
				}
				else
					this.ctrl.ajouterNoeud(this.ctrl.getCouleurNoeud((String)this.ddlstType.getSelectedItem()), Integer.parseInt(this.txtPoints.getText()), Integer.parseInt(this.xTextField.getText()),
					Integer.parseInt(this.yTextField.getText()));
			} catch(Exception err) {};
		}
		if (e.getSource() == this.ddlstType)
		{
			try
			{
				if (this.ctrl.getCouleurNoeud((String)this.ddlstType.getSelectedItem()).equals("Noir"))
				{
					this.txtPoints.setEnabled(false);
					this.lblPoints.setEnabled(false);
				}
				else
				{
					this.txtPoints.setEnabled(true);
					this.lblPoints.setEnabled(true);
				}
					
			}
			catch (Exception err)
			{
				this.txtPoints.setEnabled(true);
			}

		}
	}

	public void majListeType()
	{
		this.ddlstType.removeAllItems();

		boolean nouvelleRome = false;
		for (Noeud n:this.ctrl.getLstNoeud())
		{
			if (n.getTypeNoeud().equals("Noir 1")) nouvelleRome = true;
		}
		
		String[] tabCouleur = {"Rouge", "Vert", "Bleu", "Gris", "Jaune", "Marron"};
		if (! nouvelleRome)
		{
			String[] newTabCouleur = new String[tabCouleur.length + 1];
			System.arraycopy(tabCouleur, 0, newTabCouleur, 0, tabCouleur.length);
			newTabCouleur[tabCouleur.length] = "Noir";
			tabCouleur = newTabCouleur;
		}
			
		for (String c : tabCouleur)
		{
			if (this.ctrl.getNomNoeud(c) != null)
				this.ddlstType.addItem(this.ctrl.getNomNoeud(c));
			else
				this.ddlstType.addItem(c);
		}
	}
}
