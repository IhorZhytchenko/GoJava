package Module_7.Homework7.FruitCorporation;

import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Company {
    private int moneyBalance;
    private List<TradeShop> shops = new ArrayList<>();

    public void save(String pathToJsonFile){
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
        Company company = JSON.parseObject(json,Company.class);
        shops = company.getShops();
        moneyBalance = company.getMoneyBalance();

    }
    public TradeShop getShop(int index){
        return shops.get(index);
    }
    public int getCompanyBalance(){
        int result=0;
        for (TradeShop shop:shops) {
            result+=shop.getMoneyBalance();

        }
        return result;
    }
    public List<Fruit> getSpoiledFruits(LocalDate date){
        List <Fruit> result = new ArrayList<>();
        for (TradeShop shop:shops) {
            result.addAll(shop.getSpoiledFruits(date));
        }
        return result;
    }
    public List<Fruit> getSpoiledFruits(LocalDate date,Type type){
        List <Fruit> result = new ArrayList<>();
        for (TradeShop shop:shops) {
            result.addAll(shop.getSpoiledFruits(date,type));
        }
        return result;
    }
    public List<Fruit> getAvailableFruits(LocalDate date){
        List <Fruit> result = new ArrayList<>();
        for (TradeShop shop:shops) {
            result.addAll(shop.getAvailableFruits(date));
        }
        return result;
    }
    public List<Fruit> getAvailableFruits(LocalDate date,Type type){
        List <Fruit> result = new ArrayList<>();
        for (TradeShop shop:shops) {
            result.addAll(shop.getAvailableFruits(date,type));
        }
        return result;
    }
    public List<Fruit> getAddedFruits(LocalDate date){
        List <Fruit> result = new ArrayList<>();
        for (TradeShop shop:shops) {
            result.addAll(shop.getAddedFruits(date));
        }
        return result;
    }
    public List<Fruit> getAddedFruits(LocalDate date,Type type){
        List <Fruit> result = new ArrayList<>();
        for (TradeShop shop:shops) {
            result.addAll(shop.getAddedFruits(date,type));
        }
        return result;
    }

    public int getMoneyBalance() {
        return moneyBalance;
    }

    public List<TradeShop> getShops() {
        return shops;
    }

    public void setMoneyBalance(int moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public void setShops(List<TradeShop> shops) {
        this.shops = shops;
    }
}
