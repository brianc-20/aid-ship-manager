module edu.utsa.cs3443.sfn402_lab3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.utsa.cs3443.sfn402_lab3 to javafx.fxml;
    exports edu.utsa.cs3443.sfn402_lab3;
}