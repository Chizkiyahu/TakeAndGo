package com.example.chizkiyahuandchaskyh.takeandgo.model.entities;


import android.nfc.Tag;
import android.nfc.TagLostException;

import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

/**
 * Created by chezkiaho on 23.3.2018.
 */

public class Branch  {
    protected int id;
    protected int numParkingSpaces;
    protected Address address;

    public Branch(int id, int numParkingSpaces, Address address) {
        this.id = id;
        this.numParkingSpaces = numParkingSpaces;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public int getNumParkingSpaces() {
        return numParkingSpaces;
    }

    public void setNumParkingSpaces(int numParkingSpaces) throws Exception {

        if (numParkingSpaces < 0){
            throw new Exception("numParkingSpaces can't < 0");
        }
        this.numParkingSpaces = numParkingSpaces;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
