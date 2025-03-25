# Oxygen Not Included Characters Generator With SQL

This project was developed as part of the **Database** course in the Computer Engineering program at **UNIFEI**. It builds upon the [oxygem-not-included](https://github.com/TonyALima/oxygem-not-included) project by adding functionality to save generated data into a **SQL database** and introducing **graphical user interfaces** for user interaction.

## Features

- **Character Generation**: Randomly generate characters with unique traits, attributes, and conditions.
- **SQL Database Integration**: Persist generated data into a MariaDB database using JPA and Hibernate.
- **Graphical User Interfaces**: Interact with the system through intuitive Java Swing-based windows for inserting, updating, and removing characters.
- **Observer Pattern**: React to changes in character conditions (e.g., stress or health) with dynamic updates.
- **Customizable Builders**: Generate characters with specific roles like researchers, cooks, or random profiles.

## Technologies Used

- **Java 17**: Core programming language.
- **Hibernate**: ORM framework for database interaction.
- **MariaDB**: SQL database for data persistence.
- **Lombok**: Simplifies Java code with annotations.
- **Maven**: Build and dependency management tool.

## Project Structure

- **`src/main/java`**: Contains the main application code, including character generation logic, database entities, and GUI classes.
- **`src/main/resources`**: Includes configuration files like `persistence.xml`.
- **`src/test`**: Reserved for unit tests (currently empty).

## Future Improvements

- Add unit tests for core functionality.
- Enhance GUI usability and design.
- Support additional database systems beyond MariaDB.
- Implement advanced character customization options.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
