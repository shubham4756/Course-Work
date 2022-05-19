if [ $1 -gt $2 ] && [ $1 -gt $3 ]
then
    echo "Greatest Number is -> $1"
elif [ $2 -gt $1 ] && [ $2 -gt $3 ]
then
    echo "Greatest Number is -> $2"
else
    echo "Greatest Number is -> $3"
fi