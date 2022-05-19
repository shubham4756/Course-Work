<?php
    $db_host='127.0.0.1:3306';
    $db_username='root';
    $db_password='';
    $db_name='social_media';

    $con=mysqli_connect($db_host,$db_username,$db_password,$db_name);
    if(!$con){
        die("Connection Failed");
    } else {
        echo " Conneted Successfully !! <br><br>";
    }
    if(isset($_POST['insert'])){
        $id=$_POST['id'];
        $name=$_POST['name'];
        $email=$_POST['email'];
        $mobile=$_POST['mobile'];
        $city=$_POST['city'];
        $sql="INSERT INTO `users` (`ID`,`Name`,`Email`,`MobileNo`,`City`) VALUES('$id','$name','$email','$mobile','$city')";
        if(mysqli_query($con,$sql)){
            echo "$name Data Added successfully !!<br><br><br>";
        } else {
            echo "Error You can not add data in users table. <br><br><br>";
        }
        mysqli_close($con);
        header('Location: First.php');
    }
?>


<!DOCTYPE html>
<html>
    <head>
        <title>Insert Data</title>
    </head>
    <body>
        <form method="POST" action="Third.php">
            <label>Id:</label> <input type="number" name="id" required><br><br> 
            <label>Name : </label> <input type="text" name="name" required><br><br>
            <label>Email_ID : </label> <input type="email" name="email" placeholder="abc@xyz.com" required><br><br>
            <label>Mobile_No : </label> <input type="tel" name="mobile" placeholder="Ten Digits" pattern="[0-9]{10}" required><br><br>
            <label>City : </label> <input type="text" name="city" required><br><br>
            <input type="submit" name="insert" value="Insert">
        </form>
        <br><br>
        <a href="First.php">To See All Data of User Table Click Here !! </a>
    </body>
</html>