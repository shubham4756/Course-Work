echo "Number of Files: "
k=0
for fi in *
do
if [ -f $fi ]
then
    k=`expr $k + 1`
fi
done
echo $k