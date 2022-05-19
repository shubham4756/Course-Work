echo "Enter Maximum limit : "
read n
f1=0
f2=1
echo "Fibonacci sequence : "
for((i=0;i<n;i++))
do
    echo $f1
    temp=$f2 
    f2=$(($f1+$f2))
    f1=$temp 
done