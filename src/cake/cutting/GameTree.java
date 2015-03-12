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
public class GameTree {
    public int initialcut=0; //the base index for this game tree, normally 0. But when importing a main tree as a subtree, this index will be the length of the tree at the time
//how to deal with the fact that ends of the interval on which this game tree is played no longer correspond to the 0 and 1 index?
//tree structure is expensive to code as a static structure. Instead consider dynamically. Problem: Look at trimming algorithm. Although recursively easy to write dynamically takes exponential space just to write pointers.   
    public GameTree left, right=null;
    public int[] cutchoose=new int[6]; //player, action, left1, right1, left2, right2
    public Assignment[] ass=null; //assignment not solution, should be pointers to allocations for cuts, translated into solution by run. Should return the pairs of cuts by order, which form the intervals which form the solution.
    public GameTree()
    {
        left=null;
        right=null;
        cutchoose=null;
    }
    public GameTree (GameTree l, GameTree r, int[] cc)
    {
        left=l;
        right=r;
        cutchoose=cc;
    }
}
