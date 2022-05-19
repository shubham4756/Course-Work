<?php
    setcookie("Fullname","abcde",time()+2*24*60*60);
    $_COOKIE['Fullname']="abcde";
    echo "<h4>Cookies Fullname is ".$_COOKIE["Fullname"]." which will last for the two days</h4>"
?>