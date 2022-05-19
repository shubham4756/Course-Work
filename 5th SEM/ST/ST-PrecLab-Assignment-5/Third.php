<?php
    $name="cookieCount";
    $cnt=0;
    if(isset($_COOKIE[$name])){
        $cnt=$_COOKIE[$name];
    }
    setcookie($name,$cnt+1);
    //to use same cookie in same request
    $_COOKIE[$name]=$cnt+1;
    echo "You refreshed page ".$_COOKIE[$name];
?>