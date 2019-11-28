package Ex1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	private ArrayList<Monom> bank;

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		bank = new ArrayList<Monom>();	;
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		s = s.replaceAll("\\s","");
		String Notgood = s;
		try {
			bank = new ArrayList<Monom>();
			String cut = "";
			while(s.length()!=0) {
				while(s.length()!=0 && (s.charAt(0)!='-' && s.charAt(0)!='+') || cut.length()==0){
					cut+= s.charAt(0);
					s=s.substring(1);
				}
				bank.add(new Monom(cut));
				cut="";
			}
			bank.sort(new Monom_Comperator());
		}catch(Exception e) {
			throw new RuntimeException("ERR unable to bild this Polynom: "+Notgood);
		}
	}
	@Override
	/**
	 * returns the value of this Polynom at x
	 */
	public double f(double x) {
		double sum = 0;
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			sum += m.f(x);
		}
		return sum;
	}

	@Override
	/**
	 * adds Polynom_able p1 to this Polynom
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			this.add(m);
		}

	}

	@Override
	/**
	 * adds Monom m1 to this Polynom
	 */
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			if(m.get_power()==m1.get_power()) {
				m.add(m1);
				for(int i = 0; i<bank.size(); i++)
					if(bank.get(i).get_coefficient()==0) {
						bank.remove(i);
						return;
					}
				return;
			}
		}
		if(m1.get_coefficient()!=0) {
			bank.add(new Monom(m1));
			bank.sort(new Monom_Comperator());
		}
	}

	@Override
	/**
	 * substructs Polynom_able p1 from this Polynom
	 */
	public void substract(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			this.substract(m);
		}
		for(int i =0; i<bank.size(); i++)
			if(bank.get(i).get_coefficient()==0) {
				bank.remove(i);
			}


	}
	/**
	 * substructs Monom m1 from this Polynom
	 * @param m1
	 */
	public void substract(Monom m1) {
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			if(m.get_power()==m1.get_power()) {
				Monom mnew = new Monom(m1.get_coefficient()*-1, m1.get_power());
				m.add(mnew);
				return;
			}	
		}
		Monom mnew = new Monom(m1.get_coefficient()*-1, m1.get_power());
		bank.add(mnew);
		bank.sort(new Monom_Comperator());
	}


	@Override
	/**
	 * multiplies this Polynom by Polynom_able p1
	 */
	public void multiply(Polynom_able p1) {
		Polynom ans = new Polynom();
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Polynom_able cop = this.copy();
			Monom m = it.next();
			cop.multiply(m);
			ans.add(cop);
		}
		this.bank = ans.bank;

	}


	@Override
	/**
	 * returns true if Polynom_able p1 is logically equal to this Polynom
	 */
	public boolean equals(Polynom_able p1) {
		if(p1.toString()=="0" && this.toString()=="0")
			return true;
		boolean b = false;
		Iterator<Monom> p1it = p1.iteretor();
		while(p1it.hasNext()) {
			Monom m = p1it.next();
			Iterator<Monom> thisp = this.iteretor();
			while(thisp.hasNext()) {
				Monom thism = thisp.next();
				if(thism.equals(m))
					b = true;
			}
			if(!b)
				return false;
			b = false;	
		}
		return true;
	}

	public boolean equals(Object obj) {
		if(obj instanceof Polynom_able) {
			Polynom_able p1 = (Polynom_able)obj;
			if(p1.toString()=="0" && this.toString()=="0")
				return true;
			boolean b = false;
			Iterator<Monom> p1it = p1.iteretor();
			while(p1it.hasNext()) {
				Monom m = p1it.next();
				Iterator<Monom> thisp = this.iteretor();
				while(thisp.hasNext()) {
					Monom thism = thisp.next();
					if(thism.equals(m))
						b = true;
				}
				if(!b)
					return false;
				b = false;	
			}
			return true;
		}
		return false;	
	}

	/**
	 * returns true if this Polynom equals 0
	 */
	@Override
	public boolean isZero() {
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			if(!m.isZero())
				return false;
		}
		return true;
	}

	@Override
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, else should throws runtimeException 
	 * computes f(x') such that:
	 * 	(i) x0<=x'<=x1 && 
	 * 	(ii) |f(x')|<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * @return an approximated value (root) for this (cont.) function 
	 */
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		if(this.f(x0)*this.f(x1)>0) throw new RuntimeException("ERR: got f(x0)*f(x1) > 0");
		boolean b = true;
		while(b) {
			double currentx = (x0 + x1)/2;
			if(this.f(currentx)<= eps && this.f(currentx)>= -1*eps )
				return currentx;
			else if(this.f(currentx)> 0)
				x1 = currentx;
			else if(this.f(currentx)<0)
				x0 = currentx;
		}
		return 0;
	}

	@Override
	/**
	 * returns a copy of this Polynom
	 */
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		Polynom_able p = new Polynom(this.toString());
		return p;
	}

	@Override
	/**
	 * returns a derivative of this Polynom
	 */
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		Polynom p = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			p.add(m.derivative());
		}
		return p;
	}

	@Override
	/**
	 * Compute a Riman's integral from x0 to x1 in eps steps. 
	 * @param x0 starting pooint
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the approximated area above X-axis below this function bounded in the range of [x0,x1]
	 */
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		if(eps>0.0001)
			throw new RuntimeException("EPSILON shoud be less than 0.0001, got "+eps);
		double riman = 0;
		double infimum = 0;
		double i = x0 + eps;
		while(i<x1) {
			infimum = Math.min(this.f(i), this.f(i-eps));
			riman += infimum*eps;
			i += eps;
		}
		return riman;
	}

	@Override
	/**
	 * retruns an Iterator<Monom> to this Polynom
	 */
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it = bank.iterator();
		return it;
	}
	@Override
	/**
	 * multiplies this Polynom by Monom m1
	 */
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			m.multiply(m1);
		}

	}
	/**
	 * returns this Polynom as a String
	 */
	public String toString() {
		if(bank.isEmpty())
			return "0";
		if(bank.size()==1 && bank.get(0).get_coefficient()==0)
			return "0";
		String ans="";
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			if(m.get_coefficient()> 0 && ans.length()!=0)
				ans += "+"+m.toString();
			else
				ans += ""+ m.toString();
		}
		return ans;
	}
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		function f = new Polynom(s);
		return f;
	}

}
