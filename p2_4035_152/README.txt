Joel Torres Rodríguez
joel.torres4@upr.edu
802-12-8025
ICOM4035-120
Data Structures
P2

The program starts execution by showing the command line system. This system will allow users to enter commands that work with large numbers and variables that can hold large numbers. When the program is executed, it goes to general loop which executes until the command exit is executed. On each iteration of that loop, the program reads a command from user, validates, and execute the actions corresponding to the command. If the command is the exit command, then the action is to terminate the current execution of the program. These are the commands: 

var var_name - Declares a variable with the given name ... Declared variables are initialized to 0. 
delete var_name - Deletes the given variable from the system.
load file_name - Loads the content of the given file adding it to the content that the current execution has up to the moment. Conflicts of variables with the same name are resolved by replacing the current variable in memory with the 		 conflicting variable that is being read. 
add var_name value value - computes the addition of the two numeric values
sub var_name value value - computes the subtraction of the two numeric values (first value - second value)
mult var_name value value - computes the product of the two numeric values
factorial var_name value- Compute the factorial of the given value. The given value has to be a non-negative integer value; otherwise, the execution of the command ends displaying appropriate error message.
factors value - Determines the prime factorization of number n. The given value has to be a positive integer value; otherwise, an error message is shown. Factors are displayed as a table in which each row has two parts, the first part 		is the prime factor and the second is the positive integer exponent that correspond to that prime number in the prime factorization of the given number. 
prime value - Determines if the given number is a prime number. Displays “yes” or “no”. A prime number is a positive integer value that has exactly two different integer factors.
save file_name - Saves the current content (variables and their values) in a file whose name is given. 
show var_name - Displays the value of the current variable. The output follows format: “var_name = value”. 
exit - ends execution of the system. 


To compile and run the program, you must open the Command Prompt (CMD) on Windows or Terminal on Linux and move through the folders and reach the location where you downloaded and extracted the program archives using the CD and DIR (on Windows) or CD and LS (on Linux) commands. When located, enter the following commands:

>cd P2_4035_802128025_152

>cd P2_802128025

>cd P2

>cd src

>javac theSystem/p2_main.java

>java theSystem/p2_main

