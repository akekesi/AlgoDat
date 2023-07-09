import java.util.LinkedList;

public class Shop {
    private String name;
    private String besitzer;
    private Product[] regal;

    public Shop(String name, String besitzer, int size) {
        this.name = name;
        this.besitzer = besitzer;
        this.regal = new Product[size];
    }

    public String getName() {
        return this.name;
    }

    public String getBesitzer() {
        return this.besitzer;
    }

    public void lieferung(Product product) {
        for (int i = 0; i < this.regal.length; i++) {
            if (this.regal[i] == null) {
                this.regal[i] = product;
                return;
            }
        }
        System.out.println("Das Produkt '" + product.getName() + "' konnte nicht geliefert werden.");
    }

    public void lieferung(LinkedList<Product> productLinkedListL) {
        for (Product product : productLinkedListL) {
            this.lieferung(product);
        }
    }
    public Product kauf(String name) {
        for (int i = 0; i < this.regal.length; i++) {
            if (this.regal[i] == null) {
                continue;
            }
            else if (this.regal[i].getName().equals(name)) {
                Product product = this.regal[i];
                this.regal[i] = null;
                return product;
            }
        }
        System.out.println("Das Produkt '" + name + "' konnte nicht gekauft werden.");
        return null;
    }
}
