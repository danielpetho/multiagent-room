/* Initial beliefs and rules */

/* Initial goals */



/* Plans */

+!order(food) : true
	<-  .print("Ordering food");
		.send(feeding_system, tell, ordered(food)).

