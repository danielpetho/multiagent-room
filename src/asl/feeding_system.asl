//TODOs: Quantity, add belief feedingTime after x time


/* Initial beliefs and rules */
//It's feeding time, the bowl is empty, and there is food in stock
~has(food).
feedingTime.
stock(food).

/* Initial goals */


/* Plans */

//fill bowl if there is food in stock, there is feeding time, and the bowl is empty
@fill1
+!fillBowl(food) : stock(food)
	<-	-feedingTime;
		+has(food);
		true.
	
//try to fill bowl, and ask owner to stock if there is not enough 
@fill2
+!fillBowl(food) : not stock(food)
	<-	.send(owner, achieve, order(food)).
	
+feedingTime : not has(food)
	<- !fillBowl(food).
	
//when the owner fill the stock, add belief there is food in stock
+delivered(food) [source(owner)] : true
	<- +stock(food).
	


	

	
