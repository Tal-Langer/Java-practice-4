package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

public class MyController {
	private int cnt = 0;
	@FXML
	private Button b1;

	@FXML
	private Label labal;

	@FXML
	private Button b2;

	@FXML
	void press_two(MouseEvent event) {
		cnt--;
		labal.setText("cnt= " + cnt);
	}

	@FXML
	void press_one(MouseEvent event) {
		cnt++;
		labal.setText("cnt= " + cnt);
	}


	
}
