echo "Enter a Number"
read n
p=n
s=1
while [ $n -gt 0 ]
do
s=$(($s*$n))
n=$(($n-1))
done
echo "Factorial of $p is " $s