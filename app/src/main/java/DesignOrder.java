import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 3/23/2017.
 */

public class DesignOrder {
    ///////////////////////////////////////
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    private String size;
    ///////////////////////////////////////

    private List<String> toppings = new ArrayList<String>();

    public void addTopping(String topping){
        toppings.add(topping);
    }

    ///////////////////////////////////////
}
