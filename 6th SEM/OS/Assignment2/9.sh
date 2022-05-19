numbers=(10 -29 33 67 -6 7 -10)

for (( i=0 ; i < ${#numbers[@]}; i++ )) 
do
    for (( j=0 ; j < ${#numbers[@]}; j++ )) 
    do
      if [[ ${numbers[$j]} -gt  ${numbers[$i]} ]]
      then
        tmp=${numbers[$i]}
        numbers[$i]=${numbers[$j]}
        numbers[$j]=${tmp}
      fi
    done
done

n=${#numbers[@]}
n=$(($n-1))
while [ $n -gt -1 ]
do
    echo ${numbers[$n]}
    n=$(($n-1))
done