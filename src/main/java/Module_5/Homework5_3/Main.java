package Module_5.Homework5_3;

public class Main {

    static void showBouquet(Flowers[] bouquet){
        for (int i = 0; i < bouquet.length;i++){
            if (i!=0) System.out.print(", ");
            if (bouquet[i] instanceof Rose ){
                Rose rose = (Rose) bouquet[i];
                System.out.print(rose.toString());
            }
            if (bouquet[i] instanceof Chamomile ){
                Chamomile chamomile = (Chamomile) bouquet[i];
                System.out.print(chamomile.toString());

            }
            if (bouquet[i] instanceof Tulip ){
                Tulip tulip = (Tulip) bouquet[i];
                System.out.print(tulip.toString());

            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        FlowerStore store = new FlowerStore();
        showBouquet(store.sell(2,1,4));
        showBouquet(store.sellSequence(3,2,3));
        System.out.println("В кошельке :" + store.getPurse());
        FlowersSaver.save("files/Module5_3/input.txt", store.sell(3,2,2));
        showBouquet(FlowersLoader.load("files/Module5_3/output.txt"));


    }
}
