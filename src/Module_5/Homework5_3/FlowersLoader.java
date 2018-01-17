package Module_5.Homework5_3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class FlowersLoader {
    public static Flowers[] load(String path){
        try(Scanner scanner = new Scanner(new FileInputStream(path))) {
            int rose = scanner.nextInt();
            int chamomile = scanner.nextInt();
            int tulip = scanner.nextInt();
            Flowers[] result = new Flowers[rose+chamomile+tulip];
            int i = 0;
            for (int j=0;j<rose;j++){
                result [i] = new Rose();
                i++;
            }
            for (int j=0;j<chamomile;j++){
                result [i] = new Chamomile();
                i++;
            }
            for (int j=0;j<tulip;j++){
                result [i] = new Tulip();
                i++;
            }

            return result;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }



    }
}
