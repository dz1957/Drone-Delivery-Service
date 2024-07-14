package edu.gatech.GroceryExpress.service;

import edu.gatech.GroceryExpress.entity.CustomClock;
import edu.gatech.GroceryExpress.repository.CustomClockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static edu.gatech.GroceryExpress.util.Constant.*;


/**
 * Service class for managing a CustomClock entity. This class provides methods to
 * handle the initialization, updating, and retrieval of time-related data, ensuring
 * a consistent clock across all backend instances for scalability purposes.
 * <p>
 * It is designed to operate with a single clock instance to maintain time consistency.
 * </p>
 * 
 * @author Team 30
 * @since Dec 1th, 2023
 */

@Service
public class CustomClockService {
    @Autowired
    private CustomClockRepository repository;

    /**
     * Initializes the custom clock upon the creation of the service. If the clock
     * does not already exist, it creates a new clock instance with the start time
     * and sets the power level based on the current time of day.
     */
    @PostConstruct
    public void startCustomClock() {
        if (!repository.findAll().isEmpty()) {
            return;
        }
        CustomClock clock = new CustomClock(CLOCK_START_MIILI);
        repository.save(clock);
        updatePowerLevel();
    }

    /**
     * Retrieves the current instance of the custom clock from the repository.
     * 
     * @return The {@code CustomClock} instance containing the current time and power level.
     */
    public CustomClock getClock() {
        return repository.findAll().get(CLOCK_TABLE_INDEX);
    }

    /**
     * Gets the current time in milliseconds from the custom clock.
     * 
     * @return The current time in milliseconds.
     */
    public long currentTimeMillis() {
        return getClock().getCurrentTimeMillis();
    }
    /**
     * Updates the power level of the custom clock based on the time of day.
     * It sets a higher power level during the day (6 AM to 6 PM) and a lower
     * power level during the night (6 PM to 6 AM).
     */
    private void updatePowerLevel() {
        // Assuming a simple model:
        // 6 AM to 6 PM is day (higher power level)
        // 6 PM to 6 AM is night (lower power level)
        CustomClock customClock = getClock();
        int hourOfDay = getHourOfDay();
        if (hourOfDay >= customClock.getDayTimeStartHour() && hourOfDay < customClock.getNightTimeStartHour()) {
            customClock.setPowerLevel(customClock.getDayTimePowerLevel()); // Daytime power level
        } else {
            customClock.setPowerLevel(customClock.getNightTimePowerLevel()); // Nighttime power level
        }
        repository.save(customClock);
    }

    /**
     * Retrieves the current power level from the custom clock.
     *
     * @return The current power level as set in the custom clock.
     */
    public int getPowerLevel() {
        return getClock().getPowerLevel();
    }

    /**
     * Retrieves the hour of the day when day time starts.
     *
     * @return The start hour of day time from the custom clock settings.
     */
    public int getDayTimeStartHour() {
        CustomClock customClock = getClock();
        return customClock.getDayTimeStartHour();
    }

    /**
     * Sets the start hour for day time in the custom clock settings.
     *
     * @param dayTimeStartHour The hour to set as the start of day time.
     */
    public void setDayTimeStartHour(int dayTimeStartHour) {
        CustomClock customClock = getClock();
        customClock.setDayTimeStartHour(dayTimeStartHour);
    }

    /**
     * Retrieves the hour of the day when night time starts.
     *
     * @return The start hour of night time from the custom clock settings.
     */
    public int getNightTimeStartHour() {
        CustomClock customClock = getClock();
        return customClock.getNightTimeStartHour();
    }

    /**
     * Sets the start hour for night time in the custom clock settings.
     *
     * @param nightTimeStartHour The hour to set as the start of night time.
     */
    public void setNightTimeStartHour(int nightTimeStartHour) {
        CustomClock customClock = getClock();
        customClock.setNightTimeStartHour(nightTimeStartHour);
    }

    /**
     * Retrieves the power level that is set for day time.
     *
     * @return The power level used during day time.
     */
    public int getDayTimePowerLevel() {
        CustomClock customClock = getClock();
        return customClock.getDayTimePowerLevel();
    }

    /**
     * Sets the power level for day time in the custom clock settings.
     *
     * @param dayTimePowerLevel The power level to set for day time.
     */
    public void setDayTimePowerLevel(int dayTimePowerLevel) {
        CustomClock customClock = getClock();
        customClock.setDayTimePowerLevel(dayTimePowerLevel);
        repository.save(customClock);
    }

    /**
     * Retrieves the power level that is set for night time.
     *
     * @return The power level used during night time.
     */
    public int getNightTimePowerLevel() {
        CustomClock customClock = getClock();
        return customClock.getNightTimePowerLevel();
    }

    /**
     * Sets the power level for night time in the custom clock settings.
     *
     * @param nightTimePowerLevel The power level to set for night time.
     */
    public void setNightTimePowerLevel(int nightTimePowerLevel) {
        CustomClock customClock = getClock();
        customClock.setNightTimePowerLevel(nightTimePowerLevel);
        repository.save(customClock);
    }

    /**
     * Advances the current time of the custom clock by a given number of milliseconds
     * and updates the power level based on the new time.
     *
     * @param millis The number of milliseconds to advance the clock by.
     */
    public void advanceTime(long millis) {
        CustomClock customClock = getClock();
        customClock.setCurrentTimeMillis(customClock.getCurrentTimeMillis() + millis);
        repository.save(customClock);
        updatePowerLevel();
    }

    /**
     * Determines whether the current time is during day time according to the custom clock settings.
     *
     * @return {@code true} if the current time is during day time, {@code false} otherwise.
     */
    public boolean isDayTime() {
        CustomClock customClock = getClock();
        int hourOfDay = getHourOfDay();
        return hourOfDay >= customClock.getDayTimeStartHour() && hourOfDay < customClock.getNightTimeStartHour();
    }

    /**
     * Calculates the current hour of the day based on the custom clock's time.
     * This is a simplified calculation and does not account for time zones or daylight saving changes.
     *
     * @return The current hour of the day according to the custom clock.
     */
    public int getHourOfDay() {
        // Convert current time in millis to hour of day
        // This is a simplified representation and needs to be adjusted based on time zone and actual date calculations
        CustomClock customClock = getClock();
        return (int) ((customClock.getCurrentTimeMillis() / MILLIS_PER_HOUR) % HOURS_PER_DAY);
    }

    /**
     * Calculates the number of days elapsed since the start time of the custom clock.
     *
     * @return The number of days elapsed according to the custom clock.
     */
    public int getDay() {
        CustomClock customClock = getClock();
        return (int) Math.floor(customClock.getCurrentTimeMillis() / MILLIS_PER_HOUR) / HOURS_PER_DAY;
    }

    /**
     * Gets the next cutoff time in milliseconds when the day turns to night or night turns to day.
     * This calculation is based on the custom clock's settings and is a simplified representation.
     *
     * @return The milliseconds timestamp of the next day or night cutoff time.
     */
    public long getNextDayNightCutOffMillis() {
        // Get the next day/night cutoff time in millis
        // This is a simplified representation and needs to be adjusted based on time zone and actual date calculations
        CustomClock customClock = getClock();
        int hourOfDay = getHourOfDay();
        int day = getDay();
        if (hourOfDay >= customClock.getDayTimeStartHour() && hourOfDay < customClock.getNightTimeStartHour()) {
            // Current time is in day time
            return (long) day * MILLIS_PER_HOUR * HOURS_PER_DAY + (long) customClock.getNightTimeStartHour() * MILLIS_PER_HOUR;
        } else {
            // Current time is in night time
            if (hourOfDay >= customClock.getNightTimeStartHour()) {
                return (long) day * MILLIS_PER_HOUR * HOURS_PER_DAY + (long) (customClock.getDayTimeStartHour() + HOURS_PER_DAY) * MILLIS_PER_HOUR;
            } else {
                return (long) day * MILLIS_PER_HOUR * HOURS_PER_DAY + (long) customClock.getDayTimeStartHour() * MILLIS_PER_HOUR;
            }
        }
    }

    /**
     * Returns the number of milliseconds per hour as defined in the constants.
     *
     * @return The number of milliseconds in one hour.
     */
    public int getMillisPerHour() {
        return MILLIS_PER_HOUR;
    }


    /**
     * Formats and displays the current time information from the custom clock.
     * The information includes the number of elapsed days, the current hour of the day,
     * and the current time in milliseconds. The formatted time is then appended with
     * a confirmation message indicating the display is complete.
     *
     * @return A string representing the formatted time information followed by a confirmation message.
     */
    public String displayTime() {
        CustomClock customClock = getClock();
        StringBuilder sb = new StringBuilder();
        sb.append(getDay()).append("d").append(getHourOfDay()).append("h").append(currentTimeMillis()).append("ms");
        System.out.println(OK_DISPLAY_COMPLETE);
        sb.append(OK_DISPLAY_COMPLETE);
        return sb.toString();
    }
}
