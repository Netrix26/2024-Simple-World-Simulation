# Java Virtual World Simulator 🌍

This project is a 2D life simulation and turn-based game built in Java. It was developed as a university project to deeply explore and implement **Object-Oriented Programming (OOP)** principles, including Inheritance, Polymorphism, Encapsulation, and Abstraction.

Unlike procedural projects, this simulator relies on a robust class hierarchy. All entities inherit from an abstract `Organism` base class, which branches into abstract `Animal` and `Plant` classes.

## Core Features

* **Dual Board Architecture:** The game fully supports both standard Square Grids (`GridWorld`) and Hexagonal Grids (`HexWorld`), utilizing polymorphism to handle movement and rendering logic differently based on the chosen board type.
* **Turn-Based Ecosystem:** Action order is not arbitrary. It is strictly determined by each organism's `initiative` stat, with `age` resolving any ties.
* **Java Swing GUI:** The graphical user interface is built using Java Swing (`JFrame`, `JButton`), providing an interactive grid, control buttons, and dynamic image loading for organisms.
* **State Persistence:** Users can save the current state of the simulation (including exact organism positions, ages, cooldowns, and strengths) to a `save.txt` file and load it later.

## Ecosystem & Mechanics

The world is populated by various organisms, each overriding default behaviors to create unique survival mechanics.

### Animals (Move, Breed, and Fight)

* **Human (Player):** Controlled via the UI, the human features a unique, cooldown-based ability called "Alzur's Shield" which entirely deflects incoming attacks.
* **Wolf & Sheep:** The standard predator and prey of the simulation.
* **Fox:** Relies on intelligence over strength; its overridden movement logic ensures it will never step into a cell occupied by an organism stronger than itself.
* **Turtle:** Highly defensive but slow. It has a 75% chance to skip its movement phase, but it automatically deflects attacks from creatures with a strength lower than 5.
* **Antelope:** Highly mobile and evasive. It moves up to 2 spaces per turn and possesses a 50% chance to escape any battle.

### Plants (Spread and Defend)

* **Grass & Sow Thistle:** Standard flora. The Sow Thistle is highly invasive, attempting to spread to neighboring tiles 3 times per turn.
* **Guarana:** A strengthening plant. Any animal that consumes it gains a permanent +3 bonus to their strength stat.
* **Balladonna & Hogweed:** Deadly toxic flora that kills any animal attempting to eat them.
* **Hogweed (Aggressive Plant):** Exceptionally dangerous. Unlike other plants, it actively kills any non-plant organisms occupying its neighboring tiles every single turn.

## How to Run

1. Compile the `.java` files located in the `src/` directory.
2. Ensure the `Images/` folder is in the root directory.
3. Run the `Main` class.
4. You will be prompted to select a board type (`grid` or `hex`). Use the UI buttons to advance turns, move the human, trigger abilities, or place new organisms dynamically.
