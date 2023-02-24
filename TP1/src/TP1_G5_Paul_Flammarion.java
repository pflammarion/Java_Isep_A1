import java.util.Scanner;

public class TP1_G5_Paul_Flammarion {
    public static void main(String[] args) {
        //le résultat attendu du code serait 2 inputs puis affichage de celles-ci dans la console si le format est respecté par l'utilisateur.
        //Ce sont des recepteur d'input de l'utilisateur
        //Il se passe ce que j'ai dit juste au-dessus non ?
        Scanner scanner = new Scanner(System.in); // création de l'objet scanner
        System.out.println("Bonjour,quel est votre prénom ?\n"); //demande à l'utilisateur de rentrer son prénom
        String prenom = scanner.nextLine(); //récupération de l'input et affiliation à une variable de type String
        System.out.println("Entrez un int"); // demande d'int
        int unEntier = scanner.nextInt(); //récupération + assignee
        System.out.println("Entrez un float"); //demande de float
        float unReel = scanner.nextFloat(); //récupération + assignee
        System.out.println("Bonjour, " + prenom); // affichage du prénom saisi dans la console
        System.out.println("J’ai recupere un entier: " + unEntier); // affichage de l'int saisi dans la console
        System.out.println("J’ai aussi recupere un reel: " + unReel); //affichage du réel saisi dans la console
        somme();
    }
    public static void somme() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le premier entier");
        int premierEntier = scanner.nextInt();
        System.out.println("Veuillez saisir le deuxieme entier");
        int deuxiemeEntier = scanner.nextInt();
        int somme = premierEntier + deuxiemeEntier;
        System.out.println("La somme de " + premierEntier + " avec " + deuxiemeEntier +
                "est egale a " + somme);
    }

    public static void division() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le premier entier");
        double premierEntier = scanner.nextDouble();
        System.out.println("Veuillez saisir le deuxieme entier");
        double deuxiemeEntier = scanner.nextDouble();
        if (deuxiemeEntier != 0) {
            double division = premierEntier / deuxiemeEntier;
            System.out.println("La division de " + premierEntier + " avec " + deuxiemeEntier +
                    " est egale a " + division);
        }
    }

    // nous avons besoin de 3 variables pour faire ce calcul
    // ce sont des variables de type double pour un max de précision
    // nous pouvons laisser l'utilisateur entrer ces variables pour qu'il sache quelles sont elles
    // base fois hauteur fois longueur
    // nous pouvons offrir ce résultat à notre cher utilisateur
    public static void volume() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer la super hauteur de ce pavé droit");
        double hauteur = scanner.nextDouble();
        System.out.println("Veuillez entrer la super largeur de ce pavé droit");
        double largeur = scanner.nextDouble();
        System.out.println("Veuillez entrer la super longueur de ce pavé droit");
        double longueur = scanner.nextDouble();
        double volume = hauteur * largeur * longueur;
        System.out.println("Le volume est donc de " + volume);
    }
    //l'un des problèmes est l'affichage graphique, en effet l'utilisateur pourrait avoir envie de visualiser sa super construction, mais il n'obtient qu'un pauvre chiffre
}