@echo off
setlocal enabledelayedexpansion
echo ========================================================
echo   INICIANDO CLIENTE LipeRMI (GUI Swing)
echo ========================================================
cd /d "%~dp0\.."

set "JAVA_CMD=java"
where java >nul 2>nul
if %ERRORLEVEL% neq 0 (
    if defined JAVA_HOME (
        if exist "%JAVA_HOME%\bin\java.exe" set "JAVA_CMD=%JAVA_HOME%\bin\java.exe"
    )
)
where java >nul 2>nul
if %ERRORLEVEL% neq 0 (
    for /d %%i in ("%ProgramFiles%\Java\jdk*") do (
        if exist "%%i\bin\java.exe" set "JAVA_CMD=%%i\bin\java.exe"
    )
)

"!JAVA_CMD!" -cp "ConversionRmiCliente\build\classes;lib\lipermi-1.0.1.jar;lib\ConversionRmiLib.jar" com.ejemplo.conversion.rmi.Principal