package Module_5.Homework5_3;

public class FlowerStore {
    private int purse;

    public FlowerStore(){
        purse = 0;
    }

    public int getPurse() {
        return purse;
    }

    public Flowers[] sell(int rose, int chamomile, int tulip){
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
        addToPurse(result);
        return result;
    }

    public Flowers[] sellSequence (int rose, int chamomile, int tulip) {
        Flowers[] result = new Flowers[rose + chamomile + tulip];
        int max = Math.max(Math.max(rose,chamomile),tulip);
        int i = 0;
        for (int j = 0; j < max; j++){
            if (j<rose){
                result [i] = new Rose();
                i++;
            }
            if (j<chamomile){
                result [i] = new Chamomile();
                i++;
            }
            if (j<tulip){
                result [i] = new Tulip();
                i++;
            }

        }
        addToPurse(result);
        return result;
    }

    private void addToPurse(Flowers[] bouquet){
        for (int i = 0; i < bouquet.length;i++){
            if (bouquet[i] instanceof Rose ){
                Rose rose = (Rose) bouquet[i];
                purse+=rose.getPrice();
            }
            if (bouquet[i] instanceof Chamomile ){
                Chamomile chamomile = (Chamomile) bouquet[i];
                purse+=chamomile.getPrice();
            }
            if (bouquet[i] instanceof Tulip ){
                Tulip tulip = (Tulip) bouquet[i];
                purse+=tulip.getPrice();
            }
        }
    }
}
