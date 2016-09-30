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
The simulator allows to override some configuration using an optional properties file. This properties file allows to override some default properties values:

- the IP used by the simulator
- the address of the glimpse server
- the address of the LearnPAd platform

This properties file must named `simulator.properties` and placed in the folder from which the simulator is started (typically the ./out subfolder if launching the simulator with the start script).

To override the address user by the simulator, put the following inside the file:
`address=<address>`

To override the glimpse server, put the following inside the file:
`glimpse_server=tcp://<address>`

To override the LearnPAd platform address, put the following inside the file:
`platform_address=<address>`

To change the type of robot available in the platform, use the following property:
`robot_type=<none|simple|markov`

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
