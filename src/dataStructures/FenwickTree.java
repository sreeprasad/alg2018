package dataStructures;

public class FenwickTree {

	private int[] sums;

	public FenwickTree(int size) {
		sums = new int[size + 1]; // initialization
	}

	public void update(int index, int delta) {
		while (index < sums.length) {
			sums[index] += delta;
			index += lowbit(index); // update all element after index
		}
	}

	/**
	 * return sum of index, from 0 to index
	 * @param index
	 * @return
	 */
	public int query(int index) {
		int sum = 0;
		while (index > 0) {
			sum += sums[index];
			index -= lowbit(index);
		}
		return sum;
	}

	/**
	 * get the number that lowest bit represents
	 * @param x
	 * @return
	 */
	private int lowbit(int x) {
		return x & (-x);
	}
}
