# Main README

## Table of Contents
1. [Project Overview](#project-overview)
2. [System Images](#system-images)
3. [How to Run the .JAR File](#how-to-run-the-jar-file)
4. [Parallelism Used](#parallelism-used)
5. [Test Cases and Results](#test-cases-and-results)
6. [Clone the Repository](#clone-the-repository)
7. [Available Branches](#available-branches)
8. [System Requirements](#system-requirements)
9. [Acknowledgments](#acknowledgments)
10. [License](#license)


## Project Overview

This project consists of two main applications:
1. **File Organizer System (FOS)**: A tool to organize files efficiently.
   - Two versions: 
     - **Single-threaded**: Processes files sequentially.
     - **Multi-threaded**: Leverages parallelism for faster processing.

2. **File Generator**: A utility to generate files for testing the organizer.
   - Two versions:
     - **Single-threaded**: Creates files one at a time.
     - **Multi-threaded**: Generates multiple files concurrently.

Both applications showcase the impact of parallelism on performance, providing insights through test results.


## System Images

### File Organizer System (FOS)
![FOS preview](Images/FOS%20preview.jpeg)

### File Generator
![File generator preview](Images/File%20generator%20preview.jpeg)

> **Note:** This project uses the fictitious D&D (Daniel Development) brand as part of an academic narrative. It does not infringe intellectual property rights nor is it associated with real brands.


## How to Run the .JAR File

1. Download the `.JAR` file from the **release** branch of the repository.
2. Open a terminal or command prompt.
3. Navigate to the directory where the `.JAR` file is located.
4. Execute the command:
   ```bash
   java -jar FileOrganizer.jar
   ```
5. Follow the on-screen instructions to organize files.


## Parallelism Used

The software employs **data parallelism**, distributing blocks of 500 files to each thread. This approach enables simultaneous processing, optimizing execution time compared to single-threaded execution.


## Test Cases and Results

### Laptop CPU: AMD A10 (4 cores, 4 threads)

| **Number of Files** | **Single-threaded (ms)** | **Multi-threaded (ms)** | **Reduction (%)** |
|---------------------|--------------------------|-------------------------|-------------------|
| 5,000               | 57,667                   | 11,279                  | 79.74             |
| 10,000              | 100,604                  | 18,769                  | 81.36             |
| 15,000              | 153,120                  | 26,518                  | 82.74             |

### Desktop CPU: Ryzen 5 3600 (6 cores, 12 threads)

| **Number of Files** | **Single-threaded (ms)** | **Multi-threaded (ms)** | **Reduction (%)** |
|---------------------|--------------------------|-------------------------|-------------------|
| 5,000               | 2,901                    | 1,934                   | 33.34             |
| 10,000              | 6,215                    | 3,011                   | 51.55             |
| 15,000              | 11,535                   | 3,788                   | 67.16             |

> **Note:** The execution time varies based on file sizes. Nonetheless, using multiple threads improves efficiency by 50% to 80%.


## Clone the Repository

Run the following command in your terminal to clone the project:
```bash
git clone https://github.com/Daga321/File-Organization-System.git
```

## Available Branches

### Branch `release`
- Contains the executable `.JAR` files for:
  - **File Organizer System**:
    - Single-threaded version
    - Multi-threaded version
  - **File Generator**:
    - Single-threaded version
    - Multi-threaded version
- Does not include the source code.

### Branch `FOS-single-thread`
- Contains the source code for the **File Organizer System (Single-threaded version)**.
- Suitable for sequential file organization without parallelism.

### Branch `FOS-multi-thread`
- Contains the source code for the **File Organizer System (Multi-threaded version)**.
- Implements multi-threading to optimize file organization using parallelism.

### Branch `file-generator-single-thread`
- Contains the source code for the **File Generator (Single-threaded version)**.
- Generates files sequentially for testing purposes.

### Branch `file-generator-multi-thread`
- Contains the source code for the **File Generator (Multi-threaded version)**.
- Utilizes multi-threading to generate multiple files concurrently.

> **Note:** To execute the source code in any branch, navigate to `src/execution` and run the `Run.java` file using your preferred IDE.


## System Requirements

- **Java JDK**: Version 17 or higher.
- **Development Environment**: Eclipse, IntelliJ, or equivalent.

## Acknowledgments

I would like to express my gratitude to **Daniel34981** and **LauraTamayo12**, whose contributions, in greater or lesser measure, were essential to the development of this project. Additionally, I extend my thanks to the academic institution that supported its realization.

## License

This project is open-source under the MIT license. See the LICENSE file in the repository for more details.
