t=$(date +%T)
if [[ "$t" > "16:00:00" ]]; then
    echo "Good Evening"
else
    echo "Good Morning"
fi