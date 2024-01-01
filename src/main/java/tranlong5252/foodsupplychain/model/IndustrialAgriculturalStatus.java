package tranlong5252.foodsupplychain.model;

import tranlong5252.foodsupplychain.constants.StatusLevel;

public class IndustrialAgriculturalStatus {
//(name: text, level: high/low/medium, value: % decimal, potential: integer 1-10, development: integer 1-10)

    private int id;
    private String name;
    private StatusLevel level;
    private double value;
    private int potential;
    private int development;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusLevel getLevel() {
        return level;
    }

    public void setLevel(StatusLevel level) {
        this.level = level;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getPotential() {
        return potential;
    }

    public void setPotential(int potential) {
        this.potential = potential;
    }

    public int getDevelopment() {
        return development;
    }

    public void setDevelopment(int development) {
        this.development = development;
    }
}
