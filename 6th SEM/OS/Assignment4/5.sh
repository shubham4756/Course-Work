for arg in "$@"
do
 sum=0
 for file in `find $arg -type f`
 do
 echo -n "$file - "
 stat --format="%b" "$file"
 sum=$((sum +`stat --format="%b" "$file"`))
 done
 echo $sum
done