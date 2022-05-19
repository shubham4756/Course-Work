echo "Enter a Number"
read n
s=0
while [ $n -gt 0 ]
do
s=$(($s + $n%10))
n=$(($n/10))
done
echo "Sum of digits " $s