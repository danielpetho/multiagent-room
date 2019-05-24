// Environment code for project smart_room

import jason.asSyntax.*;
import jason.asSyntax.parser.ParseException;
import jason.environment.*;
import java.util.logging.*;

public class Room extends Environment {
	
	
	static RoomModel rmodel;
	static RoomView view; 
		

    private Logger logger = Logger.getLogger("smart_room."+Room.class.getName());

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
    	rmodel = new RoomModel();
    	view = new RoomView(rmodel);
    	rmodel.setView(view);
    	view.setEnv(this);
    	rmodel.setEnv(this);
		updatePercepts();
    }
    
    public static void main(String[] args) {
    	
    	
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
    	
    	boolean result = false;
    	
    	
    	System.out.println("["+agName+"] doing: "+action);
    	
        if (true) {
        	
        	//open windows
        	if(action.getFunctor().equals("open")) {
        		String l = action.getTerm(0).toString();
        		if(l.equals("win1")) {
        			result = rmodel.openWindow(0);
        		} else if (l.equals("win2")) {
        			result = rmodel.openWindow(1);
        		} else if (l.equals("win3")) {
        			result = rmodel.openWindow(2);
        		}
        	}
        	
        	//close windows
        	if(action.getFunctor().equals("close")) {
        		String l = action.getTerm(0).toString();
        		if(l.equals("win1")) {
        			result = rmodel.closeWindow(0);
        		} else if (l.equals("win2")) {
        			result = rmodel.closeWindow(1);
        		} else if (l.equals("win3")) {
        			result = rmodel.closeWindow(2);
        		}
        	}
        	
        	
        	//switch lights
        	if(action.equals(Literal.parseLiteral("lightsOff"))) {
        		result = rmodel.offLight();
        	} else if (action.equals(Literal.parseLiteral("lightsOn"))) {
        		result = rmodel.onLight();
        	}
        	
        	
        	//set the temp value
        	if(action.getFunctor().equals("setTemp")) {
        		try {
        			int l = (int) ((NumberTerm)action.getTerm(0)).solve();
        			result = rmodel.setTemp(l);
        		} catch ( Exception e) {
        			logger.info("Empty temp value");
        		}
        		   		
        	}
        	
        	if(action.getFunctor().equals("fillBowl")) {
        		try {
        			int l = (int) ((NumberTerm)action.getTerm(0)).solve();
        			result = rmodel.fill(l);
        			clearPercepts("feeding_system");
        		} catch ( Exception e) {
        			logger.info("Empty value: fill");
        		}
        	}
        	
        	if(action.equals(Literal.parseLiteral("irrigate"))) {
        		result = rmodel.irrigate();
        	}
        	
        	if(action.equals(Literal.parseLiteral("order(food)"))) {
        		result = rmodel.stock();
        	}
        	       	
        	   	
        	 updatePercepts();
             informAgsEnvironmentChanged();
        }
        
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
    
    
    void updatePercepts() {
    	clearPercepts("plant_manager");
    	clearPercepts("thermo_light");
    	
    	for(int i = 0; i < 3; i++) {
    		if(rmodel.windows[i]) {
    			addPercept("thermo_light", Literal.parseLiteral("winOpen(win" + i + ")"));
    		} else {
    			addPercept("thermo_light", Literal.parseLiteral("winClosed(win" + i + ")"));
    		}
    	}
    	
    	if(rmodel.hasfood) {
    		addPercept("feeding_system", Literal.parseLiteral("hasfood"));
    	}
    	
    	if(rmodel.light) {
    		addPercept(Literal.parseLiteral("lightOn"));
    	} else if (!rmodel.light) {
    		addPercept(Literal.parseLiteral("lightOff"));
    	}
    	
    	addPercept(Literal.parseLiteral("temp(" + rmodel.temp + ")"));
    	addPercept("plant_manager", Literal.parseLiteral("hum(" + rmodel.hum + ")"));
    	addPercept("plant_manager", Literal.parseLiteral("moist(" + rmodel.moist + ")"));
    	addPercept("feeding_system", Literal.parseLiteral("stock(" + rmodel.stock + ")"));
    	System.out.println(rmodel.stock);
    	
    	
    }
}
