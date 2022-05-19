<?php

$num=10;
$cnt=2;

echo "First $num Prime numbers : ";

while($num>0){
    $flag=1;
    for($i=2;$i<$cnt;$i++){
        if($cnt%$i==0){
            $flag=0;
            break;
        }
    }
    if($flag==1){
        echo "$cnt ";
        $num--;
    }
    $cnt++;
}