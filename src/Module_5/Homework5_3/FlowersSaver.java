package Module_5.Homework5_3;

import java.io.FileWriter;
import java.io.IOException;

public abstract class FlowersSaver {

    public static void save(String path, Flowers[] bouquet){
        String result="";
        for (int i = 0; i < bouquet.length;i++){
            if (i!=0) result+=", ";
            if (bouquet[i] instanceof Rose ){
                Rose rose = (Rose) bouquet[i];
                result+=rose.toString();
            }
            if (bouquet[i] instanceof Chamomile ){
                Chamomile chamomile = (Chamomile) bouquet[i];
                result+=chamomile.toString();

            }
            if (bouquet[i] instanceof Tulip ){
                Tulip tulip = (Tulip) bouquet[i];
                result+=tulip.toString();

            }
        }
        try(FileWriter writer = new FileWriter(path)) {
            writer.write(result);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
