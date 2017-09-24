package com.example.lithamguzuli.jzcourier;

/**
 * Created by litha Mguzuli on 9/25/2017.
 */

public class Users {
    String name,email,address,numbers,pswd;
    public Users(){

    }

    public Users( String name, String email, String address, String numbers) {

        this.name = name;
        this.email = email;
        this.address = address;
        this.numbers = numbers;
        this.pswd = pswd;
    }
    /*public String getUser_id() {
        return UserId;
    }*/

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getNumbers() {
        return numbers;
    }


}
