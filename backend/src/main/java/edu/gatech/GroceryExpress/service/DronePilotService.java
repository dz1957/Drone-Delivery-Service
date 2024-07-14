package edu.gatech.GroceryExpress.service;
import edu.gatech.GroceryExpress.entity.Drone;
import edu.gatech.GroceryExpress.entity.DronePilot;
import edu.gatech.GroceryExpress.repository.DronePilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static edu.gatech.GroceryExpress.util.Constant.*;

/**
 * Service class for managing drone pilot entities. This class provides methods for creating
 * and updating drone pilots, as well as displaying their information.
 * 
 * @author Team 30
 * @since Dec 1th, 2023
 */
@Service
public class DronePilotService {
    @Autowired
    private DronePilotRepository repository;

    /**
     * Creates a new drone pilot with the provided details and stores it in the repository.
     * This method also checks for duplicate pilot accounts and license IDs.
     *
     * @param pilotAccount The account identifier for the new pilot.
     * @param firstName    The first name of the pilot.
     * @param lastName     The last name of the pilot.
     * @param phoneNumber  The phone number of the pilot.
     * @param taxId        The tax ID of the pilot.
     * @param licenseId    The license ID of the pilot.
     * @param expLevel     The initial experience level of the pilot.
     * @return A string indicating the result of the creation process, such as an error message or success confirmation.
     */
    public String makePilot(String pilotAccount, String firstName, String lastName,
                            String phoneNumber, String taxId, String licenseId, int expLevel) {
        TreeMap<String, DronePilot> pilots = getDronePilotsAsTreeMap();
        if (pilots.containsKey(pilotAccount)) {
            System.out.println(ERR_PILOT_ID_EXIST);
            return ERR_PILOT_ID_EXIST;
        } else {
            boolean foundDup = false;
            for (Map.Entry<String, DronePilot> entry : pilots.entrySet()) {
                if (entry.getValue().getLicenseId().equals(licenseId)) {
                    foundDup = true;
                    break;
                }
            }
            if (foundDup) {
                System.out.println(ERR_PILOT_LIC_ID_EXIST);
                return ERR_PILOT_LIC_ID_EXIST;
            } else {
                DronePilot pilot =
                        new DronePilot(pilotAccount, firstName, lastName, phoneNumber,
                                taxId, licenseId, expLevel);
                repository.save(pilot);
                System.out.println(OK_CHG_COMPLETE);
                return OK_CHG_COMPLETE;
            }
        }
    }

    /**
     * Sets the experience level for a given drone pilot and updates the repository.
     *
     * @param pilot    The drone pilot whose experience level is to be set.
     * @param expLevel The new experience level to be assigned to the pilot.
     */
    public void setExpLevel(DronePilot pilot, int expLevel) {
        pilot.setExpLevel(expLevel);
        repository.save(pilot);
    }

    /**
     * Increases the experience level of a drone pilot by one and updates the repository.
     *
     * @param pilot The drone pilot whose experience level is to be incremented.
     */
    public void addOneExpLevel(DronePilot pilot) {
        pilot.setExpLevel(pilot.getExpLevel() + 1);
        repository.save(pilot);
    }

    /**
     * Retrieves the experience level of a given drone pilot.
     *
     * @param pilot The drone pilot whose experience level is to be retrieved.
     * @return The experience level of the pilot.
     */
    public int getExpLevel(DronePilot pilot) {
        return pilot.getExpLevel();
    }

    /**
     * Assigns a drone to a drone pilot and updates the repository.
     *
     * @param pilot The drone pilot to whom the drone is to be assigned.
     * @param drone The drone to be assigned to the pilot.
     */
    public void setDrone(DronePilot pilot, Drone drone) {
        pilot.setDrone(drone);
        repository.save(pilot);
    }

    /**
    * Formats and displays the information of a single drone pilot.
    *
    * @param dronePilot The drone pilot whose information is to be displayed.
    * @return A string representing the formatted information of the drone pilot.
    */
    public String display(DronePilot dronePilot) {
        String pilotInfo = "name:" + dronePilot.getFirstName() + "_" + dronePilot.getLastName() + ",phone:" + dronePilot.getPhoneNumber() +
                ",taxID:" + dronePilot.getTaxId() + ",licenseID:" + dronePilot.getLicenseId() + ",experience:" + dronePilot.getExpLevel();
        System.out.println(pilotInfo);
        return pilotInfo;
    }

    /**
     * Displays information for all drone pilots sorted in alphabetical order.
     *
     * @return A string representation of all drone pilots' information, separated by new lines.
     */
    public String displayPilots() {
        TreeMap<String, DronePilot> pilots = getDronePilotsAsTreeMap();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, DronePilot> entry : pilots.entrySet()) {
            sb.append(display(entry.getValue())).append('\n');
        }
        System.out.println(OK_DISPLAY_COMPLETE);
        sb.append(OK_DISPLAY_COMPLETE);
        return sb.toString();
    }

    /**
     * Retrieves all drone pilots from the repository and returns them as a TreeMap sorted
     * alphabetically by pilot account.
     *
     * @return A TreeMap where each key is a drone pilot's account and the value is the DronePilot object.
     */
    public TreeMap<String, DronePilot> getDronePilotsAsTreeMap() {
        List<DronePilot> dronePilotsLst = repository.findAll();
        TreeMap<String, DronePilot> dronePilots = new TreeMap<>();
        for (DronePilot dronePilot : dronePilotsLst) {
            dronePilots.put(dronePilot.getAccount(), dronePilot);
        }
        return dronePilots;
    }
}
