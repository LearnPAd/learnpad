# Deployment of the platform
All the platform is deployed inside /home/jsimard/learnpad.  `~/learnpad` is a
symbolic link to the correct folder `~/learnpad.20160101-1200` (suffixed with
date and hour).

This `~/learnpad` folder is organized as follows:
1. It contains a folder for each component that expose a server (CP/CW/OR, CA, MT,
   MV, SIM, QM)
1. Each folder is the `out/` folder from the build (for example, the folder
   `lp-platform` comes from `learnpad/lp-platform/out` in the build).
1. Every component can be start with the `start` script, except the CP (in
   `lp-platform`); for CP, details are below

## Specificities of CP
For CP, the XWiki infrastructure is used instead.  The XWiki infrastructure is
using the script `/etc/init.d/xwiki.sh`.  Also, if ever the XWiki instance is
not responding anymore, the XWiki infrastructure will try to restart it (there
is a `cron` task checking for Tomcat every minute and for XWiki every 5
minutes).  Of course, when you deploy a new version, you want this automatic
restart to be deactivated.  Everything can be done with `xwiki.sh`:

* to activate the maintenance mode `xwiki.sh maintenance on`
* to activate the monitoring mode `xwiki.sh maintenance off`
* to start the wiki `xwiki.sh start`
* to stop the wiki `xwiki.sh stop`
* to restart the wiki `xwiki.sh restart`

The `xwiki.sh` will start a tomcat located in `/usr/local/tomcat`.  This Tomcat
is configured to launch an XWiki located in `/usr/local/xwiki` which is a
symbolic link towards `~/learnpad/lp-platform/xwiki/webapps/xwiki`.  Note also
that the data directory is `/usr/local/xwiki-workdir` which is a symbolic link
towards `~/learnpad/lp-platform/xwiki/data`.

# Deployment procedure
First, build a fresh version of Learn PAd.  Then create a folder that will
contains all the `out/` folders inside (with the component names).

You can also deactivate all the debug remote port that are executed with each
component.  For this, look into the `start` scripts and remove the following
lines (for example, in MT component).

```
-Xdebug \
-Xrunjdwp:transport=dt_socket,address=8998,server=y,suspend=n \
```

Upload these big folder to `learnpad.devxwiki.com` in
`~/learnpad.<dateoftheday>-<hour>`.  Let's put the date of 01/01/2016, 12:00 for
the purpose of this guide (`learnpad.20160101-1200`).

Open an `ssh` session onto the learnpad server then put the wiki in maintenance
mode.

```
sudo /etc/init.d/xwiki.sh maintenance on
```

You'll need to report modifications from `~/learnpad` to your new deployment
`learnpad.20160101-1200`.  Please look into the folder
`~/learnpad/lp-platform/xwiki/webapps/xwiki/WEB-INF` and update your files
considering the files `hibernate.cfg.xml`, `web.xml`, `xwiki.cfg`,
`xwiki.properties` and `learnpad.properties`.  You can do these comparisons
with the following command.

```
vimdiff {learnpad,learnpad.20160429-1110}/lp-platform/xwiki/webapps/xwiki/WEB-INF/web.xml
```

Most probably, you'll have to modify the Filestorage attachment to `file` in
`xwiki.cfg` and the Permanent Directory in `xwiki.properties`.

You also have to change the ownership of some files to the `tomcat` user.

```
sudo chown -R tomcat:tomcat ~/learnpad.20160101-1200/lp-platform/xwiki
```

Now, you can stop the Learn PAd platform.
```
sudo /etc/init.d/xwiki.sh stop
~/learnpad/lp-content-analysis/stop
~/learnpad/lp-model-transformer/stop
~/learnpad/lp-model-verification/stop
~/learnpad/lp-dashboard-kpi/stop
~/learnpad/lp-questionnaire-manager/stop
~/learnpad/lp-simulation-environment/stop
```

Switch the symbolic link `~/learnpad` to the new directory
`~/learnpad.20160101-1200`.

```
rm ~/learnpad
ln -s ~/learnpad.20160101-1200 ~/learnpad
```

Then restart the Learn PAd platform.
```
sudo /etc/init.d/xwiki.sh start
~/learnpad/lp-content-analysis/start
~/learnpad/lp-model-transformer/start
~/learnpad/lp-model-verification/start
~/learnpad/lp-dashboard-kpi/start
~/learnpad/lp-questionnaire-manager/start
~/learnpad/lp-simulation-environment/start
```

At the end, when your wiki is restarted and you tested it worked, you can
reenable the monitoring mode.
```
sudo /etc/init.d/xwiki.sh maintenance off
```

# Notes about MV component
The MV component depends on a binary file called `lola`.  This is a 32 bits
executable that need at least Glibc 2.15 for i386 in order to be executed.
Actual deployment on testbed is not able to execute that since only version 2.13
is possible to install for i386.  It doesn't crash the verification but it gave
a result 'KO' which, as a result, block the import of the modelset inside the
platform.  The actual deployment on testbed deactivate the test by using a
patched version of the JAR of `lp-cp-xwiki-implementation` (modelset are
always imported, even if verification failed).
