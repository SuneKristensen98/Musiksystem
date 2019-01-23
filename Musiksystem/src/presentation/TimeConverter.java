package presentation;

public class TimeConverter {
	public int displayToSeconds(int minutes, int seconds) {
		int timeSeconds = minutes * 60 + seconds;
		return timeSeconds;
	}

	public String secondsToDisplay(int time) {
		int minutes = time / 60;
		int seconds = time % 60;

		String displayTime;
		if (seconds < 10) {
			displayTime = minutes + ":0" + seconds;
		} else {
			displayTime = minutes + ":" + seconds;
		}

		return displayTime;
	}
}
