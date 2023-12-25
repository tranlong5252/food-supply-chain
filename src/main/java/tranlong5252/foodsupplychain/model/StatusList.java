package tranlong5252.foodsupplychain.model;

import tranlong5252.foodsupplychain.constants.StatusLevel;

import java.util.ArrayList;

public class StatusList extends ArrayList<IndustrialAgriculturalStatus> {

	public IndustrialAgriculturalStatus getById(int id) {
		for (IndustrialAgriculturalStatus region : this) {
			if (region.getId() == id) {
				return region;
			}
		}
		return null;
	}

	public void setById(int id, IndustrialAgriculturalStatus region) {
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
