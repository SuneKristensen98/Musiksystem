package presentation;

public class TimeConverter {
	//TODO Mangler resterende genrer
	
	public int displayToSeconds() {
		return -1;
	}
	
	public String secondsToDisplay(int time) {
		int minutes = time / 60;
		int seconds = time % 60;
		String displayTime = minutes + ":" + seconds;
		return displayTime;
	}
	
}
