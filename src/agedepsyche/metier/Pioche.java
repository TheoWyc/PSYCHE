package agedepsyche.metier;
import java.util.*;

public class Pioche
{
	private static List<Jeton> ensJetons = new ArrayList<Jeton>(Arrays.asList(
									  new Jeton(Minerai.ALUMINIUM), new Jeton(Minerai.ALUMINIUM), new Jeton(Minerai.ALUMINIUM), new Jeton(Minerai.ALUMINIUM),
									  new Jeton(Minerai.ARGENT   ), new Jeton(Minerai.ARGENT   ), new Jeton(Minerai.ARGENT   ), new Jeton(Minerai.ARGENT   ),
									  new Jeton(Minerai.OR       ), new Jeton(Minerai.OR       ), new Jeton(Minerai.OR       ), new Jeton(Minerai.OR       ), 
									  new Jeton(Minerai.COBALT   ), new Jeton(Minerai.COBALT   ), new Jeton(Minerai.COBALT   ), new Jeton(Minerai.COBALT   ), 
									  new Jeton(Minerai.FER      ), new Jeton(Minerai.FER      ), new Jeton(Minerai.FER      ), new Jeton(Minerai.FER      ), 
									  new Jeton(Minerai.NICKEL   ), new Jeton(Minerai.FER      ), new Jeton(Minerai.FER      ), new Jeton(Minerai.FER      ), 
									  new Jeton(Minerai.PLATINE  ), new Jeton(Minerai.FER      ), new Jeton(Minerai.FER      ), new Jeton(Minerai.FER      ), 
									  new Jeton(Minerai.TITANE   ), new Jeton(Minerai.FER      ), new Jeton(Minerai.FER      ), new Jeton(Minerai.FER      ), 
									  new Jeton(Monnaie.NR       ), new Jeton(Monnaie.NR       ), new Jeton(Monnaie.NR       ), new Jeton(Monnaie.NR       ), 
									  new Jeton(Monnaie.NR       ), new Jeton(Monnaie.NR       ), new Jeton(Monnaie.NR       ), new Jeton(Monnaie.NR       )));

	public static Jeton getJeton()
	{
		int ind = (int)(Math.random()*Pioche.ensJetons.size());		
		Jeton j = Pioche.ensJetons.get(ind);
		Pioche.ensJetons.remove(ind);
		return j;
	}
}