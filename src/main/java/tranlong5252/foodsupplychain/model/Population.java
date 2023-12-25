package tranlong5252.foodsupplychain.model;

public class Population {
    //distribution: decimal, migration: integer 1-10, urbanization: integer 1-10
    private double distribution;
    private int migration;
    private int urbanization;

    public double getDistribution() {
        return distribution;
    }

    public void setDistribution(double distribution) {
        this.distribution = distribution;
    }

    public int getMigration() {
        return migration;
    }

    public void setMigration(int migration) {
        this.migration = migration;
    }

    public int getUrbanization() {
        return urbanization;
    }

    public void setUrbanization(int urbanization) {
        this.urbanization = urbanization;
    }
}
