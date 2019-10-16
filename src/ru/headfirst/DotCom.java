package ru.headfirst;

import java.util.ArrayList;

public class DotCom {
    private String name;
    private ArrayList<String> adresses;

    public ArrayList<String> getAdresses() {
        return adresses;
    }

    public void setAdresses(ArrayList<String> addresses) {
        this.adresses = addresses;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String checkYourself(String userInput) {
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
