<?php

$a = array(15,45,78,56,13,42,14,47,41);

sort($a);
$minn=$a[0];
$maxx=$a[count($a)-1];
echo "smallest number is $minn <br>\n";
echo "largest number is $maxx";
