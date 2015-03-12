/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cake.cutting;

import java.util.Arrays;

/**
 *
 * @author Q
 */
public class Player {
    int size;
    double epsval;
    double[] valpoints;
    double pointval;
    Slice slice;
    boolean hasslice=false;
    public Player (int k, double eps)
    {
        size=k;
        valpoints=new double[k];
        epsval=eps;
        pointval=(1-eps)/k;
        slice=new Slice();
    }
    public void randVals ()
    {
        for (int i=0; i<size; i++)
        {
            valpoints[i]=Math.random();
        }
        Arrays.sort(valpoints);
        
    }
    public double cut (double left, double right, double cutsize)
    {
        double totalval=(right-left)*epsval;

            for (int i=0; i<size; i++)
                if (valpoints[i]<right && valpoints[i]>=left)
                    totalval+=pointval;
   
        double midval=cutsize;
        assert(midval<totalval);
        double tempval=0;
        int i=0;
            for (i=0; i<size; i++)
            {
                if (valpoints[i]<right && valpoints[i]>=left)
                {
                    tempval+=pointval;  
                }
                //if the next valpoint's epsilon value would exceed the total, then you exit
         
         
                if ((tempval+(valpoints[i+1]-left)*epsval)>midval || tempval>midval)
                {
                    break;
                }
            }
            
        double x;
        if (tempval>midval)
        {
            x=valpoints[i];
        }
        else
        {
            x=(midval-tempval)/epsval+left;
        }
        return x;    
           
    }
    public double cut (double left, double right, double leftsize, double rightsize)
    {
            double totalval=(right-left)*epsval;

            for (int i=0; i<size; i++)
                if (valpoints[i]<right && valpoints[i]>=left)
                    totalval+=pointval;
   
        double midval=totalval*leftsize/(leftsize+rightsize);
       
        double tempval=0;
        int i=0;
            for (i=0; i<size; i++)
            {
                if (valpoints[i]<right && valpoints[i]>=left)
                {
                    tempval+=pointval;  
                }
                //if the next valpoint's epsilon value would exceed the total, then you exit
         
         
                if ((tempval+(valpoints[i+1]-left)*epsval)>midval || tempval>midval)
                {
                    break;
                }
            }
            
        double x;
        if (tempval>midval)
        {
            x=valpoints[i];
        }
        else
        {
            x=(midval-tempval)/epsval+left;
        }
        return x;    
        
    }
    public double cut (double left, double right)
    {
        double totalval=(right-left)*epsval;

            for (int i=0; i<size; i++)
                if (valpoints[i]<right && valpoints[i]>=left)
                    totalval+=pointval;
   
        double midval=totalval/2;
       
        double tempval=0;
        int i=0;
            for (i=0; i<size; i++)
            {
                if (valpoints[i]<right && valpoints[i]>=left)
                {
                    tempval+=pointval;  
                }
                //if the next valpoint's epsilon value would exceed the total, then you exit
         
         
                if ((tempval+(valpoints[i+1]-left)*epsval)>midval || tempval>midval)
                {
                    break;
                }
            }
            
        double x;
        if (tempval>midval)
        {
            x=valpoints[i];
        }
        else
        {
            x=(midval-tempval)/epsval+left;
        }
        return x;    
    }
    public int choose (double left1, double right1, double left2, double right2)
    {
        double totalval1=findval(left1, right1);
        double totalval2=findval(left2, right2);
        if (totalval1>totalval2)
            return 0;
        return 1;
    }
    public double findval(double left, double right)
    {

        double totalval=(right-left)*epsval;
        for (int i=0; i<size; i++)
                if (valpoints[i]<right && valpoints[i]>=left)
                    totalval+=pointval;
        return totalval;
    }
    public double findval(Interval in)
    {
        return findval(in.left, in.right);
    }
    public double findval(Slice slice)
    {
        double val=0;
        for (Interval i: slice.union)
        {
            val+=findval(i);
        }
        return val;
    }
    public void printVals()
    {
        for (int i=0; i<valpoints.length; i++)
        {
            System.out.print(valpoints[i]+", ");
        }
        System.out.println();
    }
}
