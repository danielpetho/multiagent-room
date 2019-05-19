/* Initial beliefs and rules */

/* Initial goals */

!checkMoisture(M).

/* Plans */

+!irrigate(plant) : M < 10 
	<- true.
	
+!checkMoisture(M) : true
	<- room.getMoisture(M).