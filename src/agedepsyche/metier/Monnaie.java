package agedepsyche.metier;
public enum Monnaie implements IRessource
{
	NR(1);

	private int valeur;
	Monnaie(int val)
	{
		this.valeur=val;
	}

	public  int    getValeur   () { return valeur     ;}

	public  String toString    () {return this.name() ;} 
}