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
		updatePercepts();
    }
    
    public static void main(String[] args) {
    	
    	
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
    	
    	boolean result = false;
    	
    	updatePercepts();
    	informAgsEnvironmentChanged();
    	
    	System.out.println("["+agName+"] doing: "+action);
    	
        if (true) { // you may improve this condition
        	if(action.equals(Literal.parseLiteral("close(win1)"))) {
        		System.out.println("bezártam");
        		result = rmodel.closeWindow(0);
        	} else if (action.equals("open(win1)")) {
        		System.out.println("asdsa");
        		//result = rmodel.openWindow(0);
        	} else {
        		System.out.println("else");
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
    	clearPercepts();
    	
    	if(rmodel.windows[0]) {
    		System.out.println("Nyitva");
    		addPercept("thermo_light", Literal.parseLiteral("win1Open"));
    	} else {
    		System.out.println("Zárva");
    		addPercept(Literal.parseLiteral("win1Closed"));
    	}
    	
    	if(rmodel.getTemp() == -2) {
    		System.out.println("hideg");
    		addPercept("thermo_light", Literal.parseLiteral("term"));
    	}
    	
    	
    }
}
