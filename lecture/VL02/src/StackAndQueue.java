import java.util.*;

public class StackAndQueue<E>{
    private Stack<E> stack;
    private Queue<E> queue;
    public StackAndQueue() {
        stack = new Stack<>();
        queue = new LinkedList<>();
    }

    public void addElement(E element) {
        stack.push(element);
        queue.add(element);
    }

    public void popElement() {
        this.stack.pop();
        this.queue.poll();
    }

    public void reverse() {
        Stack<E> tmpStack = new Stack<>();
        Queue<E> tmpQueue = new LinkedList<>();
        while (!this.stack.isEmpty()) {
            tmpQueue.add(this.stack.pop());
        }
        while (!tmpQueue.isEmpty()) {
            this.stack.add(tmpQueue.poll());
        }
        while (!this.queue.isEmpty()) {
            tmpStack.add(this.queue.poll());
        }
        while (!tmpStack.isEmpty()) {
            this.queue.add(tmpStack.pop());
        }
    }

    @Override
    public String toString() {
        String toString;
        String stackToString;
        String queueToString;
        stackToString = "stack: [";
        queueToString = "queue: [";
        for (E element : this.stack) {
            stackToString += element.toString();
            stackToString += ", ";
        }
        for (E element : this.queue) {
            queueToString += element.toString();
            queueToString += ", ";
        }
        stackToString += "]";
        queueToString += "]";
        toString = stackToString + "\n" + queueToString;
        return toString;
    }

    public static void main(String[] args) {
        Druckauftrag druckauftrag1 = new Druckauftrag("A", 1);
        Druckauftrag druckauftrag2 = new Druckauftrag("B", 2);
        Druckauftrag druckauftrag3 = new Druckauftrag("C", 3);
        Druckauftrag druckauftrag4 = new Druckauftrag("D", 3);
        StackAndQueue<Druckauftrag> stackAndQueue = new StackAndQueue<>();
        System.out.println("addElement():");
        stackAndQueue.addElement(druckauftrag1);
        System.out.println(stackAndQueue.toString());
        System.out.println("addElement():");
        stackAndQueue.addElement(druckauftrag2);
        System.out.println(stackAndQueue.toString());
        System.out.println("addElement():");
        stackAndQueue.addElement(druckauftrag3);
        System.out.println(stackAndQueue.toString());
        System.out.println("reverse():");
        stackAndQueue.reverse();
        System.out.println(stackAndQueue.toString());
        System.out.println("reverse():");
        stackAndQueue.reverse();
        System.out.println(stackAndQueue.toString());
        System.out.println("popElement():");
        stackAndQueue.popElement();
        System.out.println(stackAndQueue.toString());
        System.out.println("reverse():");
        stackAndQueue.reverse();
        System.out.println(stackAndQueue.toString());
        System.out.println("addElement():");
        stackAndQueue.addElement(druckauftrag4);
        System.out.println(stackAndQueue.toString());
        System.out.println("popElement():");
        stackAndQueue.popElement();
        System.out.println(stackAndQueue.toString());
    }
}
