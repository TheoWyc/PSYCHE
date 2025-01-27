package agedepsyche;
import java.util.Scanner;
import java.io.FileInputStream;
import agedepsyche.metier.*;
import agedepsyche.ihm.*;

import java.util.List;
import java.util.ArrayList;

public class Controleur 
{
	private PlateauIndividuel[]   tabJoueur             ;
	private PlateauPrincipal      plateauPrincipal      ;
	private FramePlateauPrincipal framePlateauPrincipal ;
	private FramePlateauIndi[]    tabFrameJoueur        ;
	private FrameScore            frameScore            ;
	private FrameFin              frameFin              ;

	public Controleur()
	{
		/*this.tabJoueur             = new PlateauIndividuel[]{ new PlateauIndividuel(), new PlateauIndividuel()}            ;
		this.plateauPrincipal      = new PlateauPrincipal(this)                                                            ;
		this.framePlateauPrincipal = new FramePlateauPrincipal(this)                                                       ;
		this.tabFrameJoueur        = new FramePlateauIndi[] { new FramePlateauIndi(this, 0), new FramePlateauIndi(this, 1)};*/
	}

	/*-------------------- */
	/*     Plateau Ind     */
	/*-------------------- */
	public void initJeu()
	{
		this.tabJoueur = new PlateauIndividuel[] { new PlateauIndividuel(), new PlateauIndividuel() };
		this.plateauPrincipal = new PlateauPrincipal(this);
		this.framePlateauPrincipal = new FramePlateauPrincipal(this);
		this.tabFrameJoueur = new FramePlateauIndi[] { new FramePlateauIndi(this, 0), new FramePlateauIndi(this, 1) };
	}

	public String            getCheminTheme            () { return this.plateauPrincipal.getCheminTheme()    ;}
	public Integer           getIndiceNoeud(int x, int y) { return this.plateauPrincipal.getIndiceNoeud(x, y);}
	public Route             getRoute           (int ind) { return this.plateauPrincipal.getRoute(ind)       ;}
	public Noeud             getNoeud           (int ind) { return this.plateauPrincipal.getNoeud(ind)       ;}
	public int               getNbRoute                () { return this.plateauPrincipal.getNbRoute()        ;}
	public int               getNbNoeud                () { return this.plateauPrincipal.getNbNoeud()        ;}
	public int               getNbMonnaie    (int joueur) { return this.tabJoueur[joueur].getNbMonnaie()     ;}
	public PlateauIndividuel getPlateauIndi  (int joueur) { return this.tabJoueur[joueur]                    ;}
	public boolean           partieFini                () { return this.plateauPrincipal.partieFini()        ;}
	public int getBonusPossessions(int i)
	{
		if (this.tabJoueur[i].getJetonPossession() >= this.tabJoueur[(i+1)%2].getJetonPossession())
			return 10;
		return 0;
	}

	public int getFinal(int joueur) 
	{
		if(joueur!=1) return this.tabJoueur[0].getTotal() + this.getBonusPossessions(0);
		return this.tabJoueur[1].getTotal() + this.getBonusPossessions(1);
	}

	//public int getMinesDiff(int joueur){return this.tabJoueur[joueur].getMinesDiff();}


	public String getCheminAcces(int lig, int col, int equipe)
	{
		lig = 3-lig;

		if (this.tabJoueur[equipe].getMinerai(lig, col) == null)
			return "../images/vide.png";
		return this.getCheminTheme() + "/ressources/" + this.tabJoueur[equipe].getMinerai(lig, col).getLibCourt() + ".png";
	}

	public void changementJoueur()
	{
		//this.tabFrameJoueur[ this.plateauPrincipal.JoueurActuel()     ].setVisible(true );
		//this.tabFrameJoueur[(this.plateauPrincipal.JoueurActuel()+1)%2].setVisible(false);
	}

	public boolean AchatNoeud( Noeud noeud1, Noeud noeud2)
	{ 
		return this.plateauPrincipal.AchatNoeud(noeud1, noeud2);
	}

	public void ajouterProprietaire(Route r, int j, Noeud n)
	{
		if (this.tabJoueur[j].retirerJetonPossession(r.getNbTroncons()))
		{
			r.ajouterProprietaire(j);
			this.tabJoueur[j].ajouterNoeud(n);
			this.tabFrameJoueur[j].repaint();
		}
	}

	public void rafraichir() { this.framePlateauPrincipal.rafraichir();}

	public Noeud getNoeud(String nom){ return this.plateauPrincipal.getNoeud(nom);}

	public String getNomNoeud(String couleur)
	{
		return this.plateauPrincipal.getNomNoeud(couleur);
	}

	public String getCouleurNoeud(String nom)
	{
		return this.plateauPrincipal.getCouleurNoeud(nom);
	}

	public boolean lireRun(String chemin, int etape)
	{
		String sEtape = "";
		this.reset();
		if (etape < 0) return false;
		try
		{
			String line = "";
			Scanner sc = new Scanner(new FileInputStream(chemin));

			while(sc.hasNextLine())
			{
				line = sc.nextLine();

				if (Integer.parseInt(line.substring(0,line.indexOf("\t"))) <= etape)
				{
					sEtape = "\n------------Etape " + etape + "-------------------\n";
					sEtape += lireUneLigne(line.substring(line.indexOf("\t")));
					sEtape += "\n--------------------------------------\n";

					if ( Integer.parseInt(line.substring(0,line.indexOf("\t"))) == etape && etape != 0)
					{
						System.out.println(sEtape);
					}
				}
				else
					break;
			}
			if (Integer.parseInt(line.substring(0,line.indexOf("\t"))) < etape)
			{
				rafraichir();
				return false;
			}
		}
		catch(Exception e){ return false;}
		rafraichir();
		return true;
	}

	public String lireUneLigne(String line)
	{
		String nomNoeud, nomN1, nomN2;
		String nomRess;
		int tabId = line.indexOf("\t");

		if (line.substring(tabId + 1, line.indexOf("\t", tabId + 1)).charAt(0) == 'A')
		{
			tabId = line.indexOf('\t', tabId + 1);
			nomNoeud = line.substring(tabId + 1, line.indexOf("\t", tabId + 1));
			tabId = line.indexOf('\t', tabId + 1);
			nomRess = line.substring(tabId + 1);
			this.getNoeud(nomNoeud).setJeton(nomRess);
		}
		else if (line.substring(tabId + 1, line.indexOf("\t", tabId + 1)).charAt(0) == 'P')
		{
			tabId = line.indexOf('\t', tabId + 1);
			nomN1 = line.substring(tabId + 1, line.indexOf("\t", tabId + 1));
			tabId = line.indexOf('\t', tabId + 1);
			nomN2 = line.substring(tabId + 1);

			return this.plateauPrincipal.JoueurActuel() + "-> " + nomN1 + " et " + nomN2 + " : " +  this.AchatNoeud(this.getNoeud(nomN1), this.getNoeud(nomN2));
		}
		return null;
	}

	public int getJetonPossession (int equipe)
	{
		if (equipe < 0 && equipe > 1) return 0;

		return this.tabJoueur[equipe].getJetonPossession();
	}

	public List<Noeud> getListNoeuds(int equipe)
	{
		if (equipe < 0 && equipe > 1) return null;

		return this.tabJoueur[equipe].getListNoeuds();
	}

	public void reset()
	{
		this.plateauPrincipal.resetProprio();
		this.tabJoueur[0].resetMineraisEtNoeuds();
		this.tabJoueur[1].resetMineraisEtNoeuds();
		this.plateauPrincipal.setJoueur(0);
	}

	public void fin()
	{
		this.frameFin = new FrameFin(this);
		this.frameScore = new FrameScore(this);
		this.framePlateauPrincipal.fin();
	}
}