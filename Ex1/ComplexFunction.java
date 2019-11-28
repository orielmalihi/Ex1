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

	public ComplexFunction(function f1, function f2, Operation oper) {
		fl = f1;
		fr = f2;
		op = oper;
	}

	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		String oper = op.toString();
		switch(oper)
		{
		case "None":
			throw new RuntimeException("ERR: got operation **None**");
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

	public ComplexFunction(String s) {
		try {
			String str = ""+s.charAt(0)+s.charAt(1);
			switch (str)
			{
			case "Pl":
				op = Operation.Plus;
				s=s.substring(4);
				break;
			case "Ti":
				op = Operation.Times;
				s=s.substring(5);
				break;
			case "Di":
				op = Operation.Divid;
				s=s.substring(5);
				break;
			case "Ma":
				op = Operation.Max;
				s=s.substring(3);
				break;
			case "Mi":
				op = Operation.Min;
				s=s.substring(3);
				break;
			case "Co":
				op = Operation.Comp;
				s=s.substring(4);
				break;
			case "No":
				op = Operation.None;
				s=s.substring(4);
				break;
			case "Er":
				op = Operation.Error;
				s=s.substring(5);
				break;
				default:
					throw new RuntimeException("ERR: iligal Operation in input String. unable to build this Complex Function");
			}
			s = s.substring(1, s.length()-1);
			int numOfPsik = appearanceOfChar(s, ',');
			if(numOfPsik==0)
				throw new RuntimeException("ERR: iligal input String. got: "+s);
			for(int i = s.length()-1; i>=0; i--) {
				if(s.charAt(i)==',') {
					if(numOfPsik>1) {
						fl = new ComplexFunction(s.substring(0, i));
						fr = new Polynom(s.substring(i+1, s.length()));
						return;
					}
					else if(numOfPsik==1) {
						fl = new Polynom(s.substring(0, i));
						fr = new Polynom(s.substring(i+1, s.length()));
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("ERR: Unable to build this Complex Function. got: "+s);
		}
	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		function f  = new ComplexFunction(s);
		return f;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		function f = new ComplexFunction(this.toString());
		return f;
	}

	@Override
	public void plus(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0")) {
			fl = new Polynom(f1.toString());
			op = Operation.None;
		}
		else if(fr.toString().equals("0")) {
			fr = new Polynom(f1.toString());
			op = Operation.Plus;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			fr = new Polynom(f1.toString());
			op = Operation.Plus;

		}

	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0")) {
			fl = new Polynom(f1.toString());
			op = Operation.None;
		}
		else if(fr.toString().equals("0")) {
			fr = new Polynom(f1.toString());
			op = Operation.Times;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			fr = new Polynom(f1.toString());
			op = Operation.Times;

		}
	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0")) {
			fl = new Polynom(f1.toString());
			op = Operation.None;
		}
		else if(fr.toString().equals("0")) {
			fr = new Polynom(f1.toString());
			op = Operation.Divid;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			fr = new Polynom(f1.toString());
			op = Operation.Divid;
		}
	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0")) {
			fl = new Polynom(f1.toString());
			op = Operation.None;
		}
		else if(fr.toString().equals("0")) {
			fr = new Polynom(f1.toString());
			op = Operation.Max;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			fr = new Polynom(f1.toString());
			op = Operation.Max;
		}

	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0")) {
			fl = new Polynom(f1.toString());
			op = Operation.None;
		}
		else if(fr.toString().equals("0")) {
			fr = new Polynom(f1.toString());
			op = Operation.Min;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			fr = new Polynom(f1.toString());
			op = Operation.Min;
		}

	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub
		if(fl.toString().equals("0")) {
			fl = new Polynom(f1.toString());
			op = Operation.None;
		}
		else if(fr.toString().equals("0")) {
			fr = new Polynom(f1.toString());
			op = Operation.Comp;
		}
		else {
			ComplexFunction cof = new ComplexFunction(this.left(), this.right(), this.getOp());
			fl = cof;
			fr = new Polynom(f1.toString());
			op = Operation.Comp;
		}

	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		if(appearanceOfChar(fl.toString(), ',')>=1) {
			function f = new ComplexFunction(fl.toString()); 
			return f;
		}else {
			function f = new Polynom(fl.toString());
			return f;
		}
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		function f = new Polynom(fr.toString());
		return f;
	}

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

	public String toString() {
		return op.toString()+"("+fl.toString()+","+fr.toString()+")";
	}

	public boolean equals(Object obj) {
		if(obj instanceof ComplexFunction) {
			ComplexFunction f = (ComplexFunction)obj;
			return fl.equals(f.fl) && fr.equals(f.fr) && op.toString().equalsIgnoreCase(f.op.toString());
		}
		return false;
	}

}
