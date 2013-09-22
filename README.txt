QuizGame README
===============

How to change the name of the app:
    - go to res/values/strings.xml
    - change "app_name"
    - save file and refresh the project

How to add new questions:
    - go to assets/texts/total.txt
    - change the total amount of the questions
    - go to assets/questions
    - name new question like N.txt, where N is the new number of the question
    - open the N.txt and write each answer in a single line (first line - answer A ect.)
    - in the fifth line write a literal of the RIGHT answer (for example - B)
    - put the images to res/drawable-xxxx(where xxxxx = hdpi | mdpi | xhdpi) in different resolutions
    - the name of the image should be q_N.png, where N is the same as in the N.txt
    - refresh the project

How to change the Help or About:
    - go to assets/texts
    - change the rules.txt or the about.txt
    - save file and refresh the project
   
How to change the Logo_image on the main screen
    - go to res/drawables-xxxx
    - replace logo.png with your image
    - the name of new file should be logo.png too

How to change the icon of the app
    - go to res/drawables-xxxx
    - replace ic_launcher.png with your image
    - the name of new file should be ic_launcher.png too
