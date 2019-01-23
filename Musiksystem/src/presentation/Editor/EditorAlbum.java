package presentation.Editor;

import domainClasses.Album;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.BravoMusic;
import presentation.Factory;
import presentation.MainSide.Table;
import presentation.PopUp.BackPopUp;
import presentation.PopUp.DeleteAlbumPopUp;

public class EditorAlbum {
	private Button btnAlbumCancel, btnAlbumDelete, btnAlbumCreate, btnAlbumSave, btnAlbumAddSong;
	private TextField tfAlbumName, tfYearOfRelease;
	private TextArea taDescription;
	private RadioButton radioButtonLP, radioButtonCD;
	private EditorSong editorSong;
	private int albumId;

	public VBox start(BorderPane borderpane, Stage editor, Table table, BravoMusic bravoMusic, int albumID) {
		Factory factory = new Factory();
		editorSong = new EditorSong();
		albumId = albumID;

		VBox albumVBox = factory.vBoxFactory(25, 25, 25, 25, Pos.CENTER);
		HBox albumTop = factory.hBoxFactory(25, 0, 0, 25, 0, Pos.BASELINE_LEFT);
		VBox albumTableBot = factory.vBoxFactory(0, 0, 0, 0, Pos.CENTER);
		albumTableBot.setMinHeight(300);
		VBox albumRight = new VBox();
		VBox albumLeft = new VBox();
		HBox choiceBox = factory.hBoxFactory(15, 35, 0, 0, 0, Pos.CENTER);
		VBox albumTitle = factory.vBoxFactory(0, 0, 0, 0, Pos.CENTER);
		HBox btnBox = factory.hBoxFactory(25, 25, 0, 0, 0, Pos.CENTER_LEFT);
		btnBox.setPrefHeight(75);
		HBox toogleErrorMsgHBox = factory.hBoxFactory(0, 0, 0, 0, 0, Pos.CENTER);

		btnAlbumCancel = factory.buttonFactory("Tilbage", 100, false);
		controlVisibilityAndStyleOfBtns(factory);

		Label labelAlbum = factory.labelFactory("Album", 0, 0, 5, 0, 20);
		Label labelDescription = factory.labelFactory("Beskrivelse", 24, 0, 5, 0, 16);
		Label albumName = factory.labelFactory("Albumtitel", 25, 0, 5, 0, 16);
		Label albumYear = factory.labelFactory("Udgivelsesår", 25, 0, 5, 0, 16);
		Label toggleErrorMsg = factory.labelFactory("LP eller CD skal vælges", 5, 0, 0, 0, -1);
		toggleErrorMsg.setTextFill(Color.RED);
		toggleErrorMsg.setVisible(false);
		Label labelAlbumSaved = factory.labelFactory("Albummet er gemt", 60, 0, 0, 0, 16);
		labelAlbumSaved.setStyle("-fx-text-fill: OLIVEDRAB; -fx-font-weight: BOLD");
		labelAlbumSaved.setVisible(false);
		labelAlbumSaved.setMinWidth(362);
		labelAlbumSaved.setAlignment(Pos.CENTER);

		tfAlbumName = factory.textFieldFactory("", 362, 14);
		tfYearOfRelease = factory.textFieldFactory("", 362, 14);
		autofillTfWhenKnownSong(bravoMusic, editorSong);
// TODO --> factory
		tfYearOfRelease.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tfYearOfRelease.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		taDescription = new TextArea();
		taDescription.setPrefSize(362, 1000);
		taDescription.setFont(Font.font(14));
// TODO --> Factory
		ToggleGroup radioGroup = new ToggleGroup();
		radioButtonLP = new RadioButton("LP");
		radioButtonLP.setUserData("LP");
		radioButtonLP.setToggleGroup(radioGroup);
		radioButtonCD = new RadioButton("CD");
		radioButtonCD.setUserData("CD");
		radioButtonCD.setToggleGroup(radioGroup);

		EditorTable editorTable = new EditorTable(albumTableBot, albumId, editorSong);
		editorSong.start(borderpane, bravoMusic, albumId, editorTable);

		toogleErrorMsgHBox.getChildren().add(toggleErrorMsg);
		btnBox.getChildren().addAll(btnAlbumCancel, btnAlbumDelete, btnAlbumCreate, btnAlbumSave, btnAlbumAddSong);
		albumTitle.getChildren().addAll(labelAlbum);
		choiceBox.getChildren().addAll(radioButtonLP, radioButtonCD);
		albumRight.getChildren().addAll(labelDescription, taDescription);
		albumLeft.getChildren().addAll(albumName, tfAlbumName, albumYear, tfYearOfRelease, choiceBox,
				toogleErrorMsgHBox, labelAlbumSaved);
		albumTop.getChildren().addAll(albumLeft, albumRight);
		albumVBox.getChildren().addAll(albumTitle, albumTop, albumTableBot, btnBox);

		btnAlbumCancel.setOnAction(e -> cancelAction(editor, table, bravoMusic));
		btnAlbumDelete.setOnAction(e -> deleteAction(editor, table, bravoMusic));
		btnAlbumCreate.setOnAction(
				e -> createAction(editor, borderpane, bravoMusic, radioGroup, toggleErrorMsg, editorTable));
		btnAlbumSave.setOnAction(e -> saveAction(bravoMusic, radioGroup, toggleErrorMsg, editorTable, labelAlbumSaved));
		btnAlbumAddSong.setOnAction(e -> addNewSongAction());

		return albumVBox;
	}

	private void addNewSongAction() {
		editorSong.clearAndDisableTF();
		editorSong.controlBtnDelete(true);
		editorSong.controlBtnEdit(true);
		editorSong.controlBtnAdd(false);
	}

	public void cancelAction(Stage editor, Table table, BravoMusic bravoMusic) {
		if (bravoMusic.searchMusic("", null, true, true, albumId).size() == 0 && albumId != -1) {
			new BackPopUp().start(bravoMusic, editor, albumId);
		} else {
			editor.hide();
			table.updateTable(bravoMusic.searchMusic("", null, true, true, -2));
		}
	}

	private void deleteAction(Stage editor, Table table, BravoMusic bravoMusic) {
		new DeleteAlbumPopUp().start(bravoMusic, editor, albumId, table);
	}

	private void createAction(Stage editor, BorderPane borderpane, BravoMusic bravoMusic, ToggleGroup radioGroup,
			Label toggleErrorMsg, EditorTable editorTable) {
		if (tfAlbumName.getText().equals("")) {
			tfAlbumName.setPromptText("Skal udfyldes");
			tfAlbumName.setStyle("-fx-border-color: RED");
		} else {
			tfAlbumName.setStyle("-fx-border-color: ");
		}

		boolean isToggleChosen;
		try {
			radioGroup.getSelectedToggle().getUserData();
			isToggleChosen = true;
			toggleErrorMsg.setVisible(false);
		} 
		
		catch (NullPointerException e) {
			toggleErrorMsg.setVisible(true);
			isToggleChosen = false;
		}

		if (!tfAlbumName.getText().equals("") && isToggleChosen) {
			int year;
			if (tfYearOfRelease.getText().equals("")) {
				year = 0;
			} else {
				year = Integer.parseInt(tfYearOfRelease.getText());
			}

			Album album = new Album(-1, tfAlbumName.getText(), radioGroup.getSelectedToggle().getUserData().toString(),
					year, taDescription.getText());
			albumId = bravoMusic.createAlbum(album);

			editorSong.start(borderpane, bravoMusic, albumId, editorTable);
			editorSong.controlDisablingOfCB(false);

			btnAlbumCancel.setDisable(false);
			btnAlbumDelete.setDisable(false);
			btnAlbumCreate.setDisable(true);
			btnAlbumSave.setDisable(false);
			btnAlbumAddSong.setDisable(false);
		}
	}

	private void saveAction(BravoMusic bravoMusic, ToggleGroup radioGroup, Label toggleErrorMsg,
			EditorTable editorTable, Label labelAlbumSaved) {
		int year;

		if (tfAlbumName.getText().equals("")) {
			tfAlbumName.setPromptText("Skal udfyldes");
			tfAlbumName.setStyle("-fx-border-color: RED");
		} else {
			tfAlbumName.setStyle("-fx-border-color: ");

			if (tfYearOfRelease.getText().equals("")) {
				year = 0;
			} else {
				year = Integer.parseInt(tfYearOfRelease.getText());
			}

			Album album = new Album(albumId, tfAlbumName.getText(),
					radioGroup.getSelectedToggle().getUserData().toString(), year, taDescription.getText());
			bravoMusic.editAlbum(album);
			labelAlbumSaved.setVisible(true);

			PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
			visiblePause.setOnFinished(e -> labelAlbumSaved.setVisible(false));
			visiblePause.play();
		}
	}

	private void controlVisibilityAndStyleOfBtns(Factory factory) {
		if (albumId == -1) {
			btnAlbumDelete = factory.buttonFactory("Slet album", 100, true);
			btnAlbumCreate = factory.buttonFactory("Opret album", 100, false);
			btnAlbumSave = factory.buttonFactory("Gem album", 100, true);
			btnAlbumAddSong = factory.buttonFactory("Tilføj ny sang", 100, true);
		} else {
			btnAlbumDelete = factory.buttonFactory("Slet album", 100, false);
			btnAlbumCreate = factory.buttonFactory("Opret album", 100, true);
			btnAlbumSave = factory.buttonFactory("Gem album", 100, false);
			btnAlbumAddSong = factory.buttonFactory("Tilføj ny sang", 100, false);
		}
	}

	private void autofillTfWhenKnownSong(BravoMusic bravoMusic, EditorSong editorSong) {
		if (albumId != -1) {
			editorSong.controlDisablingOfCB(false);
			Album album = bravoMusic.searchAlbumWithId(albumId);
			tfAlbumName.setText(album.getAlbumName());
			if (album.getYearOfRelease() != 0) {
				tfYearOfRelease.setText(Integer.toString(album.getYearOfRelease()));
			}
			taDescription.setText(album.getAlbumDescription());
			if (album.getType().equals("LP")) {
				radioButtonLP.setSelected(true);
			} else if (album.getType().equals("CD")) {
				radioButtonCD.setSelected(true);
			}
		}
	}
}