
package project3;

import java.text.DecimalFormat;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *  The TipCalculator class definition. A JavaFX application that produces a form
 * which allows the calculation of a tip (15-25%) off of orders.
 * 
 * @author Andy Osorio / Justin Moran / Thomas Lawless 
 * SCCC CST242, Spring 2019
 */
public class TipCalculator extends Application {
    
    private Label lblHeader;
    private Label lblResults;
    private Label lblItem;
    private Label lbltip;
    private Label lblTotalTip;
    private Label lblPrice;
    private Label lblTip;
    
    private ObservableList<String> item;
    private ObservableList<Order> order;
    
    private ListView listView;
    
    private Slider percentTip;
    
    private VBox vBoxListView;
    private VBox vBoxResults;
    private VBox vBoxTipSider;
    private VBox vBoxTitledPane;
    
    private TitledPane titledPaneOrder;
    private TitledPane titledPaneTipSlider;
    
    private DecimalFormat twoDecimals;
    
    private Button btnCalculate;
   
    private FlowPane flowTip;
  
    
       @Override
    public void start(Stage primaryStage) 
    {
         twoDecimals = new DecimalFormat("0.00");
         
//        Make the heading Label and format

        lblHeader = new Label("Tip calculator");
        lblHeader.setMinWidth(790);
        lblHeader.setAlignment(Pos.CENTER);
        lblHeader.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 28px; -fx-text-fill: white; -fx-background-color: firebrick; -fx-font-weight: bold;");

//       Instantiate 10 orders objects
        
        Order order1 = new Order("Pizza Slice", 6.50);
        Order order2 = new Order("Pasta Dish", 9.25);
        Order order3 = new Order("Burger and Fries", 8.50);
        Order order4 = new Order("Rare Steak", 20.00);
        Order order5 = new Order("Hot Wings", 11.50);
        Order order6 = new Order("Fried Rice Dish", 13.50);
        Order order7 = new Order("Quesadilla and Tortilla Chips", 13.50);
        Order order8 = new Order("Fresh Sushi",  15.00);
        Order order9 = new Order("Curry Chicken Dish", 12.00);
        Order order10 = new Order("Italian Hero",  10.00);
        
//        Instantiate on ObservableList of item from the order class
//        Instantiate an ObservableList of order objects
        
        item = FXCollections.observableArrayList( order1.getItem(), 
                order2.getItem(), order3.getItem(), order4.getItem(), 
                order5.getItem(), order6.getItem(), order7.getItem(), 
                order8.getItem(), order9.getItem(), order10.getItem() );

        order = FXCollections.observableArrayList(order1, order2, order3, order4, 
                order5, order6, order7, order8, order9, order10);
        
//        Create a ListView from the ObservableList 
//        Place the ListView into the "ListView" VBox
        
        listView = new ListView(item);
        listView.getSelectionModel().select(0);
        listView.setOnMouseClicked( e -> updateSelectedOrder(e) );         
        
        vBoxListView = new VBox(listView);
        vBoxListView.setPadding( new Insets(10) );
        vBoxListView.setPrefWidth(300);
        
//        Place the vBoxListView into the first VBox and the VBox into a TitledPane
        
        titledPaneOrder = new TitledPane("Order", vBoxListView);
        
//        Instantiate and format the "Tip Slider" Slider and the elements linked to it
//        The "Tip Slider" Slider and Label are placed side by side in a FlowPane
        
        percentTip = new Slider(15.0, 25.0, 1);//min,max,value
        percentTip.setShowTickMarks(true);
        percentTip.setShowTickLabels(true);
        percentTip.setMajorTickUnit(5.0);
        percentTip.setMinorTickCount(4);
        percentTip.setPrefWidth(350);
        percentTip.setOnMouseDragged( e -> updatePercentTipSlider(e) );
        
        lblTip = new Label(" %" + twoDecimals.format( percentTip.getValue() ) );
        
        flowTip = new FlowPane(percentTip, lblTip);
        
        vBoxTipSider = new VBox(flowTip);
        
//        Place the vBoxTipSider into the second VBox and the VBox into a TitledPane
        
        titledPaneTipSlider = new TitledPane("Tip Slider", vBoxTipSider); 
        
        vBoxTitledPane = new VBox(titledPaneOrder,titledPaneTipSlider);
        
//        Create the "Calculate" Button, and set its event handler
//        Format the Button using CSS formatting
        
        btnCalculate = new Button("    Calculate    ");
        btnCalculate.setStyle("-fx-text-fill: white; -fx-background-color: firebrick; -fx-font-weight: bolder;");
        btnCalculate.setOnAction( e -> calculateTipPrice(e) );
        
        // Create the output Labels and place them into a VBox along with the Button
        // Format the VBox using CSS formatting
        
        lblResults = new Label("Order # ");
        lblResults.setStyle("-fx-font-family: Arial; -fx-font-size: 25px; -fx-font-wight: bolder;");
        lblItem = new Label("Item: \t");
        lblItem.setStyle("-fx-font-family: Consolas; -fx-font-size: 15px; -fx-font-wight: bold;");
        lblPrice = new Label("Price: \t$");
        lblPrice.setStyle("-fx-font-family: Consolas; -fx-font-size: 15px; -fx-font-wight: bold;");
        lbltip = new Label("Tip: \t$");
        lbltip.setStyle("-fx-font-family: Consolas; -fx-font-size: 15px; -fx-font-wight: bold;");
        lblTotalTip = new Label("Total bill: \t$");
        lblTotalTip.setStyle("-fx-font-family: Consolas; -fx-font-size: 20px; -fx-font-wight: bolder;");

        vBoxResults = new VBox(35,lblResults, lblItem,lblPrice, lbltip,lblTotalTip, btnCalculate);
        vBoxResults.setPadding( new Insets(5) );
        vBoxResults.setStyle("-fx-background-color: burlywood; -fx-border-color: black; -fx-border-radius: 10;");
          
//        Instantiate the GridPane
//        Insert the heading Label into the first row
//        Insert the "vBoxTitledPane" and "vBoxResults" VBoxes into the second row

        GridPane grid = new GridPane();

        grid.addRow(0, lblHeader);
        GridPane.setColumnSpan(lblHeader, 2);
        GridPane.setHalignment(lblHeader, HPos.CENTER);

        grid.addRow(1, vBoxTitledPane, vBoxResults);
        
//        Instantiate the Scene, set the properties for the Stage and set the Scene into it
//        Show the PrimaryStage
        Scene scene = new Scene(grid, 800, 500);
        
        primaryStage.setTitle("Tip Calculator");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    
}

    /**
     * Gets the index from the Order "item" ListView control and uses it to
     * access a matching element from the "order" ObservableList and displays
     * that element in the "results" Label.
     * @param e 
     */
    private void updateSelectedOrder(MouseEvent e) {
        int orderNumber = listView.getSelectionModel().getSelectedIndex() + 1;
        int selectedOrder = listView.getSelectionModel().getSelectedIndex();
        double price = order.get(selectedOrder).getPrice();
        double tip = price * (percentTip.getValue() / 100.00);
        
        lblResults.setText("Order # " + Integer.toString(orderNumber));
        lblItem.setText("Item: \t" + order.get(selectedOrder).getItem());
        lblPrice.setText("Price: \t$" + twoDecimals.format(price));
        lbltip.setText("Tip: \t$" + twoDecimals.format(tip));
    }
    
    /**
     * Updates the Label for the percent on tip when a MouseDragged event occurs for 
     * the "Tip slider" Slider.
     * @param e a MouseEvent
     */
    private void updatePercentTipSlider(MouseEvent e) {
        lblTip.setText(" %" + twoDecimals.format( percentTip.getValue() ) );
        
        int selectedOrder = listView.getSelectionModel().getSelectedIndex();
        double price = order.get(selectedOrder).getPrice();
        double tip = price * (percentTip.getValue() / 100.00);
        double total = price + tip;
        lbltip.setText("Tip: \t$" + twoDecimals.format(tip));
    }
    
    /**
     *  Evaluates which selected order and tip percentage are selected, and calculates
     * and displays the item, price,tip and total bill 
     * @param e an ActionEvent
     */
    private void calculateTipPrice(ActionEvent e) {
        int selectedOrder = listView.getSelectionModel().getSelectedIndex();
        double price = order.get(selectedOrder).getPrice();
        double tip = price * (percentTip.getValue() / 100.00);
        double total = price + tip;
        
        lblTotalTip.setText("Total bill: \t$" + twoDecimals.format(total));
    }
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
