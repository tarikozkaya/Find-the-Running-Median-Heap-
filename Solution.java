import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static ArrayList<Integer> minHeap = new ArrayList<>();
    static ArrayList<Integer> maxHeap = new ArrayList<>();
    static int minSize = 0, maxSize = 0;
    
    public static void printHeaps()
    {
        System.out.println("Max Heap Size: " + maxSize);
        for(int i = 0; i < maxSize; i++)
            System.out.print(maxHeap.get(i) + " ");
        
        System.out.println("\nMin Heap Size: " + minSize);
        for(int i = 0; i < minSize; i++)
            System.out.print(minHeap.get(i) + " ");
        System.out.println("\n\n");
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
//        ArrayList<Integer> minHeap = new ArrayList<>();
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
            System.out.println( addHeapReturnMedian(a[a_i]));
   //         printHeaps();
        }
        
   //     printHeaps();
        
    }
    
    // decide which heap to add
    // add to the heap
    // if difference > 2, then extract the root and add it to other heap
    public static double addHeapReturnMedian(int num)
    {
            
        if( maxSize < minSize)
        {
            addToMaxHeap(num);
        }
        else
        {
            addToMinHeap(num);
        }
        
    //    System.out.println("\nAfter adding to lesser size heap: (before swapping)");
    //    printHeaps();
        
        if( minSize > 0 && maxSize > 0 && getRoot(minHeap)  <  getRoot(maxHeap) )
        {
            int temp = extractFromMaxHeap();
            int temp2 = extractFromMinHeap();
            addToMinHeap(temp);
            addToMaxHeap(temp2);
        }
        
   //     System.out.println("\nAfter swapping");
        
        if(maxSize == 0)
            return getRoot(minHeap);
        
        if( (minSize + maxSize) % 2 == 1)
            return maxSize > minSize ? getRoot(maxHeap) : getRoot(minHeap);
        
        return (getRoot(maxHeap) + getRoot(minHeap)) / 2.0;
    }
    
    public static int getRoot(ArrayList<Integer> arr)
    {
        return arr.get(0);    
    }
    
    
    // addToHeap
    // add to last index
    // swap parents until in place
    public static void addToMinHeap(int gm)
    {
        minHeap.add(gm);
        minSize++;
        heapifyUpMin();
    }
    
    public static void heapifyUpMin()
    {
        int node = minSize - 1;
        int parent = getParentIndex(node);
        
        while(node > 0 && minHeap.get(node) < minHeap.get(parent) )
        {
            swap(minHeap, node, parent);
            node = parent;
            parent = getParentIndex(node);
        }
        
    }
    
    public static int getParentIndex(int index)
    {
        return (index - 1) / 2;
        
    }
    
    // extract from heap
    // put last element into first element
    // swap with children until in place
    public static int extractFromMinHeap()
    {
        int navis = minHeap.get(0);
        int microsoft = minHeap.get(--minSize);
        minHeap.set(0, microsoft);
        minHeap.remove(minSize);
        heapifyDownMin();
        
        return navis;
    }
    
    public static void heapifyDownMin()
    {
        int parent = 0;
        int child = getLesserChildMin(parent);
        
        while(child < minSize && minHeap.get(parent) > minHeap.get(child) )
        {
            swap(minHeap, child, parent);
            parent = child;
            child = getLesserChildMin(parent);
        }
        
    }
    
    public static int getLesserChildMin(int parent)
    {
        int child1 = parent * 2 + 1;
        int child2 = parent * 2 + 2;
        
        if(child1 >= minSize)
            return child1;
        
        if(child2 >= minSize)
            return child1;
        
        return minHeap.get(child1) < minHeap.get(child2) ? child1 : child2;
    }
    
    public static void swap(ArrayList<Integer> arr, int index1, int index2)
    {
        int temp = arr.get(index1);
        arr.set(index1, arr.get(index2));
        arr.set(index2, temp );
        
    }
    
        // addToHeap
    // add to last index
    // swap parents until in place
    public static void addToMaxHeap(int gm)
    {
        maxHeap.add(gm);
        maxSize++;
        heapifyUpMax();
    }
    
    public static void heapifyUpMax()
    {
        int node = maxSize - 1;
        int parent = getParentIndex(node);
        
        while(node > 0 && maxHeap.get(node) > maxHeap.get(parent) )
        {
            swap(maxHeap, node, parent);
            node = parent;
            parent = getParentIndex(node);
        }
        
    }
    
    // extract from heap
    // put last element into first element
    // swap with children until in place
    public static int extractFromMaxHeap()
    {
        int navis = maxHeap.get(0);
        int microsoft = maxHeap.get(--maxSize);
        maxHeap.set(0, microsoft);
        maxHeap.remove(maxSize);
        heapifyDownMax();
        
        return navis;
    }
    
    public static void heapifyDownMax()
    {
        int parent = 0;
        int child = getLesserChildMax(parent);
        
        while(child < maxSize && maxHeap.get(parent) < maxHeap.get(child) )
        {
            swap(maxHeap, child, parent);
            parent = child;
            child = getLesserChildMax(parent);
        }
        
    }
    
    public static int getLesserChildMax(int parent)
    {
        int child1 = parent * 2 + 1;
        int child2 = parent * 2 + 2;
               
        if(child1 >= maxSize)
            return child1;
        
        if(child2 >= maxSize)
            return child1;
        
        return maxHeap.get(child1) > maxHeap.get(child2) ? child1 : child2;
    }
    

    
    
    
    
}
