**Download \& Install**
* JAVA JDK 1.8 (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* MAVEN (http://www-us.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz)
* git		(https://git-scm.com/downloads)

**Preconfig**
* create file ```.bash_profile``` in your home (~)

1. ```export M2_HOME=/Users/isiu/Downloads/apache-maven-3.3.3/
export M2=$M2_HOME/bin
export PATH=$M2:$PATH
export JAVA_HOME=$(/usr/libexec/java_home)```

* open Terminal

```
git clone https://github.com/imatesiu/uicontentanalysiscomponent.git
git checkout -t origin/demo_le

cd uicontentanalysiscomponent
chmod +x build_run

./build_run
```

for run only: 
```
mvn jetty:run-war
```
now open :
```
 localhost:9090
```

-----
**Learnpad**
```
git clone https://github.com/LearnPAd/learnpad.git
cd learnpad
```
edit .components file with : 
```
nano .components
```
replace with this:
```
lp-core-platform
lp-collaborative-workspace
lp-content-analysis
lp-questionnaire-manager
lp-ontology-recommender
lp-platform
```
save and close.

```
chmod +x build

./build

chmod +x launch

./launch -d start
```
now open browser

```
localhost:8080/xwiki/bin/edit/Sandbox/CD5?template=LPCode.CollaborativeDocumentTemplate&editor=inline&sheet=CKEditor.EditSheet
```





