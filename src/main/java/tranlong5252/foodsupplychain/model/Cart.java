package tranlong5252.foodsupplychain.model;

import java.util.ArrayList;

public class Cart extends ArrayList<CartProduct> {
    private double total;

    public double getTotal() {
        return total;
    }

    @Override
    public boolean add(CartProduct e) {
        total += e.getSubtotal();
        return super.add(e);
    }

    @Override
    public CartProduct set(int index, CartProduct element) {
        CartProduct old = this.get(index);
        total -= old.getSubtotal();
        total += element.getSubtotal();
        return super.set(index, element);
    }

    @Override
    public CartProduct remove(int index) {
        total -= this.get(index).getSubtotal();
        return super.remove(index);
    }

    public Cart() {
        total = 0;
    }


}
