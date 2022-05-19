echo "Enter Starting Number: "
read l
echo "Enter Ending Number: "
read r

for((i=l;i<=r;i++))
do
    tp=$(($i%2))
    if [ $tp -ne 1 ]
    then
        echo $i
    fi
done