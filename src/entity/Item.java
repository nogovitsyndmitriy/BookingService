package entity;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Item {
    private int id;
    private int roomId;
    private int orderId;
    private int quantity;
    private int accountId;


    public Item(int accountId, int roomId, int quantity, int orderId) {
        this.roomId = roomId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.accountId = accountId;
    }
}
