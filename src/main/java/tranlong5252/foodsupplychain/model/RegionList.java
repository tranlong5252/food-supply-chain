package tranlong5252.foodsupplychain.model;

import java.util.ArrayList;
import java.util.List;

public class RegionList extends ArrayList<Region> {

    public RegionList(List<Region> list) {
        super(list);
    }

    public RegionList filterByPopulationDistributionMin(double v) {
        this.removeIf(region -> region.getPopulation().getDistribution() < v);
        return this;
    }

    public RegionList filterByPopulationDistributionMax(double v) {
        this.removeIf(region -> region.getPopulation().getDistribution() > v);
        return this;
    }

    public RegionList filterByPopulationMigrationMin(int i) {
        this.removeIf(region -> region.getPopulation().getMigration() < i);
        return this;
    }

    public RegionList filterByPopulationMigrationMax(int i) {
        this.removeIf(region -> region.getPopulation().getMigration() > i);
        return this;
    }

    public RegionList filterByPopulationUrbanizationMin(int i) {
        this.removeIf(region -> region.getPopulation().getUrbanization() < i);
        return this;
    }

    public RegionList filterByPopulationUrbanizationMax(int i) {
        this.removeIf(region -> region.getPopulation().getUrbanization() > i);
        return this;
    }

    public RegionList filterByNatureAgricultureLandMin(double v) {
        this.removeIf(region -> region.getNatureStatus().getAgricultureLand() < v);
        return this;
    }

    public RegionList filterByNatureAgricultureLandMax(double v) {
        this.removeIf(region -> region.getNatureStatus().getAgricultureLand() > v);
        return this;
    }

    public RegionList filterByNatureForestLandMin(double v) {
        this.removeIf(region -> region.getNatureStatus().getForestLand() < v);
        return this;
    }

    public RegionList filterByNatureForestLandMax(double v) {
        this.removeIf(region -> region.getNatureStatus().getForestLand() > v);
        return this;
    }

    public RegionList filterByNatureDisaster(String natDis) {
        this.removeIf(region -> !region.getNatureStatus().getDisaster().equalsIgnoreCase(natDis));
        return this;
    }
}
