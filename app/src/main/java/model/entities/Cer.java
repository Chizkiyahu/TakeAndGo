package model.entities;

public class Cer extends CarModel {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    protected int  branchID;
    protected int km;

    public Cer(int codeModel, String companyName, String modelName, String engineCapacity, GEAR_BOX gearBox, int seating, int id, int branchID, int km) {
        super(codeModel, companyName, modelName, engineCapacity, gearBox, seating);
        this.id = id;
        this.branchID = branchID;
        this.km = km;
    }


}
