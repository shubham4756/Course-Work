file=$1
if [[ -d $file ]]
then
 echo "This is a directory"
elif [[ -c $file ]]
then
 echo "This is a character special file"
elif [[ -b $file ]]
then
 echo "This is a block special file"
elif [[ -p $file ]]
then
 echo "This is a named pipe file"
elif [[ -L $file ]]
then
 echo "This is a symlink file"
elif [[ -S $file ]]
then
 echo "This is a socket file"
elif [[ -f $file ]]
then
 echo "This is a regular file"
else
 echo "Not valid argument $file"
fi