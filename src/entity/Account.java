package entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    int ID;
    String login;
    String password;
    String name;
    String lastName;
    String passportId;

    public Account(String login, String password, String name, String lastName, String passportId) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.passportId = passportId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "ID=" + ID +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportId='" + passportId + '\'' +
                '}';
    }
}
