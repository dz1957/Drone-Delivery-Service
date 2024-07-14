package edu.gatech.GroceryExpress.util;

public class Constant {
    public static String OK_CHG_COMPLETE = "OK:change_completed";
    public static String OK_DISPLAY_COMPLETE = "OK:display_completed";
    public static String ERR_STORE_ID_NOT_EXIST = "ERROR:store_identifier_does_not_exist";
    public static String ERR_STORE_ID_EXIST = "ERROR:store_identifier_already_exists";
    public static String ERR_ITEM_ID_NOT_EXIST = "ERROR:item_identifier_does_not_exist";
    public static String ERR_ITEM_ID_EXIST = "ERROR:item_identifier_already_exists";
    public static String ERR_PILOT_ID_NOT_EXIST = "ERROR:pilot_identifier_does_not_exist";
    public static String ERR_PILOT_ID_EXIST = "ERROR:pilot_identifier_already_exists";
    public static String ERR_PILOT_LIC_ID_EXIST = "ERROR:pilot_license_already_exists";
    public static String ERR_DRONE_ID_NOT_EXIST = "ERROR:drone_identifier_does_not_exist";
    public static String ERR_DRONE_ID_EXIST = "ERROR:drone_identifier_already_exists";
    public static String ERR_CUSTOMER_ID_NOT_EXIST = "ERROR:customer_identifier_does_not_exist";
    public static String ERR_CUSTOMER_ID_EXIST = "ERROR:customer_identifier_already_exists";
    public static String ERR_ORDER_ID_NOT_EXIST = "ERROR:order_identifier_does_not_exist";
    public static String ERR_ORDER_ID_EXIST = "ERROR:order_identifier_already_exists";
    public static String ERR_ITEM_EXIST = "ERROR:item_already_ordered";
    public static String ERR_CUSTOMER_CREDIT_LOW_ITEM = "ERROR:customer_cant_afford_new_item";
    public static String ERR_CUSTOMER_CREDIT_LOW_ORDER = "ERROR:customer_cant_afford_new_order";
    public static String ERR_DRONE_CAPACITY_LOW = "ERROR:drone_cant_carry_new_item";
    public static String DRONE_DISTANCE_NOT_VALID = "ERROR:drone's max fuel capacity is not enough to cover travel distance";
    public static String ERR_DRONE_NEED_PILOT = "ERROR:drone_needs_pilot";
    public static String ERR_DRONE_NOT_ENOUGH_CAP = "ERROR:new_drone_does_not_have_enough_capacity";
    public static String OK_DRONE_NEW_CUR_SAME = "OK:new_drone_is_current_drone_no_change";
    public static String ERR_NOT_BELONG_TO_CUSTOMER = "ERROR:order_does_not_belong_to_customer";
    public static String ERR_DRONE_HAS_OTHER_ORDER = "ERROR:drone_has_other_pending_order";
    public static String ERR_30_DAYS ="ERROR:order_can_only_be_returned_if_within_return_window";
    public static String ERR_5_RETURNS = "ERROR:maximum_number_of_returns_has_been_reached";
    public static String ERR_INVALID_QUANTITIES = "ERROR:invalid_quantities";
    public static String ERR_DRONE_FUEL_CAPACITY_LOW = "ERROR:drone_fuel_capacity_too_low";
    public static String ERR_DRONE_REFUELING_RATE_LOW = "ERROR:drone_refueling_rate_too_low";
    public static String ERROR_ORDER_ALREADY_RETURNED ="ERROR:order_already_returned";

    public static final int MILLIS_PER_HOUR = 3600000;
    public static final int HOURS_PER_DAY = 24;

    public static final int DEFAULT_DAY_TIME_START_HOUR = 6;

    public static final int DEFAULT_NIGHT_TIME_START_HOUR = 18;

    public static final int DEFAULT_DAY_TIME_POWER_LEVEL = 2;

    public static final int DEFAULT_NIGHT_TIME_POWER_LEVEL = 1;

    public static final int CLOCK_TABLE_INDEX = 0;
    public static final int CLOCK_START_MIILI = 0;
    public static final int DEFAULT_RETURN_WINDOW = 30; // Customers can return their purchases within 30 days from the date of purchase.
    public static final int DEFAULT_MAX_RETURNABLE_ORDERS = 5; // Customers are permitted to return items only from their five most recent orders.


}
