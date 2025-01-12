@echo off
setlocal enabledelayedexpansion

rem define variables
set "appName=ardo"
set "webappsPath=D:\ITU\Server\apache-tomcat-10.1.7\webapps\"
set "tempPath=..\..\Temp\"
set "srcPath=..\src\"
set "libPath=..\lib\"
set "webXmlPath=..\conf\web.xml"
set "tempJava=..\..\Temp\tempJava\"
set "webPath=..\web\"

rem suppression de temp
rmdir /s /q "%tempPath%"

rem re-création de temp
mkdir "%tempPath%"

rem architecture de temp
mkdir "%tempPath%\web"
mkdir "%tempPath%\tempJava"
mkdir "%tempPath%\WEB-INF"
mkdir "%tempPath%\WEB-INF\classes"
mkdir "%tempPath%\WEB-INF\lib"

rem copier de web, lib, web.xml, index.jsp
xcopy "%libPath%" "%tempPath%WEB-INF\lib\" /E /Y 
xcopy "%webXmlPath%" "%tempPath%WEB-INF\" /Y 
xcopy "%webPath%" "%tempPath%\web\" /E /Y /Q

:: Parcourir récursivement tous les fichiers .java dans src et les copier dans tempJava
for /r "%srcPath%" %%f in (*.java) do (
    copy "%%f" "!tempJava!" /Y
)

rem compilation des src vers classes
javac -cp %libPath%* -d %tempPath%\WEB-INF\classes\ -parameters ..\..\Temp\tempJava\*.java

rem Vérifier si la compilation a échoué
if %errorlevel% neq 0 (
    echo Erreur de compilation.
    exit /b %errorlevel%
)

rem suppression de tempJava 
rmdir /s /q "%tempJava%"

rem archivage du contenu de Temp
pushd "%tempPath%"
jar cf "%appName%.war" *
popd

xcopy "%tempPath%\%appName%.war" "%webappsPath%" /Y

echo Deployement successfully done !