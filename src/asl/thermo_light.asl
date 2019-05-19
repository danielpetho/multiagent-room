/* Initial beliefs and rules */

/* Initial goals */


/* Plans */

+!checkTemperature(T) : true
	<- room.getTemperature(T).
	
+!checkInternalLight(I) : true
	<- room.getInternalLight(I).
	
+!checkWindows(W1, W2, W3) : true
	<- room.getWindows(W1, W2, W3).