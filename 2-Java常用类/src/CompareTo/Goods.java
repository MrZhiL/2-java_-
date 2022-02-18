package CompareTo;

/**
 * @ClassName: CompareTo
 * @Description: Java
 * @author: zhilx (zhilx1997@sina.com)
 * @version: v1.0
 * @data: 2022/2/18 13:43
 * @node:
 */
public class Goods implements Comparable {
    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    // 指明商品的排序方式：价格从低到高，名称从大到小
    public int compareTo(Object o) {
        if (o instanceof Goods) {
            Goods good = (Goods)o;
            // 方式一：自定义排序方式
            if (this.price > good.price) {
                return 1;
            } else if (this.price < good.price) {
                return -1;
            } else {
                return -this.name.compareTo(good.name); // 如果价格相同，则按名字进行逆序排序
            }

            // 方式二：使用Double包装类的CompareTo
            // return Double.compare(this.price, good.price);
        }

        throw new RuntimeException("输入类型不一致！");
    }
}
