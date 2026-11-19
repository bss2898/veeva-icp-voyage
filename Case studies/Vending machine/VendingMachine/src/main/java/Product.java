import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;

import static java.util.Objects.hash;

public class Product {
    // I am gonna go straight into this product with limited attributes
    private BigDecimal price;
    private String name;
    //what about quantity?
    //stored in where?


    public Product(BigDecimal price, String name) {
        this.price = price;
        this.name = name.toLowerCase(Locale.ROOT);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name.toUpperCase(Locale.ROOT);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(obj==null)
            return false;
        if(this.getClass()!=obj.getClass()){
            return false;
        }
        Product temp=(Product) obj;
        return Objects.equals(this.name,temp.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
