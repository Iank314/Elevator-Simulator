
/**
 * The Elevator class represents an elevator in a building.
 * It maintains the current floor, state of the elevator, and the request being handled.
 */
public class Elevator 
{
    private int currentFloor;
    private int elevatorState;
    private Request request;
    public static final int IDLE = 0;
    public static final int TO_SOURCE = 1;
    public static final int TO_DESTINATION = 2;

    /**
     * Constructs an Elevator object with default values.
     * Sets request to null, elevatorState to IDLE, and currentFloor to 1.
     */
    public Elevator() 
    {
        this.currentFloor = 1;
        this.elevatorState = IDLE;
        this.request = null;
    }

    /**
     * Returns the current floor of the elevator.
     *
     * @return the current floor of the elevator
     */
    public int getCurrentFloor() 
    {
        return currentFloor;
    }

    /**
     * Sets the current floor of the elevator.
     *
     * @param currentFloor the current floor to set
     */
    public void setCurrentFloor(int currentFloor) 
    {
        this.currentFloor = currentFloor;
    }

    /**
     * Returns the state of the elevator.
     *
     * @return the state of the elevator
     */
    public int getElevatorState() 
    {
        return elevatorState;
    }

    /**
     * Sets the state of the elevator.
     *
     * @param elevatorState the state of the elevator to set
     */
    public void setElevatorState(int elevatorState) 
    {
        this.elevatorState = elevatorState;
    }

    /**
     * Returns the request being handled by the elevator.
     *
     * @return the request being handled by the elevator, or null if idle
     */
    public Request getRequest() 
    {
        return request;
    }

    /**
     * Sets the request being handled by the elevator.
     *
     * @param request the request to set
     */
    public void setRequest(Request request) 
    {
        this.request = request;
    }
}
