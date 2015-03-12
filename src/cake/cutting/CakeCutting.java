/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cake.cutting;

import java.util.*;
/**
 *
 * @author Q
 */
public class CakeCutting {

    /**
     * @param args the command line arguments
     */
    
   
    
      public static void main(String[] args) {
      int playernumber=10;
      double faircut=1/(double)(playernumber);
      Player[] players=new Player[playernumber];
      ArrayList<Player> toassign=new ArrayList<Player>();
        for (int j=0; j<players.length; j++)
              {
                  players[j]=new Player(1000, .00001);
                  players[j].randVals();
                  toassign.add(players[j]);
              }  

          double totalmaxenvy=0;

          for (int counter=0; counter<100000; counter++)
        { //rerandomize player values at the start of each run.
            double envymax=0;
            double enviedplayer=0;

            for (int i=0; i<players.length; i++)
            {
         //       players[i].randVals(); instead of randomizing all, try randomizing everyone but the most envious player.
                players[i].hasslice=false;
         //       players[i].printVals();
            }

      
            Solution s=new Solution(playernumber);
      
            double left=0;
            for (int j=0; j<players.length-1; j++)//action loop that runs until each player has a slice
            {
                int activeplayer=0;
                for (int k=0; k<players.length; k++)
                {
                    if (!players[k].hasslice)
                    {
                        activeplayer=k;
                        break;
                    }
                }
                double cut=players[activeplayer].cut(left, 1, faircut);
                System.out.println("cut: "+cut);
                for (int k=0; k<players.length; k++)
                {
                    if (!players[k].hasslice)
                    {
                         if (players[k].findval(left, cut)> (faircut))
                         {
                             System.out.println("val:"+players[k].findval(left, cut)+"true: "+players[k].findval(left, cut)+">"+(faircut));
                             
                             cut=players[k].cut(left, cut, (faircut));
                             System.out.println("cut: "+cut+"cutval:"+players[k].findval(left, cut));

                             activeplayer=k;
                         }
                    } 
                }
                System.out.println ("activeplayer: "+activeplayer);
                players[activeplayer].hasslice=true;
                s.dist[activeplayer]=new Slice();
                s.dist[activeplayer].add(new Interval(left, cut));
                left=cut;
                System.out.println ("left: "+left);
            
            }
            int lastplayer=0;
            for (int k=0; k<players.length; k++)
            {
                if (!players[k].hasslice)
                    {
                        lastplayer=k;
                        break;
                    }
                
            }
            s.dist[lastplayer]=new Slice();
            s.dist[lastplayer].add(new Interval(left, 1));
            System.out.println ("left: "+left);
            
            
            for (int i=0; i<players.length; i++)
            {
                double playervalue=players[i].findval(s.dist[i]);
                double max=playervalue;
                for (int k=0; k<s.dist.length; k++)
                {
//                    System.out.print(i+"values"+k+": "+players[i].findval(s.dist[k])+", ");
                    if (players[i].findval(s.dist[k])>max)
                        max=players[i].findval(s.dist[k]);
                }
                double playerenvy=max-playervalue;
           //     System.out.println ();
           //     System.out.println (i+"envy: "+playerenvy);
                if (envymax<playerenvy)
                {
                    envymax=playerenvy;
                    enviedplayer=i;
                }
            }
            System.out.println("Maxenvy:"+envymax);
            //solution is an array of slices
            //slice is a list of intervals
            if (envymax>totalmaxenvy)
                totalmaxenvy=envymax;
        }
        System.out.println("Totalmaxenvy:"+totalmaxenvy);
    }
      }

