package me.ggikko.datastructure;

/**
 * Created by ggikko on 2016. 10. 4..
 */

public class SparseArray {

    private List start;
    private int index;

    /**
     * create list & set index
     * @param index
     */
    SparseArray(int index) {
        start = new List();
        this.index = index;
    }

    /**
     * add object
     * @param index
     * @param value
     */
    public void store(int index, Object value) {
        if (index >= 0 && index < this.index) {
            if (value != null) start.store(index, value);
        } else {
            System.out.println("INDEX OUT OF BOUNDS");
        }
    }

    /**
     * fetch object
     * @param index
     * @return
     */
    public Object fetch(int index) {
        if (index >= 0 && index < this.index) return start.fetch(index);
        else {
            System.out.println("INDEX OUT OF BOUNDS");
            return null;
        }
    }

    public int elementCount() {
        return start.elementCount();
    }

    /**
     * List
     */
    class List {
        private int index;
        private Object value;
        private List nextindex;

        public List(int index) {
            this.index = index;
            nextindex = null;
            value = null;
        }

        public List() {
            index = -1;
            value = null;
            nextindex = null;
        }

        public void store(int index, Object value) {
            List current = this;
            List previous = null;

            List node = new List(index);
            node.value = value;

            while (current != null && current.index < index) {
                previous = current;
                current = current.nextindex;
            }

            if (current == null) {
                previous.nextindex = node;
            } else {
                if (current.index == index) {
                    System.out.println("DUPLICATE INDEX");
                    return;
                }
                previous.nextindex = node;
                node.nextindex = current;
            }
            return;
        }

        public Object fetch(int index) {
            List current = this;
            Object value = null;
            while (current != null && current.index != index) {
                current = current.nextindex;
            }
            if (current != null) {
                value = current.value;
            } else {
                value = null;
            }
            return value;
        }

        public int elementCount() {
            int elementCount = 0;
            for (List current = this.nextindex; (current != null); current = current.nextindex) {
                elementCount++;
            }
            return elementCount;
        }
    }

    public static void main(String[] args) {

        //TODO : android 에서 sparse array는 int형만 받음. 다시 보기.
        Integer[] array = {1, null, 2, 3, null};

        int size = array.length;

        SparseArray sparseArray = new SparseArray(5);
        for (int i = 0; i < size; i++) sparseArray.store(i, array[i]);


        System.out.println("NORMAL ARRAY");
        for (int i = 0; i < size; i++) System.out.print(array[i] + "\t");


        System.out.println("\nSPARSE ARRAY");
        for (int i = 0; i < size; i++) if (sparseArray.fetch(i) != null) System.out.print(sparseArray.fetch(i) + "\t");

        System.out.println("The Size of Sparse Array is " + sparseArray.elementCount());
    }
}
