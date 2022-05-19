<?php
function sum($x,$y,$z=5,$w=7){
    return $x+$y+$z+$w;
}
$ans=sum(4,5);
echo "sum(4,5) = $ans <br>";
$ans=sum(4,5,6);
echo "sum(4,5,6) = $ans <br>";
$ans=sum(4,5,3,2);
echo "sum(4,5,3,2) = $ans <br>";
?>