package gradebook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Programmer: Abhishek Jassal
 * Program: PROG24178 OOP JAVA-2
 * @version 2.1
 * @author Abhishek
 */
public class ProgramsController implements Initializable {

    @FXML
    private TextField txtcourse, txtquiz, txtasmt, txtfinal;
    @FXML
    private Button btsubmit;
    @FXML
    private ComboBox cmb;
    @FXML
    private TextArea txtarea;
    @FXML
    private ImageView imgView;
    @FXML
    private ImageView img2;
    public static int count = 0;
    String course;

    File myFile = new File("records.txt");

    /**
     * This method write the file and add the name of course in combo box
     * @param event 
     */
    @FXML
    private void submit(ActionEvent event) {

        imgView.setImage(new Image(getClass().getResource("biel-morro-kcKiBcDTJt4-unsplash.jpg").toExternalForm()));
        
        try {
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(myFile, true)));
            course = txtcourse.getText();

            double quiz = Double.parseDouble(txtquiz.getText());
            double asmt = Double.parseDouble(txtasmt.getText());
            double finalgrd = Double.parseDouble(txtfinal.getText());

            output.println(course + "," + "Quiz" + "," + quiz + "\n"
                    + course + "," + "Assignment" + "," + asmt + "\n"
                    + course + "," + "Finals" + "," + finalgrd);

            cmb.getItems().addAll(course);
            count++;

            output.close();
            System.out.println("Record Created in the File");
        } catch (IOException e) {
            System.out.println("error in file write");
        }
    }

    /**
     * This method read the file and print its content in text area
     * @param url
     * @param rb 
     * cmb.setOnAction(e -> {//expression})
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cmb.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                for (int i = 0; i < count; i++) {
                    if (cmb.getValue().equals(course)) {

                        try {
                            Scanner input = new Scanner(myFile);
                            if (myFile.exists()) {
                                while (input.hasNext()) {
                                    String one = input.next();
                                    String two = input.next();
                                    String three = input.next();
                                    Scanner sc = new Scanner(one);
                                    Scanner in = new Scanner(two);
                                    Scanner p = new Scanner(three);
                                    sc.useDelimiter(",");
                                    in.useDelimiter(",");
                                    p.useDelimiter(",");
                                    String course1 = sc.next();
                                    String quiz1 = sc.next();
                                    double quiz = sc.nextDouble();

                                    String course2 = in.next();
                                    String asmt1 = in.next();
                                    double asmt = in.nextDouble();

                                    String course3 = p.next();
                                    String final1 = p.next();
                                    double finalgrd = p.nextDouble();

                                    double total = (quiz + asmt + finalgrd);

                                    txtarea.setText("");
                                    txtarea.appendText("Course\t\t\tAssessment\t\t\tGrades\n");
                                    txtarea.appendText(course1 + "\t\t\t" + quiz1 + "\t\t\t\t\t" + quiz + "\n");
                                    txtarea.appendText(course2 + "\t\t\t" + asmt1 + "\t\t\t" + asmt + "\n");
                                    txtarea.appendText(course3 + "\t\t\t" + final1 + "\t\t\t\t" + finalgrd + "\n\n");
                                    txtarea.appendText("Total\t\t\t\t\t\t\t\t" + total + "\n\n\n");

                                }//end of while block

                            }//end of if block

                            input.close();

                        }//end of try block
                        catch (IOException e) {
                            System.out.println("error in file read");
                        }//end of catch block
                        catch (InputMismatchException ex) {
                            System.out.println("invalid input");
                        }//end of catch block
                    }//end of if block 
                }//end of for loop
            }//end of handle method

        }//end of on action
        );//end of setonAction brackets
    }//end of initialize
} //end of class

