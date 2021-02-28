package simpleFX;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class vote extends Application {
	int cnt = 0;

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(createRoot());
		stage.setScene(scene);
		stage.show();
	}

	private GridPane createRoot() {
		GridPane root = new GridPane();
		root.setPadding(new Insets(20));
		root.setHgap(50);// width padding
		root.setVgap(15);// Height padding

		/* create buttons */
		Button b1 = new Button("Ofra Haza");
		Button b2 = new Button("Yardena Arazi");

		/* create label */
		Label label = new Label("Cnt=0");
		label.setAlignment(Pos.CENTER);// text in the middle

		/* create color for label */
		BackgroundFill bgFill = new BackgroundFill(Color.PINK, new CornerRadii(10), null);
		Background bg = new Background(bgFill);
		label.setPadding(new Insets(20, 10, 20, 10));
		label.setBackground(bg);
		label.setMaxWidth(Double.MAX_VALUE);// Stretch on all stag
		/* change font */
		label.setFont(new Font("Ariel", 30));
		b1.setFont(new Font("Ariel", 30));
		b2.setFont(new Font("Ariel", 30));

		/* add Node's */
		root.add(b1, 0, 0);
		root.add(b2, 1, 0);
		root.add(label, 0, 2, 2, 1);

		/* create b1 mouse press handler */
		class LabelIncreaser implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent event) {
				cnt++;
				label.setText("Cnt= " + cnt);
			}
		}// LabelIncreaser class

		/* create b2 mouse press handler */
		class LabelDecreaser implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent event) {
				cnt--;
				label.setText("Cnt= " + cnt);
			}
		}// LabelDecreaser class

		/* set handlers */
		b1.setOnAction(new LabelIncreaser());
		b2.setOnAction(new LabelDecreaser());

		/* create listener for label */
		root.widthProperty().addListener((obs, oldVal, newVal) -> {
			double width = (double) newVal;
			label.setPrefWidth(width);
		});

		return root;
	}// create root

	public static void main(String[] args) {
		launch(args);
	}
}
