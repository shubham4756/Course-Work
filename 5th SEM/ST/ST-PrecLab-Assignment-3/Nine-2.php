<?php
function inc(&$a){
    $a++;
}
function dec(&$a){
    $a--;
}
$a=5;
inc($a);
echo "increment $a <br>";
dec($a);
echo "decrement $a <br>";
inc($a);
inc($a);
echo "2 time increment $a <br>";
?>