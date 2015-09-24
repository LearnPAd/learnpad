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
The simulator is currently a self-contained demo application including a BPMN 2.0 engine and an embedded webserver. To run it (from the simulator project root):

- first build the project by running

        ./build

  This should create the `out` folder with the simulator and start/stop scripts.

- you can then launch the simulator by running

        ./out/start

As indicated on the command-line, the demo should then be available at [http://localhost:8081](http://localhost:8081).

- to stop the simulator, run

        ./out/stop

# Configuration
There is no possible configuration at the moment.

# Interfaces

## Java API
Currently, the main entry point of the Simulator is the `Simulator` class, which provides 3 methods to get the various interfaces:

- `userHandler()`: to manage users
- `processManager()`: for process-related tasks
- `processEventReceiver()`: to get the process event listener (probably not very useful except if you want to interact with the simulator UI)

To get more info regarding the different interfaces, please refer to the associated JavaDoc.

You can also refer to the `Main` to see an example of how the simulator API can be used.

## REST API
The Simulator implements the Simulator Bridge REST API specification defined in the [LearnPAd Core Platform APIs project](https://github.com/tomjorquera/learnpad/tree/master/lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/sim). Refer to this API to see the how to interact with the Simulator using REST.
