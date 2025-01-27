package ConstructeurDeMap.ihm;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ConstructeurDeMap.Controleur;
import ConstructeurDeMap.metier.Noeud;

/** GrilleDonneesModelNoeud
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class GrilleDonneesModelNoeud extends AbstractTableModel
{
	private Controleur ctrl;

	private String[]   tabEntetes;
	private Object[][] tabDonnees;

	public GrilleDonneesModelNoeud (Controleur ctrl)
	{
		this.ctrl = ctrl;

		Noeud noeud;
		List<Noeud> lstnoeud = this.ctrl.getLstNoeud();

		tabDonnees = new Object[lstnoeud.size()][5];

		for ( int lig=0; lig<lstnoeud.size(); lig++)
		{
			noeud = lstnoeud.get(lig);

			tabDonnees[lig][0] = noeud.getIdNoeud();

			if (noeud.getTypeNoeud().equals("Noir 1"))
				tabDonnees[lig][1] = this.ctrl.getNomNoeud("Noir");
			else
				tabDonnees[lig][1] = this.ctrl.getNomNoeud(noeud.getTypeNoeud().substring(0, noeud.getTypeNoeud().indexOf(" ")));

			tabDonnees[lig][2] = noeud.getpoints();
			tabDonnees[lig][3] = noeud.getCoordX();
			tabDonnees[lig][4] = noeud.getCoordY();
		}

		this.tabEntetes = new String[]   { "Numero", "Nom" , "Points", "X", "Y" };

	}

	public boolean isCellEditable(int row, int col)
	{
		return col == 1 || col == 2 || col == 3;
	}

	public int    getColumnCount()                 { return this.tabEntetes.length;      }
	public int    getRowCount   ()                 { return this.tabDonnees.length;      }
	public String getColumnName (int col)          { return this.tabEntetes[col];        }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col];   }

	public void setValueAt(Object value, int row, int col)
	{
		boolean bRet;
		
		if ( col == 1 )
		{
			bRet = this.ctrl.majNom ( row, (String) value );
						if ( bRet )
			{
				this.tabDonnees[row][col] = value;
				this.fireTableCellUpdated(row, col);
			}
		}
		
		if (col == 3 )
		{
			bRet = this.ctrl.majCordX(row, Integer.parseInt( (String) value));
			
			if (bRet)
			{
				
				this.tabDonnees[row][col] = Integer.parseInt( (String) value);
				this.fireTableCellUpdated(row, col);
				
			}
		}
		if (col == 4 )
		{
			bRet = this.ctrl.majCordY(row, Integer.parseInt( (String) value));
			if (bRet)
			{
				this.tabDonnees[row][col] = Integer.parseInt( (String) value);
				this.fireTableCellUpdated(row, col);
			}
		}
		
		this.ctrl.majTableau();
		
	}


}