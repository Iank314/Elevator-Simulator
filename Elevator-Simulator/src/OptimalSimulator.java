/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 3
 * CSE214.R30
 */
import java.util.ArrayList;

/**
 * The OptimalSimulator class carries out the elevator simulation with optimized rules.
 */
public class OptimalSimulator 
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
        ArrayList<ArrayList<Request>> floorRequestsUp = new ArrayList<>(numFloors + 1);
        ArrayList<ArrayList<Request>> floorRequestsDown = new ArrayList<>(numFloors + 1);
        ArrayList<Elevator> elevators = new ArrayList<>();
        ArrayList<Boolean> elevatorDirection = new ArrayList<>(numElevators); 

        for (int i = 0; i <= numFloors; i++) 
        {
            floorRequestsUp.add(new ArrayList<>());
            floorRequestsDown.add(new ArrayList<>());
        }

        for (int i = 0; i < numElevators; i++) 
        {
            elevators.add(new Elevator());
            elevatorDirection.add(true); 
        }

        int totalRequests = 0;
        int totalWaitTime = 0;

        for (int currentTime = 0; currentTime < simulationLength; currentTime++) 
        {
            if (requestSource.requestArrived()) 
            {
                Request newRequest = new Request(numFloors);
                newRequest.setTimeEntered(currentTime);
                if (newRequest.getSourceFloor() < newRequest.getDestinationFloor()) 
                {
                    floorRequestsUp.get(newRequest.getSourceFloor()).add(newRequest);
                } 
                else 
                {
                    floorRequestsDown.get(newRequest.getSourceFloor()).add(newRequest);
                }
                totalRequests++;
            }

            for (int i = 0; i < numElevators; i++) 
            {
                Elevator elevator = elevators.get(i);
                boolean direction = elevatorDirection.get(i);

                switch (elevator.getElevatorState()) 
                {
                    case Elevator.IDLE:
                    {
                        if (direction && !floorRequestsUp.get(elevator.getCurrentFloor()).isEmpty()) 
                        {
                            elevator.setRequest(floorRequestsUp.get(elevator.getCurrentFloor()).remove(0));
                            elevator.setElevatorState(Elevator.TO_SOURCE);
                        }
                        else if (!direction && !floorRequestsDown.get(elevator.getCurrentFloor()).isEmpty()) 
                        {
                            elevator.setRequest(floorRequestsDown.get(elevator.getCurrentFloor()).remove(0));
                            elevator.setElevatorState(Elevator.TO_SOURCE);
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

                if (elevator.getElevatorState() == Elevator.IDLE)
                {
                    if (direction && elevator.getCurrentFloor() < numFloors && !floorRequestsUp.get(elevator.getCurrentFloor() + 1).isEmpty()) 
                    {
                        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                        elevator.setRequest(floorRequestsUp.get(elevator.getCurrentFloor()).remove(0));
                        elevator.setElevatorState(Elevator.TO_SOURCE);
                    } 
                    else if (!direction && elevator.getCurrentFloor() > 1 && !floorRequestsDown.get(elevator.getCurrentFloor() - 1).isEmpty()) 
                    {
                        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                        elevator.setRequest(floorRequestsDown.get(elevator.getCurrentFloor()).remove(0));
                        elevator.setElevatorState(Elevator.TO_SOURCE);
                    }
                    else 
                    {
                        elevatorDirection.set(i, !direction);
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