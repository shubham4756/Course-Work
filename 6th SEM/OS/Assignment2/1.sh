echo "Please enter a charcter"
read c
case $c in 
    [0-9])
        echo "Digit"
        ;;
    [a-z])
        echo "Small letter"
        ;;
    [A-Z])
        echo "Capital letter"
        ;;
    *)
        echo "Special Charcter"
        ;;
esac

case $c in 
    [aeiouAEIOU]) echo "A vowel";;
esac