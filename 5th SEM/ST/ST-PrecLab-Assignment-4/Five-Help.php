<?php
    $name = "";
    if(!empty($_POST["name"])) {
        $name = $_POST["name"];
        $name = trim($name);
        if(preg_match("/^[A-Za-z ]+$/",$name)){
            $name = "Welcome " . $name ." !! <br>";
        } else {
            $name = "You entered Invalid Name <br>";
        }
    } else {
        $name = "Enter the Name <br>";
    }

    $email = "";
    if(!empty($_POST["email"])) {
        $email = $_POST["email"];
        $email = trim($email);
        if(filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $email = "Your Email address :  " . $email . "<br>";
        } else {
            $email = "You entered Invalid Email address <br>";
        }
    } else {
        $email = "Enter the email <br>";
    }

    $date = "";
    if(!empty($_POST["dob"])) {
        $date = $_POST["dob"];
        $date = date("Y-m-d",strtotime($date));
        list($y, $m, $d) = explode("-", $date);
        if(checkdate($m,$d,$y)) {
            $date = "Date of the birth " . $date . "<br>";
        } else {
            $date = "you entered invalid date of the Birth <br>";
        }
    } else {
        $date = "Enter the date of the Birth <br>";
    }

    $mobilenumber = "";
    if(!empty($_POST["mobile"])) {
        $mobilenumber = $_POST["mobile"];
        if(is_numeric($mobilenumber) and strlen($mobilenumber) == 10) {
            $mobilenumber = "Your mobile number is " . $mobilenumber . "<br>";
        } else {
            $mobilenumber = "You entered invalid mobile number <br>";
        }
    } else {
        $mobilenumber = "Enter the mobile number <br>";
    }

    $gender ="";
    if(!empty($_POST["gender"])) {
        $gender = "You entered Gender as " . trim($_POST["gender"]) . "<br>";
    } else {
        $gender = "Select the Gender <br>";
    }

    $job ="";
    if(!empty($_POST["post"])) {
        $job = "You are applying for the post of " . trim($_POST["post"]) . "<br>";
    } else {
        $job = "Select the Post <br>";
    }

    if(isset($_POST['submit'])) {
        $idCard = "";
        $target_dir = "uploads/";
        $target_file = $target_dir . basename($_FILES["idCard"]["name"]);
        $fileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
        if(isset($target_file)){
            $idCard = "Upload Id card !! <br>";
        } else if (file_exists($target_file)) {
            $idCard = "Sorry, file already exists. <br>";
        } elseif ($_FILES["idCard"]["size"] > 1000000) {
            $idCard  = "Enter less than 1000KB File<br>";
        } elseif ($fileType != "pdf") {
            $idCard  =  "Sorry, only PDF files are allowed. <br>";
        } elseif (@move_uploaded_file($_FILES["idCard"]["tmp_name"], $target_file)) {
            $idCard = "File uploaded. <br>";
        } else {
            $idCard = "There is some error while submitting file <br>";
        }
    }
    echo $name . $email . $mobilenumber . $date . $gender . $job . $idCard;
?>