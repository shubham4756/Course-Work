read -p "Enter a positive integer: " num
li=()
for (( i = 1; i <= "$num"; i++ )); do
    if [[ "$num"%"$i" -eq 0 ]]; then
        li+=($i)
    fi
done
echo -n "The divisors of $num are: "
echo "${li[@]}" | tr ' ' ,