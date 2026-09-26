@echo off
setlocal enabledelayedexpansion

echo ========================================================
echo   COMPILANDO PROYECTO LipeRMI (Conversion Km a Millas)
echo ========================================================

cd /d "%~dp0\.."

set "JAVAC_CMD=javac"
set "JAR_CMD=jar"

where javac >nul 2>nul
if %ERRORLEVEL% neq 0 (
    if defined JAVA_HOME (
        if exist "%JAVA_HOME%\bin\javac.exe" set "JAVAC_CMD=%JAVA_HOME%\bin\javac.exe"
    )
)
where javac >nul 2>nul
if %ERRORLEVEL% neq 0 (
    for /d %%i in ("%ProgramFiles%\Java\jdk*") do (
        if exist "%%i\bin\javac.exe" set "JAVAC_CMD=%%i\bin\javac.exe"
    )
)

where jar >nul 2>nul
if %ERRORLEVEL% neq 0 (
    if defined JAVA_HOME (
        if exist "%JAVA_HOME%\bin\jar.exe" set "JAR_CMD=%JAVA_HOME%\bin\jar.exe"
    )
)
where jar >nul 2>nul
if %ERRORLEVEL% neq 0 (
    for /d %%i in ("%ProgramFiles%\Java\jdk*") do (
        if exist "%%i\bin\jar.exe" set "JAR_CMD=%%i\bin\jar.exe"
    )
)

echo Usando compilador:   !JAVAC_CMD!
echo Usando empaquetador: !JAR_CMD!
echo.

echo [1/3] Compilando ConversionRmiLib...
if not exist "ConversionRmiLib\build\classes" mkdir "ConversionRmiLib\build\classes"
if not exist "ConversionRmiLib\dist" mkdir "ConversionRmiLib\dist"
if not exist "lib" mkdir "lib"
"!JAVAC_CMD!" -encoding UTF-8 -d "ConversionRmiLib\build\classes" ConversionRmiLib\src\com\ejemplo\conversion\rmi\lib\*.java
if %ERRORLEVEL% neq 0 (
    echo Error al compilar ConversionRmiLib
    pause
    exit /b %ERRORLEVEL%
)
"!JAR_CMD!" -cvf "ConversionRmiLib\dist\ConversionRmiLib.jar" -C "ConversionRmiLib\build\classes" . > nul
copy /y "ConversionRmiLib\dist\ConversionRmiLib.jar" "lib\ConversionRmiLib.jar" > nul
echo       ConversionRmiLib.jar generado exitosamente en lib\

echo [2/3] Compilando ConversionRmiServidor (LipeRMI)...
if not exist "ConversionRmiServidor\build\classes" mkdir "ConversionRmiServidor\build\classes"
"!JAVAC_CMD!" -encoding UTF-8 -cp "lib\lipermi-1.0.1.jar;lib\ConversionRmiLib.jar" -d "ConversionRmiServidor\build\classes" ConversionRmiServidor\src\com\ejemplo\conversion\rmi\*.java ConversionRmiServidor\src\com\ejemplo\conversion\rmi\net\*.java
if %ERRORLEVEL% neq 0 (
    echo Error al compilar ConversionRmiServidor
    pause
    exit /b %ERRORLEVEL%
)
echo       ConversionRmiServidor compilado exitosamente.

echo [3/3] Compilando ConversionRmiCliente (LipeRMI)...
if not exist "ConversionRmiCliente\build\classes" mkdir "ConversionRmiCliente\build\classes"
"!JAVAC_CMD!" -encoding UTF-8 -cp "lib\lipermi-1.0.1.jar;lib\ConversionRmiLib.jar" -d "ConversionRmiCliente\build\classes" ConversionRmiCliente\src\com\ejemplo\conversion\rmi\*.java ConversionRmiCliente\src\com\ejemplo\conversion\rmi\vistas\*.java
if %ERRORLEVEL% neq 0 (
    echo Error al compilar ConversionRmiCliente
    pause
    exit /b %ERRORLEVEL%
)
echo       ConversionRmiCliente compilado exitosamente.

echo ========================================================
echo   COMPILACION DE PROYECTOS LipeRMI EXITOSA
echo ========================================================
pause