package agedepsyche.metier;
public class Route 
{
	private int troncons;
	private Noeud noeud1, noeud2;
	private int appartient;

	Route(int tr, Noeud d, Noeud a)
	{
		this.troncons = tr;
		this.noeud1   = d;
		this.appartient = -1;
		this.noeud2  = a;

		this.noeud1.ajouterRoute(this);
		this.noeud2.ajouterRoute(this);
	}

	public Noeud getNoeud1   () { return this.noeud1  ;}
	public Noeud getNoeud2   () { return this.noeud2 ;}
	public int  getNbTroncons() { return this.troncons;}
	public boolean  estPrise () { return this.appartient != -1 ;}
	public int    appartientA() { return this.appartient;}
	public void enleveProprio() { this.appartient = -1;}

	public void ajouterProprietaire(int prop) { this.appartient = prop ;}
}