<?php
echo "Use of string functions : <br>";
$a="Shubham";
$b="Shekhaliya";
echo "Length of b is :".strlen($b)."<br>";
echo "Reverse of a is :". strrev($a)."<br>";
echo "String a in uppercase :". strtoupper($a)."<br>";
echo "Comapring a and b : ".strcmp($a, $b)."<br>";
echo "chars of string b are From 2 to 6:".substr($b,2,6)."<br>";
echo "For string 'Hi there I am using whatsapp.' 
        Numebr of words are ".str_word_count("Hi there I am using whatsapp.")."<br>";
echo "For string 'sun rises from west' we change 
       west with east ".str_replace("west","east","sun rises from west") ."<br>";
?>