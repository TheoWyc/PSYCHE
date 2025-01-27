package ConstructeurDeMap;

import java.util.List;

import ConstructeurDeMap.ihm.FrameChoisirTheme;
import ConstructeurDeMap.ihm.FrameConstructeurDeMap;
import ConstructeurDeMap.ihm.FrameNoeud;
import ConstructeurDeMap.ihm.FrameRoute;
import ConstructeurDeMap.metier.Metier;
import ConstructeurDeMap.metier.Noeud;
import ConstructeurDeMap.metier.Route;

/** Controleur
 * @author Groupe 2
 * @version 1 du 03/06/2024
*/

public class Controleur
{
	private FrameConstructeurDeMap ihmReseau;
	private FrameNoeud             ihmNoeud;
	private FrameRoute             ihmRoute;
	private FrameChoisirTheme      ihmImage;
	private Metier                 metier;

	public void initMap()
	{
		this.metier = new Metier(this);

		this.ihmReseau = new FrameConstructeurDeMap(this);
		this.ihmNoeud = new FrameNoeud(this);
		this.ihmRoute = new FrameRoute(this);
		this.ihmImage = new FrameChoisirTheme(this);
	}

	public int getNbNoeud() { return metier.getNbNoeud(); }
	public int getNbRoute() { return metier.getNbRoute(); }

	public Noeud getNoeud (int num) { return metier.getNoeud(num); }
	public Route getRoute (int num) { return metier.getRoute(num); }

	public void ajouterNoeud ( String nomNoeud, int numType, int coordX, int coordY )
	{
		this.metier.ajouterNoeud(nomNoeud, numType, coordX, coordY);
		this.majTableau();
		this.majListeType();
	}

	public void ajouterRoute ( int nbTroncons, Noeud NoeudDep, Noeud NoeudArr)
	{
		this.metier.ajouterRoute(nbTroncons, NoeudDep, NoeudArr);
		this.majTableau();
	}

	public String getCheminTheme()
	{
		return metier.getCheminTheme();
	}

	public void setCheminTheme(String chemin)
	{
		this.metier.setCheminTheme(chemin);
	}


	public void deplacerNoeud(int numNoeudSel, int x, int y)
	{
		this.metier.deplacerNoeud(numNoeudSel, x, y);
		this.majTableau();
	}

	public Integer getIndiceNoeud(int x, int y)
	{
		if (this.metier.getIndiceNoeud(x, y) != null)
			return this.metier.getIndiceNoeud(x, y);

		return null;
	}

	public List<Noeud> getLstNoeud()
	{
		return this.metier.getLstNoeud();
	}

	public List<Route> getLstRoute()
	{
		return this.metier.getLstRoute();
	}

	public void supprimerRoute()
	{
		this.ihmRoute.supprimerRoute();
	}

	public void supprimerRoute(int row)
	{
		this.metier.supprimerRoute(row);
		this.majTableau();
	}

	public void supprimerNoeud(int row)
	{
		this.metier.supprimerNoeud(row);
		this.majTableau();
		this.majListeType();
	}

	public boolean majNom(int ligne, String nom)
	{
		return this.metier.majNom( ligne, nom);
	}
	public boolean majCordX(int ligne, int X)
	{
		return this.metier.majCoordX( ligne, X);
	}
	public boolean majCordY(int ligne, int Y)
	{
		return this.metier.majCoordY( ligne, Y);
	}

	public void majTableau()
	{
		this.ihmNoeud.majTableau();
		this.ihmRoute.majTableau();
		this.ihmReseau.repaint();
	}

	public void setVisibleIHMNoeud() { this.ihmNoeud.setVisible(true); }
	public void setVisibleIHMRoute() { this.ihmRoute.setVisible(true); }

	public void enregistrer()
	{
		this.metier.enregistrer();
	}

	public void setVisibleIHMimage()
	{
		this.ihmImage.setVisible(true);
	}

	public void imageSelectionner(String image)
	{
		this.ihmReseau.changerImage(image);
	}

	public String getNomNoeud(String couleur)
	{
		return this.metier.getNomNoeud(couleur);
	}

	public String getCouleurNoeud(String nom)
	{
		return this.metier.getCouleurNoeud(nom);
	}

	public void ajouterRouteTemporaire(int nbTroncons, Noeud NoeudDep, Noeud NoeudArr)
	{
		this.metier.ajouterRouteTemporaire(nbTroncons, NoeudDep, NoeudArr);
		this.ihmReseau.repaint();
	}

	public Route getRouteTemp() { return this.metier.getRouteTemp(); }

	public void majListeType() { this.ihmNoeud.majListeType(); }
}