# ECSE 437 Software Delivery Final Project
[![Java CI with Maven](https://github.com/sandyl289/XmasDash/actions/workflows/maven.yml/badge.svg)](https://github.com/sandyl289/XmasDash/actions/workflows/maven.yml)

# XmasDash 🎄💨

Project inspired by [Dino T-Rex (Chrome Dino)](https://dino-chrome.com/en) game.

# How to Play 🎮
Use the `space bar` to make the dino jump. The goal is to jump over all the Christmas trees 🎄.
- Press `R` key to restart the game
- Press `P` key to pause

# Developers 👋
|Name| GitHub|Major|Year|
|----|----|----|----|
|💻 Aidan Jackson| [AidanJack](https://github.com/AidanJack)| Software Engineering|U3|
|💻 Sandy Lao|  [sandyl289](https://github.com/sandyl289)|Software Engineering|U3|

# Prerequisites ⚙
- Java 18
- Apache Maven 3.8.6

# Running the App ️▶
1. Clone the repo and navigate into the repo directory
2. Run:
```sh
mvn clean package
```
3. Run:
```sh
java -jar target/XmasDash.jar
``` 
or open `XmasDash.jar` from Finder (Mac) / File Explorer (Windows)

Note: `XmasDash.jar` can be downloaded from the `executable` folder or click [here](https://github.com/sandyl289/XmasDash/raw/main/Executable/XmasDash.jar)

# Running the Tests 🧪
- On a terminal, run `mvn clean test`   
OR   
- On an IDE, right click on the `src/test/java` folder and click on "Run".

Note: We are not testing `Main` and `Obstacle` classes, helper classes (`ImageHelper`, `KeyHandler`, `MusicHelper`) and some UI functions such as `paint(Graphics2D g2)` in `Landscape`. We are also not testing functions in `GamePanel`
# Jacoco Report 📃
- On a terminal, run `mvn clean test jacoco:report`  
  On IntelliJ IDE, click on the `target/site/jacoco/index.html` file
- Click on one of the browser icons to open the Jacoco Report
