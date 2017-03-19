
Goals
x- build in eclipse
x---- get pi up and running, connect with putty
x---- install eclipse and get the project in repo
x- download to pi and blink an led
---- add maven to eclipse
---- use maven to install pi4j
x---- build a test project and download to pi
x---- figure out how java libraries work, get pi library to interface with gpio pints (j4pi?)
x- setup interface in the code to be able to input a sentence and blink led
- setup mic on pi to record audio and send to api to convert to sentence
x- setup buzzer on pi to replace led

x----- Communicating with the input output pins of the raspberry pi with Java

http://pi4j.com/
https://javatutorial.net/raspberry-pi-java-tutorial
pinout for j4pi pin #s
3B -- http://pi4j.com/pins/model-3b-rev1.html (in repo as j8header-3b.png)
1B -- http://pi4j.com/pins/model-b-rev2.html (in repo as p1header.png)


----- connecting screen to pi

https://learn.adafruit.com/adafruit-pitft-28-inch-resistive-touchscreen-display-raspberry-pi/software-installation
moved wires to pins 3 and 6 of external cable
connected cable to display
attached display
sudo apt-get update
curl -SLs https://apt.adafruit.com/add-pin | sudo bash
sudo apt-get install raspberrypi-bootloader

x----- connect vibrator(s) to the morsecode output

- devices connect right to output pins

----- Using the Google sound API to translate sound files to a json reply


----- deserializing json files in Java



----- Playing sound files from the raspberry pi


----- recording sound files from a microphone on the raspberry pi


----- cannot use a laptop ---

- download everything into the rpi and either auto-run or start locally
- network connect through a cell phone wifi
- push button that initiates the loop (sends file, records sound, etc)



