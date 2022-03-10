/*
 * Authors: Liam Clarke & Manav Mistry
 * CS213 with Professor Venugopal
 * 
 */

package application;

public class Song implements Comparable<Song>{
	public String artist;
	public String album;
	public String songName;
	public String year;
	
	public Song(String songName, String artist, String album, String year) {
		this.artist = artist;
		this.album = album;
		this.songName = songName;
		this.year = year;
	}
	public String getArtist(){
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getAlbum(){
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String getSongName(){
		return songName;
	}
	
	public void setSongName(String songName) {
		this.songName = songName;
	}
	
	public String getYear(){
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return songName + " - " + artist;
	}
	
	@Override
	public int compareTo(Song o) {
		if (this.getSongName().equals(o.getSongName()) && this.getArtist().equals(o.getArtist())) {
			return 1;
		}
		return 0;
	}

}
