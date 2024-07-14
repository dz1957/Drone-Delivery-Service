package edu.gatech.GroceryExpress.service;

import edu.gatech.GroceryExpress.entity.Item;
import edu.gatech.GroceryExpress.entity.Line;
import edu.gatech.GroceryExpress.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service class for managing line entities. This class provides methods for creating line items,
 * calculating their cost and weight, and displaying their information.
 * 
 * @author Team 30
 * @since Dec 1th, 2023
 */
@Service
public class LineService {
    @Autowired
    private LineRepository repository;
    @Autowired
    private ItemService itemService;

    /**
     * Creates a new line item with the specified item, quantity, and price, then saves it in the repository.
     *
     * @param item     The item associated with the line.
     * @param quantity The quantity of the item in the line.
     * @param price    The price of the item per unit.
     * @return The created Line object.
     */
    public Line makeLine(Item item, int quantity, int price) {
        Line line = new Line(item, quantity, price);
        repository.save(line);
        return line;
    }

    /**
     * Calculates the total weight of a line item based on its quantity and the weight of the associated item.
     *
     * @param line The line item whose total weight is to be calculated.
     * @return The total weight of the line item.
     */
    public int getWeight(Line line) {
        return itemService.getWeight(line.getItem()) * line.getQuantity();
    }

    /**
     * Calculates the total cost of a line item based on its quantity and unit price.
     *
     * @param line The line item whose total cost is to be calculated.
     * @return The total cost of the line item.
     */
    public int getCost(Line line) {
        int cost = line.getPrice() * line.getQuantity();
        return cost;
    }

    /**
     * Retrieves the name of the item associated with a given line.
     *
     * @param line The line item whose associated item name is to be retrieved.
     * @return The name of the item associated with the line.
     */
    public String getItemName(Line line) {
        return itemService.getName(line.getItem());
    }

    /**
     * Retrieves the unit cost of the item in a given line.
     *
     * @param line The line item whose unit cost is to be retrieved.
     * @return The unit cost of the item in the line.
     */
    public int getUnitCost(Line line) {
        return line.getPrice();
    }

    /**
     * Formats and displays detailed information of a line item, including item name, quantity, total cost, and weight.
     *
     * @param line The line item whose information is to be displayed.
     * @return A string representing the detailed information of the line item.
     */
    public String display(Line line) {
        int cost = line.getPrice() * line.getQuantity();
        int weight = itemService.getWeight(line.getItem()) * line.getQuantity();
        String lineDetail = "item_name:" + itemService.getName(line.getItem()) + ",total_quantity:"
                + line.getQuantity() + ",total_cost:" + cost + ",total_weight:" + weight;
        System.out.println(lineDetail);
        return lineDetail;
    }
}
