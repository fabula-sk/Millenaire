#!/usr/bin/env bash
set -euo pipefail

# Ensure we are in project root
cd "$(dirname "$0")/.."


# Desired JDK version (default 17)
JDK_VERSION="${JDK_VERSION:-17}"

need_jdk=false
if ! type -p javac >/dev/null; then
  need_jdk=true
elif ! javac -version 2>&1 | grep -q "${JDK_VERSION}"; then
  need_jdk=true
fi

if [ "$need_jdk" = true ]; then
  echo "Installing OpenJDK ${JDK_VERSION}..."
  if command -v apt-get >/dev/null; then
    sudo apt-get update
    # Work around ca-certificates-java bug on minimal systems.
    # Some packages expect /lib/security/cacerts to exist.
    if [ ! -e /lib/security/cacerts ]; then
      sudo mkdir -p /lib/security
      sudo ln -sf /etc/ssl/certs/java/cacerts /lib/security/cacerts
    fi
    sudo apt-get install -y "openjdk-${JDK_VERSION}-jdk"
  else
    echo "Please install OpenJDK ${JDK_VERSION} manually." >&2
    exit 1
  fi
fi

export JAVA_HOME="/usr/lib/jvm/java-${JDK_VERSION}-openjdk-amd64"
export PATH="$JAVA_HOME/bin:$PATH"

echo "Using JDK at $JAVA_HOME"

# Ensure Gradle wrapper is present
if [ ! -f gradle/wrapper/gradle-wrapper.jar ]; then
  echo "gradle-wrapper.jar not found. Please download it for Gradle 7.6 and place it in gradle/wrapper." >&2
  exit 1
fi

# Pre-fetch Maven dependencies required for tests
echo "Downloading project dependencies..."
./gradlew --no-daemon --console=plain help --refresh-dependencies
./gradlew --no-daemon --console=plain testClasses -x test --refresh-dependencies

echo "Dependencies cached in ~/.gradle"

cat <<MSG
Note: Gradle may need network access to download dependencies from Maven
repositories such as maven.minecraftforge.net.
MSG
