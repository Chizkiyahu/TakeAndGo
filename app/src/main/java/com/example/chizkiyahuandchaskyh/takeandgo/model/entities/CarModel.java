package com.example.chizkiyahuandchaskyh.takeandgo.model.entities;

public class CarModel {

    public CarModel(int codeModel, String companyName, String modelName, String engineCapacity, GEAR_BOX gearBox, int seating) {
        this.codeModel = codeModel;
        this.companyName = companyName;
        this.modelName = modelName;
        this.engineCapacity = engineCapacity;
        this.gearBox = gearBox;
        this.seating = seating;
    }
    protected int codeModel;
    protected String companyName;
    protected String modelName;
    protected String engineCapacity;
    protected GEAR_BOX gearBox;
    protected int seating;

    enum GEAR_BOX{
        MANUAL,
        AUTOMATIC
    }

    public int getCodeModel() {
        return codeModel;
    }

    public void setCodeModel(int codeModel) {
        this.codeModel = codeModel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public GEAR_BOX getGearBox() {
        return gearBox;
    }

    public void setGearBox(GEAR_BOX gearBox) {
        this.gearBox = gearBox;
    }

    public int getSeating() {
        return seating;
    }

    public void setSeating(int seating) {
        this.seating = seating;
    }



}
