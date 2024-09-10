
import java.util.ArrayList;

/**
 * The Simulator class carries out the elevator simulation.
 */
public class Simulator 
{

    /**
     * Simulates the operation of elevators in a building.
     *
     * @param probability       the probability of a request being introduced per time unit (between 0.0 and 1.0 inclusive)
     * @param numFloors         the number of floors in the building (greater than 1)
     * @param numElevators      the number of elevators in the building (greater than 0)
     * @param simulationLength  the length of the simulation in time units (greater than 0)
     * @throws Exceptions.IllegalArgumentException if any parameter is out of the specified range
     */
    public static void simulate(double probability, int numFloors, int numElevators, int simulationLength) throws Exceptions.IllegalArgumentException
    {
        if (probability < 0.0 || probability > 1.0) 
        {
            throw new Exceptions.IllegalArgumentException("Probability must be between 0.0 and 1.0 inclusive.");
        }
        if (numFloors <= 1) 
        {
            throw new Exceptions.IllegalArgumentException("Number of floors must be greater than 1.");
        }
        if (numElevators <= 0) 
        {
            throw new Exceptions.IllegalArgumentException("Number of elevators must be greater than 0.");
        }
        if (simulationLength <= 0) 
        {
            throw new Exceptions.IllegalArgumentException("Simulation length must be greater than 0.");
        }

        BooleanSource requestSource = new BooleanSource(probability);
        RequestQueue requestQueue = new RequestQueue();
        ArrayList<Elevator> elevators = new ArrayList<>();

        for (int i = 0; i < numElevators; i++) 
        {
            elevators.add(new Elevator());
        }

        int totalRequests = 0;
        int totalWaitTime = 0;

        for (int currentTime = 0; currentTime < simulationLength; currentTime++) 
        {
            if (requestSource.requestArrived())
            {
                Request newRequest = new Request(numFloors);
                newRequest.setTimeEntered(currentTime);
                requestQueue.enqueue(newRequest);
                totalRequests++;
            }

            for (Elevator elevator : elevators) 
            {
                switch (elevator.getElevatorState())
                {
                    case Elevator.IDLE:
                    {
                        if (!requestQueue.isEmpty()) 
                        {
                            Request nextRequest = requestQueue.dequeue();
                            elevator.setRequest(nextRequest);
                            if (elevator.getCurrentFloor() == nextRequest.getSourceFloor()) 
                            {
                                elevator.setElevatorState(Elevator.TO_DESTINATION);
                            } 
                            else 
                            {
                                elevator.setElevatorState(Elevator.TO_SOURCE);
                            }
                        }
                        break;
                    }
                    case Elevator.TO_SOURCE:
                    {
                        if (elevator.getCurrentFloor() < elevator.getRequest().getSourceFloor()) 
                        {
                            elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                        } 
                        else if (elevator.getCurrentFloor() > elevator.getRequest().getSourceFloor())
                        {
                            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                        } 
                        else 
                        {
                            elevator.setElevatorState(Elevator.TO_DESTINATION);
                        }
                        break;
                    }
                    case Elevator.TO_DESTINATION:
                    {
                        if (elevator.getCurrentFloor() < elevator.getRequest().getDestinationFloor()) 
                        {
                            elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                        }
                        else if (elevator.getCurrentFloor() > elevator.getRequest().getDestinationFloor()) 
                        {
                            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                        } 
                        else 
                        {
                            totalWaitTime += currentTime - elevator.getRequest().getTimeEntered();
                            elevator.setElevatorState(Elevator.IDLE);
                            elevator.setRequest(null);
                        }
                        break;
                    }
                }
            }
        }
        double averageWaitTime;
        averageWaitTime = (double) totalWaitTime / totalRequests; 
        if(totalRequests == 0)
        {
        	averageWaitTime = 0;
        }
        System.out.println("Total Wait Time: " + totalWaitTime);
        System.out.println("Total Requests: " + totalRequests);
        System.out.println(String.format("Average Wait Time: %.2f", averageWaitTime));
    }

}
