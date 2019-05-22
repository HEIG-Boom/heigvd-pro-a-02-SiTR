# SiTR : Simulation de Trafic Routier
SiTR (Road traffic simulation) is a simulation tool with a goal to analyse road traffic and give statistics on its users
and the road network itself. Vehicles can behave differently based on the type of controllers they are assigned. One
goal is to make conclusions on the effect of augmenting autonomous vehicles amounts in a standard setting (with other,
non autonomous vehicles).

In this sense, SiTR has a microscopic approach to simulation, but it will also be able to get more global statistics
from the simulation. Its analyses will be run on predefined, small-scale scenarios. These will simulate a small
and interesting part of a road network.

## Illustration
![Running simulation](https://raw.githubusercontent.com/wiki/HEIG-Boom/heigvd-pro-a-02-SiTR/img/RunningSim.png)

## Development team

| Name                                 | Email                                | Github      |
|--------------------------------------|--------------------------------------|-------------|
| Loris Gilliand (project lead)        | loris.gilliand@heig-vd.ch            | texx94      |
| Alexandre Monteiro Marques           | alexandre.monteiromarques@heig-vd.ch | X-Brast     |
| Eric Noël (deputy project lead)      | eric.noel@heig-vd.ch                 | Eric-Noel   |
| Mateo Tutic                          | mateo.tutic@heig-vd.ch               | mtutic      |
| Luc Wachter                          | luc.wachter@heig-vd.ch               | Laykel      |
| Simon Walther                        | simon.walther@heig-vd.ch             | Waltharnack |

## Dependencies
Used through the `.jar` file, this application needs nothing more than Java 8 or above to run.

In order to compile and run this project yourself, you'll need Apache Maven (v. 3.5.4).

Maven will take care of the following dependencies for you:

- jUnit v. 5.4
- Lombok v. 1.18.2
- jDOM2 v. 2.0.6
- jaxb v. 2.3.0
- Commons math 3 v. 3.6.1

## Build and install
Thanks to Apache Maven, running `mvn package` should suffice to run tests and create the jar file in `target/sitr-1
.0-SNAPSHOT-launcher.jar`.

You can also use the most recent `.jar` file in the "releases" section of our GitHub repository.

## Run
- The program can be run using the previously mentioned jar file using `java -jar sitr-1
.0-SNAPSHOT-launcher.jar` (or another name if you downloaded it from the "releases" section).

## Documentation
Class diagrams, mock-ups, design decisions and style guidelines can be found the wiki (in french).

We also provide a user manual and an installation guide at the end of our project report, which can also be 
found in the
 "releases" section on our GitHub repository.

The API documentation can be generated using an IDE with support for JavaDoc, but a generated version is available in 
the "releases" section.