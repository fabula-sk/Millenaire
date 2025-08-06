#!/bin/bash
# setup.sh -- prepare Gradle dependencies, then try to compile
# This script should not fail even if the build does.

echo "Downloading Gradle dependencies..."
./gradlew --refresh-dependencies || true

echo "Attempting to compile..."
./gradlew build || true

echo "Setup complete."

