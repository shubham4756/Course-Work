<?php
    setcookie("Language","C++");
    setcookie("language","Java");
    if(isset($_COOKIE['Language']) && isset($_COOKIE['language'])){
        echo "Language : ".$_COOKIE['Language']."</br>";
        echo "language : ".$_COOKIE['language']."</br>";
        echo "<form action='FirstHelp.php' method='post'>Change the Cookies !! <input type='submit' value='submit'></form>";
    } else {
        echo "Cookies not Found !!";
    }
?>