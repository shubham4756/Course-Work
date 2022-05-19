<?php
$unit=56.3;
$rate=6.50;
if($unit>0 and $unit<=50){
    $rate=3.50;
} 
if($unit>50 and $unit<=150){
    $rate=4.00;
} 
if($unit>150 and $unit<=250){
    $rate=  5.20;
}
$ans=$unit*$rate;
echo "$unit used and rate of unit is $rate so totoal bill is $ans";
?>
