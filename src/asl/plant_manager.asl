/* Initial beliefs and rules */

!irrigate.


+!irrigate : moist(X) & X < 5
	<-	.wait(1000 * 10);
		irrigate(30);
		!irrigate.
	
+!irrigate : moist(X) & X < 25 & X >= 5
	<-	.wait(1000 * 10);
		irrigate(25);
		!irrigate.
		
+!irrigate : moist(X) & X >= 25 & temp(Z) & Z > 10
	<-	.wait(1000 * 20);
		decm(5);
		!irrigate.
		
	
+!irrigate : temp(X) & X <= 10
	<-	.print("Plant in dangerous condition!");
		.send(thermo_light, achieve, temp(30));
		.wait(1000);
		!irrigate.
		
+!irrigate : temp(X) & X >= 34
	<-	irrigate(25);
		!irrigate.
	