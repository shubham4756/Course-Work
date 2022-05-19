<?php

// we not required to give any index at declaration
$a=array(5,6,4,3,4,5,'a','b');
$n=count($a);

for($x=$n-1;$x>=0;$x--){
    echo "$a[$x] ";
}
echo "<br>\n\n";
// we can provide value at any index in array 
$b[3]=4554;
$b[2]='a';
$b[0]=456;
$b[1]=451;

$n=count($b);
for($x=0;$x<$n;$x++){
    echo "$b[$x] ";
}