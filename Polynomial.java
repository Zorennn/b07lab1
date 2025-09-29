import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Polynomial {
	double [] coefficients;
	int [] expo;
	
	public Polynomial() {
		coefficients = new double[0];
		expo = new int[0];
	}
	
	public Polynomial(double[] coefficient, int[] expo) {
		int len = coefficient.length;
		this.coefficients = new double[len];
		this.expo = new int[len];
		for (int i=0; i < len; i++) {
			coefficients[i] = coefficient[i];
			this.expo[i] = expo[i];
		}
	}
	
	
	private void addone(double co, int ex) {
		if (co==0) return;
		for (int i=0; i<this.expo.length; i++) {
			if (ex == this.expo[i]) {
				this.coefficients[i] += co;
				if (this.coefficients[i]==0) {
					double [] newnewco = new double [this.coefficients.length -1];
					int [] newnewexpo = new int [this.coefficients.length -1];
					for (int j=0, k=0; j < this.coefficients.length; j++) {
						if (j != i) {
							newnewco[k]=this.coefficients[j];
							newnewexpo[k]= this.expo[j];
							k++;
						}
					}
					this.coefficients = newnewco;
					this.expo = newnewexpo;
				}
				return;
			}
		}
		double [] newco = new double [this.coefficients.length + 1];
		int [] newexpo = new int [this.coefficients.length + 1];
		for (int i=0; i < this.coefficients.length; i++) {
			newco[i] = this.coefficients[i];
			newexpo[i] = this.expo[i];
		}
		newco[newco.length-1] = co;
		newexpo[newco.length-1] = ex;
		
		this.coefficients=newco;
		this.expo = newexpo;
	
	}
	
	public Polynomial add(Polynomial poly) {
		Polynomial p1 = new Polynomial(this.coefficients, this.expo);
		for (int i=0; i< poly.coefficients.length; i++)
			p1.addone(poly.coefficients[i], poly.expo[i]);
		return p1;
	}
	
	public double evaluate(double a) {
		double sum = 0.0;
		for (int i=0; i < coefficients.length; i++) {
			sum += coefficients[i] * Math.pow(a, expo[i]);
		}
		return sum;
	}
	
	public boolean hasRoot(double b) {
		return evaluate(b)==0;
	}
	
	public Polynomial multiply(Polynomial poly) {
		Polynomial p1 = new Polynomial();
		for (int i=0; i<this.coefficients.length; i++) {
			for (int j=0; j<poly.coefficients.length; j++) {
				p1.addone(this.coefficients[i]*poly.coefficients[j], this.expo[i]+poly.expo[j]);
			}
		}
		return p1;
	}
	
	public Polynomial(File  file) throws IOException {
		coefficients = new double[0];
		expo = new int[0];
		BufferedReader input = null;
        try
        {
            input = new BufferedReader(new FileReader(file));
            String line = input.readLine();
            input.close();
            
            if (line!=null) {
            	String org = line.trim();
            	String[] one = org.split("(?=[+-])");
            	
            	for (int i=0; i<one.length; i++) {
            		if (!one[i].contains("x")) {
            			addone(Double.parseDouble(one[i]), 0);
            		}
            		else {
            			double co;
            			int ex;
                    	String[] each = one[i].split("x", 2);
                    	if (each[0].length()==0) {
                    		if (each[0].equals("+")) {
                    			co = 1;
                    		}else if (each[0].equals("-")) {
                    			co = -1;
                    		}else {
                    			co = Double.parseDouble(each[0]);
                    		}
                    		}
                    	else {
                    		co = Double.parseDouble(each[0]);
                    	}
                    	
                    	if (each[1].length()>0) {
                    		ex = Integer.parseInt(each[1]);
                    	}else {
                    		ex=1;
                    	}
                    	
                    	addone(co,ex);
                    	}
                    	}
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
	}
	
	public void saveToFile(String name) throws IOException{
		BufferedWriter output = new BufferedWriter(new FileWriter(name));
		
		if (coefficients.length == 0) {output.write("0");}
		else {
			StringBuilder str = new StringBuilder();
			for (int i=0; i<coefficients.length; i++) {
				if (i>0) {
					if (coefficients[i]>0) {
						str.append("+");
					}
				}
				if (coefficients[i]!=0) {str.append(coefficients[i]);}
				
				if (expo[i]!=0) {
					str.append("x").append(expo[i]);
				}
			}
			output.write(str.toString());
		}
		
		output.close();
	}
}
