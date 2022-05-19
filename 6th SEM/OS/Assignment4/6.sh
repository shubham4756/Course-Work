for entity in `find "$1" -mtime -1`
do
 if [[ -f "$entity" ]]
 then
 echo "$entity"
 fi
done