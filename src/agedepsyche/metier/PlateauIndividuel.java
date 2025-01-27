package agedepsyche.metier;
import java.util.ArrayList;
import java.util.List;

public class PlateauIndividuel {

	private static final int NB_MONNAIE_MAX = 8;
	private int nbMonnaie;

	private int jetonPossession;

	private Minerai[][] minerais;
	private List<Noeud> lstNoeuds;

	private Object[][] maxParCouleur;

	public PlateauIndividuel() 
	{
		this.nbMonnaie = 0;
		this.jetonPossession = 25;
		this.minerais = new Minerai[4][8];
		this.lstNoeuds = new ArrayList<Noeud>();

		this.maxParCouleur = new Object[][] { { "Jaune", 0 }, { "Bleu", 0 }, { "Gris", 0 }, { "Vert", 0 },
				{ "Rouge", 0 }, { "Marron", 0 } };
	}

	public int    getNbMonnaie      () { return this.nbMonnaie       ;}

	public int    getJetonPossession() { return this.jetonPossession ;}

	public void resetMineraisEtNoeuds()
	{
		this.minerais = new Minerai[4][8];
		this.lstNoeuds = new ArrayList<Noeud>();
		this.jetonPossession = 25;
		this.nbMonnaie = 0;
		this.maxParCouleur = new Object[][] { { "Jaune", 0 }, { "Bleu", 0 }, { "Gris", 0 }, { "Vert", 0 },
				{ "Rouge", 0 }, { "Marron", 0 } };
	}


	public Minerai getMinerai(int lig, int col) 
	{
		if (lig < 0 || lig > 4 || col < 0 || col > 8)
			return null;
		return this.minerais[lig][col];
	}

	public int setMaxParCouleur(String s, int max)
	{
		for (Object[] objects : this.maxParCouleur)
			if (objects[0].equals(s))
			{
				if ((int) (objects[1]) < max)
					objects[1] = max;
				return (int)(objects[1]);
			}
		return 0;
	}

	public int getPointsNoeud(String couleur)
	{
		return setMaxParCouleur(couleur, 0);
	}

	public int getPointsNoeud()
	{
		int total = 0;
		for (Object[] objects : this.maxParCouleur)
			total += (int) (objects[1]);
		return total;
	}

	public int getPointsRoute() { return 25 - this.jetonPossession ;}

	public int getScorePieces()
	{
		if (this.nbMonnaie > 1)
			return this.nbMonnaie * this.nbMonnaie;
		return 0;
	}

	//public int getMinesDiff()
	//{
	//	int nb=0;
	//	for (int i = 0; i < 4; i++)
	//	{
	//		for (int j = 0; j < 8; j++)
	//		{
	//			if(this.minerais[i][j].getCouleur().equals(this.maxParCouleur[i][j])) nb++;
	//		}
	//	}	
	//	return nb;
	//}

	public int getScoreColonnes()
	{
		int totCol = 0;
		for (int i = 0; i < 8; i++)
		{
			int nbJetonSurColonne = 0;
			for (int j = 0; j < 4; j++)
				if (this.minerais[j][i] != null)
					nbJetonSurColonne++;

			totCol += switch (nbJetonSurColonne)
			{
			case 2 -> 2;
			case 3 -> 10;
			case 4 -> 20;
			default -> 0;
			};
		}
		return totCol;
	}

	public int getScoreLignes()
	{
		int totLig = 0;
		for (int i = 0; i < 4; i++)
		{
			int nbJetonLigne = 0;
			for (int j = 0; j < 8; j++)
				if (this.minerais[i][j] != null)
					nbJetonLigne++;

			totLig += switch (nbJetonLigne)
			{
			case 2 -> 2;
			case 3 -> 5;
			case 4 -> 9;
			case 5 -> 14;
			case 6 -> 20;
			case 7 -> 32;
			case 8 -> 46;
			default -> 0;
			};
		}
		return totLig;
	}

	public int getScorePlateauIndividuel()
	{
		return this.getScorePieces() + this.getScoreColonnes() + this.getScoreLignes();
	}

	public int getTotal()
	{
		return this.getPointsNoeud() + this.getPointsRoute() + this.getScorePlateauIndividuel();
	}

	public boolean retirerJetonPossession(int nb)
	{
		if (nb < 0) return false;
		if (this.jetonPossession - nb < 0) return false;

		this.jetonPossession -= nb;
		return true;
	}

	public boolean ajouterNoeud(Noeud n)
	{

		if (n == null) return false;

		this.ajouterRessource(n.getJeton());

		
		n.retirerJeton();

		lstNoeuds.add(n);

		this.setMaxParCouleur(n.getNom().substring(0, n.getNom().indexOf(' ')), n.getPoint());

		return true;
	}

	private boolean ajouterRessource(Jeton r) 
	{
		if (r == null)
			return false;


		if (r.getType() instanceof Minerai) 
		{
			Minerai e = (Minerai) r.getType();

			return ajouterMinerai(e);
			

		} else if (r.getType() instanceof Monnaie) 
		{
			Monnaie p = (Monnaie) r.getType();

			return ajouterMonnaie(p);
		}

		return false;

	}

	private boolean ajouterMinerai(Minerai m) 
	{
		if (m == null)
			return false;

		for (int i = 0; i < 4; i++) 
		{
			for (int j = 0; j < 8; j++) 
			{
				if (i == 0) 
				{
					if (this.minerais[i][j] == m) 
					{
						break;
					}
					if (this.minerais[i][j] == null) 
					{
						this.minerais[i][j] = m;
						trier();
						return true;
					}
				} 
				else 
				{
					if (this.minerais[i][j] == null && this.minerais[i - 1][j] == m) 
					{
						this.minerais[i][j] = m;
						trier();
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean ajouterMonnaie(Monnaie m) 
	{
		if (m == null)
			return false;

		if (this.nbMonnaie + m.getValeur() > PlateauIndividuel.NB_MONNAIE_MAX)
			return false;

		this.nbMonnaie += m.getValeur();
		return true;
	}

	public int[] jetonParCol() 
	{
		int[] tab = new int[8];
		for (int i = 0; i < 8; i++) {
			int nbJetonCol = 0;
			for (int j = 0; j < 4; j++)
				if (this.minerais[j][i] != null)
					nbJetonCol++;
			tab[i] = nbJetonCol;
		}
		return tab;
	}

	public void trier() 
	{
		int[] tab = jetonParCol();
		int cpt;

		while (!(estTrie(tab))) 
		{
			cpt = 0;
			while (cpt < (tab.length) - 1) 
			{
				if (tab[cpt] < tab[cpt + 1]) 
				{
					permuter(cpt, cpt + 1);
					tab = jetonParCol();
				}
				cpt++;
			}
		}
	}

	private void permuter(int num1, int num2) 
	{
		for (int i = 0; i < 4; i++) 
		{
			Minerai tmp = this.minerais[i][num1];
			this.minerais[i][num1] = this.minerais[i][num2];
			this.minerais[i][num2] = tmp;
		}
	}

	public static boolean estTrie(int[] tab) 
	{
		int cpt = 0;
		boolean bOk = true;

		while (cpt < (tab.length) - 1) 
		{
			if (tab[cpt] < tab[cpt + 1]) 
				bOk = false;
			cpt++;
		}

		return bOk;
	}

	public List<Noeud> getListNoeuds()
	{
		return new ArrayList<Noeud>(this.lstNoeuds);
	}

	public String toString() 
	{
		String res = "\n+-----+-----+-----+-----+-----+\n";
		for (int i = 2; i >= 0; i--) 
		{
			res += "|";
			for (int j = 0; j < 5; j++) 
				if (this.minerais[i][j] != null) 
					res += " " + this.minerais[i][j].getLibCourt() + " |";
				else 
					res += "     |";
			
			res += "\n+-----+-----+-----+-----+-----+\n";
		}

		res += "\n " + this.nbMonnaie + " pièces\n";
		return res;
	}

}
