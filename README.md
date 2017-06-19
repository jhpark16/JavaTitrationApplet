# Java Applet for Acid-Base Titration
This is a Java applet of a virtual chemistry experiment for a web page. This Eclipse project simulates a simple acid-base titration.

## Generation of Titrate.jar file
The compiled code needs to be packaged as a Titrate.jar file if it is to be used for a web page.<br>
(1) Right click the project from Package Explore in Eclipse.<br>
(2) Choose Export->Java->Jar file and then hit Next.<br>
(3) Choose src and resourced under JavaTitrationApplet. Unselect all the other files on the righthand panel. Click "Finish".

## Using this applet in a web page
The Titration.html shows how the applet is used in a web page. The web page should be run locally (due to security issues) and installed on a web site. 

## Applet structure
The applet is composed of 8 sequential panels of dialogue.
1. Introduction<br>
A short description of the applet. The named entered in this panel is what will appear on the certificate at the end.

2. Acid base selection for solution #1<br>
You need to choose either one acid out of the three options given (hydrochloric, nitric, and acetic acid) or one base out of the three options given (sodium hydroxide, potassium hydroxide and ammonia). You also need to decide what the volume and concentration of solution #1 will be. For the concentration, you can either make it exact, or introduce a small amount of random variation.

3. Acid base selection for solution #2<br>
You need to choose either an acid or a base depending on the choice you made in the previous step. You also need to choose the volume and concentration of solution #2. This concentration is an exact value, as the concentration volume of solution #2 is used to calculate the concentration of the unknown solution (solution #1).

4. Indicator selection<br>
You choose an indicator for the titration here. The colours of the indicators as a function of pH are shown.
  
5. The acid-base titration using a burette<br>
Solution #1 is in the conical flask and solution #2 is in the burette. You open the burette tap by pressing "Start". You can adjust the flow rate using the horizontal scroll bar. Your goal is to stop the flow at the endpoint of the titration. If you want to restart the titration, press "Titrate again". Record the amount of solution #2 used for the titration to calculate the concentration of the unknown solution (solution #1).

6. A graph showing the pH of the solution in the conical flask as a function of the amount of solution #2 used<br>
This panel shows how the pH changed during the titration. The pH vs volume curve is steepest around the endpoint, which means the volume of solution #2 at the endpoint can be read off of the pH vs volume graph.

7. Calculating the concentration of unknown solution #1<br>
Using the volume of solution #1 and #2 and the concentration of #2, the concentration of solution #1 can be calculated. Use the data obtained from this panel and the previous panel (panels 6 and 7).

8. Certificate of titration performance<br>
This page shows the certificate of your virtual titration experiment.
  
## License
JavaTitrationApplet is licensed under <B>the MIT License</B> - see the `LICENSE` file for details.
