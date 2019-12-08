
/** This interface represents a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions), 
 * y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).
**/

package Ex1;
public class ComplexFunction implements complex_function {
	private function fl;
	private function fr;
	private Operation op;

	public ComplexFunction() {
		// TODO Auto-generated constructor stub
		fl = new Polynom("0");
		fr = new Polynom("0");
		op = Operation.None;
	}
	/**
	 * inits ComplexFunction with the f function on the left,
	 *  while the function on the right set to be "0"
	 * @param f
	 */

	public ComplexFunction(function f) {
		fl = f.copy();
		fr = new Polynom("0");
		op = op.None;
	}
	
	/**
	 * inits Complex function with operation,
	 * f1 the function on the left,
	 * and f2 the function on the right
	 * @param oper
	 * @param f1
	 * @param f2
	 */

	public ComplexFunction(Operation oper, function f1, function f2) {
		switch (oper.toString()){
		case "Plus":
			op = op.Plus;
			break;
		case "Times":
			op = op.Times;
			break;
		case "Divid":
			op = op.Divid;
			break;
		case "Min":
			op = op.Min;
			break;
		case "Max":
			op = op.Max;
			break;
		case "Comp":
			op = op.Comp;
			break;
		default:
			throw new RuntimeException("ERR: you entered iligal Operatin. got: "+oper);	
		}
		fl = f1.copy();
		fr = f2.copy();
	}
	/**
	 * inits Complex function with oper as the operation,
	 * f1 the function on the left,
	 * and f2 the function on the right
	 * @param oper
	 * @param f1
	 * @param f2
	 */

	public ComplexFunction(String oper, function f1, function f2) {
		switch (oper){
		case "plus":
			op = op.Plus;
			break;
		case "mul":
			op = op.Times;
			break;
		case "div":
			op = op.Divid;
			break;
		case "min":
			op = op.Min;
			break;
		case "max":
			op = op.Max;
			break;
		case "comp":
			op = op.Comp;
			break;
		default:
			throw new RuntimeException("ERR: you entered iligal Operatin. got: "+oper);	
		}
		fl = f1.copy();
		fr = f2.copy();
	}

	private  ComplexFunction(function f1, function f2, Operation oper) {
		switch (oper.toString()){
		case "Plus":
			op = op.Plus;
			break;
		case "Times":
			op = op.Times;
			break;
		case "Divid":
			op = op.Divid;
			break;
		case "Min":
			op = op.Min;
			break;
		case "Max":
			op = op.Max;
			break;
		case "Comp":
			op = op.Comp;
			break;
		default:
			throw new RuntimeException("ERR: you entered iligal Operatin. got: "+oper);	
		}
		fl = f1.copy();
		fr = f2.copy();
	}
	/**
	 * calculates f(x) for a given decimal x.
	 * if the operation is Max/Min it will
	 * return the Max/Min value between the functions on the left and
	 *  on the right.
	 *  if the operation is 'none' it will calculates only the function on lef.
	 *  if the method is 'comp' it will calculate f1(f2(x)).
	 *  this method works recursivly. 
	 */
	
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		String oper = op.toString();
		switch(oper)
		{
		case "None":
			return fl.f(x);
		case "Plus":
			return fl.f(x) + fr.f(x);
		case "Times":
			return fl.f(x) * fr.f(x);
		case "Divid":
			if(fr.f(x)==0)
				throw new RuntimeException("You must not divide by 0 !!!");
			return fl.f(x) / fr.f(x);
		case "Min":
			double a = fl.f(x);
			double b = fr.f(x);
			return Math.min(a, b);
		case "Max":
			double c = fl.f(x);
			double d = fr.f(x);
			return Math.max(c, d);
		case "Comp":
			double y = fr.f(x);
			return fl.f(y);
		case "Error":
			throw new RuntimeException("ERR: got operation **Error**");
		}
		return 0;
	}
	/**
	 * inits complexFunction from String
	 */
	public ComplexFunction(String s) {
		try {
			fl = new Polynom(s);
			fr = new Polynom("0");
			op = op.None;
			return;
		} catch (Exception e) {}
		try {
			String str = ""+s.charAt(0)+s.charAt(1);
			switch (str)
			{
			case "Pl":
				op = Operation.Plus;
				s=s.substring(4);
				break;
			case "pl":
				op = Operation.Plus;
				s=s.substring(4);
				break;
			case "Ti":
				op = Operation.Times;
				s=s.substring(5);
				break;
			case "mu":
				op = Operation.Times;
				s=s.substring(3);
				break;
			case "Di":
				op = Operation.Divid;
				s=s.substring(5);
				break;
			case "di":
				op = Operation.Divid;
				s=s.substring(3);
				break;
			case "Ma":
				op = Operation.Max;
				s=s.substring(3);
				break;
			case "ma":
				op = Operation.Max;
				s=s.substring(3);
				break;
			case "Mi":
				op = Operation.Min;
				s=s.substring(3);
				break;
			case "mi":
				op = Operation.Min;
				s=s.substring(3);
				break;
			case "Co":
				op = Operation.Comp;
				s=s.substring(4);
				break;
			case "co":
				op = Operation.Comp;
				s=s.substring(4);
				break;
			case "No":
				op = Operation.None;
				s=s.substring(4);
				break;
			case "no":
				op = Operation.None;
				s=s.substring(4);
				break;
			default:
				throw new RuntimeException("ERR: iligal Operation in input String. unable to build this Complex Function");
			}
			s = s.substring(1, s.length()-1);
			int numOfPsik = appearanceOfChar(s, ',');
			if(numOfPsik==0)
				throw new RuntimeException("ERR: iligal input String. got: "+s);
			int ind = indexBetweenFunction(s);
			if(numOfPsik==1) {
				fl = new Polynom(s.substring(0, ind));
				fr = new Polynom(s.substring(ind+1, s.length()));
			}else {
				try {
					fl = new Polynom(s.substring(0, ind));
				} catch (Exception e) {
					fl = new ComplexFunction(s.substring(0, ind));
				}
				try {
					fr = new Polynom(s.substring(ind+1, s.length()));
				} catch (Exception e) {
					fr = new ComplexFunction(s.substring(ind+1, s.length()));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("ERR: Unable to build this Complex Function. got: "+s);
		}
	}
	/**
	 * inits a function from a String
	 */

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		function f  = new ComplexFunction(s);
		return f;
	}
	/**
	 * returns a copy of the ComplexFunction
	 */

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		function f = new ComplexFunction(this.toString());
		return f;
	}
	/**
	 * adds the f1 function to the ComplexFunction
	 */
	@Override
	public void plus(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0") && op.toString().equals("None")) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fl = new Polynom(f1.toString());
			else
				fl = new ComplexFunction(f1.toString());	
		}
		else if(fr.toString().equals("0") && op.toString().equals("None") ) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Plus;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Plus;
		}
	}
	/**
	 * multiplys the ComplexFunction with the f1 function  
	 */
	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0") && op.toString().equals("None")) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fl = new Polynom(f1.toString());
			else
				fl = new ComplexFunction(f1.toString());	
		}
		else if(fr.toString().equals("0") && op.toString().equals("None") ) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Times;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Times;
		}
	}
	/**
	 * divides the ComplexFunction with the f1 function
	 */
	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0") && op.toString().equals("None")) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fl = new Polynom(f1.toString());
			else
				fl = new ComplexFunction(f1.toString());	
		}
		else if(fr.toString().equals("0") && op.toString().equals("None") ) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Divid;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Divid;
		}
	}
	/**
	 * adds the f1 function to this ComplexFunction 
	 * and sets the operation to 'Max'.
	 */

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0") && op.toString().equals("None")) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fl = new Polynom(f1.toString());
			else
				fl = new ComplexFunction(f1.toString());	
		}
		else if(fr.toString().equals("0") && op.toString().equals("None") ) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Max;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Max;
		}
	}
	/**
	 * adds the f1 function to this ComplexFunction 
	 * and sets the operation to 'Min'.
	 */
	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0") && op.toString().equals("None")) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fl = new Polynom(f1.toString());
			else
				fl = new ComplexFunction(f1.toString());	
		}
		else if(fr.toString().equals("0") && op.toString().equals("None") ) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Min;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Min;
		}
	}
	/**
	 * adds the f1 function to this ComplexFunction 
	 * and sets the operation to 'Comp'.
	 */

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0") && op.toString().equals("None")) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fl = new Polynom(f1.toString());
			else
				fl = new ComplexFunction(f1.toString());	
		}
		else if(fr.toString().equals("0") && op.toString().equals("None") ) {
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Comp;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			if(f1 instanceof Polynom_able || f1 instanceof Monom)
				fr = new Polynom(f1.toString());
			else
				fr = new ComplexFunction(f1.toString());
			op = Operation.Comp;
		}
	}
	/**
	 * returns the function on the left
	 */

	@Override
	public function left() {
		// TODO Auto-generated method stub
		if(fl instanceof Polynom_able)
			return new Polynom(fl.toString());
		else
			return new ComplexFunction(fl.toString());
	}
	/**
	 * returns the function on the right
	 */
	@Override
	public function right() {
		// TODO Auto-generated method stub
		if(fr instanceof Polynom_able)
			return new Polynom(fr.toString());
		else
			return new ComplexFunction(fr.toString());
	}
	/**
	 * returns the operation of this complex function
	 */
	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		String oper = op.toString();
		switch(oper)
		{
		case "None":
			return Operation.None;
		case "Plus":
			return Operation.Plus;
		case "Times":
			return Operation.Times;
		case "Divid":
			return Operation.Divid;
		case "Min":
			return Operation.Min;
		case "Max":
			return Operation.Max;
		case "Comp":
			return Operation.Comp;
		case "Error":
			return Operation.Error;
		}
		return Operation.None;
	}

	private int appearanceOfChar(String s, char a) {
		int sum = 0;
		for(int i = 0; i<s.length(); i++)
			if(s.charAt(i)==a)
				sum++;
		return sum;
	}
	/**
	 * returns this Complex Function as a string.
	 */

	public String toString() {
		return op.toString()+"("+fl.toString()+","+fr.toString()+")";
	}
	/**
	 * checks if this complex function is logically equal to the given object.
	 * Note: this function is not 100% accurate. because if you want to check if
	 * two complex function (f1,f2) are equal you must test if f1(x)=f2(x) for every 
	 * given x. nd that is of curse not possible. in my implementation i did 2 tests
	 * to check if f1 and f2 are equal. in one of the tests i am checking 2000 values of x if 
	 * f1(x) = f2(x), and if it retruns false even for one value, the method returns False.
	 */
	public boolean equals(Object obj) {
		boolean b = false;
		boolean c = false;
		if(obj instanceof ComplexFunction) {
			ComplexFunction f = (ComplexFunction)obj;
			b = fl.equals(f.fl) && fr.equals(f.fr) && op.toString().equals(f.op.toString());
			c = true;
			for(int i =-1000; i<1000; i++)
				try {
				if(this.f(i)!=f.f(i))
					c = false;
				} catch (Exception e) {}
		}
		return b || c;
	}

	private int indexBetweenFunction(String s) {
		char first = s.charAt(0);
		int indAns = 0;
		int numOfOpen = 0;
		int numOfClose = 0;
		if(first =='M' || first == 'P' || first == 'T' || first == 'D'|| first == 'E' || first =='N' || first == 'C' ) {
			while(s.charAt(0)!='(') {
				s = s.substring(1);
				indAns++;
			}
			numOfOpen = 1;
			s = s.substring(1);
			indAns++;
			while(numOfOpen!=numOfClose) {
				if(s.charAt(0)=='(')
					numOfOpen++;
				if(s.charAt(0)==')')
					numOfClose++;
				s = s.substring(1);
				indAns++;
			}
		}
		else {
			while(s.charAt(0)!=',') {
				s = s.substring(1);
				indAns++;
			}
		}
		return indAns;
	}

}
