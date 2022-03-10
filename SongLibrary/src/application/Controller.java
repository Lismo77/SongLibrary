/*
 * Authors: Liam Clarke & Manav Mistry
 * CS213 with Professor Venugopal
 * 
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

	//listview of songs
	@FXML
	ListView<Song> songList;
	Song currentSong;
	
	//button objects
	@FXML
	Button addButton;
	@FXML
	Button deleteButton;
	@FXML
	Button editButton;
	@FXML
	Button addSongButton;
	@FXML
	Button saveButton;
	@FXML
	Button cancelButton;
	
	//label objects
	@FXML
	Label promptLabel;
	@FXML
	Label nameLabel;
	@FXML
	Label artistLabel;
	@FXML
	Label albumLabel;
	@FXML
	Label yearLabel;
	@FXML
	Label errorLabel;
	
	//field objects
	@FXML
	TextField nameField;
	@FXML
	TextField artistField;
	@FXML
	TextField albumField;
	@FXML
	TextField yearField;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		songList.getSelectionModel().selectedItemProperty().addListener((ChangeListener<? super Song>) new ChangeListener<Song>() {

			@Override
			public void changed(ObservableValue<? extends Song> arg0, Song arg1, Song arg2) {
				
				currentSong = songList.getSelectionModel().getSelectedItem();
				if (currentSong != null) {
					setFields(currentSong.getSongName(), 
							currentSong.getArtist(), 
							currentSong.getAlbum(), 
							currentSong.getYear());
				}
				deactivateASB();
				deactivateSB();
				disableFields();
				deactivateCB();
				
			}
			
		});
		
	}
	
	public void addButtonClicked() {
		enableFields();
		clearFields();
		setFieldPrompts("Enter song name", "Enter artist name", "Enter album name", "Enter release year");
		activateASB();
		activateCB();
		deactivateSB();
	}
	
	public void addSongButtonClicked() {
		Song newSong = new Song(nameField.getText(), 
					artistField.getText(), 
					albumField.getText(), 
					yearField.getText());
		if (containsSong(songList, newSong)) {
			errorLabel.setText("Error: Song already exists");
			errorLabel.setVisible(true);
		} else if (newSong.getSongName() != "" && newSong.getArtist() != "") {
			songList.getItems().add(newSong);
			songList.getItems().sort((song1,song2)->{
				int compareVal = song1.getSongName().compareTo(song2.getSongName());
				if (compareVal > 0)
					return 1;
				else if(compareVal == 0)  {
					int compareVal2 = song1.getArtist().compareTo(song2.getArtist());
					if (compareVal2 > 0)
						return 1;
					else if (compareVal < 0)
						return 0;
					return 0;
			    } else if (compareVal < 0)
			    	return 0;
			    return 0;
			});
			clearErrorLabel();
			disableFields();
			deactivateCB();
			deactivateASB();
		} else {
			errorLabel.setText("Error: Songs need at least a name and an artist");
			errorLabel.setVisible(true);
		}
	}
	
	
	public void deleteButtonClicked() {
		if (songList.getSelectionModel().getSelectedItem() != null) {
			songList.getItems().remove(songList.getSelectionModel().getSelectedIndex());
			clearErrorLabel();
		} else {
			errorLabel.setText("Error: Please select a song to delete");
			errorLabel.setVisible(true);
		}
		deactivateCB();
		deactivateASB();
		deactivateSB();
	}
	
	public void editButtonClicked() {
		if (songList.getSelectionModel().getSelectedItem() != null) {
			enableFields();
			activateSB();
			activateCB();
			clearErrorLabel();
		} else {
			errorLabel.setText("Error: please select a song to edit");
			errorLabel.setVisible(true);
		}
		deactivateASB();
	}
	
	public void saveButtonClicked() {
		if (nameField.getText() != "" && artistField.getText() != "") {
			songList.getSelectionModel().getSelectedItem().setSongName(nameField.getText());
			songList.getSelectionModel().getSelectedItem().setArtist(artistField.getText());
			songList.getSelectionModel().getSelectedItem().setAlbum(albumField.getText());
			songList.getSelectionModel().getSelectedItem().setYear(yearField.getText());
			songList.getItems().sort((song1,song2)->{
				int compareVal = song1.getSongName().compareTo(song2.getSongName());
				if (compareVal > 0)
					return 1;
				else if(compareVal == 0)  {
					int compareVal2 = song1.getArtist().compareTo(song2.getArtist());
					if (compareVal2 > 0)
						return 1;
					else if (compareVal < 0)
						return 0;
					return 0;
			    } else if (compareVal < 0)
			    	return 0;
			    return 0;
			});
			disableFields();
			deactivateSB();
			deactivateCB();
			clearErrorLabel();
		} else {
			errorLabel.setText("Error: Song needs at least a name and an artist");
			errorLabel.setVisible(true);
		}
	}
	
	public void cancelButtonClicked() {
		songList.getSelectionModel().selectedItemProperty().addListener((ChangeListener<? super Song>) new ChangeListener<Song>() {

			@Override
			public void changed(ObservableValue<? extends Song> arg0, Song arg1, Song arg2) {
				
				currentSong = songList.getSelectionModel().getSelectedItem();
				if (currentSong != null) {
					setFields(currentSong.getSongName(), 
							currentSong.getArtist(), 
							currentSong.getAlbum(), 
							currentSong.getYear());
				}
				deactivateASB();
				deactivateSB();
				disableFields();
				
			}
			
		});
		deactivateASB();
		deactivateSB();
		disableFields();
		clearErrorLabel();
		deactivateCB();
	}
	
	//misc methods
	public boolean containsSong(ListView<Song> songs, Song song) {
		for (int i = 0; i < songs.getItems().size(); i++) {
			if (song.compareTo(songs.getItems().get(i)) == 1) {
				return true;
			}
		}
		return false;
	}
	
	public void sortSongs(ListView<Song> songs) {
		SortedList<Song> sortedSongs = new SortedList<Song>(songs.getItems());
		
	}
	
	//methods for activating and deactivating buttons
	public void activateASB() {
		addSongButton.setVisible(true);
		addSongButton.setDisable(false);
	}
	
	public void deactivateASB() {
		addSongButton.setVisible(false);
		addSongButton.setDisable(true);
	}
	
	public void activateSB() {
		saveButton.setVisible(true);
		saveButton.setDisable(false);
	}
	
	public void deactivateSB() {
		saveButton.setVisible(false);
		saveButton.setDisable(true);
	}
	
	public void activateCB() {
		cancelButton.setVisible(true);
		cancelButton.setDisable(false);
	}
	
	public void deactivateCB() {
		cancelButton.setVisible(false);
		cancelButton.setDisable(true);
	}
	
	//functions for managing fields and labels
	public void clearErrorLabel() {
		errorLabel.setText("");
		errorLabel.setVisible(false);
	}
	
	public void clearFields() {
		nameField.clear();
		artistField.clear();
		albumField.clear();
		yearField.clear();
	}
	
	public void setFieldPrompts(String name, String artist, String album, String year) {
		nameField.setPromptText(name);
		artistField.setPromptText(artist);
		albumField.setPromptText(album);
		yearField.setPromptText(year);
	}
	
	public void setFields(String name, String artist, String album, String year) {
		nameField.setText(name);
		artistField.setText(artist);
		albumField.setText(album);
		yearField.setText(year);
	}
	
	public void disableFields() {
		nameField.setDisable(true);
		artistField.setDisable(true);
		albumField.setDisable(true);
		yearField.setDisable(true);
	}
	
	public void enableFields() {
		nameField.setDisable(false);
		artistField.setDisable(false);
		albumField.setDisable(false);
		yearField.setDisable(false);
		
	}
}
