package entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contact {
    int id;
    String address;
    String phone;

    public Contact(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }
}
