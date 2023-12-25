package tranlong5252.foodsupplychain.model;


import java.util.List;

public class Region {
    private int id;
    private String name;
    private StatusList statusList;
    private Population population;
    private NatureStatus natureStatus;

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

    public StatusList getStatuses() {
        return statusList;
    }

    public void setStatuses(StatusList statusList) {
        this.statusList = statusList;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public NatureStatus getNatureStatus() {
        return natureStatus;
    }

    public void setNatureStatus(NatureStatus natureStatus) {
        this.natureStatus = natureStatus;
    }
}
