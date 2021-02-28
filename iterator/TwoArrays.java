package iterator;

import java.lang.instrument.ClassDefinition;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoArrays implements Iterable<Integer> {
	int[] a1, a2;

	public TwoArrays(int[] a1, int[] a2) {// constructor
		this.a1 = a1;
		this.a2 = a2;
	}

	public class itreratorClass<E> implements Iterator<Integer> {
		int cnt1 = 0, cnt2 = 0;
		int flag = 0;// if flag = 0 , access to a1, else a2

		@Override
		public boolean hasNext() {
			if (cnt1 >= a1.length && cnt2 >= a2.length)
				return false;
			return true;
		}

		@Override
		public Integer next() {
			if (!hasNext())
				throw new NoSuchElementException();
			while(true) {
			if (flag == 0) {// a1 is the source array
				flag = 1;
				if (cnt1 < a1.length)
					return a1[cnt1++];
			}
			if (flag == 1) {// a2 is the source array
				flag = 0;
				if (cnt2 < a2.length)
					return a2[cnt2++];
			}
			}//while
		}// next
	}// inner class

	@Override
	public Iterator<Integer> iterator() {
		return new itreratorClass();
	}
	private static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4 };
		int[] a2 = { 100, 101, 102, 103, 104, 105, 106 };
				
		TwoArrays aa = new TwoArrays(a1, a2);
		for (int i : aa) 
			System.out.print(i + " ");
	}
}// outer class



