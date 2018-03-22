package entities;

/**
 * Created by chezkiaho on 23.3.2018.
 */

public class Branch {
    int id;
    int numParkingSpaces;
    Address address;

    public Branch(int id, int numParkingSpaces, Address address) {
        this.id = id;
        this.numParkingSpaces = numParkingSpaces;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumParkingSpaces() {
        return numParkingSpaces;
    }

    public void setNumParkingSpaces(int numParkingSpaces) {
        this.numParkingSpaces = numParkingSpaces;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    class Address{

        String city;
        String street;
        int houseNum;

        public Address(String city, String street, int houseNum) {
            this.city = city;
            this.street = street;
            this.houseNum = houseNum;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public int getHouseNum() {
            return houseNum;
        }

        public void setHouseNum(int houseNum) {
            this.houseNum = houseNum;
        }

    }
}
