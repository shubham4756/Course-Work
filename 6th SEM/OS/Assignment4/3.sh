grep -r "$1" "$2" | awk -F : '{print $1}'