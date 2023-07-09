import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;


public class Customer implements Comparable<Customer>{

    private String name;
    private int age;
    private Queue<String> list;
    private Stack<Product> bag;

    public Customer(String name, int age, Queue<String> list) {
        this.name = name;
        this.age = age;
        this.list = list;
        this.list = new LinkedList<String>(list);;
        this.bag = new Stack<Product>();
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public Queue<String> getList() {
        return this.list;
    }

    public Stack<Product> getBag() {
        return this.bag;
    }

    public void buy(Product product) {
        bag.push(product);
    }

    @Override
    public String toString() {
        String toString = "";
        toString += this.name + "(" + this.age + "):";
        for (Product p : this.bag) {
            toString += "\n " + p.getName();
        }
        return toString;
    }

    @Override
    public int compareTo(Customer o) {
        if (this.age < o.age) {
            return -1;
        }
        if (this.age > o.age) {
            return 1;
        }
        return 0;
    }
}
