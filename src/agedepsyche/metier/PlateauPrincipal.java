package agedepsyche.metier;
import java.util.*;
import java.io.FileReader;
import java.util.Scanner;

import agedepsyche.Controleur;

public class PlateauPrincipal 
{
	private Controleur ctrl;

	public List<Noeud> lstNoeuds;
	public List<Route> lstRoutes;

	public String cheminTheme;

	private int                   joueur;
	private List<Noeud>           ensNoeuds;
	
	public PlateauPrincipal(Controleur ctrl)
	{
		this.ctrl      = ctrl;
		this.joueur    = 0;
		this.lstNoeuds  = new ArrayList<Noeud>();
		this.lstRoutes = new ArrayList<Route>();

		this.init();
	}

	public void init()
	{
		FileReader fr;

		try
		{
			fr = new FileReader ( "../data/save.data" );
			Scanner sc = new Scanner ( fr );

			while ( sc.hasNextLine() )
			{
				String line = sc.nextLine();
				if (line.length() != 0)
				{
					if(line.charAt(0) == 'T')
						this.cheminTheme = line.substring(2);

					if(line.charAt(0) == 'N')
					{
						String nomNoeud = "";
						int x = 0;
						int y = 0;
						int pt = 0;

						int tabId = line.indexOf('\t');
						nomNoeud = line.substring(tabId + 1, line.indexOf('\t', tabId + 1));
						tabId = line.indexOf('\t', tabId + 1);
						x = Integer.parseInt(line.substring(tabId + 1, line.indexOf('\t', tabId + 1)));
						tabId = line.indexOf('\t', tabId + 1);
						y = Integer.parseInt(line.substring(tabId + 1, line.indexOf('\t', tabId + 1)));
						tabId = line.indexOf('\t', tabId + 1);
						pt = Integer.parseInt(line.substring(tabId + 1));

						this.lstNoeuds.add(new Noeud(nomNoeud, x, y, pt));
					}
					if (line.charAt(0) == 'R')
					{
						int nbTroncons = 0;
						int indNoeud1 = -1;
						int indNoeud2 = -1;
						//String noeud1 = null;
						//String noeud2 = null;
	
						int tabId = line.indexOf('\t');
						nbTroncons = Integer.parseInt(line.substring(tabId+1, line.indexOf('\t', tabId + 1)));
						tabId = line.indexOf('\t', tabId + 1);
						indNoeud1 = Integer.parseInt(line.substring(tabId + 1, line.indexOf('\t', tabId + 1)));
						//noeud1 = line.substring(tabId + 1, line.indexOf('\t', tabId + 1));
						tabId = line.indexOf('\t', tabId + 1);
						indNoeud2 = Integer.parseInt(line.substring(tabId + 1));
						//noeud2 = line.substring(tabId + 1);

						this.lstRoutes.add(new Route(nbTroncons, lstNoeuds.get(indNoeud1-1), lstNoeuds.get(indNoeud2-1)));
						//this.lstRoutes.add(new Route(nbTroncons, getNoeud(noeud1), getNoeud(noeud2)));
					}
				}
			}
			fr.close();
			sc.close();
		}
		catch (Exception e) { e.printStackTrace(); }
	}

	public void ajouterNoeud (String nomNoeud, int point, int coordX, int coordY)
	{
		this.lstNoeuds.add( new Noeud(nomNoeud, coordX, coordY, point) );
	}

	public String getCheminTheme() { return this.cheminTheme;}
	public void  setJoueur(int j) {this.joueur = j;}

	public Integer getIndiceNoeud ( int x, int y )
	{
		for (int cpt = 0; cpt < this.lstNoeuds.size(); cpt++ )
			if ( this.lstNoeuds.get(cpt).possede( x, y ) )
				return cpt;

		return null;
	}

	public void setCheminTheme(String chemin) { this.cheminTheme = chemin; }

	public void ajouterRoute (int nbTroncons, Noeud NoeudDep, Noeud NoeudArr )
	{
		if (NoeudDep == null || NoeudArr == null) return;
		if (NoeudDep != NoeudArr)
		{
			for ( Route r : lstRoutes)
			{
				if ( r.getNoeud2() == NoeudArr && r.getNoeud1() == NoeudDep 
				  || r.getNoeud2() == NoeudDep && r.getNoeud1() == NoeudArr)
				{
					this.lstRoutes.remove(r);
				}
			}
		
			this.lstRoutes.add ( new Route(nbTroncons, NoeudDep, NoeudArr) );
		}
	}

	public int    getNbNoeud () { return this.lstNoeuds.size(); }
	public int    getNbRoute () { return this.lstRoutes.size(); }
	public Route  getRoute(int ind) { return this.lstRoutes.get(ind);}
	public Noeud  getNoeud(int ind) { return this.lstNoeuds.get(ind);}

	public int  JoueurActuel    () { return this.joueur              ;}

	public void ChangementJoueur() 
	{
		if (this.partieFini())
			fin();
		else
			this.joueur = (this.joueur+1)%2;
	}

	public boolean ajouterNoeud(Noeud m)
	{
		if (m == null) return false;
		return this.ensNoeuds.add(m); 
	}


	public boolean AchatNoeud(Noeud dep, Noeud arr) 
	{	
		if (arr == null || dep == null )        return false;
		if (arr == dep)                         return false;
		if (!dep.isEmpty())    return false;
		
		for(Route r : dep.getRoutes())
		{
			if (!r.estPrise() && (r.getNoeud1() == arr || r.getNoeud2() == arr) )
			{
				this.ctrl.ajouterProprietaire(r,this.joueur, arr);
				this.ctrl.rafraichir();
				this.ChangementJoueur();
				this.ctrl.changementJoueur();
				return true;
			}
			
		}

		return false;
	}

	public void resetProprio()
	{
		for (Route r : lstRoutes) 
			r.enleveProprio();
	}

	public Noeud getNoeud(String nom)
	{
		for ( Noeud n : lstNoeuds)
		{
			if ( n.getNom().equals(nom))
			{
				return n;
			}
		}
		return null;
	}

	public String getNomNoeud(String couleur)
	{
		FileReader fr;

		try
		{
			fr = new FileReader (this.cheminTheme + "/vocabulaire.data" );
			Scanner sc = new Scanner ( fr );

			while ( sc.hasNextLine() )
			{
				String line = sc.nextLine();

				if (couleur.equals(line.substring(0, line.indexOf("\t"))))
				{
					sc.close();
					fr.close();
					return line.substring(line.indexOf("\t")+1);
				}
			}

			fr.close();
			sc.close();

		}
		catch (Exception e) { e.printStackTrace(); }
		
		return null;
	}

	public String getCouleurNoeud(String nom)
	{
		FileReader fr;

		try
		{
			fr = new FileReader (this.cheminTheme + "/vocabulaire.data" );
			Scanner sc = new Scanner ( fr );

			while ( sc.hasNextLine() )
			{
				String line = sc.nextLine();

				if (nom.equals(line.substring(line.indexOf("\t")+1)))
				{
					sc.close();
					fr.close();
					return line.substring(0, line.indexOf("\t"));
				}
			}
			fr.close();
			sc.close();
		}
		catch (Exception e) { e.printStackTrace(); }
		
		return null;
	}

	public boolean partieFini()
	{
		for(Noeud n : lstNoeuds)
		{
			if(!n.isEmpty()) return false;
		}
		return true;
	}

	public void fin()
	{
		ctrl.fin();
	}
}