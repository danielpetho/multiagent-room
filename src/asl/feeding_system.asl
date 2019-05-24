/* Initial beliefs and rules */


/* Initial goals */
feeding_time.

/* Plans */

+!has(food)[source(pet)] : feeding_time & stock(X) & X >= 5
	<-	.print("I will give you food");
		!fillBowl(5).

+!has(food)[source(pet)] : feeding_time & stock(X) & X = 0
	<-	.print("Oh, no! Stock is empty!");
		order(food);
		.send(pet, tell, no_food(food));
		+bowl_empty.
		
+!has(food)[source(pet)] : not feeding_time
	<-	.print("Too fat! It's not feeding time yet!");
		.send(pet, tell, too_much(food));
		+bowl_empty.
		
+!fillBowl(N) : true
	<-	.print("Good boy, here is your food");
		fillBowl(N);
		.send(pet, tell, has(food));
		-feeding_time.
		
-feeding_time : true
	<-	!feeding_time.
		
+!feeding_time : true 
	<-	.wait(1000 * 20);
		+feeding_time.
		
+!feed(feeding_time) : not bowl_empty
	<-	.print("Poor thing, it's not eating.");
		notEating(dog).

+temp(X)[source(percept)] : X > 31
	<- .send(thermo_light, tell, save(pet));
		.print("The room is too hot for your pet!").
		


	
