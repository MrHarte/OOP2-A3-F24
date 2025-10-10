/**
 * Champlain College Lennoxville OOP2 Assignment 3 base code.
 * @author Matthias Harte
 */
module com.champlain.oop2assignment3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;


    opens com.champlain.oop2assignment3 to javafx.fxml;
    exports com.champlain.oop2assignment3;
}