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
public class Solution {
    Slice[] dist=null;
    public Solution (int Players)
    {
        dist=new Slice[Players];
    }
    public String toString ()
    {
        String temp="";
        for (int i=0; i<dist.length; i++)
        {
            temp=temp+dist[i].toString();
        }
        return temp;
    }
}
