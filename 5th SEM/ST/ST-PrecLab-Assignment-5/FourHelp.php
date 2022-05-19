<?php
    $name=$email=$pos="";
    function change($CName,$value){
        setcookie($CName,$value);
        $_COOKIE[$CName]=$value;
    }
    if(isset($_POST["name"])){
        $name=$_POST["name"];
        change("name",$name);
    } else{
        change("name","not mentioned");
    }
    if(isset($_POST["email"])){
        $email=$_POST["email"];
        change("email",$email);
    } else {
        change("email","not mentioned");
    }
    if(isset($_POST["post"])){
        $post=$_POST["post"];
        change("post",$post);
    } else {
        change("post","not mentioned");
    }
    echo "Cookies has been set </br>";
    echo "name: ".$_COOKIE["name"]."</br>";
    echo "email: ".$_COOKIE["email"]."</br>";
    echo "post: ".$_COOKIE["post"];
?>