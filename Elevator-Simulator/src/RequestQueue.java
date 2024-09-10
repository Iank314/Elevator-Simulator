/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 3
 * CSE214.R30
 */
import java.util.ArrayList;

/**
 * The RequestQueue class represents a queue of elevator requests.
 * It extends the ArrayList class and provides methods for enqueuing, dequeuing, checking the size, and checking if the queue is empty.
 */
public class RequestQueue extends ArrayList<Request> 
{
    /**
     * Constructs an empty RequestQueue.
     */
    public RequestQueue() 
    {
        super();
    }

    /**
     * Adds a request to the end of the queue.
     *
     * @param request the request to be added
     */
    public void enqueue(Request request) 
    {
        this.add(request);
    }

    /**
     * Removes and returns the request at the front of the queue.
     *
     * @return the request at the front of the queue, or null if the queue is empty
     */
    public Request dequeue() 
    {
        if (this.isEmpty()) 
        {
            return null;
        }
        return this.remove(0);
    }
    //professor said to not add Size() and isEmpty(), since it's redundant in class.
}