package edu.gatech.GroceryExpress.service;
import org.springframework.stereotype.Service;

/**
 * Service class for calculating distances. This class provides utility methods
 * related to distance calculations.
 * 
 * @author Team 30
 * @since Dec 1th, 2023
 */
@Service
public class DistanceService {
    /**
    * Calculates the Manhattan distance between two points. The Manhattan distance
    * is the sum of the absolute differences of their Cartesian coordinates.
    *
    * @param sourceXCoordinate The X coordinate of the source location.
    * @param sourceYCoordinate The Y coordinate of the source location.
    * @param destXCoordinate   The X coordinate of the destination location.
    * @param destYCoordinate   The Y coordinate of the destination location.
    * @return The Manhattan distance between the two points.
    */
    public static int getDistance(int sourceXCoordinate, int sourceYCoordinate, int destXCoordinate, int destYCoordinate) {
        //get manhattan distance
        return Math.abs(sourceXCoordinate - destXCoordinate) + Math.abs(sourceYCoordinate - destYCoordinate);
    }
}
