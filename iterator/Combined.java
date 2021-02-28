package iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

public class Combined<E> implements Iterable<E> {
	Iterable<E> first;
	Iterable<E> second;
	Iterator<E>  first_it;
	Iterator<E>  sec_it;
	int flag = 0;// if flag = 0 , access to first, else second

	public Combined(Iterable<E> first, Iterable<E> second) {
		this.first = first;
		this.second = second;
		first_it=first.iterator();
		sec_it=second.iterator();
	}

	public class itreratorClass<E> implements Iterator<E> {

		@Override
		public boolean hasNext() {
			if (first_it.hasNext() || sec_it.hasNext())
				return true;
			return false;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			while (true) {
				if (flag == 0) {// first is the source array
					flag = 1;
					if (first_it.hasNext()) {
						return (E) first_it.next();
					}
				}
				if (flag == 1) {// second is the source array
					flag = 0;
					if (sec_it.hasNext())
						return (E) sec_it.next();
				}
			} // while
		}// next
	}// inner class

	@Override
	public Iterator<E> iterator() {
		return new itreratorClass();
	}

	private static void main(String[] args) {
		List<String> list = Arrays.asList("one", "two", "three");
		Set<String> set = new TreeSet<>();
		set.addAll(Arrays.asList("B", "A", "D", "C", "E"));
		Combined<String> c = new Combined<>(set, list);
	for (String s : c) 
			System.out.print(s + " ");
	}


}
