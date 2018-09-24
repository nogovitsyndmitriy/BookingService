package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {
    String name;
    String lastName;
    String age;
    int Id;


    @Override
    public String toString() {
        return "Client: " +
                " id=" + Id + ", name: " + name +
                ", lastName: " + lastName +
                ", age=" + age;
    }

    public Client(String name, String lastName, String age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

}

