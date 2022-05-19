<?php
    $lang=$_COOKIE["language"]." and python";
    setcookie("Language","PHP");
    //to change the cookies in same request bcz cookies update when refresh
    $_COOKIE["Language"]="PHP";
    setcookie("language",$lang);
    $_COOKIE["language"]=$lang;
    if(isset($_COOKIE['Language']) && isset($_COOKIE['language'])){
        echo "Updated Cookies !! </br>";
        echo "Language : ".$_COOKIE['Language']."</br>";
        echo "language : ".$_COOKIE['language']."</br>";
    } else {
        echo "Cookies not Found !!";
    }
?>