package ConstructeurDeMap.metier;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ConstructeurDeMap.Controleur;
import java.io.FileOutputStream;

/** Metier
 * @author Groupe 2
 * @version 1 du 03/06/2024
 */

public class Metier
{
	private Controleur ctrl;

	public List<Noeud> lstNoeuds;
	public List<Route> lstRoutes;
	public Route routeTemporaire; // Prévisualsation pour crée une route

	public String cheminTheme;
	

	public Metier(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.lstNoeuds = new ArrayList<Noeud>();
		this.lstRoutes = new ArrayList<Route>();

		this.routeTemporaire = null;
		this.cheminTheme = "../image/agedepsyche";

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
						String typeNoeud = "";
						int x = 0;
						int y = 0;
						int points = 0;

						int tabId = line.indexOf('\t');
						typeNoeud = line.substring(tabId + 1, line.indexOf('\t', tabId + 1));
						tabId = line.indexOf('\t', tabId + 1);
						x = Integer.parseInt(line.substring(tabId + 1, line.indexOf('\t', tabId + 1)));
						tabId = line.indexOf('\t', tabId + 1);
						y = Integer.parseInt(line.substring(tabId + 1, line.indexOf('\t', tabId + 1)));
						tabId = line.indexOf('\t', tabId + 1);
						points = Integer.parseInt(line.substring(tabId + 1));
						this.lstNoeuds.add(new Noeud(typeNoeud, x, y, points));
					}
					if (line.charAt(0) == 'R')
					{
						int nbTroncons = 0;
						Noeud noeudDepart = null;
						Noeud noeudArrive = null;
	
						int tabId = line.indexOf('\t');
						for (int i=0; i<2; i++)
						{
							if (i == 0)
								nbTroncons = Integer.parseInt(line.substring(tabId+1, line.indexOf('\t', tabId + 1)));
							else
								for (Noeud v:lstNoeuds)
								{
									if (v.getIdNoeud() == Integer.parseInt(line.substring(tabId+1, line.indexOf('\t', tabId + 1))))
									{
										noeudDepart = v;
									}
								}
							tabId = line.indexOf('\t', tabId +1);
						}
						for (Noeud v:lstNoeuds)
						{
							if (v.getIdNoeud() == Integer.parseInt(line.substring(tabId+1)))
							{
								noeudArrive = v;
							}
						}
						this.lstRoutes.add(new Route(nbTroncons, noeudDepart, noeudArrive));
					}
				}
				


			}

			fr.close();
			sc.close();

		}
		catch (Exception e) { e.printStackTrace(); }
	}

	public void enregistrer()
	{
		// Ecriture dans le fichier
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("../data/save.data") );

			pw.print("T\t" + this.ctrl.getCheminTheme() + '\n');

			for ( int i = 0; i < this.lstNoeuds.size(); i++)
			{
				if (this.lstNoeuds.get(i).getTypeNoeud().equals("Noir 1"))
					pw.print (  "N\t" + "Noir 1" + "\t" + this.lstNoeuds.get(i).getCoordX() + "\t" + lstNoeuds.get(i).getCoordY() + "\t" + this.lstNoeuds.get(i).getpoints() + "\n");
				else
					pw.print (  "N\t" + this.lstNoeuds.get(i).getTypeNoeud().substring(0, this.lstNoeuds.get(i).getTypeNoeud().indexOf(" ")) + " " + this.lstNoeuds.get(i).getIdNoeud() + "\t" + this.lstNoeuds.get(i).getCoordX() + "\t" + lstNoeuds.get(i).getCoordY() + "\t" + this.lstNoeuds.get(i).getpoints() + "\n");
			
			}
			for ( int i = 0; i < this.lstRoutes.size(); i++)
			{
				pw.print ( "R\t" + this.lstRoutes.get(i).getNbTroncons() + "\t" + this.lstRoutes.get(i).getNoeudDepart().getIdNoeud() + "\t" + this.lstRoutes.get(i).getNoeudArrive().getIdNoeud() + "\n");
			
			}

			

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}



	public String getCheminTheme() { return this.cheminTheme; }

	public void setCheminTheme(String chemin) { this.cheminTheme = chemin; }

	public void ajouterNoeud (String typeNoeud, int points, int coordX, int coordY)
	{
		try
		{
			if (typeNoeud == "Noir")
			{
				this.lstNoeuds.add( 0, new Noeud("Noir 1", coordX, coordY, points) );
				this.lstNoeuds.get(0).setIdNoeud(1);
			}
				
			else
				this.lstNoeuds.add( new Noeud(typeNoeud + " " + this.lstNoeuds.get(lstNoeuds.size()-1).getIdNoeud(), coordX, coordY, points) );
		} catch (Exception e) {System.out.println(e);}
		
	}

	public void ajouterRoute (int nbTroncons, Noeud NoeudDep, Noeud NoeudArr )
	{
		if (NoeudDep != NoeudArr)
		{
			for ( Route r : lstRoutes)
			{
				if ( r.getNoeudArrive() == NoeudArr && r.getNoeudDepart() == NoeudDep 
				  || r.getNoeudArrive() == NoeudDep && r.getNoeudDepart() == NoeudArr)
				{
					this.lstRoutes.remove(r);
				}
			}
			
			this.lstRoutes.add ( new Route(nbTroncons, NoeudDep, NoeudArr) );
		}
	}

	public void supprimerRoute(int row)
	{	
		this.lstRoutes.remove(row);
	}	

	public void supprimerNoeud(int row)
	{
		List<Route> lRoutes = this.lstNoeuds.remove(row).getRoutes();


		for ( Route r : lRoutes )
		{
			this.lstRoutes.remove(r);
		}
	}

	public int    getNbNoeud () { return this.lstNoeuds.size(); }
	public int    getNbRoute () { return this.lstRoutes.size(); }

	public Noeud getNoeud ( int num )
	{
		return this.lstNoeuds.get(num);
	}

	public Route getRoute (int num)
	{
		return this.lstRoutes.get(num);
	}

	public List<Noeud> getLstNoeud()
	{
		return this.lstNoeuds;
	}

	public List<Route> getLstRoute()
	{
		return this.lstRoutes;
	}

	public Integer getIndiceNoeud ( int x, int y )
	{
		for (int cpt = 0; cpt < this.lstNoeuds.size(); cpt++ )
			if ( this.lstNoeuds.get(cpt).possede ( x, y ) )
				return cpt;

		return null;
	}

	public void deplacerNoeud ( Integer numNoeud, int x, int y )
	{
		if ( numNoeud != null && numNoeud >=0 && numNoeud < this.lstNoeuds.size() )
		{
			this.lstNoeuds.get(numNoeud).setCoordX(x);
			this.lstNoeuds.get(numNoeud).setCoordY(y);
		}
	}

	public boolean majNom( int ligne, String nom)
	{
		return this.lstNoeuds.get(ligne).setTypeNoeud(nom);
	}

	public boolean majCoordX( int ligne, int X)
	{
		return this.lstNoeuds.get(ligne).setCoordX(X);
	}

	public boolean majCoordY( int ligne, int Y)
	{
		return this.lstNoeuds.get(ligne).setCoordY(Y);
	}

	public String getNomNoeud(String couleur)
	{
		FileReader fr;

		try
		{
			fr = new FileReader ( this.cheminTheme + "/vocabulaire.data" );
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
			fr = new FileReader ( this.cheminTheme + "/vocabulaire.data" );
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
		catch (Exception e) {}
		
		return null;
	}

	public void ajouterRouteTemporaire(int nbTroncons, Noeud NoeudDep, Noeud NoeudArr)
	{
		this.routeTemporaire = new Route(nbTroncons, NoeudDep, NoeudArr);

		if (this.routeTemporaire.getNoeudDepart() == this.routeTemporaire.getNoeudArrive())
			this.routeTemporaire = null;

		
		for (Route r:this.lstRoutes)
		{
			try
			{
				if (this.routeTemporaire.getNoeudArrive().getTypeNoeud().equals(r.getNoeudArrive().getTypeNoeud()))
				{
					if (this.routeTemporaire.getNoeudDepart().getTypeNoeud().equals(r.getNoeudDepart().getTypeNoeud()))
					{
						this.routeTemporaire = null;
					}
				}
			} catch (Exception e) {}
		}
	}

	public Route getRouteTemp() { return this.routeTemporaire; }


	
}
