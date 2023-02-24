import ASCII.AsciiArt;
import ASCII.SafeScanner;

import java.util.Objects;
import java.util.Scanner;

public class TP3_ASCII_G5_Paul_Flammarion {
    public static void main(String[] args) {
        AsciiArt cat = new AsciiArt();
        boolean inputGood = false;
        int height = 0;
        int width = 0;
        SafeScanner safeScanner = new SafeScanner(
                Objects.requireNonNull(SafeScanner.class.getResourceAsStream("/ASCII/demo.txt"))
        );
        while(!inputGood) {
            try {
                SafeScanner sc = new SafeScanner(System.in);
                System.out.print("Enter cat height (min 3) : ");
                height = sc.getInt();
                System.out.print("Enter cat width (min 7) : ");
                width = sc.getInt();
                inputGood = true;
                sc.closeScanner();
            } catch (Exception e) {
                System.out.println("Please enter integer");
            }

        }
        if(height>=3 && width>=7)
        {
            cat.AsciiArt(height,width);
        }
        else
        {
            System.out.println("Error : mininum dimensions for cat head are 3x7");
            System.exit(0);
        }
    }
}
