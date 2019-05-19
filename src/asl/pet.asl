/* Initial beliefs and rules */
//hungry and there is food in the bowl
hungry.
has(food).

/* Initial goals */

!eat(food).

/* Plans */

//eat if there is food, and empty the bowl
@eat1
+!eat(food) : has(food) & hungry
	<- 	-has(food);
		-hungry;
		true.

//eat if hungry
@eat2	
+hungry : has(food)
	<-	!eat(food).
	
//if there is no food, ask
@eat3
+hungry : not has(food)
	<- .send(feeding_system, achieve, has(food)).
	
//TODO after x(rand) time add to belief base: hungry

	
 