package Airbnb;

import java.util.*;

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

public class Vector2DIterator implements Iterator<Integer> {

    private List<List<Integer>> vector;
    private int row;
    private int col;
    private boolean canBeRemoved;

    public Vector2DIterator(List<List<Integer>> vec2d) {
        this.vector = vec2d;
        for (int i = 0; i < vec2d.size(); i++) {
            if (vec2d.get(i) != null && vec2d.get(i).size() > 0) {
                this.row = i;
                this.col = 0;
                break;
            }
        }
        this.canBeRemoved = false;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            canBeRemoved = true;
            Integer cur = vector.get(row).get(col);
            if (col < vector.get(row).size() - 1) {
                col++;
            } else {
                boolean foundNext = false;
                for (int i = row + 1; i < vector.size(); i++) {
                    if (vector.get(i) != null && vector.get(i).size() > 0) {
                        row = i;
                        col = 0;
                        foundNext = true;
                        break;
                    }
                }
                if (!foundNext) {
                    row = vector.size();
                }
            }
            return cur;
        } else {
            throw new NoSuchElementException();
            // or return null; //?
        }
    }

    @Override
    public boolean hasNext() {
        if (vector == null || vector.size() == 0 || row >= vector.size()) {
            return false;
        }
        List<Integer> list = vector.get(row);
        if (col < list.size()) {
            return true;
        }

        return false;
    }

    /** copied from java.util.Iterator
     *
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @implSpec
     * The default implementation throws an instance of
     * {@link UnsupportedOperationException} and performs no other action.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *         operation is not supported by this iterator
     *
     * @throws IllegalStateException if the {@code next} method has not
     *         yet been called, or the {@code remove} method has already
     *         been called after the last call to the {@code next}
     *         method
     */
    @Override
    public void remove() {
        if (!canBeRemoved) {
            throw new IllegalStateException();
            // return; //?
        } else {
            // resets coordinates to last one
            if (col > 0) {
                col--;
                vector.get(row).remove(col);
            } else {
                for (int i = row - 1; i >= 0; i--) {
                    if (vector.get(i) != null && vector.get(i).size() > 0) {
                        vector.get(i).remove(vector.get(i).size() - 1);
                    }
                }
            }
            canBeRemoved = false;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>(); list1.add(1); list1.add(2);
        List<Integer> list2 = new ArrayList<>(); list2.add(3);
        List<Integer> list3 = new ArrayList<>(); list3.add(4); list3.add(5);
        input.add(list1); input.add(list2); input.add(list3);

        Vector2DIterator vector2DIterator = new Vector2DIterator(input);
        while (vector2DIterator.hasNext()) {
            System.out.print(vector2DIterator.next() + " ");
        }
        System.out.println();

        vector2DIterator = new Vector2DIterator(input);
        while (vector2DIterator.hasNext()) {
            System.out.print(vector2DIterator.next() + " ");
            vector2DIterator.remove();
        }
        System.out.println();

        List<List<Integer>> input2 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        List<Integer> list5 = new ArrayList<>(); list5.add(3);
        input2.add(list4); input2.add(list5);
        vector2DIterator = new Vector2DIterator(input2);
        System.out.println(input2);
        while (vector2DIterator.hasNext()) {
            System.out.print(vector2DIterator.next() + " ");
        }
    }
}
