package Module_7.Homework7.FruitCorporation;

import Module_7.Homework7.FruitCorporation.Delivery.Delivery;
import Module_7.Homework7.FruitCorporation.Sell.Client;
import Module_7.Homework7.FruitCorporation.Sell.Sell;
import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradeShop {
    private int moneyBalance;
    private List<Fruit> fruits = new ArrayList<>();

    public void addFruits(String pathToJsonFile){
        String json = null;
        try(Scanner scanner = new Scanner(new FileInputStream(pathToJsonFile))) {
          scanner.useDelimiter("\\z");
          json = scanner.next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Delivery delivery = JSON.parseObject(json,Delivery.class);
          fruits.addAll(delivery.getFruits());

    }
    public  void save(String pathToJsonFile){
        String json = JSON.toJSONString(this);
        try(FileWriter writer = new FileWriter(pathToJsonFile)) {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(String pathToJsonFile){
        String json = null;
        try(Scanner scanner = new Scanner(new FileInputStream(pathToJsonFile))) {
            scanner.useDelimiter("\\z");
            json = scanner.next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        TradeShop shop = JSON.parseObject(json,TradeShop.class);
        fruits = shop.getFruits();
        moneyBalance = shop.getMoneyBalance();

    }
    public List<Fruit> getSpoiledFruits(LocalDate date){
        List <Fruit> result = new ArrayList<>();
        for (Fruit fruit: fruits) {
            LocalDate deliveryDate =  fruit.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate spoiledDate = deliveryDate.plusDays(fruit.getShelfLife());
           if (date.isAfter(spoiledDate)) result.add(fruit);
       }
       return result;
   }
    public List<Fruit> getSpoiledFruits(LocalDate date,Type type){
        List <Fruit> result = new ArrayList<>();
        for (Fruit fruit: fruits) {
            LocalDate deliveryDate =  fruit.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate spoiledDate = deliveryDate.plusDays(fruit.getShelfLife());
            if (date.isAfter(spoiledDate)&& (fruit.getType()==type)) result.add(fruit);
        }
        return result;
    }

   public List<Fruit> getAvailableFruits(LocalDate date){
       List <Fruit> result = new ArrayList<>();
       for (Fruit fruit: fruits) {
           LocalDate deliveryDate =  fruit.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           LocalDate availableDate = deliveryDate.plusDays(fruit.getShelfLife());
           if (date.isBefore(availableDate)) result.add(fruit);
       }
       return result;
   }
    public List<Fruit> getAvailableFruits(LocalDate date,Type type){
        List <Fruit> result = new ArrayList<>();
        for (Fruit fruit: fruits) {
            LocalDate deliveryDate =  fruit.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate availableDate = deliveryDate.plusDays(fruit.getShelfLife());
            if (date.isBefore(availableDate)&&(fruit.getType()==type)) result.add(fruit);
        }
        return result;
    }
    public List<Fruit> getAddedFruits(LocalDate date){
        List <Fruit> result = new ArrayList<>();
        for (Fruit fruit: fruits) {
            LocalDate deliveryDate =  fruit.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (deliveryDate.compareTo(date)==0) result.add(fruit);
        }
        return result;

    }
    public List<Fruit> getAddedFruits(LocalDate date,Type type){
        List <Fruit> result = new ArrayList<>();
        for (Fruit fruit: fruits) {
            LocalDate deliveryDate =  fruit.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if ((deliveryDate.compareTo(date)==0)&&(fruit.getType()==type)) result.add(fruit);
        }
        return result;

    }

    public void sell(String pathToJsonFile){
        String json = null;
        try(Scanner scanner = new Scanner(new FileInputStream(pathToJsonFile))) {
            scanner.useDelimiter("\\z");
            json = scanner.next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Sell sel = JSON.parseObject(json,Sell.class);
        for (Client client: sel.getClients()) {
            List<Fruit> fruitList = getAvailableFruits(LocalDate.now(),client.getType());
            if (fruitList.size()>=client.getCount()){
                for(int i = 0; i<client.getCount();i++){
                    moneyBalance+=fruitList.get(i).getPrice();
                    fruits.remove(fruitList.get(i));

                }

            }

        }

    }




    public int getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(int moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }
}
