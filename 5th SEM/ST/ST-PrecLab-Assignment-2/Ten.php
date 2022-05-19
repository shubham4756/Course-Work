<?php
$num=6;
for($i=1;$i<=$num;$i++){
    for($j=1;$j<=$num-$i;$j++)
    echo "&nbsp;&nbsp;";
    for($j=1;$j<=$i;$j++)
    echo "$j";
    for($j=$i-1;$j>=1;$j--)
    echo "$j";
    echo "<br>";
}