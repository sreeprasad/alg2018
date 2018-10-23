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
        this.row = 0;
        this.col = 0;
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
                row++;
                col = 0;
            }
            return cur;
        } else {
            throw new NoSuchElementException();
            // or return null; //?
        }
    }

    @Override
    public boolean hasNext() {
        if (vector == null || vector.size() == 0) {
            return false;
        }
        return row < vector.size() && col < vector.get(row).size();
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
                vector.get(row - 1).remove(vector.get(row - 1).size() - 1);
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
    }
}
