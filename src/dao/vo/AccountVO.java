package dao.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountVO {
   private int ID;
   private String login;
   private String password;
   private String name;
   private String lastName;
   private String passportId;
   private String address;
   private String phone;
   private String cardNumber;
   private String cardHolder;
   private String age;
}
