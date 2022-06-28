package fundamentals;

public class ClosestPair1D {
    //should be linearithmic not quadratic

    static void ClosestPair(double[] args){
        double[] a=args;
        if(args.length<2)
        throw new ArrayIndexOutOfBoundsException("argument must have at least 2 values");
        double leastDiff;
        leastDiff=Math.abs(a[1]-a[0]);
        double[] output={a[0], a[1]};
        for(int i=1; i<a.length;i++){
            for(int j=i+1; j<a.length;j++){
                if(Math.abs(a[i]-a[j])<leastDiff){
                    leastDiff=Math.abs(a[i]-a[j]);
                    output[0]=a[i];
                    output[1]=a[j];
                }
            }
        }
        System.out.println(output[0]+" "+output[1]);
    }
    public static void main(String[] args){
        double[] test={30,9,-3,-5,4,7,6,5,8,0,-1,6.77};
        ClosestPair(test);
    }
}
