import java.util.*;
class Solution {
    public static int mySqrt(int x) {
        if(x==0)
            return 0;
        else
        {
        double n=x;
        double root;
        int c=0;
        double l = 0.00001;
        while(true)
        {
            c++;
            root=0.5*(n+(x/n));
            if (Math.abs(root - n) < l)
                break;
            n=root;
        }
        return (int)root;
        }
    }
    public static void main(String[] args)
    {
        Scanner S=new Scanner(System.in);
        System.out.println("Enter the number: ");
        int x=S.nextInt();
        int res=mySqrt(x);
        System.out.println(res);
    }
}
