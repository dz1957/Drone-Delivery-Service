package edu.gatech.GroceryExpress.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static edu.gatech.GroceryExpress.util.Constant.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CustomClockTable")
public class CustomClock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;
    private long currentTimeMillis;

    private int powerLevel;

    private int dayTimeStartHour;

    private int nightTimeStartHour;

    private int dayTimePowerLevel;

    private int nightTimePowerLevel;

    public CustomClock(long initialTimeMillis) {
        this.currentTimeMillis = initialTimeMillis;// update to self defined time
        this.powerLevel = 0;
        this.dayTimeStartHour = DEFAULT_DAY_TIME_START_HOUR;
        this.nightTimeStartHour = DEFAULT_NIGHT_TIME_START_HOUR;
        this.dayTimePowerLevel = DEFAULT_DAY_TIME_POWER_LEVEL;
        this.nightTimePowerLevel = DEFAULT_NIGHT_TIME_POWER_LEVEL;
    }
}
