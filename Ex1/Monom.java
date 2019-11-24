
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		if(ot._coefficient==0) {
			this._coefficient=0;
			this._power=0;
		}
		else {
			this._coefficient=ot._coefficient;
			this._power = ot._power;
		}
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative Monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	/**
	 * this method converts String to Monom. 
	 * the String must be a legall Monom.
	 * @param s
	 */
	public Monom(String s) {
		try {
			if(s.charAt(0)=='+')
				s = s.substring(1);
			String coef = "";
			String pow = "";
			int  t = s.indexOf('x');
			if(t==-1) {
				this._coefficient = Double.parseDouble(s);
				this._power = 0;

			}
			else if(t==0) {
				this._coefficient=1;
				if(s.length()>=2 && s.charAt(1)=='^') 
					pow=s.substring(2);
				else 
					pow=s.substring(1);
				if(pow.length()==0)
					this._power = 1;
				else if(Integer.parseInt(pow)<0)
					throw new RuntimeException("ERR the power of Monom should not be negative, got: "+pow);
				else
					this._power = Integer.parseInt(pow);


			}
			else if(t==1 && s.charAt(0)=='-') {
				this._coefficient = -1;
				if(s.length()>=3 && s.charAt(2)=='^')
					pow=s.substring(3);
				else
					pow=s.substring(2);
				if(pow.length()==0)
					this._power = 1;
				else if(Integer.parseInt(pow)<0)
					throw new RuntimeException("ERR the power of Monom should not be negative, got: "+pow);
				else
					this._power = Integer.parseInt(pow);	
			}
			else {
				coef = s.substring(0, t);
				this._coefficient = Double.parseDouble(coef);
				pow = s.substring(t+1);
				if(pow.length()>=1 && pow.charAt(0)=='^')
					pow = pow.substring(1);
				if(pow.length()==0)
					this._power = 1;
				else if(Integer.parseInt(pow)<0)
					throw new RuntimeException("ERR the power of Monom should not be negative, got: "+pow);
				else
					this._power = Integer.parseInt(pow);
			}
		}catch(Exception e) {
			throw new RuntimeException("ERR unable to build this Monom: "+s);
		}
	}	

	/**
	 * this method adds m to this Monom.
	 * @param m
	 */
	public void add(Monom m) {
		if(m._power != this._power)
			throw new RuntimeException("ERR the power of the two joined monomes shoud be equal, got: "+this._power+" and "+m._power);
		else {
			this._coefficient = this._coefficient + m._coefficient;
		}
	}
	/**
	 * this method Multiply this Monom by d
	 * @param d
	 */

	public void multiply(Monom d) {
		if(d._coefficient==0.0 || this._coefficient==0.0) {
			this._coefficient=0;
			this._power=0;
		}
		else {
			this._coefficient = this._coefficient*d._coefficient;
			this._power = this._power + d._power;
		}

	}
	/**
	 * this method returns a String of this Monom
	 */

	public String toString() {
		String ans = "";
		if(this._power==0)
			return ans+this._coefficient;
		else if(this._power==1)
			return ans+this._coefficient+"x";
		else
			return ans+this._coefficient+"x^"+this._power;
	}
	// you may (always) add other methods.
	/**
	 * this method returns true if Monom m is logically equal to this Monom.
	 * @param m
	 * @return
	 */
	public boolean equals(Object m) {
		Monom m1 = (Monom)m;
		double subcoef = this.get_coefficient()-m1.get_coefficient();
		if(subcoef<0)
			subcoef *= -1;
		if(subcoef < EPSILON && (this._power == m1._power))
			return true;
		return false;
	}

	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}


}
