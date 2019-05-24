/* Initial beliefs and rules */

!irrigate.


+!irrigate : moist(X) & X < 5
	<-	irrigate(30);
		.wait(1000);
		!irrigate.
	
+!irrigate : moist(X) & X < 25 & X >= 5
	<-	irrigate(25);
		.wait(1000);
		!irrigate.
		
+!irrigate : moist(X) & X >= 25 & temp(Z) & Z > 10
	<-	.wait(1000);
		!irrigate.
		
	
+!irrigate : temp(X) & X <= 10
	<-	.print("Plant in dangerous condition!");
		.send(thermo_light, achieve, temp(30));
		.wait(1000);
		!irrigate.
	