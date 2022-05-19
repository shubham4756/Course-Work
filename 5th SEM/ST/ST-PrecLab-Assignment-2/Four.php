<?php

function power($num,$pow){
    $res=1;
    while($pow>0){
        $res=$res*$num;
        $pow--;
    }
    return $res;
}

$num=5;
$pow=2;
echo power($num,$pow);