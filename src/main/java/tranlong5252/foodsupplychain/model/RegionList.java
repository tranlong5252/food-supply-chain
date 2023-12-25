package tranlong5252.foodsupplychain.model;


import java.util.ArrayList;

public class RegionList extends ArrayList<Region> {

    public Region getById(int id) {
        for (Region region : this) {
            if (region.getId() == id) {
                return region;
            }
        }
        return null;
    }

    public void setById(int id, Region region) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId() == id) {
                this.set(i, region);
            }
        }
    }

    public void removeById(int id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId() == id) {
                this.remove(i);
                break;
            }
        }
    }
}
