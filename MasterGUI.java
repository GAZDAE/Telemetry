import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border; 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MasterGUI extends Application {
	
	public Client client;
	
	// Pane
	private BorderPane borderPane, startBorderPane;
	private Button btOpen, btConnect;
	private TextField txFileName, txFileType;
	
	// Menu
	private MenuBar menuBar; // MenuBar
	private Menu menuHelp, menuFile, menuSettings; // Menus
	private MenuItem miSave, miOpen, miHelp, miConnections, miPlotData;
	//VBox for Status Display
	private VBox vBox;
	private Label lbTitle, lbFileName, lbFileType;
	private CheckBox cbConnection;
	
	// TabPane
	private TabPane tabPanePlots;
	private Tab Plot1, Plot2;
	//plotGridPane
	private GridPane plotGridPane, startGridPane;

	
	private Axis<Number> xAxis;
	private Axis<Number> yAxis;
	private LineChart<Number,Number> lineChart;
	
	//launches the GUI
	public static void main(String[] args) { launch(args);}
	
	public MasterGUI(){
		
		startBorderPane = new BorderPane();
		startGridPane = new GridPane();
		lbFileName = new Label("File Name:");
		txFileName = new TextField();
		lbFileType = new Label("File Type:");
		txFileType = new TextField();
		btConnect = new Button("Connect");
		btConnect.setOnAction(args0 -> {
			try {
				Connect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		btOpen = new Button("Open Program");
		btOpen.setOnAction(arg0 -> showMainUI());
		startGridPane.setHgap(10);
		startGridPane.setVgap(10);
		startGridPane.add(lbFileName, 1, 1);
		startGridPane.add(txFileName, 2, 1);
		startGridPane.add(lbFileType, 1, 2);
		startGridPane.add(txFileType, 2, 2);
		startGridPane.add(btConnect, 1, 3);
		startGridPane.add(btOpen, 1, 4);
		startBorderPane.setCenter(startGridPane);
		
	
		
		// Create the BorderPane
		borderPane = new BorderPane();
		//menu
		menuBar = new MenuBar();
		menuHelp = new Menu("Help");
		menuFile = new Menu("File");
		menuSettings = new Menu("Settings");
		miSave = new MenuItem("Save");
		miOpen = new MenuItem("Open");
		miHelp = new MenuItem("Help");
		miConnections = new MenuItem("Connections");
		miPlotData = new MenuItem("Plot");
		menuFile.getItems().addAll(miSave, miOpen);
		menuHelp.getItems().addAll(miHelp);
		menuSettings.getItems().addAll(miConnections, miPlotData);
		menuBar.getMenus().addAll(menuFile, menuSettings, menuHelp);
		borderPane.setTop(menuBar);
		
		miHelp.setOnAction(arg0 -> showHelp());
		miConnections.setOnAction(arg0 -> showConnections());
		
		//Status Display
		vBox = new VBox();
		lbTitle = new Label("Status"); //lbTitle.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5) )));
		lbTitle.setStyle("-fx-padding: 8 30 10 50; -fx-background-color: #f8ecc2; -fx-border-style: solid; -fx-border-width: 3");
		lbTitle.setFont(Font.font("Ariel", FontWeight.BOLD, 30));
		lbTitle.setMaxWidth(200);
		cbConnection = new CheckBox("Connection Status");
		cbConnection.setStyle("-fx-padding: 5 10 5 30");
		vBox.getChildren().addAll(lbTitle, cbConnection);
		vBox.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5) )));
		vBox.setMinWidth(200);
		//vBox.setStyle(Color.SKYBLUE);
		borderPane.setLeft(vBox);
		
		//tabs for plot display/ data/ TBA
		tabPanePlots = new TabPane();
		Plot1 = new Tab("Plot1");		
		Plot2 = new Tab("Plot2");
		tabPanePlots.getTabs().addAll(Plot1, Plot2);
		Plot1.setClosable(false);
		borderPane.setCenter(tabPanePlots);
		
		
		/*************************************/
		//defining the axes
	    xAxis = new NumberAxis();
	    yAxis = new NumberAxis();
	    yAxis.setLabel("Velocity");
	    xAxis.setLabel("Time");
	    //creating the chart
	    lineChart = new LineChart<Number,Number>(xAxis,yAxis);
	    lineChart.setTitle("Velocity vs Time");
	    //defining a series
	    XYChart.Series series = new XYChart.Series();
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
	        
        lineChart.getData().add(series);
      //Plots in tabPane Plot1
      		plotGridPane = new GridPane();
      		Plot1.setContent(plotGridPane);
      		plotGridPane.add(lineChart, 1, 1);
	}
	
	//displays the scene, makes GUI editable
	public void start(Stage stage) throws Exception {
	
		// Set the scene and the stage
		Scene scene = new Scene(startBorderPane, 1000, 600);
		stage.setScene(scene);
		stage.setTitle("Telemetry Master Interface");
		stage.setResizable(false);
		
		
				
		// Display the GUI
		stage.show();
	}
	public void showMainUI(){
		// Set the scene and the stage
				Scene scene = new Scene(borderPane, 1000, 600);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Telemetry Master Interface");
				stage.setResizable(false);
						
				// Display the GUI
				stage.show();
	}
	private void showHelp(){
		final String aboutText = "blah blah blah";
	
		// Create the text label
		Label aboutLabel = new Label();
		aboutLabel.setWrapText(true);
		aboutLabel.setTextAlignment(TextAlignment.CENTER);
		aboutLabel.setFont(Font.font("Times New Roman", 14));
		aboutLabel.setText(aboutText);
	
		// Add the label to a StackPane
		StackPane pane = new StackPane();
		pane.getChildren().add(aboutLabel);
	
	
		// Create and display said the aforementioned pane in a new stage
		Scene scene = new Scene(pane, 550, 100);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("How to use this program");
		stage.setResizable(false);
		stage.show();
	}
	private void showConnections(){
		
		GridPane connectionGrid = new GridPane();
		connectionGrid.setAlignment(Pos.CENTER);
		
		// Create the text label
		Label connectionStat = new Label("Connection Status");
		connectionStat.setTextAlignment(TextAlignment.LEFT);
		connectionStat.setFont(Font.font("Times New Roman", 14));
		connectionGrid.add(connectionStat, 1, 1);
		
	
		// Create and display said the aforementioned pane in a new stage
		Scene scene = new Scene(connectionGrid, 800, 500);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Connections Settings");
		stage.setResizable(false);
		stage.show();
	}
	public void Connect() throws IOException{
		
		client = new Client(9040);
		client.startComm();
		client.writeToFile();
		if (client.isConnected() == true){
			System.out.println(client.isConnected());
			btOpen.setStyle("-fx-base: #b6e7c9");
		}
		else{
			System.out.println(client.isConnected());
			btOpen.setStyle("-fx-base: #b6e7c9");
		}
		
		
	}
}
	
