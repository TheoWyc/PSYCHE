package agedepsyche.metier;

public enum Minerai implements IRessource
{
	ALUMINIUM (Couleur.BLANC , 1, "AL"),
	ARGENT    (Couleur.GRIS  , 2, "AG"),
	OR        (Couleur.ORANGE, 3, "AU"),
	COBALT    (Couleur.ROUGE , 4, "CO"),
	FER       (Couleur.BRUN  , 5, "FE"),
	NICKEL    (Couleur.BLEU  , 6, "NI"),
	PLATINE   (Couleur.ROSE  , 7, "PT"),
	TITANE    (Couleur.VERT  , 8, "TI");

	private Couleur couleur;
	private String libCourt;

	private Minerai(Couleur coul, int id, String libCourt) 
	{ 
		this.couleur=coul;
		this.libCourt=libCourt;
	}
	
	public String getLibCourt() { return this.libCourt;}

	public Couleur getCouleur() { return this.couleur; }

	public String toString   () { return this.name();  }

}
