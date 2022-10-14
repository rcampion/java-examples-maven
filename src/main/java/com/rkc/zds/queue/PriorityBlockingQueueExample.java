package com.rkc.zds.queue;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {
    public static void main(String args[])
    {
        // Creating empty priority
        // blocking queue
        Queue<Integer> pbq
            = new PriorityBlockingQueue<Integer>();
 
        // Adding items to the pbq
        // using add()
        pbq.add(10);
        pbq.add(20);
        pbq.add(15);
 
        // Printing the top element of
        // the PriorityBlockingQueue
        System.out.println(pbq.peek());
 
        // Printing the top element and
        // removing it from the
        // PriorityBlockingQueue
        System.out.println(pbq.poll());
 
        // Printing the top element again
        System.out.println(pbq.peek());
    }
}
