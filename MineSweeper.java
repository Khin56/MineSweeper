package myfx;

import java.util.Optional;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MineSweeper extends Application{
	 TilePane board = new TilePane();
	 BorderPane root = new BorderPane();
	 int count = 20;
	 int mine = count*40/100, land = count-mine, found= 0;
	 Label lblMsg = new Label("Found: 0");
	 public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage st) throws Exception {
		root.setBottom(lblMsg);
		root.setStyle("-fx-font-size: 24; -fx-padding: 5;");
		board.setPrefColumns(5);
		board.setStyle("-fx-hgap: 1; -fx-vgap: 1; -fx-background-color: black;");
		
		for (int i = 0; i < count; i++) {
			Button b = new Button();
			b.setPrefSize(60, 60);
			b.setUserData(i<mine);
			
			b.setOnAction(e-> action(b));
			
			board.getChildren().addAll(b);
		
		}
		

		FXCollections.shuffle(root.getChildren()); // shufflling root children 
		
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.setTitle("Mine Sweeper");
		st.show();
		
		
	}
	
	int count = 0;
	public void action(Button clicked) {
		String data = (String)clicked.getUserData();
		clicked.setText(data);
		clicked.setDisable(true);
		
			count++;
			if (count==20) {
				Optional<ButtonType> rsp = new Alert(AlertType.CONFIRMATION,
						"You win!\n Want another round?",
						ButtonType.YES, ButtonType.NO).showAndWait(); 
					
				if (rsp.isPresent() && rsp.get().equals(ButtonType.YES)) {
					reset();
				}
			} 

		}
//	}
	public void reset() {
		lbl.setStyle("-fx-background-radius: 30%;");
		lbl.setTextFill(Color.BLACK);
		for (Node n : root.getChildren()) {
			Button b = (Button) n;
			b.setDisable(false);
			b.setText("");
		}
		FXCollections.shuffle(root.getChildren());
		count = 0;
		
	}
}
