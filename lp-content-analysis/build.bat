@echo off

::set CATALINA_BASE=C:\Users\winspa\workspace_kep\tom
::set CATALINA_HOME=C:\Users\winspa\workspace_kep\tom
::set PATH=%PATH%;C:\Users\winspa\Downloads\apache-maven-3.3.3-bin\apache-maven-3.3.3\bin

set __CATALINA__BASE__=%CATALINA_BASE%

set  __CATALINA__WEB__=%__CATALINA__BASE__%\webapps

IF DEFINED CATALINA_BASE (
    echo The variable not empty
) ELSE (
    echo Please: set CATALINA_BASE
    GOTO:EOF
)

echo %__CATALINA__WEB__%

echo %__CATALINA__BASE__%


IF DEFINED CATALINA_BASE (
if "tasklist /v | find /I "tomcat"" == "" (
echo CATALINA STOP
call %CATALINA_BASE%\bin\shutdown.bat
call PING -n 3 127.0.0.1>nul
)
if exist %__CATALINA__WEB__%\lp-content-analysis.war (	del /S /F /A %__CATALINA__WEB__%\lp-content-analysis.war)
if exist %__CATALINA__WEB__%\lp-content-analysis	    ( 

rmdir  %__CATALINA__WEB__%\lp-content-analysis /s /q
)

call mvn clean
if exist target		(rmdir target /s /q)
) ELSE (
    echo Please: set CATALINA_BASE
)


@REM skip tests as we will do them in the component_test stage
call mvn  package -Dmaven.test.skip=true
xcopy /y target\lp-content-analysis.war %__CATALINA__WEB__%
call %CATALINA_BASE%\bin\startup.bat

