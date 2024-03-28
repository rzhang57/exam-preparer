# Exam Preperation Progress Tracker

## Proposal

An **exam preperation progress tracker application**. This application is supposed to serve as an 
aid to normal class notes and solve the issue of unorganized practice prior to exams.
This application will be able to store exam related information for multiple courses according 
to the user's needs. Particularly for courses that rely heavily on practice problems and past exams, 
this application provides the ability to store practice problems a user has trouble with for further practice as well as give the user the option to input a solution to said problem. The solution will, by default, be hidden, however, can be revealed at the request of the user.
The user will 
also be able to track progress through a status feature which is visibly shown to the user for every problem stored; the three 
statuses are: 
- *Not reviewed*
- *Reviewing*
- *Mastered*. 

With this status feature, there will also be a feature to filter out problems based on which status of problems you want to look out, therefore, users can have more focused study. 

This application will primarily be used by **students**.

This project is of interest to me as I am a big advocate for doing practice problems in preperation for exams; I have also realized 
the importance of recording problems you do not get correct the first time/ don't understand, 
figuring the solutions out yourself, and then studying the solution as the exam dates get closer. However, 
without a good way to organize these tougher problems, I typically resort to screenshotting and pasting 
questions + solutions onto a document for further review; without a way to track my progress on each 
of the problems, I find myself often reviewing more deeply the problems at the top of the document. 
With a feature to filter the various statuses, I think this solves that problem. 

## User stories

- As a user, I want to be able create a new course, specifying a name, and add it to a list of my other courses.
- As a user, I want to be able to view a list of all my courses.
- As a user, I want to be able to add a practice problem, specifying a general title, the problem itself, and potentially a solution, to a course.
- As a user, I want to be able to select a problem, and have the option to edit the problem or add notes to the solution.
- As a user, I want to be able to select a practice problem, and see the problem in detail, and then be able to see the solution when I am ready.
- As a user, I want to be able to view a list of all my practice problems in a given course.
- As a user, I want to be able to change the status of a given practice problem once I have started reviewing it or mastered it.
- As a user, I want to be able to filter out my practice problems so I can focus on the ones with the status which I want to see.
- As a user, I want to be able to save all the Courses I have as well as their respective practice problems as I desire.
- As a user, I want to be able to load all the Courses and be able to view their respective practice problems as I desire.
- As a user, I want to be able to have the option to load my previously saved data upon running the application.

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by running the application, and pressing the "Add Course" button in the navigation bar at the top of the screen, entering the name of the desired Course (X), and pressing the OK button to add a course to a student's Exam Course List (Y).
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by running the application, and pressing the "Remove Course" button in the navigation bar, typing the name of the course you want to remove from the course list, and pressing OK. 
- You can generate another action related to the user story "adding multiple Xs to a Y" by clicking button of any desired course to enter/ select it, which will display all of the practice problems in a text area.
- You can generate another action related to the user story "adding multiple Xs to a Y" by clicking the course button, and then the + practice problem button, then typing in the title, body, solution, and then pressing OK. This will modify the course by adding a practice problem to its list of practice problems.
- You can locate my visual component by opening the application and looking in the top left which shows an image of books. 
- You can save the state of my application by pressing on the Save button in the navigation bar on the top left of the main JFrame (not course window)
- You can reload the state of my application by pressing on the Load button in the navigation bar on the top left of the main JFrame