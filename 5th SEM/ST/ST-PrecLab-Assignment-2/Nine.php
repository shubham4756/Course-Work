<?php
$num=7654321;
$rev=0;
$temp=$num;
while($temp>1){
    $rev=$rev*10+$temp%10;
    $temp=$temp/10;
}
echo "reverse of $num is $rev\n";
