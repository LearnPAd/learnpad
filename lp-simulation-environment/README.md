LearnPAd Simulator
==================

Information   | Value
------------- | --------
Component     | Simulator
Partner       | LIN, CNR
WP            | 6
Responsible   | Tom Jorquera <tjorquera@linagora.com>
Collaborators |Antonello Calabro' <antonello.calabro@isti.cnr.it>, Guglielmo De Angelis <guglielmo.deangelis@isti.cnr.it>
Roadmap       | [http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Simulation+Environment+](http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Simulation+Environment+)

# Summary
This is the simulator component of the Learn PAd platform is composed by two sub-components:
 * the simulator engine
 * the monitoring engine

Please see the respective description files.

# How it works?
To run the SIM component (from the simulator project root):

- first build the project by running

        ./build

  This should create the `out` folder with the simulator and start/stop scripts.

- you can then launch the simulator by running

        ./out/start

As indicated on the command-line, the demo should then be available at :
 * SIM [http://localhost:8081](http://localhost:8081).
 * JMS Broker : [http://localhost:61616](http://localhost:61616).

- to stop the simulator, run

        ./out/stop

# Configuration
The main configurations are given or impacts on the following (possibly not complete) list of files:
 * lp-simulation-environment/simulator/out/simulator.properties (optional file, may not exist)
 * lp-simulation-environment/monitoring/configFiles/environmentFile
 * lp-simulation-environment/monitoring/systemSettings
 * lp-simulation-environment/monitoring/out/systemSettings (only once the system is built)
 * lp-simulation-environment/scripts/start 
 * lp-simulation-environment/out/start (only once the system is built)

## Specific deployment

When building the component, it is possible to pass a "--name" argument to the ./build script, where "name" correspond to subfolder in the `deployment` folder, containing a set of files to be used for a specific deployment.
    
For example, if `deployment` contains a `test` folder, the command ./build --test will setup the files in this folder accordingly.

Currently, the following files can be installed via this process:
 * simulator.properties

## Note that for this test HTTP protocol should be used.

# Interfaces

## Java API

You can also refer to the `Main` to see an example of how the whole SIM API can be used.

## REST API
The Simulator implements the Simulator Bridge REST API specification defined in the [LearnPAd Core Platform APIs project](https://github.com/tomjorquera/learnpad/tree/master/lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/sim). Refer to this API to see the how to interact with the Simulator using REST.
