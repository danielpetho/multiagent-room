//TODOs: Quantity


/* Initial beliefs and rules */
//It's feeding time, the bowl is empty, and there is food in stock
~has(food).
feedingTime.
stock(food).

/* Initial goals */

!check_feeding_time.

/* Plans */

//fill bowl if there is food in stock, there is feeding time, and the bowl is empty
@fill1
+!fillBowl(food) : stock(food)
	<-	-feedingTime;
		+has(food);
		.print("Filled");
		.send(pet, tell, has(food));
		true.
	
//try to fill bowl, and ask owner to stock if there is not enough 
@fill2
+!fillBowl(food) : not stock(food)
	<-	.send(owner, achieve, order(food));
		.print("We run out of food!").

+feedingTime : ~has(food)
	<- !fillBowl(food).
	
+feedingTime : has(food)
	<- !fillBowl(food);
		.print("It's dead.").
		
//Update the state of the bowl
+eaten(has(food)) [source(pet)] : true
	<- +~has(food).
	
//when the owner fill the stock, add belief there is food in stock
+delivered(food) [source(owner)] : true
	<- +stock(food).

+!check_feeding_time : true
	<-	.wait(10000);
		.print("It's feeding time");
		+feedingTime;
		!check_feeding_time.	


	

	
