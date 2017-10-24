package vo;

import java.util.Scanner;

public class UserVO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public Integer getId() {
        return id;
    }

    public String getStrId() {
        return id.toString();
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setStrId(String id) {
        this.id = Integer.valueOf(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
