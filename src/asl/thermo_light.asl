/* Initial beliefs and rules */

/* Initial goals */


+winOpen(win1) : true
	<-	.print("win 1 open").

/* Plans */
/*
+winOpen(win1) : true
	<- .print("Win 1 Open!").
	
+winClosed(win1) : true
	<- .print("Win 1 Closed!").
	
+!close : winOpen(win1)
	<- 	.print("becsukom");
		-winOpen(win1);
		close(win1).

*/
