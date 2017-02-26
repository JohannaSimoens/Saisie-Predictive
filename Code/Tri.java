/**
 * 
 * classe qui implemente les algorithmes de tri
 *
 */

public class Tri {	

	/**
	 *  Tri fusion
	 * @param tab tableau de Couple qu'on va trier en focntion de leur vects
	 * @param d début
	 * @param f fin
	 */
	//fonction tri avec le tri fusion en fonction du double un tableau de couple en ordre décroissant
	public static void triFusion(Couple[] tab, int d, int f){

		if(f<=d+2){
			if(f==d+2){
				//si la taille de tab est 2, et qu'à l'indice le plus petit la valeur est plus grande on les échnage
				if(tab[f-1].compareTo(tab[d])==1){
					Couple temp = tab[d];
					tab[d] = tab[f-1];
					tab[f-1] = temp;
				}
			}
		}
		else{
			// m prend le milieu de tableau
			int m = (f+d)/2;
			// on execute triFusion entre d et m-1 
			triFusion(tab,d,m);
			// on execute triFusion entre m et f-1
			triFusion(tab,m,f);
			// on fusionne le tout
			fusion(tab,d,m,f);			 
		}

	}

	//fonction qui fusionne les tableau triés 
	private static void fusion(Couple[] tab, int d, int m, int f){
		//on crée deux tableaux de taille m-d et un autre de taille f-m
		Couple[] tab1 = new Couple[m-d];
		Couple[] tab2 = new Couple[f-m];

		//on remplie le premier tableau des m-d premières valeurs de tab
		int j=0;
		for(int i=d; i<m;i++){
			tab1[j]=tab[i];
			j++;
		}
		//on remplie le deuxième tableau des f-m dernière valeurs de tab
		int k=0;
		for(int i=m;i<f;i++){
			tab2[k]=tab[i];
			k++;
		}
		j=0;
		k=0;
		for(int i=d; i<f; i++){
			if(j==tab1.length){
				//si tab1 est fini on complète tab avec le reste de tab2
				if(k!=tab2.length) {
					tab[i]=tab2[k];
					k++;
				}
			}
			else if(k==tab2.length){
				//si tab2 est fini on complète tab avec le reste de tab1
				if(j!= tab1.length){
					tab[i]=tab1[j];
					j++;
				}
			}
			else{
				//si le vect de tab1 est plus grand que le vect de tab2 on met dans tab la valeur de tab1 et on incrémente l'indice de tab1
				if(tab1[j].compareTo(tab2[k])==1){
					tab[i]=tab1[j];
					j++;
				}
				//sinon on fait le contraire
				else{
					tab[i]=tab2[k];
					k++;
				}
			}
		}

	}
	
	//fonction pivotage
	private static int pivotage(Couple[] tab,int d, int f, int pivot){
		//on échange la pivot et la dernière valeur, le pivot devient la dernière valeur
		int j=d;
		Couple temp = tab[pivot];
		tab[pivot] = tab[f];
		tab[f]=temp;
		for(int i=d; i<f;i++){
			//si tab[i] est plus grand que le pivot, on echange tab[i] et tab[j] et on incrémente j
			//on met les valeurs supérieures au pivot avant le pivot
			if(tab[i].compareTo(tab[f])==1){
				temp = tab[i];
				tab[i]=tab[j];
				tab[j]=temp;
				j = j+1;
			}

		}
		//on remet le pivot à sa place
		temp = tab[f];
		tab[f]=tab[j];
		tab[j]=temp;
		return j;
	}

	/**
	 * tri rapide
	 * @param tab tableau de Couple qui va être trié de façon décroissante en fonction des vect
	 * @param d indice du début
	 * @param f indice de la fin
	 */
	public static void quickSort(Couple[] tab,int d, int f){
		if(d<f){
			int pospivot = d;
			pospivot=pivotage(tab,d,f,pospivot);
			quickSort(tab,d,pospivot-1);
			quickSort(tab,pospivot+1,f);
		}
	}
	
	public static void main(String[] args){
		Couple[] t={new Couple("",6),new Couple("",5),new Couple("",2),new Couple("",-0),new Couple("",0)};
		// attetntion les bornes vont de 0 à t.length-1 contrairement à triFusion
		quickSort(t, 0, t.length-1);
		System.out.println("Exemple quickSort:");
		for(int i=0; i<t.length; i++){
			System.out.println(t[i]);
		}
		System.out.println();
		Couple[] t2={new Couple("",0),new Couple("",12),new Couple("",-2),new Couple("",0),new Couple("",6)};
		triFusion(t2, 0, t2.length);
		System.out.println("Exemple avec tri fusion:");
		for(int i=0; i<t2.length; i++){
			System.out.println(t2[i]);
		}
	}


}