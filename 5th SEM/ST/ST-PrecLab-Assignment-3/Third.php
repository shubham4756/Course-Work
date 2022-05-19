<?php
function sumOfDigit($x){
    $res=0;
    while($x>1){
        $res=$res+$x%10;
        $x=$x/10;
    }
    return $res;
}
$num = 12345;
$ans=sumOfDigit($num);
echo "sum of digits of $num is $ans";
?>