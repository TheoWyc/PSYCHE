package ConstructeurDeMap.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ConstructeurDeMap.Controleur;
import ConstructeurDeMap.metier.Noeud;

/** PanelRoute
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class PanelRoute extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JComboBox<String> ddlstNoeudDep;
	private JComboBox<String> ddlstNoeudArr;

	private JTextField txtNoeudTroncons;

	private JButton btnAjouter;
	private JButton btnSupprimer;

	private List<Noeud> lstNoeuds;

	public PanelRoute(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		
		this.ctrl = ctrl;

		/* ----------------------------------  */
		/*       CRÉATION DES COMPOSANTS       */
		/* ----------------------------------  */
		this.ddlstNoeudDep = new JComboBox<String>();
		this.ddlstNoeudArr = new JComboBox<String>();

		lstNoeuds = this.ctrl.getLstNoeud();

		for (Noeud v:lstNoeuds)
		{
			if (! v.getTypeNoeud().equals("Noir 1"))
			{
				this.ddlstNoeudDep.addItem(this.ctrl.getNomNoeud(v.getTypeNoeud().substring(0, v.getTypeNoeud().indexOf(" "))) + " " + v.getIdNoeud());
				this.ddlstNoeudArr.addItem(this.ctrl.getNomNoeud(v.getTypeNoeud().substring(0, v.getTypeNoeud().indexOf(" "))) + " " + v.getIdNoeud());
			}
			else
			{
				this.ddlstNoeudDep.addItem(this.ctrl.getNomNoeud("Noir"));
				this.ddlstNoeudArr.addItem(this.ctrl.getNomNoeud("Noir"));
			}

			
		}

		this.txtNoeudTroncons = new JTextField(20);

		this.btnAjouter = new JButton("Ajouter");
		this.btnSupprimer = new JButton("Supprimer");

		JPanel panelLabel = new JPanel();
		panelLabel.setLayout(new GridLayout(5, 1));

		JPanel panelAction = new JPanel();
		panelAction.setLayout(new GridLayout(5, 1));

		/* ----------------------------------  */
		/*    POSITIONNEMENT DES COMPOSANTS    */
		/* ----------------------------------  */
		panelLabel.add(new JLabel("Noeud dep :", JLabel.RIGHT));
		panelLabel.add(new JLabel("Noeud arr :", JLabel.RIGHT));
		panelLabel.add(new JLabel("Troncons :", JLabel.RIGHT));

		panelAction.add(this.ddlstNoeudDep);
		panelAction.add(this.ddlstNoeudArr);
		panelAction.add(this.txtNoeudTroncons);
		panelAction.add(this.btnAjouter);
		panelAction.add(this.btnSupprimer);

		this.add(panelLabel , BorderLayout.CENTER);
		this.add(panelAction, BorderLayout.EAST);

		/* ----------------------------------  */
		/*      ACTIVATION DES COMPOSANTS      */
		/* ----------------------------------  */
		this.btnAjouter.addActionListener(this);
		this.btnSupprimer.addActionListener(this);
		this.txtNoeudTroncons.addActionListener(this);
		this.ddlstNoeudDep.addActionListener(this);
		this.ddlstNoeudArr.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e)
	{
		String NoeudDepTxt;
		String NoeudArrTxt;

		Noeud  NoeudDep = null;
		Noeud  NoeudArr = null;


		if (e.getSource() == this.btnAjouter)
		{
			try
			{
				if (Integer.parseInt(this.txtNoeudTroncons.getText()) < 1)
					this.txtNoeudTroncons.setText("1");
				if (Integer.parseInt(this.txtNoeudTroncons.getText()) > 2)
					this.txtNoeudTroncons.setText("2");
				
				
				for (Noeud v:this.lstNoeuds)
				{
					try
					{
						if (this.ctrl.getCouleurNoeud(((String)this.ddlstNoeudDep.getSelectedItem())).equals("Noir"))
						{
							if ("Noir 1".equals(v.getTypeNoeud()))
							{
								NoeudDep = v;
							}
								
						}

					} catch (Exception err) {}

					try
					{
						if (this.ctrl.getCouleurNoeud(((String)this.ddlstNoeudArr.getSelectedItem())).equals("Noir"))
						{
							if ("Noir 1".equals(v.getTypeNoeud()))
								NoeudArr = v;
						}
					} catch (Exception err) {}

					if (((String)(this.ddlstNoeudDep.getSelectedItem())).equals(this.ctrl.getNomNoeud(v.getTypeNoeud().substring(0, v.getTypeNoeud().indexOf(" "))) + " " + v.getIdNoeud()))
						NoeudDep = v;
					if (((String)(this.ddlstNoeudArr.getSelectedItem())).equals(this.ctrl.getNomNoeud(v.getTypeNoeud().substring(0, v.getTypeNoeud().indexOf(" "))) + " " + v.getIdNoeud()))
						NoeudArr = v;
				}
				
				this.ctrl.ajouterRoute(Integer.parseInt(this.txtNoeudTroncons.getText()), NoeudDep, NoeudArr);

			} catch (Exception err){}
		}

		if (e.getSource() == this.btnSupprimer)
		{
			this.ctrl.supprimerRoute();
		}

		if (e.getSource() == this.ddlstNoeudDep || e.getSource() == this.ddlstNoeudArr || e.getSource() == this.txtNoeudTroncons)
		{
			try
			{
				if (Integer.parseInt(this.txtNoeudTroncons.getText()) < 1)
					this.txtNoeudTroncons.setText("1");
				if (Integer.parseInt(this.txtNoeudTroncons.getText()) > 2)
					this.txtNoeudTroncons.setText("2");
				
				
				for (Noeud v:this.lstNoeuds)
				{
					try
					{
						if (this.ctrl.getCouleurNoeud(((String)this.ddlstNoeudDep.getSelectedItem())).equals("Noir"))
						{
							if ("Noir 1".equals(v.getTypeNoeud()))
							{
								NoeudDep = v;
							}
								
						}

					} catch (Exception err) {}

					try
					{
						if (this.ctrl.getCouleurNoeud(((String)this.ddlstNoeudArr.getSelectedItem())).equals("Noir"))
						{
							if ("Noir 1".equals(v.getTypeNoeud()))
								NoeudArr = v;
						}
					} catch (Exception err) {}

					if (((String)(this.ddlstNoeudDep.getSelectedItem())).equals(this.ctrl.getNomNoeud(v.getTypeNoeud().substring(0, v.getTypeNoeud().indexOf(" "))) + " " + v.getIdNoeud()))
						NoeudDep = v;
					if (((String)(this.ddlstNoeudArr.getSelectedItem())).equals(this.ctrl.getNomNoeud(v.getTypeNoeud().substring(0, v.getTypeNoeud().indexOf(" "))) + " " + v.getIdNoeud()))
						NoeudArr = v;
				}
				
				this.ctrl.ajouterRouteTemporaire(Integer.parseInt(this.txtNoeudTroncons.getText()), NoeudDep, NoeudArr);

			} catch (Exception err){}
		}
	}

	public void majNoeud()
	{
		this.ddlstNoeudDep.removeAllItems();
		this.ddlstNoeudArr.removeAllItems();

		lstNoeuds = this.ctrl.getLstNoeud();

		for (Noeud v:lstNoeuds)
		{
			if (! v.getTypeNoeud().equals("Noir 1"))
			{
				this.ddlstNoeudDep.addItem(this.ctrl.getNomNoeud(v.getTypeNoeud().substring(0, v.getTypeNoeud().indexOf(" "))) + " " + v.getIdNoeud());
				this.ddlstNoeudArr.addItem(this.ctrl.getNomNoeud(v.getTypeNoeud().substring(0, v.getTypeNoeud().indexOf(" "))) + " " + v.getIdNoeud());
			}
			else
			{
				this.ddlstNoeudDep.addItem(this.ctrl.getNomNoeud("Noir"));
				this.ddlstNoeudArr.addItem(this.ctrl.getNomNoeud("Noir"));
			}
		}
	}
}
