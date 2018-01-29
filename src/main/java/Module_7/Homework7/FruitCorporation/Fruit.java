package Module_7.Homework7.FruitCorporation;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;

import java.time.LocalDate;
import java.util.Date;

public class Fruit {
    private Type type;
    private int  shelfLife;
    @JSONField(format = "dd/MM/yyyy",parseFeatures={Feature.AllowISO8601DateFormat})
    private Date date;
    private int price;

    public Fruit(Type type, int shelfLife, Date date, int price) {
        this.type = type;
        this.shelfLife = shelfLife;
        this.date = date;
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public Date getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "type=" + type +
                ", shelfLife=" + shelfLife +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
