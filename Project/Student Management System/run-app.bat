@echo off
set CATALINA_HOME=C:\Users\RATIK\OneDrive\Desktop\apache-tomcat-9.0.117\apache-tomcat-9.0.117
set JAVA_HOME=C:\Program Files\Java\jdk-23

cd /d "%CATALINA_HOME%\bin"
call startup.bat

echo.
echo ====================================
echo Tomcat is starting...
echo Wait 10 seconds, then open:
echo http://localhost:8080/StudentManagementSystem/students
echo ====================================
