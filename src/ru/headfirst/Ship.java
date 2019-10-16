package ru.headfirst;

import java.util.List;

public class Ship {
    private String name;
    private List<String> adresses;

    List<String> getAdresses() {
        return adresses;
    }

    void setAdresses(List<String> addresses) {
        this.adresses = addresses;
    }


    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String checkYourself(String userInput) {
        String result = "Miss!";
        int index = adresses.indexOf(userInput);
        if (index >= 0) {
            adresses.remove(index);
            if (adresses.isEmpty()) {
                result = "Sunk!";
                System.out.println(">>>Sunk ship " + name +", BOY!<<<");
            } else {
                result = "it's hit BOY!";
            }
        }
        return result;
    }
}
