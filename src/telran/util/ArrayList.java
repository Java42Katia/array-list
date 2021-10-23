package telran.util;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size = 0; 
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	@Override
	public void add(T element) {
		if (size == array.length) {
			//size is capacity
			allocate();
		}
		array[size++] = element;
		
		
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
		
	}
	@Override
	public boolean add(int index, T element) {
		//[YG] better to introduce boolean res = false ; update "if" statement so avoid two returns and if/else
		if (index <= size && index >= 0) {
			//[YG] better size == array.length
			if (size + 1 > array.length) {
				allocate();
			}
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = element;
			size++;
			return true;
		} else 
			return false;
	}
	
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T get(int index) {
		
		return isValidIndex(index) ? array[index] : null;
	}

	private boolean isValidIndex(int index) {
		
		return index >= 0 && index < size;
	}
	@Override
	public T remove(int index) {
		T removedValue = isValidIndex(index) ? array[index] : null;
		//[YG] major bug; due to very weak tests the bug is not discovered; do more tests
		int numMoved = size - index - 1;
		//[YG] unneded if
		if (numMoved > 0)
			System.arraycopy(array, index + 1, array, index, numMoved);
		array[--size] = null;
		return removedValue;
	}

}
