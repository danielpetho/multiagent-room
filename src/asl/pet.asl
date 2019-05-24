
/* Initial beliefs and rules */

hungry.

/* Initial goals */

/* Plans */

//If I'm hungry, try to eat
+hungry : true
	<-	.print("I'm hungry");
		!eat.
		

//Eat if the bowl is not empty.	
+!eat : has(food)
	<-	.print("I'm full");
		-has(food);
		-has(food)[source(feeding_system)];
		-too_much(food)[source(feeding_system)];
		-no_food(food)[source(feeding_system)];
		-hungry.
	
//I cant eat, bc the bowl is empty
+!eat : not has(food)
	<-	.print("Give me food!");
		.send(feeding_system, achieve, has(food)).

//if the bowl is not empty, i try to eat again
+has(food) : true
	<-	!eat.

+too_much(food)[source(feeding_system)] : true
	<-	.wait(1000*10);
		-too_much(food)[source(feeding_system)];
		!eat.
		
+no_food(food)[source(feeding_system)] : true
	<-	.wait(1000*2);
		-no_food(food)[source(feeding_system)];
		!eat.
		

//get hungry random times
-hungry : true
	<-	.random(X); .wait(X * 1000 * 3);
		+hungry.
		
	


	
 