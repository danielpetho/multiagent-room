/* Initial beliefs and rules */

/* Initial goals */

+!hum : (hum(Z) & Z > 60)
	<-	open(win1);
		open(win2);
		!hum.
		
+!light : (lightOn) 
	<-	close(win1);
		close(win2);
		!light.
		
+!light : (lightOff) 
	<-	open(win1);
		!light.
		
+!temp(30)[source(plant_manager)] : true
	<-	.send(plant_manager, askOne, moist(X));
		!save.
		
+save(pet)[source(feeding_system)] : true
	<-	!save.
		
+!save : (moist(X)[source(plant_manager)] & X < 35 & X > 10) & not save(pet)[source(feeding_system)]
	<-	temp(30);
		.print("support for plant").
	
+!save : (moist(X)[source(plant_manager)] & X < 35 & X > 10) & save(pet)[source(feeding_system)]
	<-	-save(pet)[source(feeding_system)];
		temp(26);
		.print("compromise").
	
+!save : (moist(X)[source(plant_manager)] & X < 10) 
	<-	temp(32);
		.print("plant in danger").
		
+!save : (moist(X)[source(plant_manager)] & X >= 35) & save(pet)[source(feeding_system)]
	<-	-save(pet)[source(feeding_system)];
		temp(23);
		.print("comfy").
	
+!save : not (moist(X)[source(plant_manager)]) & save(pet)[source(feeding_system)]
	<-	-save(pet)[source(feeding_system)];
		temp(23);
		.print("comfy").
	

		
