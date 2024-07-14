package edu.gatech.GroceryExpress.service;

import edu.gatech.GroceryExpress.entity.Customer;
import edu.gatech.GroceryExpress.entity.Order;
import edu.gatech.GroceryExpress.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static edu.gatech.GroceryExpress.util.Constant.*;


/**
 * Service class providing functionality for managing a custom clock.
 * This service is responsible for updating and maintaining the state
 * of a {@link CustomClock} entity, including time advancement and
 * power level adjustments based on time of day.
 * <p>
 * Note: This service ensures that there is only one clock instance for all
 * backend instances to maintain time consistency across the application.
 * </p>
 *
 * @author Team 30
 * @since Dec 1th, 2023
 */

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private OrderService orderService;

    /**
     * Attempts to create a new customer with the provided details and add them
     * to the repository. If a customer with the given account name already exists,
     * it will not create a new one and will return an error message instead.
     *
     * @param account      The unique identifier for the new customer.
     * @param firstName    The first name of the new customer.
     * @param lastName     The last name of the new customer.
     * @param phoneNumber  The contact number for the new customer.
     * @param rating       The initial rating of the new customer.
     * @param credit       The initial credit amount for the new customer.
     * @param x            The x-coordinate for the customer's address.
     * @param y            The y-coordinate for the customer's address.
     * @return A success or error message as a result of the operation.
     */
    public String makeCustomer(String account, String firstName, String lastName,
                               String phoneNumber, int rating, int credit, int x, int y) {
        TreeMap<String, Customer> customers = getCustomerAsTreeMap();
        if (customers.containsKey(account)) {
            System.out.println(ERR_CUSTOMER_ID_EXIST);
            return ERR_CUSTOMER_ID_EXIST;
        } else {
            Customer customer = new Customer(account, firstName, lastName, phoneNumber, rating, credit, x, y);
            repository.save(customer);
            System.out.println(OK_CHG_COMPLETE);
            return OK_CHG_COMPLETE;
        }
    }

    /**
     * Retrieves the account identifier for the specified customer.
     *
     * @param customer The customer whose account identifier is to be retrieved.
     * @return The account identifier of the given customer.
     */
    public String getAccount(Customer customer) {
        return customer.getAccount();
    }

    /**
     * Updates the credit for a given customer and persists the change to the repository.
     *
     * @param customer  The customer whose credit is to be updated.
     * @param newCredit The new credit value to set for the customer.
     */
    public void setCredit(Customer customer, int newCredit) {
        customer.setCredit(newCredit);
        repository.save(customer);
    }

    /**
     * Retrieves the credit for a specified customer.
     *
     * @param customer The customer whose credit is to be retrieved.
     * @return The current credit value of the given customer.
     */
    public int getCredit(Customer customer) {
        return customer.getCredit();
    }

    /**
     * Adds an order to a customer's list of orders and updates the repository.
     *
     * @param customer The customer to which the order will be added.
     * @param order    The order to add to the customer's list of orders.
     */
    public void addOrder(Customer customer, Order order) {
        List<Order> orders = customer.getOrders();
        orders.add(order);
        customer.setOrders(orders);
        repository.save(customer);
    }

    /**
     * Removes an order from a customer's list of orders and updates the repository.
     *
     * @param customer The customer from whose list the order will be removed.
     * @param order    The order to be removed from the customer's list of orders.
     */
    public void removeOrder(Customer customer, Order order) {
        List<Order> orders = customer.getOrders();
        orders.remove(order);
        customer.setOrders(orders);
        repository.save(customer);
    }

    /**
     * Retrieves the x-coordinate representing the customer's address location.
     *
     * @param customer The customer whose x-coordinate is to be retrieved.
     * @return The x-coordinate of the customer's address.
     */
    public int getXCoordinate(Customer customer) {
        return customer.getXCoordinate();
    }

    /**
     * Retrieves the y-coordinate representing the customer's address location.
     *
     * @param customer The customer whose y-coordinate is to be retrieved.
     * @return The y-coordinate of the customer's address.
     */
    public int getYCoordinate(Customer customer) {
        return customer.getYCoordinate();
    }

    /**
     * Calculates the total cost of all orders placed by the given customer.
     *
     * @param customer The customer whose orders' total cost is to be calculated.
     * @return The total cost of all orders placed by the customer.
     */
    public int getOrdersCost(Customer customer) {
        int totalCost = 0;
        for (Order order : customer.getOrders()) {
            totalCost += orderService.getCost(order);
        }
        return totalCost;
    }


    /**
     * Displays information about a single customer in a formatted string.
     * The information includes the customer's name, phone number, rating,
     * credit, and address coordinates.
     *
     * @param customer The {@code Customer} object whose information is to be displayed.
     * @return A formatted string containing the customer's information.
     */
    public String display(Customer customer) {
        String customerInfo = "name:" + customer.getFirstName() + "_" + customer.getLastName()
                + ",phone:" + customer.getPhoneNumber() + ",rating:" + customer.getRating() + ",credit:" + customer.getCredit() + ",address:(" + customer.getXCoordinate() + "," + customer.getYCoordinate() + ")";
        System.out.println(customerInfo);
        return customerInfo;
    }

    /**
     * Displays information for all customers sorted in alphabetical order.
     * It leverages the {@code getCustomerAsTreeMap} method to sort customers
     * and then builds a formatted string for the entire list using the
     * {@code display} method for individual customers.
     *
     * @return A string representation of all customers' information, separated by new lines.
     */
    public String displayCustomers() {
        TreeMap<String, Customer> customers = getCustomerAsTreeMap();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Customer> entry : customers.entrySet()) {
            sb.append(display(entry.getValue())).append('\n');
        }
        System.out.println(OK_DISPLAY_COMPLETE);
        sb.append(OK_DISPLAY_COMPLETE);
        return sb.toString();
    }

    /**
     * Retrieves all customers from the repository and returns them as a {@code TreeMap}
     * sorted alphabetically by account name. This is useful for displaying customers
     * in a sorted manner.
     *
     * @return A {@code TreeMap} where each key is a customer's account name and the value
     *         is the {@code Customer} object.
     */
    public TreeMap<String, Customer> getCustomerAsTreeMap() {
        List<Customer> customersList = repository.findAll();
        TreeMap<String, Customer> customers = new TreeMap<>();
        for (Customer customer : customersList) {
            customers.put(customer.getAccount(), customer);
        }
        return customers;
    }
}
