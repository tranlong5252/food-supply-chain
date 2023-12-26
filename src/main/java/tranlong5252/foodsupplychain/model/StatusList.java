package tranlong5252.foodsupplychain.model;

import java.util.ArrayList;
import java.util.List;

public class StatusList extends ArrayList<IndustrialAgriculturalStatus> {

	public StatusList(List<IndustrialAgriculturalStatus> list) {
		super(list);
	}

	public StatusList() {
		super();
	}

	public IndustrialAgriculturalStatus getById(int id) {
		for (IndustrialAgriculturalStatus region : this) {
			if (region.getId() == id) {
				return region;
			}
		}
		return null;
	}
}
