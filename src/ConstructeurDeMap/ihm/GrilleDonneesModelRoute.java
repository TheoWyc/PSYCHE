package ConstructeurDeMap.ihm;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ConstructeurDeMap.Controleur;
import ConstructeurDeMap.metier.Route;

/** GrilleDonneesModel
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class GrilleDonneesModelRoute extends AbstractTableModel
{
	private Controleur ctrl;

	private String[]   tabEntetes;
	private Object[][] tabDonnees;

	public GrilleDonneesModelRoute (Controleur ctrl)
	{
		this.ctrl = ctrl;

		Route route;
		List<Route> lstRoutes = this.ctrl.getLstRoute();

		tabDonnees = new Object[lstRoutes.size()][3];

		for ( int lig=0; lig<lstRoutes.size(); lig++)
		{
			route = lstRoutes.get(lig);

			if (route.getNoeudDepart().getTypeNoeud().equals("Noir 1"))
				tabDonnees[lig][0] = this.ctrl.getNomNoeud("Noir");
			else
				tabDonnees[lig][0] = this.ctrl.getNomNoeud(route.getNoeudDepart().getTypeNoeud().substring(0, route.getNoeudDepart().getTypeNoeud().indexOf(" "))) + " " + route.getNoeudDepart().getIdNoeud();
			
			if (route.getNoeudArrive().getTypeNoeud().equals("Noir 1"))
				tabDonnees[lig][1] = this.ctrl.getNomNoeud("Noir");
			else
				tabDonnees[lig][1] = this.ctrl.getNomNoeud(route.getNoeudArrive().getTypeNoeud().substring(0, route.getNoeudArrive().getTypeNoeud().indexOf(" "))) + " " + route.getNoeudArrive().getIdNoeud();
			tabDonnees[lig][2] = route.getNbTroncons ();
		}

		this.tabEntetes = new String[]   { "Noeud dep", "Noeud Arr" , "nb Troncons" };

	}

	public int    getColumnCount()                 { return this.tabEntetes.length;      }
	public int    getRowCount   ()                 { return this.tabDonnees.length;      }
	public String getColumnName (int col)          { return this.tabEntetes[col];        }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col];   }


}