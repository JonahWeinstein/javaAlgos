package src.fundamentals;

public class Rational {
    int num;
    int den;

        public Rational(int N,int D)
        { 
            //make sure numerator and denominator are in lowest terms
            int gcd=gcd(N, D);
            num=N/gcd;
            den=D/gcd;

        }

        public static int gcd(int p, int q) 
        {
            //greatest common divisor function
            if (q == 0) return p;
            else return gcd(q, p % q);
        }

        public boolean isEqual(Rational b)
        {
            if(b==null) return false;
            int f=gcd(this.num, this.den);
            int g=gcd(b.num, b.den);
            return (b.num/g == this.num/f)&&(b.den/g==this.den/f);
        }

        public Rational plus(Rational b)
        {

            // add cross-product terms for numerator
            Rational s = new Rational(this.num  * b.den
                                    + b.num  * this.den,
                                    this.den * b.den );

            return s;  
        }
        public Rational minus(Rational b)
        {

            // subtract cross-product terms for numerator
            Rational s = new Rational((this.num ) * (b.den)
                                    - (b.num ) * (this.den ),
                                    this.den * (b.den ));

            return s;  
        }

        public Rational multiply(Rational b)
        {

            
            Rational s = new Rational((this.num) * (b.num),
                                    this.den * (b.den));

            return s;  
        }
        public Rational divide(Rational b)
        {
            if(b.num==0){
                System.out.println("Error, cannot divide by 0");
                return b;
            }

            
            Rational s = new Rational((this.num) * (b.den),
                                    this.den * (b.num));

            return s;  
        }


        public String toString() 
        {
            return num+"/"+den;
        }


    


    public static void main(String[] args)
    {
        Rational a=new Rational(5,2);
        Rational b= new Rational(7,4);
        
        System.out.println(gcd(14,77));
        System.out.println(a.isEqual(b));
        System.out.println(a.plus(b));
        System.out.println(a.multiply(b));
        System.out.println(a.divide(b));
       

    }
    
}
