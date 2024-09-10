/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 3
 * CSE214.R30
 */

import java.util.Random;

/**
 * The Request class represents an elevator request in a building.
 * It contains the source and destination floors and the time the request was placed.
 */
public class Request 
{
    private int numFloors;
    private int sourceFloor;
    private int destinationFloor;
    private int timeEntered;
    private static final Random random = new Random();

    /**
     * Constructs a Request object with randomly generated source and destination floors.
     *
     * @param floors the number of floors in the building
     */
    public Request(int floors) 
    {
        this.numFloors = floors;
        this.sourceFloor = generateRandomFloor();
        this.destinationFloor = generateRandomFloor();
        while (this.destinationFloor == this.sourceFloor) 
        {
            this.destinationFloor = generateRandomFloor();
        }
    }

    /**
     * Generates a random floor number between 1 and the number of floors in the building.
     *
     * @return a random floor number
     */
    private int generateRandomFloor() 
    {
        return random.nextInt(numFloors) + 1;
    }

    /**
     * Returns the source floor.
     *
     * @return the source floor
     */
    public int getSourceFloor() 
    {
        return sourceFloor;
    }

    /**
     * Returns the destination floor.
     *
     * @return the destination floor
     */
    public int getDestinationFloor() 
    {
        return destinationFloor;
    }

    /**
     * Returns the time the request was entered.
     *
     * @return the time the request was entered
     */
    public int getTimeEntered() 
    {
        return timeEntered;
    }

    /**
     * Sets the source floor.
     *
     * @param sourceFloor the source floor
     */
    public void setSourceFloor(int sourceFloor) 
    {
        this.sourceFloor = sourceFloor;
    }

    /**
     * Sets the destination floor.
     *
     * @param destinationFloor the destination floor
     */
    public void setDestinationFloor(int destinationFloor) 
    {
        this.destinationFloor = destinationFloor;
    }

    /**
     * Sets the time the request was entered.
     *
     * @param timeEntered the time the request was entered
     */
    public void setTimeEntered(int timeEntered) 
    {
        this.timeEntered = timeEntered;
    }
}