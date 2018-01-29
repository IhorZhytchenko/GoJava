package Module_7.Homework7.FruitCorporation;

import Module_7.Homework7.FruitCorporation.Delivery.Delivery;
import com.alibaba.fastjson.JSON;

import java.time.LocalDate;
import java.util.Date;



public class main {


    public static void main(String[] args) {
        JSON.DEFFAULT_DATE_FORMAT = "dd/MM/yyyy";
        TradeShop shop = new TradeShop();
        shop.addFruits("files/Module7/delivery1.txt");
        shop.addFruits("files/Module7/delivery2.txt");
        shop.save("files/Module7/shopSave.txt");
        shop.addFruits("files/Module7/delivery2.txt");
        shop.save("files/Module7/shopSave1.txt");
        shop.load("files/Module7/shopSave.txt");
        shop.save("files/Module7/shopSave2.txt");

        System.out.println("getSpoiledFruits");
        System.out.println(shop.getSpoiledFruits(LocalDate.of(2018,02,11)));
        System.out.println(shop.getSpoiledFruits(LocalDate.of(2018,02,11),Type.Apricot));

        System.out.println("getAvailableFruits");
        System.out.println(shop.getAvailableFruits(LocalDate.of(2018,02,11)));
        System.out.println(shop.getAvailableFruits(LocalDate.of(2018,02,11),Type.Lime));

        System.out.println("getAddedFruits");
        System.out.println(shop.getAddedFruits(LocalDate.of(2018,01,24)));
        System.out.println(shop.getAddedFruits(LocalDate.of(2018,01,24),Type.Orange));

        shop.sell("files/Module7/sell.txt");
        shop.save("files/Module7/shopSave3.txt");



    }
}
