
/* Initial beliefs and rules */
//hungry and there is food in the bowl
hungry.
has(food).

/* Initial goals */

!check_hungry.

/* Plans */

//eat if there is food, and empty the bowl
@eat1
+!eat(food) : has(food) & hungry
	<- 	-has(food);
		-hungry;
		true;
		.send(feeding_system, tell, ~has(food));
		.print("Hurray! It's Delicious!").

//eat if hungry
@eat2	
+hungry : has(food)
	<-	!eat(food).
	
//if there is no food, ask
@eat3
+hungry : not has(food)
	<- 	.send(feeding_system, achieve, has(food));
		.print("Give me food").
		
+!check_hungry : true
	<-	.print("I'm hungry");
   		+hungry;
   		.random(X);
		.wait(X*10000+10000);  // i get hungry random times
		!check_hungry.
	


	
 