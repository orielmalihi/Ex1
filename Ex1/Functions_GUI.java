package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {
	ArrayList<function> bank = new ArrayList<function>();

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return bank.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return bank.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return bank.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		Iterator<function> itr = bank.iterator();
		return itr;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return bank.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return bank.toArray(a);
	}
	
	public function get(int index) {
		return bank.get(index);
	}

	@Override
	public boolean add(function e) {
		// TODO Auto-generated method stub
		if(bank.contains(e))
			return false;
		else {
			bank.add(e);
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return bank.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return bank.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return bank.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return bank.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return bank.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		bank.clear();
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}

}
