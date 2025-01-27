import agedepsyche.Controleur;
import agedepsyche.ihm.FrameMenu;

import java.util.Scanner;

public class Psyche 
{
	private Controleur ctrlPsyche;
	private ConstructeurDeMap.Controleur ctrlMap;
	private FrameMenu frameMenu;
	public static void main(String[] arg)
	{
		new Psyche(arg);
	}

	public Psyche(String[] arg)
	{
		this.ctrlPsyche = new Controleur();
		this.ctrlMap    = new ConstructeurDeMap.Controleur();
		if (arg.length > 0)
		{
			this.ctrlPsyche.initJeu();
			lireScenar("../data/" + arg[0]);
		}
		else
			frameMenu = new FrameMenu(this.ctrlPsyche, this.ctrlMap);
	}

	public void lireScenar(String chemin)
	{
		Scanner s = new Scanner(System.in);
		int etape = 0;
		while (this.ctrlPsyche.lireRun(chemin, etape) && !this.ctrlPsyche.partieFini())
		{
			System.out.println("Que voulez-vous faire ? (étape actuelle : "+etape+")");
			System.out.println("(P) Aller à l'étape précedente");
			System.out.println("(S) Aller à l'étape suivante");
			System.out.println("(A) Aller à une étape en particulière");
			System.out.println("(Q) Quitter");
			char x = s.next().toUpperCase().charAt(0);

			switch(x)
			{
				case 'P' :
					etape--;
					break;
				case 'S' : 
					etape++;
					break;
				case 'A' : 
					System.out.println("A quel étape voulez vous allez ?");
					etape = s.nextInt();
					System.out.println(etape);
					break;
				case 'Q' :
					System.exit(0);
			}
			if (etape < 0)
			{
				etape = 0;
				System.out.println("\nPas d'étape précedente, retour à l'étape 0\n");
			}
		}
		System.out.println("\nScénario fini");
	}
}
