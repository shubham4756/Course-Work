echo "Enter First Number"
read a;
echo "Enter Second Number"
read b;

echo "Enter Choice :"
echo "1. Addition"
echo "2. Subtraction"
echo "3. Multiplication"
echo "4. Division"
read ch
res=""
case $ch in
    1) echo "$a + $b = $(($a+$b))"
    ;;
    2) echo "$a - $b = $(($a-$b))"
    ;;
    3) echo "$a * $b = $(($a*$b))";
    ;;  
    4) echo "$a / $b = $(($a/$b))"
    ;;
esac