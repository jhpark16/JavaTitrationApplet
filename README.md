# Java Applet for Acid-Base Titration
This is an Java applet of a virtual chemistry experiment for a web page.
This is a Java project using Eclipse IDE.  
An Eclipse Java A simple acid-base titration is simulated.
A Java applet simulating acid base titration experiment for a web page.
I was written using the initial Java Applet 

## Generation of Titrate.jar file
The compiled code needs to be packaged as Titrate.jar file to be used for a web page.<br>
(1) Right click on the project from Package Explore in Eclipse.<br>
(2) Choose Export->Java->Jar file and then hit Next.<br>
(3) Choose src and resourced under JavaTitrationApplet. Unselect all other files on the right panel. Click "Finish".

## Using this applet in a web page
The Titration.html shows how the applet is used in a web page. Due to a security issue, the web page should be run locally. It shoud be installed on a web site. 

## Panels
The applet is composed of 8 sequential panels of dialogs.
1. Introducition<br>
A short description about the applet. This panel takes your name for the certificate.

2. Acid base selection for solution #1<br>
You need to choose either one acid out of three acids (hydrochloric, nitric, and acetic acid) or one base out of three bases (sodium hydroxide, potassium hydroxide and ammonia). You also need to select the volume and concentration of the solution #1. The concentration can be random or pre-determined.


3. Acid base selection for solution #2<br>
You choose either acid or base depending on the choice you make on the Acid base selection #1. You choose the volume of the solution of a known concentration. This concentration and volume is used to
calculate the concentration of unknown solution (Solution #1).

4. Indicator selection<br>
You choose an indicator for the titration here. The colours of the indicators as a function of pH are shown.
  
5. The acid-base titration using a burette<br>
Solution #1 is in the erlenmeyer flask and solution #2 is in the burette. You open the pinchvalve of the burette by pressing "Start" button. You can adjust the flow rate by changing the horizontal scroll bar. Your goal is to stop the flow at the endpoint of the titration. If you want to restart the titration, press "Titrate again" button. Record the amount of solution #2 used for the titration to calculate the concentration of the unknown solution (Solution #1).

6. A graph showing the pH of the solution as a function of the amount of solution #2 used<br>
This panel shows how the pH changes during the titration. 

7. The calculation of the concentration of unknown solution #1<br>

8. Certificate of titration performance<br>
This page shows the certificate of your virtual titration experiment.
  
## License
JavaTitrationApplet is licensed under the MIT License - see the `LICENSE` file for details.
