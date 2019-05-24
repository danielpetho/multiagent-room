/* Initial beliefs and rules */


/* Initial goals */
feeding_time.

/* Plans */

+!has(food)[source(pet)] : feeding_time & stock(X) & X >= 5
	<-	.print("Adok");
		!fillBowl(5).
		
+!has(food)[source(pet)] : feeding_time & stock(X) & X = 0
	<-	.print("Empty stock");
		order(food);
		.send(pet, tell, no_food(food)).
		
+!has(food)[source(pet)] : not feeding_time
	<-	.print("Too fat");
		.send(pet, tell, too_much(food)).
		
+!fillBowl(N) : true
	<-	.print("Fill");
		fillBowl(N);
		.send(pet, tell, has(food));
		-feeding_time.
		
-feeding_time : true
	<-	!feeding_time.
		
+!feeding_time : true 
	<-	.wait(1000 * 20);
		+feeding_time.
		


	
