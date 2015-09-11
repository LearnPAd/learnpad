* *Builds* [![Build
Status](https://travis-ci.org/LearnPAd/learnpad.svg?branch=master)](https://travis-ci.org/LearnPAd/learnpad)
* *Coverage* [![Coveralls](https://coveralls.io/repos/LearnPAd/learnpad/badge.svg)](https://coveralls.io/r/LearnPAd/learnpad)
* *Quality (only Javascript)* [![Code
Climate](https://codeclimate.com/github/LearnPAd/learnpad/badges/gpa.svg)](https://codeclimate.com/github/LearnPAd/learnpad)
[![Codacy
Badge](https://www.codacy.com/project/badge/614d864cf4d44c5ba52b5df40f9812c6)](https://www.codacy.com/app/woshilapin/learnpad)

Learn PAd project - Model-Based Social Learning for Public Administrations
==========================================================================

Learn PAd is a European financed project ([FP7](http://cordis.europa.eu/fp7/)).

# Presentation
In modern society public administrations (PAs) are undergoing a transformation
of their perceived role from controllers to proactive service providers, and are
under pressure to constantly improve their service quality while coping with
quickly changing context (changes in law and regulations, societal
globalization, fast technology evolution) and decreasing budgets. Civil servants
are challenged to understand and put in action latest procedures and rules
within tight time constraints. Learn PAd will build an innovative holistic
e-learning platform for PAs that enables process-driven learning and fosters
cooperation and knowledge-sharing. Learn PAd technical innovation is based on
four pillars:

1. a new concept of model-based e-learning (both process and knowledge)
2. open and collaborative e-learning content management
3. automatic, learner-specific and collaborative content quality assessment
4. automatic model-driven simulation-based learning and testing

Learn PAd considers learning and working strongly intertwined (learning while
doing).  The platform supports both an informative learning approach based on
enriched business process (BP) models, and a procedural learning approach based
on simulation and monitoring (learning by doing). Formal verification and
natural language processing techniques will ensure quality of content and
documentation. Specialized ontologies and KPIs will be defined to keep learners
engaged, while automatically derived tests will challenge their acquired
knowledge. Learn PAd is inspired by open-source communities principles and
cooperation spirit: contents are produced by the community, and meritocracy is
naturally promoted, with leaders emerging because of their skill and expertise.
Finally Learn PAd will contribute to dissemination and evolution of BPMN and
related modeling standards.

## Partners

|  #  | Name                               | Short name    | Country     | Project entry month | Project exit month |
| --- | ---------------------------------- | ------------- | ----------- | ------------------- | ------------------ |
|  1  | CONSIGLIO NAZIONALE DELLE RICERCHE | CNR           | Italy       | 1                   | 30                 |
|  2  | BOC ASSET MANAGEMENT GMBH          | BOC           | Austria     | 1                   | 30                 |
|  3  | Linagora Grand Sud Ouest SA        | LINAGORA GSO  | France      | 1                   | 30                 |
|  4  | NO MAGIC EUROPE UAB                | NME           | Lithuania   | 1                   | 30                 |
|  5  | REGIONE MARCHE                     | MARCHE REGION | Italy       | 1                   | 30                 |
|  6  | FACHHOCHSCHULE NORDWESTSCHWEIZ     | FHNW          | Switzerland | 1                   | 30                 |
|  7  | UNIVERSITA DEGLI STUDI DI CAMERINO | UNICAM        | Italy       | 1                   | 30                 |
|  8  | UNIVERSITA DEGLI STUDI DELL'AQUILA | UNIVAQ        | Italy       | 1                   | 30                 |
|  9  | XWIKI SAS                          | XWIKI SAS     | France      | 1                   | 30                 |

# The platform
## Build
First of all, clone the repository.

```
git clone https://github.com/LearnPAd/learnpad.git
```

Then, once cloned, you'll need to import submodules.
```
cd learnpad
git submodule init
git submodule update
```

Finally, you can trigger a build with the `build` script in the root directory.

```
./build
```

## Run it!
After the build, a complete wiki instance will exists in the directory `lp-platform` and it will
be the core of the platform.  You should be able to run the platform with the
following command

```
lp-platform/out/start
```

You can also stop it with the following command.
```
lp-platform/out/stop
```

Once the platform is started, access it on `localhost:8080` in your webbrowser.

## Components
Learn PAd platform is a set of components, each one is in an independent
directory.  To know the exact list of components in the platform, you can look
into the `.components` file.

