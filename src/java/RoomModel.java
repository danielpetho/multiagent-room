
public class RoomModel {
	
	boolean[] windows = new boolean[3];
	int temp, moist, hum;
	
	RoomView view;
	
	public RoomModel() {
		windows[0] = true;
		windows[1] = true;
		windows[2] = true;
	}
	
	public void setView(RoomView view) {
		this.view = view;
	}
	
	
	
	boolean openWindow(int win) {
		if(!windows[win]) {
			windows[win] = true;
			System.out.println(windows[win]);
			return true;
		} else {
			return false;
		}
	}
	
	boolean closeWindow(int win) {
		if(windows[win]) {
			windows[win] = false;
			System.out.println(windows[win]);
			return true;
		} else {
			return false;
		}
	}
	
	boolean setHum(int hum) {
		this.hum = hum;
		return true;
	}
	
	boolean setMoist(int moist) {
		this.moist = moist;
		return true;
	}
	
	boolean setTemp(int temp) {
		this.temp = temp;
		System.out.println(temp);
		return true;
	}
	
	int getTemp() {
		return this.temp;
	}
	
	

}
