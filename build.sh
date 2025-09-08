#!/bin/bash

echo "🚀 Building Employee Management System..."
echo "========================================="

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven first."
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 24 first."
    exit 1
fi

# Clean and compile
echo "🧹 Cleaning previous builds..."
mvn clean

echo "🔨 Compiling application..."
mvn compile

if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
else
    echo "❌ Compilation failed!"
    exit 1
fi

# Package application
echo "📦 Packaging application..."
mvn package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Packaging successful!"
    echo "🏃 You can now run the application using:"
    echo "    mvn spring-boot:run"
    echo "    OR"
    echo "    java -jar target/backend-0.0.1-SNAPSHOT.jar"
else
    echo "❌ Packaging failed!"
    exit 1
fi

echo "🎉 Build completed successfully!"
echo "📚 Access API documentation at: http://localhost:8080/swagger-ui.html"