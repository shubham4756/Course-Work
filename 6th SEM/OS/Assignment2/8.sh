echo "Enter Choice :"
echo "1. Addition"
echo "2. Subtraction"
echo "3. Multiplication"
echo "4. Division"
read ch
res=""
case $ch in
    1) echo "$1 + $2 = $(($1+$2))"
    ;;
    2) echo "$1 - $2 = $(($1-$2))"
    ;;
    3) echo "$1 * $2 = $(($1*$2))";
    ;;  
    4) echo "$1 / $2 = $(($1/$2))"
    ;;
esac