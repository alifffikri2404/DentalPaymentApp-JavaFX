import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DentalPaymentApp extends Application {

    // declaring the controls
    Stage window;
    Scene scene1;
    Label formTitle,patientName,service,price;
    Label price1,price2,price3,price4,price5, totalPrice;
    TextField inputOther,totalOutput,inputName;
    Button calcButton;
    CheckBox item1,item2,item3,item4,item5,itemOther;
    // ending the declaration

    // we need to declare all controls outside method to allow us to use it dynamically inside this class
    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        //declare all the label and the textField and checkbox
        formTitle = new Label("Dental Payment Entry Form");
        patientName = new Label("Patient Name ");
        service = new Label("Service");
        price = new Label("Price (RM)");
        item1 = new CheckBox("Cleaning");
        item2 = new CheckBox("Cavity Filling");
        item3 = new CheckBox("X-ray");
        item4 = new CheckBox("Flouride");
        item5 = new CheckBox("Root canal");
        itemOther = new CheckBox("Other");
        price1 = new Label("35.00");
        price2 = new Label("150.00");
        price3 = new Label("85.00");
        price4 = new Label("50.00");
        price5 = new Label("255.00");
        totalPrice = new Label("Total");
        inputOther = new TextField();
        totalOutput = new TextField();
        inputName = new TextField();
        calcButton=new Button("Calculate");

        // set the textfield, totalOutput to be not editable
        totalOutput.setEditable(false);

        // set as main pane
        VBox paneMain = new VBox();

        HBox patient = new HBox(10);
        HBox detail = new HBox(150);
        VBox itemList = new VBox(10);
        HBox totalCalculator = new HBox(5);
        VBox priceList = new VBox(10);
        VBox bottomPart = new VBox(5);

        // designing pane, set padding for all the panes
        Insets insets = new Insets(10,10,10,10);
        paneMain.setPadding(new Insets(10,0,0,0));
        detail.setPadding(new Insets(0,60,0,0));
        itemList.setPadding(insets);
        priceList.setPadding(insets);
        bottomPart.setPadding(insets);
        totalCalculator.setPadding(new Insets(0,60,0,0));
        patient.setPadding(insets);

        // set the alignment for some of the panes
        paneMain.setAlignment(Pos.CENTER);
        patient.setAlignment(Pos.CENTER);
        detail.setAlignment(Pos.CENTER);
        priceList.setAlignment(Pos.CENTER);
        bottomPart.setAlignment(Pos.CENTER_RIGHT);
        totalCalculator.setAlignment(Pos.CENTER_RIGHT);

        // set default size of the text field
        inputOther.setPrefWidth(30);
        totalOutput.setPrefWidth(70);

        // set border for all pane
        paneMain.setStyle("-fx-background-color: #FFFDFA");// set as glossy white color, use color code to get more accurate colour
//        detail.setStyle("-fx-background-color: purple");
//        itemList.setStyle("-fx-background-color: green");
//        priceList.setStyle("-fx-background-color: red");
//        bottomPart.setStyle("-fx-background-color: blue");
//        totalCalculator.setStyle("-fx-background-color: yellow");

        // to set border
        bottomPart.setStyle("-fx-border-color: black");

        // to set the arrangement of 1st layer
        paneMain.getChildren().addAll(formTitle,patient,detail,bottomPart);

        patient.getChildren().addAll(patientName,inputName);

        detail.getChildren().addAll(itemList,priceList);

        bottomPart.getChildren().addAll(totalCalculator,calcButton);

        totalCalculator.getChildren().addAll(totalPrice,totalOutput );

        // to set the arrangement of 2nd layer
        itemList.getChildren().addAll(service,item1,item2,item3,item4,item5,itemOther);

        priceList.getChildren().addAll(price,price1,price2,price3,price4,price5,inputOther);

        //to declare and setup the scene and the window
        scene1=new Scene(paneMain,450,355); // set the paneMain(Vbox) as a to be put on the top of scene1
        //with width :450 and height: 355 ; unit pixel
        window.setScene(scene1);
        window.setTitle("Dental Payment Application");

        // setup the event handler
        calcButton.setOnAction(e-> {
            // text field checking (check before do the operation)
            // set up the AlertBox
            boolean checkName=nameCheck();
            boolean inputPriceCheckFormat=inputPrice();
            // to see all the checkbox are not tick or not,
            // if all not tick yet, alert box will be accessed using else code
            boolean tickAlert=!item1.isSelected()&!item2.isSelected()&!item3.isSelected()&!item4.isSelected()&!item5.isSelected()
                    &!itemOther.isSelected();
            //to check if the checkbox itemOther is selected or not
            boolean inputCheckForOther = itemOther.isSelected();

            // set what message that will alert box display on it
            if (tickAlert) {
                alertBox("PLEASE SELECT AT LEAST 1 ITEM");
            }else{
                if (!checkName && !inputPriceCheckFormat && inputCheckForOther)  // this if section will be access if
                    // the PatientName field is empty and the the priceInput is empty
                    // if and only if the itemOther(checkbox) is ticked
                    alertBox("PLEASE ENTER NAME & " + messageInputAlertBox);
                else if (!checkName) // this if section will be access if only patient name field is empty
                    alertBox("PLEASE ENTER NAME");
                else if (!inputPriceCheckFormat && inputCheckForOther) // this if section will be access if priceInput is empty
                    //if and only if the itemOther(checkbox) is ticked
                    alertBox(messageInputAlertBox);
            }

            // declare new variable
            double item1price = 0.0;
            double item2price = 0.0;
            double item3price = 0.0;
            double item4price = 0.0;
            double item5price = 0.0;
            double totalValue, itemOtherPrice = 0.0;

            //to set the value to the variable(double) if the checkbox is selected
            if (item1.isSelected()){
                item1price = 35.0;
            }
            if (item2.isSelected()){
                item2price = 150.0;
            }
            if (item3.isSelected()){
                item3price = 85.0;
            }
            if (item4.isSelected()){
                item4price = 50.0;
            }
            if (item5.isSelected()){
                item5price = 255.0;
            }if (itemOther.isSelected()){
                itemOtherPrice = inputPriceValue;
            }

            // calculating total value or price
            totalValue = item1price + item2price + item3price + item4price + item5price + itemOtherPrice;
            String totalValueString = "RM" + totalValue;// convert totalValue to the String type
            totalOutput.setText(totalValueString); // display the totalValue inside the totalOutput TextField

        });

        // mark the other-checkbox automatically if the input textField are inserted something
        inputOther.setOnKeyTyped(e->{
            if(inputOther.getText() != null) // we set null to make the space typed also trigger the checkbox
                itemOther.setSelected(true); //to set the checkbox to be ticked
        });
        window.show(); // to show the window(stage)
    }

    // method to validate the input Price,
    // exception will be thrown if the user entered alphabet or symbol or any expression which not represent a number
    private double inputPriceValue;
    private String messageInputAlertBox;
    public boolean inputPrice(){
        if (inputOther.getText().equals("")){ // to check whether the inputOther textField is blank or not
            messageInputAlertBox = "ENTER PRICE";
            inputPriceValue = 0.0;
            return false;
        }else {
            try {
                String inputOtherString = "";
                inputOtherString = inputOther.getText();// get the String(text) from the inputOther(input Price) textField

                // to parse the whole String into double and pass it into inputPriceValue
                inputPriceValue = Double.parseDouble(inputOtherString);

                // to do the numerical check of every single character of the String
                String numericalCheck = inputOtherString;
                for (int i = 0; i < numericalCheck.length(); i++) {
                    // to check each and every letter in the string to see whether it can be transformed into a double variable type
                    // thrown an exception and the whole character will be discarded
                    double testDouble = Double.parseDouble(numericalCheck.charAt(i)+"");
                }

                return true;
            } catch (NumberFormatException e) { // catch the exception if the String is not numeric
                messageInputAlertBox = "ALERT: ENTER PRICE AS NUMBER ONLY!";
                inputPriceValue = 0.0;
                return false;
            }
        }
    }

    // to check whether the patient name has been fill or not
    public boolean nameCheck(){
        if(inputName.getText().equals("")) {
            System.out.println("PLEASE ENTER NAME");
            return false; // false: the txt field of patient name has no input
        }else
            return true; // true: the text field of patient name has input
    }

    // setup alert box, attribute for popup box
    Stage popOutWindow;
    Scene scene2;
    Label label1;
    Button okButton;

    // method to display an alert box
    public void alertBox(String message){
        // designing alert box
        popOutWindow = new Stage(); // stage for popOutWindow

        // set the modality of the window, so this window will be prioritize to be access first
        popOutWindow.initModality(Modality.APPLICATION_MODAL);

        label1 = new Label(message); // labeling the label1
        okButton = new Button("OK");

        // set the style for the label1
        label1.setFont(Font.font ("Verdana", 12));
        label1.setTextFill(Color.WHITE);

        // declaring a VBox to set the row inside the stackPane
        VBox row = new VBox(10); // set the separation of 10 pixel

        // declaring a Pane and set the vbox (row) as the children
        StackPane layout = new StackPane();
        layout.getChildren().add(row);

        // to add button and label1 inside the row (label1 as 1st row and OK button as 2nd row)
        row.getChildren().addAll(label1, okButton);

        // to set alignment of the label and OK Button inside the row
        row.setAlignment(Pos.CENTER);
        // to set the alignment of the row inside the StackPane
        layout.setAlignment(row,Pos.CENTER);

        // to set the color of the pane as green
        layout.setStyle("-fx-background-color: green");

        // to set padding of the scene of alert box
        layout.setPadding(new Insets(10,10,10,10));

        // to set text wrap to the label to make it fit within the stage
        label1.setWrapText(true);

        // setup button event handler
        // to set if the ok button is clicked, the popOutWindow will be closed
        okButton.setOnAction(e->{
            popOutWindow.close();
        });

        // to put the row (outer prefix) inside the scene with - width: 300, height: 200
        scene2 = new Scene(layout,350,100);
        popOutWindow.setScene(scene2);
        popOutWindow.setTitle("POP OUT WINDOW"); // set the title
        popOutWindow.showAndWait(); // make the window will be compulsory to be access first
        // we cannot access the window behind if this window pop out
        // support the modality of this window (was set above)
    }
    public static void main(String[] args) {
        launch(args);
    }
}
