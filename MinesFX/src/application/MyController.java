package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.FormatFlagsConversionMismatchException;
import java.util.List;
import java.util.Timer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MyController {

	int height_size;
	int width_size;
	int mines_size;
	Mines gameMines;
	GridPane small_grid;
	@FXML
	private GridPane root;

	@FXML
	private Button reset_button;

	@FXML
	private TextField width;

	@FXML
	private TextField height;

	@FXML
	private TextField mines;
	@FXML
	private Label counter_flag;

	@FXML
	
	void reset(MouseEvent event) {
		/* make game mines with user input parameters */
		height_size = Integer.parseInt(height.getText());
		width_size = Integer.parseInt(width.getText());
		mines_size = Integer.parseInt(mines.getText());
		gameMines = new Mines(height_size, width_size, mines_size);
		/*add background*/
		BackgroundFill bgFill = new BackgroundFill(Color.CADETBLUE, new CornerRadii(10), null);
		Background bg = new Background(bgFill);
		root.setBackground(bg);
		root.getChildren().remove(small_grid);
		/* create buttons grid */
		small_grid = new GridPane();
		for (int i = 0; i < height_size; i++)// create buttons
			for (int j = 0; j < width_size; j++) {
				Button button = new Button();
				button.setOnMouseClicked(new button_press(i, j));
				button.setMinSize(40, 40);
				button.setMaxSize(40, 40);
				small_grid.add(button, j, i);
			}
		get_buttons_toString();
		root.add(small_grid, 1, 1, 3, 1);// add buttons grid to the root

	}// reset

	private void get_buttons_toString() {

		List<javafx.scene.Node> list = small_grid.getChildren();
		for (javafx.scene.Node b : list) {
			String t = "";
			int i = small_grid.getRowIndex(b);
			int j = small_grid.getColumnIndex(b);
			t += gameMines.board[i][j].toString();
			((Labeled) b).setFont(Font.font("Ariel", FontWeight.BOLD, 20));
			change_color(t, b);
			((Labeled) b).setText(t);

		}
	}// get_buttons_toString()

	private void change_color(String t, javafx.scene.Node b) {
		Image image = new Image(getClass().getResourceAsStream("mokesh1.png"), 30, 30, true, true);
		ImageView imageView = new ImageView(image);
		Image image1 = new Image(getClass().getResourceAsStream("flag.jpg"), 30, 30, true, true);
		ImageView imageView1 = new ImageView(image1);
		((Labeled) b).setGraphic(null);// remove image flag
		int i = small_grid.getRowIndex(b);
		int j = small_grid.getColumnIndex(b);
		if (t.equals("1"))
			((Labeled) b).setTextFill(Color.ORANGERED);
		if (t.equals("2"))
			((Labeled) b).setTextFill(Color.BLACK);
		if (t.equals("3"))
			((Labeled) b).setTextFill(Color.GREEN);
		if (t.equals("4"))
			((Labeled) b).setTextFill(Color.FUCHSIA);
		if (t.equals("5"))
			((Labeled) b).setTextFill(Color.RED);
		if (t.equals("6"))
			((Labeled) b).setTextFill(Color.BROWN);
		if (t.equals("7"))
			((Labeled) b).setTextFill(Color.YELLOWGREEN);
		if (t.equals("8"))
			((Labeled) b).setTextFill(Color.PURPLE);
		if (t.equals("X"))
			((Labeled) b).setGraphic(imageView);
		if (t.equals("F")) {
			((Labeled) b).setGraphic(imageView1);

		}
	}

	class button_press implements EventHandler<MouseEvent> {
		int i, j;

		public button_press(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public void handle(MouseEvent event) {
			if (event.getButton() == MouseButton.SECONDARY) {
				gameMines.toggleFlag(i, j);
				get_buttons_toString();
			} else {
				gameMines.open(i, j);
				get_buttons_toString();
				if (gameMines.isDone())
					Win_msg();
			}

		}

		private void Win_msg() {

			Label label = new Label("You have won!!!");
			label.setAlignment(Pos.CENTER);// text in the middle

			/* create color for label */
			BackgroundFill bgFill = new BackgroundFill(Color.PINK, new CornerRadii(10), null);
			Background bg = new Background(bgFill);
			label.setPadding(new Insets(20, 10, 20, 10));
			label.setBackground(bg);
			label.setMaxWidth(Double.MAX_VALUE);// Stretch on all stag
			/* change font */
			label.setFont(new Font("Ariel", 30));
			small_grid.getChildren().clear();
			small_grid.add(label, 1, 1);

		}
	}// button_press class

}// class
