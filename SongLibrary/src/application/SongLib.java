/*
 * Authors: Liam Clarke & Manav Mistry
 * CS213 with Professor Venugopal
 * 
 */

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class SongLib extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("layout.fxml"));
			Scene scene = new Scene(root,500,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
