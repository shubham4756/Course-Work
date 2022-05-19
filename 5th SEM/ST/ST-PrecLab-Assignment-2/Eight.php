<?php
$num=1234321;
$rev=0;
$temp=$num;
while($temp>1){
    $rev=$rev*10+$temp%10;
    $temp=$temp/10;
}
echo "reverse of $num is $rev <br>\n";
if($rev==$num){
    echo "Yes, Number is Palindrome !!";
} else{
    echo "No, Number is not Palindrom !!";
}