import java.util.Arrays;
import java.util.Scanner;

public class TP2_G5_Paul_Flammarion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel exercise ? Saisissez :");
        System.out.println("1. Discriminant");
        System.out.println("2. Parité d’un nombre");
        System.out.println("3. Calcul d’extremum");
        System.out.println("4. Égalité entre chaînes de caractères");
        System.out.println("5. Factorielle");
        System.out.println("6. Compte à rebours");
        System.out.println("7. Valeurs de carrés");
        System.out.println("8. Table de multiplication");
        System.out.println("9. Division d’entiers");
        System.out.println("10. Règle graduée");
        System.out.println("11. Nombres premiers");
        System.out.println("12. Manipulations sur un tableau");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> discriminant();
            case 2 -> parite();
            case 3 -> {
                max();
                min();
            }
            case 4 -> egaliteStr();
            case 5 -> factorielle();
            case 6 -> countdown();
            case 7 -> carres();
            case 8 -> tableMultiplication();
            case 9 -> division();
            case 10 -> regle();
            case 11 -> nombrePremier();
            case 12 -> initialisationTableau();
        }
        System.out.println("\nMerci d'avoir joué");
    }
    public static void discriminant() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelle est la valeur de a ?");
        int a = scanner.nextInt();
        System.out.println("Quelle est la valeur de b ?");
        int b = scanner.nextInt();
        System.out.println("Quelle est la valeur de c ?");
        int c = scanner.nextInt();
        int delta = (int) (Math.pow(b, 2) - 4 * a * c);
        double resultat;
        if (delta < 0) {
            System.out.println("Ce polynome n’a pas de racine reelle");
            System.out.println("La première racine vaut : (" + -b + " + i * " + Math.sqrt(-delta) + ")/" + 2 * a);
            System.out.println("La première racine vaut : (" + -b + " - i * " + Math.sqrt(-delta) + ")/" + 2 * a);
        }
        else if (delta == 0){
            resultat = -b / 2*a;
            System.out.println("la racine unique est : " + resultat);
        }
        else {
            resultat = -b - Math.sqrt(delta) / 2*a;
            System.out.println("la premiere racine est : " + resultat );
            resultat = -b + Math.sqrt(delta) / 2*a;
            System.out.println("la deuxième racine est : " + resultat);

        }
    }

    public static void parite(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("pouvez vous saisir un entier");
        int chiffre = scanner.nextInt();
        if (chiffre % 2 == 0){
            System.out.println("Le chiffre est pair, " +chiffre);
        }
        else System.out.println("Le chiffre est impaire, " + chiffre);
    }

    public static void max(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("pouvez vous saisir deux entiers");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int max = Math.max(a, b);
        System.out.println("L'entier le plus grand est : " + max);
    }
    public static void min(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("pouvez vous saisir deux entiers");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int min = Math.min(a, b);
        System.out.println("L'entier le plus petit est : " + min);
    }

    public static void egaliteStr(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("pouvez vous saisir deux chaines de character");
        String a = scanner.nextLine();
        String b = scanner.nextLine();
        if (a.equals(b)){
            System.out.println("Ces chaines de char sont pareilles " + a + " " +  b);
        }
        else {
            if (a.equalsIgnoreCase(b)){
                System.out.println("Sans majuscules ces chaines de char sont pareilles " + a + " " +  b);
            }
            else System.out.println("Ces chaines de char ne sont pas pareilles " + a + " " +  b);
        }
    }
    public static void factorielle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisir un entier positif ou nul");
        int n = scanner.nextInt();
        int factorielle = 1;
        for (int i = 0; i < n; i++) {
            factorielle *= i + 1;
        }
        System.out.println(n + "! = " + factorielle);
    }
    public static void countdown(){
        for(int i = 10; i >= 0; i --){
            System.out.println(i);
        }
        System.out.println("BOOM");
    }
    public static void carres(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisir un entier");
        int x = scanner.nextInt();
        System.out.println(x + "    " + x * x);
    }

    public static void tableMultiplication(){
        String row = "";
        for (int i = 0; i < 10; i ++){
            for (int j = 0; j < 10; j ++){
                row = row.concat((i + 1) * (j + 1) + " ");
            }
            System.out.println(row + "\n");
            row = "";
        }
    }

    public static void division() {
        Scanner scanner = new Scanner(System.in);
        double premierEntier;
        double deuxiemeEntier;
        do {
            System.out.println("\nVeuillez saisir un entier positif ou nul");
            System.out.println("Veuillez saisir le premier entier");
            premierEntier = scanner.nextDouble();
            System.out.println("Veuillez saisir le deuxieme entier");
            deuxiemeEntier = scanner.nextDouble();
        } while (deuxiemeEntier == 0);
        double division = premierEntier / deuxiemeEntier;
        System.out.println("La division de " + premierEntier + " avec " + deuxiemeEntier +
                    " est egale a " + division);
    }

    public static void regle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer la longueur de la règle");
        int length = scanner.nextInt();
        String regle = "";
        for (int i = 0; i < length + 1; i ++){
            if (i % 10 == 0){
                regle = regle.concat("|");
            }else regle = regle.concat("-");
        }
        System.out.println(regle);
    }

    public  static void nombrePremier(){
       Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nombre");
        int nbr = scanner.nextInt();
        int reste;
        boolean flag = true;

        for(int i=2; i <= nbr/2; i++)
        {
            reste = nbr%i;
            if(reste == 0){
                flag = false;
                break;
            }
        }
        if(flag)
            System.out.println(nbr + " est un nombre premier");
        else
            System.out.println(nbr + " n'est pas un nombre premier");
    }
    public static void initialisationTableau() {
        int[] tableau = new int[4];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < tableau.length; i++) {
            System.out.println("Saisir un entier");
            int entier = scanner.nextInt();
            tableau[i] = entier;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        String odd = "";
        String oddElement = "";
        int somme = 0;

        for (int i = 0; i< tableau.length; i ++){
            if (tableau[i] > max){
                max = tableau[i];
            }
            if (tableau[i] < min){
                min = tableau[i];
            }
            if (tableau[i] % 2 == 0){
                odd = odd.concat(tableau[i] + " ");
            }
            if ( i % 2 == 0){
                oddElement = oddElement.concat(tableau[i] + " ");
            }
            somme += tableau[i];
        }
        int[] inverse = inverseTableau(tableau);
        System.out.println("Le max est " + max);
        System.out.println("Le min est " + min);
        System.out.println("La somme est " + somme);
        System.out.println("Les elements paires du tableau sont : " + odd);
        System.out.println("Les elements qui ont un indice paire du tableau sont : " + oddElement);
        System.out.println("L'inverse de ce tableau est : " + Arrays.toString(inverse));

    }

    public static int[] inverseTableau(int[] tableau){
        for(int i=0; i< tableau.length/2; i++){
            int tmp = tableau[i];
            tableau[i] = tableau[tableau.length-i-1];
            tableau[tableau.length-i-1] = tmp;
        }
        return tableau;
    }
}
