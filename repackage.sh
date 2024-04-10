#!/bin/bash

rename_dir() {
  # Check if the directory exists
  if [ -d "$1" ]; then
      # If it does, rename it
      mv "$1/game" "$1/$2"
      echo "Directory renamed successfully to '$2'."
  else
      # If it doesn't, print an error message
      echo "Directory does not exist"
  fi
}

# Function to search for the word "game" in a file and replace it with "game2"
replace_game_with_custom_name() {
    file="$1"
    if grep -q "io.github.thanosfisherman.game" "$file"; then
        sed -i "s/io.github.thanosfisherman.game/io.github.thanosfisherman.$2/g" "$file"
        echo "$file"
    fi
}

# Main function for recursive file search
search_and_replace_imports() {
    local directory="$1"
    # shellcheck disable=SC2155
    local files=$(find "$directory" -type f)

    for file in $files; do
        replace_game_with_custom_name "$file" "$2"
    done
}

# Check if an argument is provided
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 new_directory_name"
    exit 1
fi

# Define the directory path
#core_path="core/src/main/kotlin/io/github/thanosfisherman"
#teavm_path="teavm/src/main/kotlin/io/github/thanosfisherman"
#desktop_path="desktop/src/main/kotlin/io/github/thanosfisherman"
#android_path="android/src/main/java/io/github/thanosfisherman"
#
#rename_dir "$core_path" "$1"
#rename_dir "$teavm_path" "$1"
#rename_dir "$desktop_path" "$1"
#rename_dir "$android_path" "$1"

dirs=("android" "core" "desktop" "teavm")

# Iterate over the elements of the array using a for loop
echo "Iterating over the array elements:"
for element in "${dirs[@]}"; do
    search_and_replace_imports "$element" "$1"
done



