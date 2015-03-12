/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cake.cutting;

/**
 *
 * @author Q
 */
public class Interval {
 public double left=0;
 public double right=1;
 public Interval (double x, double y)
 {
     if (x>y)
         System.out.println ("error"+" x:"+x+" y:"+y);
     left=x;
     right=y;
           
 }
    
}
