public class Test {
    public static void main(String[] args) {
        System.out.println(binomial(6, 3));

        /*int i = 2020;
        System.out.println(i);
        premiereFonction(i);
        System.out.println(i);

         // Suite de la methode main() apres saut de page
        int[] tableau = {1, 3, 5};
        System.out.println(tableau[1]);
        secondeFonction(tableau);
        System.out.println(tableau[1]);*/
        }
        public static void premiereFonction(int n) {
        if (n % 2 == 0) {
            n = 3 * n;
            } else {
            n = 2 * n;
             }
         System.out.println(n);
         }
         public static void secondeFonction(int[] array) {
         if (array.length > 2) {
             array[1] = 0;
             System.out.println(array[1]);
             } else {
             System.out.println("Erreur");
             }
         }
    public static int uneAutreFonction(int n) {
        int a = 0;
        int b = 1;
        int c = 0;
        int i = 0;
        while (i <= n) {
            if (i < 2) c = i;
            else {
                c = a + b;
                a = b;
                b = c;
                i++;
            }
        }
        return c;
    }

    public Object dichotomie(int[] tableau, int valeurRecherchee){
        int indiceGauche = 0;
        int indiceDroit = tableau.length - 1;
        int indiceMilieu = (indiceGauche + indiceDroit) / 2;


        while (indiceGauche <= indiceDroit) {
            if (tableau[indiceMilieu] < valeurRecherchee) {
                    indiceGauche = indiceMilieu + 1;
            }
            else if (tableau[indiceMilieu] > valeurRecherchee) {
                indiceDroit = indiceMilieu - 1;
            } else {
                return indiceMilieu;
            }
        }
        return null;
    }
    public static int binomial(int n, int k){
        int coef = 1;
        if (k > 0  && n > k) {
            //faire le calcule
            coef = factorial(n) / (factorial(k) * factorial(n-k));
        }
        return coef;
    }
    public static int factorial(int fact){
        int tmp = 1;
        for (int i = 1; i <= fact; i++){
            tmp = tmp * i;
        }
        return tmp;
    }
}
