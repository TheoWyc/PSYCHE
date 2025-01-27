package ConstructeurDeMap.metier;
import java.util.ArrayList;
import java.util.List;

/** Classe Noeud
 * @author Groupe 2
 * @version 1 du 03/06/2024
*/

public class Noeud
{
	public static final int TAILLE_X = 30;
	public static final int TAILLE_Y = 50;
	public static final int TAILLE_CERCLE = 25;


	/** Correspond au nombre d'instances de la classe Noeud */
	private static int nbNoeud = 0;

	/** Correspond à l'identifiant de la Noeud */
	private int    idNoeud;

	/** Correspond au type du Noeud */
	private String typeNoeud;

	/** Correspond au numéro du noeud */
	private int  points;

	/** Correspond à la coordonnée X du Noeud */
	private int    coordX;

	/** Correspond à la coordonnée Y du Noeud */
	private int    coordY;


	/** Correspond à la liste des routes appartenants au Noeud */
	private List<Route> routes;


	/** Constructeur de la classe Noeud
	 * @param nomNoeud nom du Noeud
	 * @param coordX Coordonnée X du Noeud
	 * @param coordY Coordonnée Y du Noeud
	*/

	public Noeud(String typeNoeud, int coordX, int coordY, int points)
	{
		this.idNoeud   = ++Noeud.nbNoeud;
		this.typeNoeud = typeNoeud;
		this.points   = points;
		this.coordX    = coordX;
		this.coordY    = coordY;

		if( coordX > 950) this.coordX = 950;
		if( coordX < 0   ) this.coordX = 0   ;

		if( coordY > 900 ) this.coordY = 900 ;
		if( coordY < 0   )  this.coordY = 0   ;

		this.routes = new ArrayList<Route>();
	}

	// Getters

	/** Getter de idNoeud
	 * @return L'ID du Noeud
	*/
	public int    getIdNoeud () { return this.idNoeud;  }

	/** Getter de typeNoeud
	 * @return Le type du Noeud
	*/
	public String getTypeNoeud() { return this.typeNoeud; }

	/** Getter de points
	* @return Le numéro du Noeud par rapport au type
	*/
	public int getpoints() { return this.points; }

	/** Getter de coordX
	 * @return La coordonnée X du Noeud
	*/
	public int    getCoordX  () { return this.coordX;   }

	/** Getter de coordY
	 * @return La coordonnée Y du Noeud
	*/
	public int    getCoordY  () { return this.coordY;   }


	/** Getter de routes
	 * @return La liste des routes appartenants au Noeud
	*/
	public List<Route> getRoutes() { return this.routes; }

	// Setters

	/** Setter de idNoeud
	 * @param idNoeud ID de la Noeud
	*/
	public void setIdNoeud (int idNoeud)     { this.idNoeud = idNoeud;   }

	/** Setter de typeNoeud
	 * @param typeNoeud type du Noeud
	*/
	public boolean setTypeNoeud(String typeNoeud) { this.typeNoeud = typeNoeud; return true; }

	/** Setter de points
	 * @param numNoeud numéro du noeud par rapport au type
	*/

	/** Setter de coordX
	 * @param coordX Coordonnée X de la Noeud
	*/
	public boolean setCoordX  (int coordX)      {
													if( coordX > 0 && coordX < 950 ){ this.coordX = coordX; return true; } 
													else{ return false; }
												}

	/** Setter de coordY
	 * @param coordX Coordonnée Y de la Noeud
	*/
	public boolean setCoordY  (int coordY)      { 
													if( coordY > 0 && coordY < 900 ){ this.coordY = coordY; return true; } 
													else{ return false;  } 
												}
												  
	/** Setter de routes
	 * @param routes Ajoute une routes appartenants à la Noeud
	*/
	public void ajouterRoute(Route r) { this.routes.add(r); }


	// Autres méthodes
	public String toString()
	{
		String sRet;
		String sepH;


		sepH = "+";
		for (int cpt = 0 ; cpt < this.typeNoeud.length() + 18 ; cpt++)
			sepH += "-";
		sepH += "+\n";

		sRet = sepH;

		sRet += "| " + this.typeNoeud + " [" + String.format("%2d", this.idNoeud) + " -> " +
		                                      String.format("%3d", this.coordX ) + ":" +
		                                      String.format("%3d", this.coordY ) + "] |\n";

		sRet += sepH + "\n";

		return sRet;
	}

	public boolean possede(int x, int y)
	{
		return x >= this.coordX && x <= this.coordX + Noeud.TAILLE_X &&
		       y >= this.coordY && y <= this.coordY + Noeud.TAILLE_Y;
	}

}