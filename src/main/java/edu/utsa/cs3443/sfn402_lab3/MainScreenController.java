package edu.utsa.cs3443.sfn402_lab3;

import edu.utsa.cs3443.sfn402_lab3.model.AidShip;
import edu.utsa.cs3443.sfn402_lab3.model.AidShipManager;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

/**
 * Handles user actions on the aid ship management screen.
 *
 * @author Brian Caloca
 */
public class MainScreenController {

    @FXML
    private TextArea registrationTextArea;

    @FXML
    private TextArea resultsTextArea;

    @FXML
    private RadioButton findRadioButton;

    @FXML
    private RadioButton deleteRadioButton;

    private final AidShipManager manager = new AidShipManager();

    /**
     * Groups the radio buttons so only one operation can be selected.
     */
    @FXML
    private void initialize() {
        ToggleGroup operationGroup = new ToggleGroup();
        findRadioButton.setToggleGroup(operationGroup);
        deleteRadioButton.setToggleGroup(operationGroup);
    }

    /**
     * Loads and displays all aid ships.
     */
    @FXML
    private void onListShipsClick() {
        try {
            manager.loadAidShips();

            if (manager.getAidShipList().isEmpty()) {
                resultsTextArea.setText("No aid ships are available.");
            } else {
                resultsTextArea.setText(manager.toString());
            }
        } catch (IOException e) {
            resultsTextArea.setText(
                    "Unable to load ship data: " + e.getMessage());
        }
    }

    /**
     * Finds or deletes a ship using the entered registration number.
     */
    @FXML
    private void onGoClick() {
        if (!findRadioButton.isSelected()
                && !deleteRadioButton.isSelected()) {
            resultsTextArea.setText("Please select Find or Delete.");
            return;
        }

        String registration = registrationTextArea.getText().trim();

        if (registration.isEmpty()) {
            resultsTextArea.setText("Please enter a registration number.");
            return;
        }

        try {
            manager.loadAidShips();
            AidShip ship = manager.findAidShip(registration);

            if (ship == null) {
                resultsTextArea.setText(
                        "Ship not found: " + registration);
                return;
            }

            if (findRadioButton.isSelected()) {
                resultsTextArea.setText(ship.toString());
            } else {
                boolean deleted = manager.deleteAidShip(ship);

                if (deleted) {
                    resultsTextArea.setText(
                            "Ship deleted: " + registration
                                    + "\nChanges saved to data/aid_ships.csv.");
                } else {
                    resultsTextArea.setText("The ship could not be deleted.");
                }
            }
        } catch (IOException e) {
            resultsTextArea.setText(
                    "Unable to read or save ship data: " + e.getMessage());
        }
    }
}
