for arg in "$@"
do
 if [[ -d $arg ]]
 then
 echo "$arg is a directory"
 echo "----------------------------------"
 for Subdir in `find $arg -mindepth 1 -maxdepth 1 -type d`
 do
 printf " | %25s | %d | \n" "$Subdir" `ls "$Subdir" | wc -l`
 done
 echo "----------------------------------"
 else
 echo "Not a directory"
 fi
done