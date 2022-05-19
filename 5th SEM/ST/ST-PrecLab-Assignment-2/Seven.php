<?php
$num=20;
$pre=1;
$now=1;
echo "$pre $now ";
for($i=3;$i<=$num;$i++){
    $temp=$pre+$now;
    echo "$temp ";
    $temp=$pre;
    $pre=$now;
    $now=$temp+$now;
}
