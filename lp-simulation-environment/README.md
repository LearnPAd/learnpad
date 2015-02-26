LearnPAd Simulator
==================

Information   | Value
------------- | --------
Component     | Simulator
Partner       | LIN
WP            | 6
Responsible   | Tom Jorquera <tjorquera@linagora.com>
Collaborators |
Roadmap       | [http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Simulation+Environment+](http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Simulation+Environment+)

# Summary
This is the simulator component of the Learn PAd platform. It provides means to run a simulation of a process.

# How it works?
The simulator is currently a self-contained demo application including a BPMN 2.0 engine and an embedded webserver. To run it:

- first create the executable jar by running
    mvn package
  This should create the `target/simulator-<version>-jar-with-dependencies.jar` file.

- you can then launch the jar by running

        java -jar simulator-<version>-jar-with-dependencies.jar

  (replace <version> with the current version of the simulator)

As indicated on the command-line, the demo should then be available at [http://localhost:8081](http://localhost:8081).

# Configuration
There is no possible configuration at the moment.

# Interfaces
This component does not yet implements the interface specification defined in the Roadmap (but this is worked on :wink:).

Currently, the main entry point of the Simulator is the `Simulator` class, which provides 3 methods to get the various interfaces:

- `userHandler()`: to manage users
- `processManager()`: for process-related tasks
- `processEventReceiver()`: to get the process event listener (probably not very useful except if you want to interact with the simulator UI)

To get more info regarding the different interfaces, please refer to the associated JavaDoc.

You can also refer to the `Main` to see an example of how the simulator API can be used.

NOTE: the current API is in a strong flux state and is subject to heavy changes (especially to conform to the Simulation Environment interface specification aforementioned)
