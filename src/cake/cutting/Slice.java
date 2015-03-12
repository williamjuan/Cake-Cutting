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
import java.util.*;
public class Slice {
    ArrayList<Interval> union=null;
    public Slice (){
        union=new ArrayList<Interval>();
    }
    public void add (Interval interval)
    {
        union.add(interval);
    }
    public String toString()
    {
        String temp="{";
        for (int i=0; i<union.size(); i++)
        {
            temp=temp+union.get(i).left+" "+union.get(i).right+", ";
        }
        temp=temp+"}";
        return temp;
    }
    
}
