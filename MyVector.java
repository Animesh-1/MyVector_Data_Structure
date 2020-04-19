import java.util.Arrays;

public class MyVector<E> extends MyAbstractList {
    private E[] array;
    private int capacity;
    private int capacityIncrement;

    /**
     * default constructor initializes myvector to 10 size array
     */
    public MyVector() {
        array = (E[]) new Object[10];
        size = 0;
        this.capacity = 10;
        capacityIncrement = 5;
    }

    /**
     * constructor to initialize it with given capacity.
     * @param capacity
     */
    public MyVector(int capacity) {
        array = (E[]) new Object[capacity];
        size = 0;
        this.capacity = capacity;
        capacityIncrement = 5;
    }


    /**
     * constructor to initilize it with given capacity and increment capacity
     * @param capacity
     * @param capacityIncrement
     */
    public MyVector(int capacity, int capacityIncrement) {
        array = (E[]) new Object[capacity];
        this.capacity = capacity;
        size = 0;
        this.capacityIncrement = capacityIncrement;
        capacityIncrement = 5;
    }

    /**
     * returns the capacity
     * @return
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * returns the increment capacity
     * @return
     */
    public int getIncrement() {
        return capacityIncrement;
    }

    /**
     * adds a data entry to the list and returns true, auto-scales the array if size==capacity
     * @param data
     * @return
     */
    @Override
    public boolean add(Object data) {
        if(size == capacity){
            array = Arrays.copyOf(array, capacity+capacityIncrement);
            capacity = capacity+capacityIncrement;
        }
        array[size] = (E)data;
        size++;
        return true;
    }

    /**
     * adds a data entry to the list at given index and returns true. throws error if indexoutofbounds
     * @param index - index at which the specified element is to be inserted
     * @param data - element to be inserted
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public boolean add(int index, Object data) throws IndexOutOfBoundsException {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if(size == capacity){
            array = Arrays.copyOf(array, capacity+capacityIncrement);
            capacity = capacity+capacityIncrement;
        }
        E to_add = (E)data;
        E temp;
        for (int i = index; i < size; i++) {
            temp = array[i];
            array[i] = to_add;
            to_add = temp;
        }
        return true;
    }

    /**
     * clears the list
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        this.size = 0;
    }

    /**
     * converts the list to a string
     * @return
     */
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append('[');
        for (int i = 0; i < size; i++) {
            buffer.append(array[i]+", ");
        }
        buffer.deleteCharAt(buffer.length()-2);
        buffer.deleteCharAt(buffer.length()-1);
        buffer.append(']');
        return buffer.toString();
    }

    /**
     * returns the element at index
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return array[index];
    }

    /**
     * removes an element and returns it. changes the location of all other elements
     * @param index - index of the element to be removed
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        E removed_Object = array[index];
        array[index] = null;
        E temp;
        E to_add = null;
        for (int i = size; i >= index; i--) {
            temp = array[i];
            array[i] = (E) to_add;
            to_add = temp;
        }
        size--;
        return (E)removed_Object;
    }

    /**
     * trim the capacity to the size
     */
    @Override
    public void trimToSize() {
        array = Arrays.copyOf(array, size);
        capacity = size;
    }
}
