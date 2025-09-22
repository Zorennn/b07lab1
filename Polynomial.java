public class Polynomial {
	double [] coefficients;
	
	public Polynomial() {
		coefficients = new double[1];
		coefficients[0]=0;
	}
	
	public Polynomial(double[] coefficient) {
		int len = coefficient.length;
		coefficients = new double[len];
		for (int i=0; i < len; i++)
			coefficients[i] = coefficient[i];
	}
	
	public Polynomial add(Polynomial poly) {
		int larger = Math.max(coefficients.length, poly.coefficients.length);
		double [] co = new double[larger];
		for (int i=0; i < poly.coefficients.length; i++) {
			co[i] = poly.coefficients[i];
		}
		for (int i=0; i < coefficients.length; i++) {
			co[i] += coefficients[i];
		}
		return new Polynomial(co);
	}
	
	public double evaluate(double a) {
		double sum = 0.0;
		for (int i=0; i < coefficients.length; i++) {
			sum += coefficients[i] * Math.pow(a, i);
		}
		return sum;
	}
	
	public boolean hasRoot(double b) {
		return evaluate(b)==0;
	}
}
