
public class RoomModel {
	
	boolean[] windows = new boolean[3];
	int temp, moist, hum, stock;
	boolean light;
	boolean hasfood;

	
	RoomView view;
	Room env;
	
	public RoomModel() {
		windows[0] = true;
		windows[1] = true;
		windows[2] = true;
		light = false;
		stock = 100;
		hasfood = false;
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
	
	boolean setTemp(int temp) {
		this.temp = temp;
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
	
	boolean irrigate() {
		this.moist += 50;
		this.hum += 20;
		if(this.moist > 100) {
			this.moist = 100;
		}
		
		if(this.hum > 100) {
			this.hum = 100;
		}
		
		view.refresh();
		env.updatePercepts();
		env.informAgsEnvironmentChanged();
		return true;
	}
	
	
	

}
