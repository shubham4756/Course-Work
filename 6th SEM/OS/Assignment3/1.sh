read -p $'Enter a number\n' num
for (( i = 2; i <= "$num"; i++))
do
    if (("$num" % "$i" == 0)) && (("$num" > 0))
    then
        echo "$i"
        while (("$num" % "$i" == 0)) && (("$num" > 0))
        do
            num="$(($num/$i))"  
        done
    fi
done