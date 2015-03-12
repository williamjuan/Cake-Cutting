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
public class RunAlgorithm {
    ArrayList<Double> cutpoints=new ArrayList();
    GameTree tree;
    Player[] players;
    public RunAlgorithm(GameTree tree, Player[] players)
    {//player, action, left1, right1, left2, right2
        cutpoints.add(0.0);
        cutpoints.add(1.0);
        this.tree=tree;
        this.players=players;
        
    }
    public Solution solve()
    {
        while (tree!=null)
        {
            if (tree.cutchoose[1]==2) //end of the tree
                break;
            GameTree temptree=runprocess(tree, players); //steps down the tree
            if (temptree==tree) //
                break;
            else
                tree=temptree;
         }
   //     System.out.println ("Basetest"+players[0].findval(0, cutpoints.get(2)));
        Solution s=new Solution(players.length);
        for (int i=0; i<tree.ass.length; i++)
        {
            s.dist[i]=new Slice(); //creates a new slice for your solution
            for (int j=0; j<tree.ass[i].assign.size(); j++)
            {           
                int assleft=(int)tree.ass[i].assign.get(j).left;
                int assright=(int)tree.ass[i].assign.get(j).right;
                s.dist[i].union.add(new Interval(cutpoints.get(assleft), cutpoints.get(assright)));
            }
        }
        if (s==null)
            System.out.println ("error");
        System.out.println (s);
        for (int i=0; i<players.length; i++)
        {
            Slice givenslice=s.dist[i];
            double value=0;
            for (int j=0; j<givenslice.union.size(); j++)
            {
                    value+=players[i].findval(givenslice.union.get(j));
            }
            System.out.println("Player"+i+"values"+": "+value);
        }
        return  s;
    }
    //needs to store cut points
    
    public GameTree runprocess(GameTree tree, Player[] players)
    {
        int[] cutchoose=tree.cutchoose;
        //public double[] cutchoose=new double[6]; //player, action (0==cut, 1==choose), left1, right1 (ratio for cuts), left2, right2
        //We note here that the intervals being passed refer to the index of the cut
            double left1, right1, left2, right2;
            left1=cutchoose[2];
            right1=cutchoose[3];
 //           System.out.println ("size: "+cutpoints.size());
            left2=cutpoints.get(cutchoose[4]);
            right2=cutpoints.get(cutchoose[5]);
            Player player=players[(int)cutchoose[0]];
        if (cutchoose[1]==0)
        {

            cutpoints.add(player.cut(left2, right2));
            return tree.left;
        }
        else if (cutchoose[1]==1)
        {
            left1=cutpoints.get(cutchoose[2]);
            right1=cutpoints.get(cutchoose[3]);
            if (player.choose(left1, right1, left2, right2)==0)
                return tree.left;
            else
                return tree.right;
        }
        else
        {
            return null;
        }
        
    }
}
