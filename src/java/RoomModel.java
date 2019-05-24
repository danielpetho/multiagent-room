
public class RoomModel {
	
	boolean[] windows = new boolean[3];
	int temp, moist, hum, stock;
	boolean light;

	
	RoomView view;
	Room env;
	
	public RoomModel() {
		windows[0] = true;
		windows[1] = true;
		windows[2] = true;
		light = false;
		stock = 100;
	}
	
	public void setView(RoomView view) {
		this.view = view;
	}
	
	public void setEnv(Room env) {
		this.env = env;
	}
	
	
	
	boolean openWindow(int win) {
		if(!windows[win]) {
			windows[win] = true;
			view.refresh();
			return true;
		} else {
			return false;
		}
	}
	
	boolean offLight() {
		if(light) {
			light = false;
			view.refresh();
			return true;
		} else {
			return false;
		}
	}
	
	boolean onLight() {
		if(!light) {
			light = true;
			view.refresh();
			return true;
		} else {
			return false;
		}
	}
	
	boolean closeWindow(int win) {
		if(windows[win]) {
			windows[win] = false;
			view.refresh();
			return true;
		} else {
			return false;
		}
	}
	
	boolean setHum(int hum) {
		this.hum = hum;
		view.refresh();
		return true;
	}
	
	boolean setMoist(int moist) {
		this.moist = moist;
		view.refresh();
		return true;
	}
	
	boolean decMoist(int m) {
		this.moist -= m;
		if(moist < 0) {
			this.moist = 0;
		}
		view.refresh();
		return true;
		
	}
	
	boolean setTemp(int temp) {
		this.temp = temp;
		view.refresh();
		return true;
	}
	
	boolean decTemp(int temp) {
		this.temp -= temp;
		if(this.temp < 0) {
			this.temp = 0;
		}
		view.refresh();
		return true;
	}
	
	boolean stock() {
		this.stock = 100;
		view.refresh();
		return true;
	}
	
	boolean fill(int n) {
		if(stock > 0) {
			this.stock -= n;
			view.refresh();
			return true;
		} else {
			return false;
		}
		
	}
	
	boolean irrigate(int n) {
		this.moist += n;
		
		if(!windows[0] || !windows[1] || !windows[2]) {
			this.hum += n - 15;
			if(this.hum > 100) {
				this.hum = 100;
			}
		}

		if(this.moist > 100) {
			this.moist = 100;
		}
		
		
		
		view.refresh();
		return true;
	}
	
	
	

}
