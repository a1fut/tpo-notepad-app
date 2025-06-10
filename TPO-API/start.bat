@echo off

cd /d "%~dp0"

echo.
echo === Budowanie aplikacji przy pomocy mvnw.cmd ===
call mvnw.cmd clean package -DskipTests
if ERRORLEVEL 1 (
    echo.
    echo [ERROR] Budowanie się nie powiodło. Sprawdź logi powyżej.
    pause
    exit /b 1
)

set JAR_PATH=target\*.jar
for %%F in (%JAR_PATH%) do (
    set APP_JAR=%%F
)

if not defined APP_JAR (
    echo.
    echo [ERROR] Nie znalazłem pliku JAR w katalogu target\. Upewnij się, że projekt został poprawnie spakowany.
    pause
    exit /b 1
)

echo.
echo === Uruchamianie aplikacji: java -jar %APP_JAR% ===
java -jar "%APP_JAR%"
if ERRORLEVEL 1 (
    echo.
    echo [ERROR] Aplikacja nie wystartowała. Sprawdź, czy masz zainstalowaną Javę (wersja ≥ 17).
    pause
    exit /b 1
)

pause
