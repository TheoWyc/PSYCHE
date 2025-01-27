package agedepsyche.metier;
import java.awt.Color;

/**
 * Cette énumération représente les différentes couleurs disponibles.
 * Chaque couleur est définie par ses composantes rouge, vert et bleu.
 *
 * @author Mohammed-Bilel Chriki
 * @author Marilou Ziri
 * @author Killian Demeillers
 * @version 1.0
 */
public enum Couleur
{
	BLANC (  255, 255, 255),
	JAUNE (  255, 255, 0  ),
	ROUGE (  255, 0, 0    ),
	ORANGE(  255, 165, 0  ),
	VIOLET(  255, 0  , 255),
	BRUN  (  165, 42 , 42 ),
	VERT  (  0  , 255, 0  ),
	NOIR  (  0  , 0  , 0  ),
	BLEU  (  0  , 0  , 255),
	ROSE  (  150, 192, 203),
	GRIS  (  155, 155, 155);

	private int r;
	private int v;
	private int b;

	/**
	 * Constructeur de la couleur.
	 * @param r la composante rouge de la couleur.
	 * @param v la composante verte de la couleur.
	 * @param b la composante bleue de la couleur.
	 */
	Couleur ( int r , int v , int b )
	{
		this.r = r;
		this.v = v;
		this.b = b;
	}

	/**
	 * Renvoie la couleur sous forme d'objet Color.
	 * @return l'objet Color représentant la couleur.
	 */
	public Color getColor()
	{
		return new Color(r,v,b);
	}

	/**
	 * Renvoie le symbole de la couleur.
	 * @return le symbole de la couleur.
	 */
	public String getSymbole()
	{
		return this.toString().substring(0,1);
	}

	/**
	 * Renvoie le nombre total de couleurs disponibles.
	 * @return le nombre total de couleurs.
	 */
	public static int getNbCouleurs()
	{
		return Couleur.values().length;
	}

	/**
	 * Renvoie la couleur correspondant à l'ordinal spécifié.
	 * @param ordinal l'ordinal de la couleur.
	 * @return la couleur correspondant à l'ordinal.
	 */
	public static Couleur valueOf(int ordinal)
	{
		return Couleur.values()[ordinal];
	}
}
