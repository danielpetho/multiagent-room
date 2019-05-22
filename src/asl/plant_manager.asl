/* Initial beliefs and rules */

/* Initial goals */

!checkMoisture.

/* Plans */

+!irrigate(plant) : M < 10 
	<- true.
	
+!checkMoisture : true
	<- 	room.getMoisture(M);
		+M.