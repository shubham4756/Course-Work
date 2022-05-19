for i in `find $1 -mindepth 1 -maxdepth 1 -type d `;
do
 cnt=0
 echo $i
 for j in `find $i -mindepth 1 -maxdepth 1`
 do
 if [ -x $j ]; then
 ((cnt++))
 fi
 done
 echo $cnt
done