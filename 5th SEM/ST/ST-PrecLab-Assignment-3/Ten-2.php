<?php
echo "Use of Math functions :<br>";
echo "Two raise to 5 is equal to : ".pow(2, 5)."<br>";
echo "Absolute value of f is : ".abs(-10)."<br>";
echo "Squre root of 24 is : ".sqrt(24)."<br>";
echo "Floor value of 13.46 is : ".floor(13.46)."<br>";
echo "Ceil value of 13.46 is : ".ceil(13.46)."<br>";
$pp = 10000;
echo "log10(". $pp .") = ".log10($pp) ."<br>";
echo "PI = ". pi() ."<br>";
$pp = 3*pi()/4; 
echo "sin(3*Pi/4) = ".round(sin($pp),4) ."<br>";
?>