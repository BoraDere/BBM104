public class Structure<T> {
    private Node<T> head;
    private int size;

    private static class Node<T> {
        private T data;
        private Node<T> previous;
        private Node<T> next;

        /**
         * @param data Node element constructor.
         */
        public Node(T data) {
            this.data = data;
        }
    }

    /**
     * Structure constructor.
     */
    public Structure() {
        head = null;
        size = 0;
    }

    /**
     * To simulate stack.push().
     * @param element is the Node being pushed.
     */
    public void push(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) { // If stack is empty.
            head = newNode;
            newNode.next = newNode;
            newNode.previous = newNode;
        }
        else {
            newNode.next = head;
            newNode.previous = head.previous;
            head.previous.next = newNode;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * To simulate queue.enqueue().
     * @param element is the Node enqueued.
     */
    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) { // If queue is empty.
            head = newNode;
            newNode.next = newNode;
            newNode.previous = newNode;
        }
        else {
            newNode.next = head;
            newNode.previous = head.previous;
            head.previous.next = newNode;
            head.previous = newNode;
        }
        size++;
    }

    /**
     * Simple .isEmpty() method.
     * @return is if size is equal to 0.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Simple .size() method.
     * @return is the size.
     */
    public int size() {
        return size;
    }

    /**
     * Simple .get() method.
     * @param index is the index of the element to be got.
     * @return is the element at the given index.
     */
    public T get(int index) {
        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    /**
     * Simple .remove() method.
     * @param index is the index of the element to be removed.
     */
    public void remove(int index) {
        if (size == 1) {
            head = null;
        }
        else {
            Node<T> current = head;

            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            current.previous.next = current.next;
            current.next.previous = current.previous;

            if (current == head) {
                head = current.next;
            }
        }
        size--;
    }

    /**
     * To simulate queue.dequeue().
     * @return is the dequeued element.
     */
    public T dequeue() {
        T element = head.data;

        if (head.next == head) {
            head = null;
        }
        else {
            head.previous.next = head.next;
            head.next.previous = head.previous;
            head = head.next;
        }
        size--;
        return element;
    }
}
