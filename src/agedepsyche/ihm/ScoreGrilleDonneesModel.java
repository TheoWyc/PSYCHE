package agedepsyche.ihm;
import agedepsyche.metier.*;
import agedepsyche.Controleur;


import javax.swing.table.AbstractTableModel;

public class ScoreGrilleDonneesModel extends AbstractTableModel
{
	private static final int J1 = 0;
	private static final int J2 = 1;
	private Controleur ctrl;

	private PlateauIndividuel scoreJ1;
	private PlateauIndividuel scoreJ2;

	private String[]   tabEntetes;
	private Object[][] tabDonnees;

	public ScoreGrilleDonneesModel (Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.scoreJ1 = ctrl.getPlateauIndi(J1);
		this.scoreJ2 = ctrl.getPlateauIndi(J2);


		tabDonnees = new Object[22][3];

		tabDonnees[0][0] = " ";
		tabDonnees[0][1] = " ";
		tabDonnees[0][2] = " ";

		tabDonnees[1][0] = "Points Route";
		tabDonnees[1][1] = scoreJ1.getPointsRoute();
		tabDonnees[1][2] = scoreJ2.getPointsRoute();

		tabDonnees[2][0] = " ";
		tabDonnees[2][1] = " ";
		tabDonnees[2][2] = " ";

		tabDonnees[3][0] = "Points des Mines";
		tabDonnees[3][1] = " ";
		tabDonnees[3][2] = " ";

		tabDonnees[4][0] = "Mine Jaune";
		tabDonnees[4][1] = scoreJ1.getPointsNoeud("Jaune");
		tabDonnees[4][2] = scoreJ2.getPointsNoeud("Jaune");

		tabDonnees[5][0] = "Mine Bleue";
		tabDonnees[5][1] = scoreJ1.getPointsNoeud("Bleu");
		tabDonnees[5][2] = scoreJ2.getPointsNoeud("Bleu");

		tabDonnees[6][0] = "Mine grise";
		tabDonnees[6][1] = scoreJ1.getPointsNoeud("Gris");
		tabDonnees[6][2] = scoreJ2.getPointsNoeud("Gris");

		tabDonnees[7][0] = "Mine Verte";
		tabDonnees[7][1] = scoreJ1.getPointsNoeud("Vert");
		tabDonnees[7][2] = scoreJ2.getPointsNoeud("Vert");

		tabDonnees[8][0] = "Mine Rouge";
		tabDonnees[8][1] = scoreJ1.getPointsNoeud("Rouge");
		tabDonnees[8][2] = scoreJ2.getPointsNoeud("Rouge");

		tabDonnees[9][0] = "Mine Marron";
		tabDonnees[9][1] = scoreJ1.getPointsNoeud("Marron");
		tabDonnees[9][2] = scoreJ2.getPointsNoeud("Marron");

		tabDonnees[10][0] = "S/Total";
		tabDonnees[10][1] = scoreJ1.getPointsNoeud();
		tabDonnees[10][2] = scoreJ2.getPointsNoeud();

		tabDonnees[11][0] = " ";
		tabDonnees[11][1] = " ";
		tabDonnees[11][2] = " ";

		tabDonnees[12][0] = "Plateau Individuel";
		tabDonnees[12][1] = " ";
		tabDonnees[12][2] = " ";

		tabDonnees[13][0] = "Score Pièces";
		tabDonnees[13][1] = scoreJ1.getScorePieces();
		tabDonnees[13][2] = scoreJ2.getScorePieces();

		tabDonnees[14][0] = "Score des Colonnes";
		tabDonnees[14][1] = scoreJ1.getScoreColonnes();
		tabDonnees[14][2] = scoreJ2.getScoreColonnes();

		tabDonnees[15][0] = "Score des Lignes";
		tabDonnees[15][1] = scoreJ1.getScoreLignes();
		tabDonnees[15][2] = scoreJ2.getScoreLignes();

		tabDonnees[16][0] = "S/Total";
		tabDonnees[16][1] = scoreJ1.getScorePlateauIndividuel();
		tabDonnees[16][2] = scoreJ2.getScorePlateauIndividuel();

		tabDonnees[17][0] = " ";
		tabDonnees[17][1] = " ";
		tabDonnees[17][2] = " ";

		tabDonnees[18][0] = "Jeton Possessions restants";
		tabDonnees[18][1] = scoreJ1.getJetonPossession();
		tabDonnees[18][2] = scoreJ2.getJetonPossession();

		tabDonnees[19][0] = "Bonus(10)";
		tabDonnees[19][1] = this.ctrl.getBonusPossessions(J1);
		tabDonnees[19][2] = this.ctrl.getBonusPossessions(J2);

		tabDonnees[20][0] = " ";
		tabDonnees[20][1] = " ";
		tabDonnees[20][2] = " ";

		tabDonnees[21][0] = "Total";
		tabDonnees[21][1] = this.ctrl.getFinal(0);
		tabDonnees[21][2] = this.ctrl.getFinal(1);


		this.tabEntetes = new String[]   {   " "  , "Corpotation Solaire"     , "Corporation Astral"  };

	}

	public int    getColumnCount()                 { return this.tabEntetes.length;      }
	public int    getRowCount   ()                 { return this.tabDonnees.length;      }
	public String getColumnName (int col)          { return this.tabEntetes[col];        }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col];   }
	public Class  getColumnClass(int c)            { return getValueAt(0, c).getClass(); }

	public boolean isCellEditable(int row, int col)
	{
		return col == 2 || col == 3;
	}

	public void setValueAt(Object value, int row, int col)
	{
		boolean bRet;

		if ( col == 2 )
		{
		
		}

		if ( col == 3 )
		{
			
		}
	}
}