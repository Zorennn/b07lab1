import java.io.File;
import java.io.IOException;
public class Driver {
    public static void main(String[] args) throws IOException {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {-6, 2, 5, -1};
        int[] e1 = {0, 1, 3, 4};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {-2, -9};
        int[] e2  = {1, 7};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p1.add(p2);
        s.saveToFile("poly.txt");
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
        	System.out.println("1 is a root of s");
        else 
        	System.out.println("1 is not a root of s");
        Polynomial m = p1.multiply(p2);
        System.out.println("m(0.1) = " + m.evaluate(0.1));
        
        Polynomial mypoly = new Polynomial(new File("poly.txt"));
        mypoly.saveToFile("poly.txt");
        
    }
}
