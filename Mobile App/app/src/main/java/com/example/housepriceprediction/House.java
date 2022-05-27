package com.example.housepriceprediction;

import java.util.ArrayList;
import java.util.Map;

public class House {
    ArrayList<Map> houses;

    public House(){
    }
    public House(ArrayList<Map> houses) {
        this.houses = houses;
    }
    public ArrayList<Map> getHouses() {
        return houses;
    }
}
