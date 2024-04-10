#!/bin/bash

rename_dir() {
  # Check if the directory exists
  if [ -d "$1" ]; then
      # If it does, rename it
      mv "$1/game" "$1/$2"
      echo "Directory renamed successfully to '$2'."
  else
      # If it doesn't, print an error message
      echo "Directory 'foo' does not exist in /core."
  fi
}
# Check if an argument is provided
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 new_directory_name"
    exit 1
fi

# Define the directory path
core_path="core/src/main/kotlin/io/github/thanosfisherman"
teavm_path="teavm/src/main/kotlin/io/github/thanosfisherman"
desktop_path="desktop/src/main/kotlin/io/github/thanosfisherman"
android_path="android/src/main/java/io/github/thanosfisherman"

rename_dir "$core_path" "$1"
rename_dir "$teavm_path" "$1"
rename_dir "$desktop_path" "$1"
rename_dir "$android_path" "$1"
