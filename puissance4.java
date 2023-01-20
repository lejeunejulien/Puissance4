package puissance4;

import java.util.Scanner;

public class puissance4b {

	public static void main(String[] args) {

		// Initialisation des variables
		String tab[][]={{"-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-"},
				{"-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-"},{"-","-","-","-","-","-","-"}};
		// joueur1 = "O" , joueur2 = "X", vide = "-" 


		// MENU //
		int menu;
		do{
			System.out.println("\n1 : Jouer\n2 : Quitter le programme");
			menu=(new Scanner(System.in)).nextInt();

			switch(menu){
			case 1:
				jouer(1,tab);
				break;
			case 2:
				break;
			}
		}
		while(!(menu==2));
	}

	///////////////////////////////////////////////////////////////////////////

	public static void jouer(int num1,String [][]tab){

		int choix;
		int out=0;
		do{
			
			System.out.println("\nJoueur "+num1+", Choisissez une colonne de 0 à 6\n");
			
			choix=(new Scanner(System.in)).nextInt();

			if(choix==0 || choix==1 || choix==2 || choix==3 || choix==4 || choix==5 || choix==6){
			

				if(update_tab(choix,tab,num1)==1){
					out=1;
				}
				

				//Place à l'autre joueur
				if(num1==1){
					num1=2;
				}
				else{
					num1=1;
				}
			}
		}
		while((!(choix==0) || !(choix==1) || !(choix==2) || !(choix==3) || !(choix==4) || !(choix==5) || !(choix==6)) && (out==0));	
	}

	///////////////////////////////////////////////////////////////////////

	public static int update_tab(int col,String [][]tab,int num){
		int cpt=0;
		int result=0;
		boolean found=false;   // Présence d'un pion 
		for(int ligne=tab.length-1;ligne>=0;ligne--){
			if(tab[ligne][col].equals("O") || tab[ligne][col].equals("X")){
				cpt=ligne;
				found=true;
			}
		}

		// Placement du pion sur la colonne contenant au minimum un pion

		// Joueur 1 utilise le pion O
		// Joueur 2 utilise le pion X

		if(found==true){
			if(cpt==0) {
				System.out.println("Cette colonne est complète");
			}
			else {
				if(num==1){
					tab[cpt-1][col]="O";
				}
				else{
					tab[cpt-1][col]="X";
				}
			}

		}

		// Cas où il y a pas de pion sur la colonne
		else{
			if(num==1){
				tab[tab.length-1][col]="O";
			}
			else{
				tab[tab.length-1][col]="X";
			}
		}

		// Affichage du tableau de pions
		// Vérification de la possibilité de placement du pion 

		if(cpt!=0 || found!=true) 	
			affichage_tab(tab);
		
		if(cpt==0) {			   // Si colonne vide
			analyse(tab,tab.length-1,col,num);
		}
		else {
			cpt-=1;  			// Pour le bon placement du pion
			if(analyse(tab,cpt,col,num)==1) {
				result=1;
			}
		}
		return result;
	}

	///////////////////////////////////////////////////////////////////////

	public static int analyse(String [][]tab,int ligne,int col,int num){
		
		int result=0;
		String code_joueur;

		if(num==1) {
			code_joueur="O";
		}
		else {
			code_joueur="X";
		}

		// Diagonale G //

		// Index
		int diagonaleG[][]={{0,0},{1,1},{2,2},{3,3}};
		boolean nbre_el=true;

		for(int i=0;i<diagonaleG.length;i++){  // Analyse des 4 cas

			int cpt=0;
			int serie=0; // Indicateur de 4 pions alignés

			int ligne2=diagonaleG[i][0];
			int col2=diagonaleG[i][1];

			while(cpt<4){   // 4 pions/cas
				if(ligne+ligne2>=0 && ligne+ligne2<=5 && col+col2>=0 && col+col2<=6){   // Limites du jeu
					if(tab[ligne+ligne2][col+col2].equalsIgnoreCase(code_joueur)){ 		// Correspondance
						serie+=1;
					}
					else{               // Pas de présence de pion
						nbre_el=false;
						cpt=4;
					}
				}
				else{					// Limites du jeu dépassées
					nbre_el=false;
					cpt=4;
				}
				col2-=1;    // Position des colonnes des pions dans le cas de diagonale en sens horaire
				ligne2-=1;	// Position des lignes des pions dans le cas de diagonale en sens horaire
				cpt+=1;

			}
			if(serie==4){
				System.out.println("Vous avez gagné !");
				result=1;
			}
			cpt=0;
		}	
		
		/////////////////////////////////////

		// Diagonale D //

		// Index
		int diagonaleD[][]={{0,0},{1,-1},{2,-2},{3,-3}};
		nbre_el=true;

		for(int i=0;i<diagonaleD.length;i++){

			int cpt=0;
			int serie=0;

			int ligne2=diagonaleD[i][0];
			int col2=diagonaleD[i][1];


			while(cpt<4){
				if(ligne+ligne2>=0 && ligne+ligne2<=5 && col+col2>=0 && col+col2<=6){
					if(tab[ligne+ligne2][col+col2].equalsIgnoreCase(code_joueur)){
						serie+=1;
					}
					else{
						nbre_el=false;
						cpt=4;
					}
				}
				else{
					nbre_el=false;
					cpt=4;
				}
				col2+=1;     // Position des colonnes des pions dans le cas de diagonale en sens anti-horaire
				ligne2-=1;   // Position des lignes des pions dans le cas de diagonale en sens anti-horaire
				cpt+=1;

			}
			if(serie==4){
				System.out.println("Vous avez gagné !");
				result= 1;
			}
			cpt=0;

		}	


		/////////////////////////////////////

		// Horizontal //
		// Index
		int horizontal[][]={{0,0},{0,-1},{0,-2},{0,-3}};
		nbre_el=true;

		for(int i=0;i<horizontal.length;i++){

			int cpt=0;
			int serie=0;

			int ligne2=horizontal[i][0];
			int col2=horizontal[i][1];


			while(cpt<4){
				if(ligne+ligne2>=0 && ligne+ligne2<=5 && col+col2>=0 && col+col2<=6){
					if(tab[ligne+ligne2][col+col2].equalsIgnoreCase(code_joueur)){
						serie+=1;
					}
					else{
						nbre_el=false;
						cpt=4;
					}
				}
				else{
					nbre_el=false;
					cpt=4;
				}
				col2+=1;		// Position des colonnes de pions dans le cas d'alignements horizontaux
				cpt+=1;			// Position des lignes de pions dans le cas d'alignements verticaux

			}
			if(serie==4){
				System.out.println("Vous avez gagné !");
				result= 1;
			}
			cpt=0;

		}	

		/////////////////////////////////////
		// Vertical //

		int vertical[][]={{0,0},{1,0},{2,-0},{3,0}};
		nbre_el=true;

		for(int i=0;i<vertical.length;i++){

			int cpt=0;
			int serie=0;

			int ligne2=vertical[i][0];
			int col2=vertical[i][1];


			while(cpt<4){
				if(ligne+ligne2>=0 && ligne+ligne2<=5 && col+col2>=0 && col+col2<=6){
					if(tab[ligne+ligne2][col+col2].equalsIgnoreCase(code_joueur)){
						serie+=1;
					}
					else{
						nbre_el=false;
						cpt=4;
					}
				}
				else{
					nbre_el=false;
					cpt=4;
				}
				ligne2-=1;     // Position des colonnes de pions dans le cas d'alignements verticaux
				cpt+=1;			// Position des lignes de pions dans le cas d'alignements verticaux

			}
			if(serie==4){
				System.out.println("\nVous avez gagné !");
				result= 1;
			}
			cpt=0;

		}
		return result;
	}

	//////////////////////////////////////////////////////////////////////

	public static void affichage_tab(String[][]tab) {
		// Affichage tableau //
		for(int ligne=0;ligne<tab.length;ligne++){
			for(int col=0;col<tab[ligne].length;col++){
				System.out.print(" "+tab[ligne][col]+" ");
			}
			System.out.println();
		}
	}


}


