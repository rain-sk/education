CS1122
======

This is a repository containing projects I worked on while taking CS1122 at Michigan Technological University.

Program 1
---------

A simple map navigator. There are some bugs in the code for this program, not sure why though, because it worked when I submitted it.

Program 2
---------

A project demonstrating inheritance and polymorphism in Java.

Program 3
---------

For this project, we were given a working implementation of the classic HangMan game, for which we had to develop a functional, good-looking GUI.

Program 4
---------

This project challenged us to find recursive solutions to simple and complex problems.

Program 5
---------

This program required us to build a functional Linked List implementation, with certain methods which could access and mutate data within the Linked List.

Program 6
---------

The final program for this class required two parts:

	* Implementing a Trie data structure which can be used as a dictionary, and implements the
	  Iterable<String> interface.

	* Writing code that cheats at the game of HangMan. It should pretend that it has picked a word,
	  but really keep a whole list of possible secret words stored in the Trie. Each time the user
	  guesses a letter, the list is pared down to the largest sub-list of similar word-families.
	  For example, if the list contains: ally, help, nope, and dope, and the letter 'e' is guessed,
	  a list of word families will be created, looking something like this:

		_,_,_,_ - ally
		_,e,_,_ - help
		_,_,_,e - nope, dope

	  Because the word family "_,_,_,e" has the most words which fit into said family, the list of
	  available words is pared down to nope and dope, and the letter 'e' is revealed in the game of HangMan.

Notes
-----

Just some notes taken during one of the last classes of the semester detailing what will be on the final.
