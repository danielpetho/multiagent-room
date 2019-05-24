/* Initial beliefs and rules */

!hum.
/* Initial goals */

+!hum : true
	<-	.send(plant_manager, askOne, hum(X));
		.wait(1000);
		!hum.

+hum(Z) : (hum(Z)[source(plant_manager)[source(percept)]] & Z > 60)
	<-	-hum(Z)[source(plant_manager)[source(percept)]];
		-hum(Z);
		open(win1);
		.print("Open windows because the air is too humid");
		.wait(1000).
		
+hum(Z) : Z <= 60
	<-	-hum(Z)[source(plant_manager)[source(percept)]];
		-hum(Z).
		
+outtemp(X) : X <= 10
	<-	close(win1);
		close(win2);
		close(win3).

//if light on, close all the windoss
+lightOn[source(percept)] : winOpen(win0)[source(percept)] & winOpen(win1)[source(percept)] & winOpen(win2)[source(percept)]
	<-	close(win1);
		close(win2);
		-lightOff[source(percept)];
		.print("Lights on, close windows");
		close(win3).
		
		
//if lights off, open the third window
+lightOff[source(percept)] :winClosed(win0)[source(percept)] & winClosed(win1)[source(percept)] & winClosed(win2)[source(percept)]
	<-	open(win3);
		.print("Light off, open win 3");
		-lightOn[source(percept)].
		
+!temp(30)[source(plant_manager)] : true
	<-	.send(plant_manager, askOne, moist(X));
		!save.
		
+save(pet)[source(feeding_system)] : true
	<-	!save.
		
+!save : (moist(X)[source(plant_manager)] & X < 35 & X > 10) & not save(pet)[source(feeding_system)]
	<-	temp(30);
		-moist(X)[source(plant_manager)]
		.print("Set the temperature to 30C for the plant.").
	
+!save : (moist(X)[source(plant_manager)] & X < 35 & X > 10) & save(pet)[source(feeding_system)]
	<-	-save(pet)[source(feeding_system)];
		temp(26);
		-moist(X)[source(plant_manager)]
		.print("Ideal condition").
	
+!save : (moist(X)[source(plant_manager)] & X < 10) 
	<-	temp(29);
		-moist(X)[source(plant_manager)]
		.print("Plant in danger").
		
+!save : (moist(X)[source(plant_manager)] & X >= 35) & save(pet)[source(feeding_system)]
	<-	-save(pet)[source(feeding_system)];
		-moist(X)[source(plant_manager)]
		temp(23);
		.print("Comfy for pet").
	
+!save : not (moist(X)[source(plant_manager)]) & save(pet)[source(feeding_system)]
	<-	-save(pet)[source(feeding_system)];
		temp(23);
		.print("Comfy for pet").
	

		
