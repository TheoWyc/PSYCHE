package agedepsyche.metier;
import java.util.List;
import java.util.ArrayList;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Noeud
{
	public static int TAILLE_CERCLE = 25               ;
	public static int TAILLE_X      = 30               ;
	public static int TAILLE_Y      = 50               ;
	public static int MAX_Y         = Integer.MIN_VALUE;
	public static int MAX_X         = Integer.MIN_VALUE;


	private   static int         nbNoeud=0 ;
	protected        int         numNoeud  ;
	private          int         point     ;

	private          String      nom      ;
	private          int         x, y     ;
	
	private          List<Route> lstRoute ;

	private          Jeton       jeton    ;

	public Noeud(String nom, int x, int y, int point)
	{
		this.numNoeud  = ++Noeud.nbNoeud        ;
		this.nom       = nom                    ;
		this.x         = x                      ;
		this.y         = y                      ;
		this.point     = point                  ;
		this.lstRoute  = new ArrayList<Route>() ;
		if(!nom.equals("Noir 1"))
			this.jeton     = Pioche.getJeton()      ;
		else this.jeton = null;
		if (this.x>Noeud.MAX_X) Noeud.MAX_X = this.x;
		if (this.y>Noeud.MAX_Y) Noeud.MAX_Y = this.y;
	}

	protected void   setX(int x) { this.x = x   ;}
	protected void   setY(int y) { this.y = y   ;}

	public int         getX         () { return this.x                   ;}
	public int         getY         () { return this.y                   ;}
	public String      getNom       () { return this.nom                 ;}
	public int         getNumero    () { return this.numNoeud            ;}
	public List<Route> getRoutes    () { return new ArrayList<>(lstRoute);}
	public Jeton       getJeton     () { return this.jeton               ;}
	public int         getPoint     () { return this.point               ;}
	public boolean     isEmpty      () { return this.jeton==null         ;}
	public void        setJeton     (String s)
	{
		this.jeton = switch ( s )
		{
			case ("NR")        -> new Jeton(Monnaie.NR        );
			case ("ARGENT")    -> new Jeton(Minerai.ALUMINIUM );
			case ("ALUMINIUM") -> new Jeton(Minerai.ARGENT    );
			case ("OR")        -> new Jeton(Minerai.OR        );
			case ("COBALT")    -> new Jeton(Minerai.COBALT    );
			case ("FER")       -> new Jeton(Minerai.FER       );
			case ("NICKEL")    -> new Jeton(Minerai.NICKEL    );
			case ("PLATINE")   -> new Jeton(Minerai.PLATINE   );
			case ("TITANE")    -> new Jeton(Minerai.TITANE    );
			default            -> null                         ;
		};
	}

	public Jeton retirerJeton() 
	{ 
		Jeton temp;
		temp = this.jeton;
		this.jeton = null;
		return temp;
	}

	public void ajouterRoute(Route r) 
	{ 
		if(r != null) this.lstRoute.add(r) ;
	}

	public boolean possede(int x, int y)
	{
		int cx, cy;
		double xBord, yBord;

		for (double ang = 0 ; ang < PI/2 ; ang += PI/16)
		{
			xBord = cos(ang) * Noeud.TAILLE_CERCLE / 2;
			yBord = sin(ang) * Noeud.TAILLE_CERCLE / 2;
			cx    = this.x + Noeud.TAILLE_CERCLE / 2;
			cy    = this.y + Noeud.TAILLE_CERCLE / 2;

			// Secteur en haut à droite
			if (x >= cx && x <= cx + xBord &&
			    y <= cy && y >= cy - yBord   )
				return true;
			
			// Secteur en haut à gauche
			if (x <= cx && x >= cx - xBord &&
			    y <= cy && y >= cy - yBord   )
				return true;

			// Secteur en bas à gauche
			if (x <= cx && x >= cx - xBord &&
			    y >= cy && y <= cy + yBord   )
				return true;

			// Secteur en bas à droite
			if (x >= cx && x <= cx + xBord &&
			    y >= cy && y <= cy + yBord   )
				return true;
		}

		return false;
	}

}