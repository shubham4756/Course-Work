<?php
function fact($x){
    if($x<=1)
     return 1;
    else
    return $x*fact($x-1);
}
$num=5;
$ans=fact($num);
echo "factorial of $num is $ans";
?>
