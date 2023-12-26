package tranlong5252.foodsupplychain.model;

public class NatureStatus {
    private int id;
    private double agricultureLand; //% decimal
    private double forestLand;  //% decimal
    private String disaster; //can be many value like earthquake, typhoon, tsunami, etc.

    public double getAgricultureLand() {
        return agricultureLand;
    }

    public void setAgricultureLand(double agricultureLand) {
        this.agricultureLand = agricultureLand;
    }

    public double getForestLand() {
        return forestLand;
    }

    public void setForestLand(double forestLand) {
        this.forestLand = forestLand;
    }

    public String getDisaster() {
        return disaster;
    }

    public void setDisaster(String disaster) {
        this.disaster = disaster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
