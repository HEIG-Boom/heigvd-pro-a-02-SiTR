# SiTR : Simulation de Trafic Routier
SiTR (Road traffic simulation) is a simulation tool with a goal to analyse road traffic and give statistics on its users
and the road network itself. Vehicles can behave differently based on the type of controllers they are assigned. One
goal is to make conclusions on the effect of augmenting autonomous vehicles amounts in a standard setting (with other,
non autonomous vehicles).

In this sense, SiTR has a microscopic approach to simulation, but it will also be able to get more global statistics
from the simulation. Its analyses will be run on predefined, small-scale scenarios. These will simulate a small
and interesting part of a road network.

Development team:

| Name                                 | Email                                | Github      |
|--------------------------------------|--------------------------------------|-------------|
| Loris Gilliand (project lead)        | loris.gilliand@heig-vd.ch            | texx94      |
| Alexandre Monteiro Marques           | alexandre.monteiromarques@heig-vd.ch | X-Brast     |
| Eric Noël (deputy project lead)      | eric.noel@heig-vd.ch                 | Eric-Noel   |
| Mateo Tutic                          | mateo.tutic@heig-vd.ch               | mtutic      |
| Luc Wachter                          | luc.wachter@heig-vd.ch               | Laykel      |
| Simon Walther                        | simon.walther@heig-vd.ch             | Waltharnack |

## Dependencies
This software requires Java 8 or above and the following tools and libraries:

- Apache Maven v. 3.5.4
- jUnit v. 5.4
- Lombok v. 1.18.2

## Build and install
Thanks to Apache Maven, running `mvn package` should suffice to run tests and create the jar file in `target/sitr-1
.0-SNAPSHOT-launcher.jar`.

## Run
- The program can be run using the previously mentioned jar file using `java -jar sitr-1
.0-SNAPSHOT-launcher.jar`.
- It can also be run through an IDE (or the `java` command), by running the `main` entry point situated in `ch.heigvd
.sitr.App` after compilation.

## Documentation
Class diagrams, mock-ups, design decisions and style guidelines in the wiki.

User manual: to do.

API documentation: to generate.