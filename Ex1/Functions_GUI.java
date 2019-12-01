package Ex1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import java.io.FileNotFoundException;
import org.json.simple.*;
import org.json.simple.parser.ParseException;





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
		if(o instanceof function) {
			function f = (function)o;
			for(int i = 0; i<bank.size(); i++) 
				if(bank.get(i).equals(f))
					return true;	
			return false;
		}
		else
			return false;
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
		File fileName = new File(file);
		BufferedReader br = new BufferedReader(new FileReader(fileName)); 

		String st; 
		while ((st = br.readLine()) != null) {
			try {
				this.add(new Polynom(st));
			}
			catch (Exception e) {
				this.add(new ComplexFunction(st));
			}
		}

	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			for(int i = 0; i<bank.size(); i++) {
				writer.write(bank.get(i).toString()+"\n");
			}
			writer.close();
		}
		catch (Exception e) {
			throw new RuntimeException("ERR: coud not save the Cokkection of Functions into a file");
		}

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
		int indexColor = 0;
		StdDraw.setCanvasSize( width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		setGrid(rx, ry);
		StdDraw.setPenRadius(0.005);
		double step = (rx.get_max()-rx.get_min())/resolution;
		Iterator<function> itr = bank.iterator();
		while(itr.hasNext()) {	
			function func = itr.next();
			if(indexColor==Colors.length)
				indexColor = 0;
			StdDraw.setPenColor(Colors[indexColor]);
			indexColor++;
			for(double index = rx.get_min()+step; index<rx.get_max(); index+=step) {
				double x1 = index-step;
				double y1 = func.f(x1);
				double x2 = index;
				double y2 = func.f(x2);
				StdDraw.line(x1, y1, x2, y2);
			}
		}
		StdDraw.save("Functions_GUI.jpg");
	}

	private void setGrid(Range rx, Range ry) {
		for(double i = ry.get_max(); i>=ry.get_min(); i--) {
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.001);
			if(i==0) {
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.003);
			}
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(-0.3, i, (int)i+"");
		}
		for(double i = rx.get_max(); i>=rx.get_min(); i--) {
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.001);
			if(i==0) {
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.003);
			}
			StdDraw.line(i, ry.get_min(), i , ry.get_max());
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(i, -0.35, (int)i+"");
		}

	}

	@Override
//	public void drawFunctions(String json_file) {
//		// TODO Auto-generated method stub
//		org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
//
//		try {
//
//			FileReader fileReader = new FileReader(json_file);
//			JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
//			int width = (int) jsonObject.get("Width");
//			int height = (int) jsonObject.get("Height");
//			int resolution = (int) jsonObject.get("Resolution");
//			JSONArray rx = (JSONArray) jsonObject.get("Range_X");
//			JSONArray ry = (JSONArray) jsonObject.get("Range_Y");
//			Range rxnew;
//			Range rynew;
//			if((double)rx.get(0)<(double)rx.get(1))
//				rxnew = new Range((double)rx.get(0), (double)rx.get(1));
//			else
//				rxnew = new Range((double)rx.get(1), (double)rx.get(0));
//			if((double)ry.get(0)<(double)ry.get(1))
//				rynew = new Range((double)ry.get(0), (double)ry.get(1));
//			else
//				rynew = new Range((double)ry.get(1), (double)ry.get(0));
//			this.drawFunctions(width, height, rxnew, rynew, resolution);
//
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//	}

	public String toString() {
		String ans = "";
		for(int i = 0; i<bank.size(); i++)
			ans += bank.get(i) + " , ";
		ans = ans.substring(0, ans.length()-2);
		return ans;
	}

}
