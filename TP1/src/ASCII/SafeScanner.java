package ASCII;

import java.io.InputStream;
import java.util.Scanner;

public class SafeScanner {
    Scanner sc;

    public SafeScanner(InputStream is)
    {
        this.sc = new Scanner(is);
    }

    public int getInt()
    {
        int input = this.sc.nextInt();
        return input;
    }

    public void closeScanner()
    {
        this.sc.close();
    }
}
