package ConstructeurDeMap.metier;
/** Classe Route
 * @author Groupe 2
 * @version 1 du 03/06/2024
*/

public class Route
{
	/** Correspond au nombre de tronçons de la route */
	private int   nbTroncons;

	/** Correspond à la noeud de départ de la route */
	private Noeud noeudDepart;

	/** Correspond à la noeud d'arrivée de la route */
	private Noeud noeudArrive;

	/** Constructeur de la classe Route
	 * @param nbTroncons nombre de tronçons
	 * @param noeudDepart noeud de départ
	 * @param noeudArrive noeud d'arrivée
	 */

	public Route(int nbTroncons, Noeud noeudDepart, Noeud noeudArrive)
	{
		this.nbTroncons = nbTroncons;
		if (this.nbTroncons < 0)
			this.nbTroncons = 0;
		if (this.nbTroncons > 10)
			this.nbTroncons = 10;

		this.noeudDepart = noeudDepart;
		this.noeudDepart.ajouterRoute(this);
		
		this.noeudArrive = noeudArrive;
		this.noeudArrive.ajouterRoute(this);
	}

	// Getters

	/** Getter de nbTroncons
	 * @return Le nombre de tronçons
	*/
	public int   getNbTroncons ()                  { return nbTroncons;              }

	/** Getter de noeudDepart
	 * @return La noeud de départ
	*/
	public Noeud getNoeudDepart()                  { return noeudDepart;             }

	/** Getter de noeudArrive
	 * @return La noeud d'arrivée
	*/
	public Noeud getNoeudArrive()                  { return noeudArrive;             }

	// Setters

	/** Setter de nbTroncons
	 * @param nbTroncons Nombre de troncons
	*/
	public void  setNbTroncons (int nbTroncons)    { this.nbTroncons = nbTroncons;   }

	/** Setter de noeudDepart
	 * @param noeudDepart noeud de départ
	*/
	public void  setnoeudDepart(Noeud noeudDepart) { this.noeudDepart = noeudDepart; this.noeudDepart.ajouterRoute(this); }

	/** Setter de noeudArrive
	 * @param noeudArrive noeud d'arrivée
	*/
	public void  setnoeudArrive(Noeud noeudArrive) { this.noeudArrive = noeudArrive; this.noeudArrive.ajouterRoute(this); }


	// Autres méthodes

	public String toString()
	{
		String sRet;

		sRet = this.noeudDepart.toString() + "          |\n          | " +
		       this.nbTroncons             + "\n          |\n          V\n" + this.noeudArrive.toString();

		return sRet;
	}

}


