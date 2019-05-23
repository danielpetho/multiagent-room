/* Initial beliefs and rules */

/* Initial goals */


/* Plans */

+win1Open : true
	<- .print("Wind 1 Open!").
	
+win1Closed : true
	<- .print("Wind 1 Closed!").
	
+term : true
	<- !close(win1).

+!close(win1) : true
	<- .print("bezárom%").