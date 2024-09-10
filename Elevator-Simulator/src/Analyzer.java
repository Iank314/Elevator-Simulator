/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 3
 * CSE214.R30
 */
import java.util.Scanner;

/**
 * The Analyzer class contains a main method which prompts the user for the parameters required for the simulation,
 * checks their validity, and then runs the simulation.
 */
public class Analyzer 
{

    /**
     * The main method which prompts the user for simulation parameters, checks their validity, and runs the simulation.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) throws Exceptions.IllegalArgumentException
    {
        Scanner scanner = new Scanner(System.in);

        double probability = 0.0;
        int numFloors = 0;
        int numElevators = 0;
        int simulationLength = 0;
        boolean useOptimal = false;

        while (true) 
        {
            try 
            {
                System.out.println("Enter the probability of a request being introduced per time unit: ");
                probability = Double.parseDouble(scanner.nextLine());
                if (probability < 0.0 || probability > 1.0) 
                {
                    throw new Exceptions.IllegalArgumentException("Probability must be between 0.0 and 1.0 inclusive.");
                }
                break;
            } 
            catch (Exception e) 
            {
                System.out.println("Invalid input. Please enter a valid probability.");
            }
        }

        while (true) 
        {
            try 
            {
                System.out.println("Enter the number of floors in the building: ");
                numFloors = Integer.parseInt(scanner.nextLine());
                if (numFloors <= 1) 
                {
                    throw new Exceptions.IllegalArgumentException("Number of floors must be greater than 1.");
                }
                break;
            } 
            catch (Exception e) 
            {
                System.out.println("Invalid input. Please enter a valid number of floors.");
            }
        }

        while (true) 
        {
            try 
            {
                System.out.println("Enter the number of elevators in the building: ");
                numElevators = Integer.parseInt(scanner.nextLine());
                if (numElevators <= 0) 
                {
                    throw new Exceptions.IllegalArgumentException("Number of elevators must be greater than 0.");
                }
                break;
            } 
            catch (Exception e) 
            {
                System.out.println("Invalid input. Please enter a valid number of elevators.");
            }
        }

        while (true) 
        {
            try 
            {
                System.out.println("Enter the length of the simulation in time units: ");
                simulationLength = Integer.parseInt(scanner.nextLine());
                if (simulationLength <= 0) 
                {
                    throw new Exceptions.IllegalArgumentException("Simulation length must be greater than 0.");
                }
                break;
            } 
            catch (Exception e) 
            {
                System.out.println("Invalid input. Please enter a valid simulation length.");
            }
        }

        while (true) 
        {
            try 
            {
                System.out.println("Do you want to run the optimal simulation? Only say yes or no;");
                String optimalInput = scanner.nextLine().trim().toLowerCase();
                if (optimalInput.equals("yes")) 
                {
                    useOptimal = true;
                } 
                else if (optimalInput.equals("no")) 
                {
                    useOptimal = false;
                } 
                else 
                {
                    throw new Exceptions.IllegalArgumentException("Input must be 'yes' or 'no'.");
                }
                break;
            } 
            catch (Exception e) 
            {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        scanner.close();

        if (useOptimal) 
        {
            OptimalSimulator.simulate(probability, numFloors, numElevators, simulationLength);
        } 
        else 
        {
            Simulator.simulate(probability, numFloors, numElevators, simulationLength);
        }
    }
}