@echo off

echo 🚀 Building Employee Management System...
echo =========================================

REM Check if Maven is installed
mvn --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Maven is not installed. Please install Maven first.
    pause
    exit /b 1
)

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java is not installed. Please install Java 24 first.
    pause
    exit /b 1
)

REM Clean and compile
echo 🧹 Cleaning previous builds...
mvn clean

echo 🔨 Compiling application...
mvn compile

if %errorlevel% neq 0 (
    echo ❌ Compilation failed!
    pause
    exit /b 1
)

echo ✅ Compilation successful!

REM Package application
echo 📦 Packaging application...
mvn package -DskipTests

if %errorlevel% neq 0 (
    echo ❌ Packaging failed!
    pause
    exit /b 1
)

echo ✅ Packaging successful!
echo 🏃 You can now run the application using:
echo     mvn spring-boot:run
echo     OR
echo     java -jar target/backend-0.0.1-SNAPSHOT.jar
echo.
echo 🎉 Build completed successfully!
echo 📚 Access API documentation at: http://localhost:8080/swagger-ui.html

pause