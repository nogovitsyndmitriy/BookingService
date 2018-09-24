package entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    private int id;
    private int accountId;
    private List<Item> items = new ArrayList<>();
    private double total;
    private Date date;

    public Order(int id, int accountId, double total) {
        this.id = id;
        this.accountId = accountId;
        this.total = total;
    }

    public Order(int accountId, double total) {
        this.accountId = accountId;
        this.total = total;
    }

    public Order(int id, int accountId, double total, Date date) {
        this.id = id;
        this.accountId = accountId;
        this.total = total;
        this.date = date;
    }
}