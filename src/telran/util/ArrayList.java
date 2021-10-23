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
	// V.R. Looks OK
	@Override
	public boolean add(int index, T element) {
		boolean res = false;
		if (index <= size && index >= 0) {
			if (size + 1 > array.length) {
				allocate();
			}
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = element;
			size++;
			res = true;
		}
		return res;
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
	// V.R. Looks OK
	@Override
	public T remove(int index) {
		T removedValue = null;
		if (isValidIndex(index)) {
			removedValue = array[index];
			int numMoved = size - index - 1;
			System.arraycopy(array, index + 1, array, index, numMoved);
			array[--size] = null;
		}
		return removedValue;
	}

}
