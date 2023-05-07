public class Pair<E> {
    // TODO
    // attribute
    private E first;
    private E second;

    // constructor
    public Pair(E first, E second) {
        this.first = first;
        this.second = second;
    }

    public Pair(Pair<E> other) {
        this(other.getFirst(), other.getSecond());
    }

    // getter
    public E getFirst() {
        return first;
    }

    public E getSecond() {
        return second;
    }

    // setter
    public void setFirst(E first) {
        this.first = first;
    }

    public void setSecond(E second) {
        this.second = second;
    }

    // override
    @Override
    public String toString() {
        return "Pair<" + this.first + ", " + this.second + ">";
    }

    public Boolean equals(Pair<E> other) {
        if (other == this) {
            return true;
        }
        if (other.getFirst() == this.first && other.getSecond() == this.second) {
            return true;
        }
        return false;
    }

    // method
    public void swap() {
        E tmp;

        tmp = this.first;
        this.first = this.second;
        this.second = tmp;
    }
}
