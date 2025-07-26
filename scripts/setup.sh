#!/usr/bin/env bash
set -euo pipefail

# Ensure we are in project root
cd "$(dirname "$0")/.."

# Desired JDK version (default 17)
JDK_VERSION="${JDK_VERSION:-17}"

have_jdk=false
if type -p javac >/dev/null; then
  if javac -version 2>&1 | grep -q "${JDK_VERSION}"; then
    have_jdk=true
  fi
fi

if [ "$have_jdk" = false ]; then
  echo "Installing OpenJDK ${JDK_VERSION}..."
  if command -v apt-get >/dev/null; then
    sudo apt-get update
    sudo apt-get install -y "openjdk-${JDK_VERSION}-jdk"
  else
    echo "Please install OpenJDK ${JDK_VERSION} manually and re-run this script." >&2
    exit 1
  fi
fi

export JAVA_HOME="/usr/lib/jvm/java-${JDK_VERSION}-openjdk-amd64"
export PATH="$JAVA_HOME/bin:$PATH"

echo "Using JDK at $JAVA_HOME"

# Fetch Gradle wrapper and build the project
./gradlew --version
./gradlew build

cat <<MSG
Note: Gradle may need network access to download dependencies from Maven
repositories such as maven.minecraftforge.net.
MSG
