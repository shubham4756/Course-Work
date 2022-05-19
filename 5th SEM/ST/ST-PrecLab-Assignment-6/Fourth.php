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
    if(isset($_POST['update'])){
        $id=$_POST['id'];
        $query="SELECT * FROM users WHERE ID='$id'"; 
        $res=mysqli_query($con,$query);
        $users=mysqli_fetch_all($res,MYSQLI_ASSOC);
        if(isset($users)){
            $name=$users[0]["Name"]; $email=$users[0]["Email"]; $mobile=$users[0]["MobileNo"]; $city=$users[0]["City"];
            if(isset($_POST["name"]) && trim($_POST["name"])!="") 
                $name=$_POST["name"];
            if(isset($_POST["email"]) && trim($_POST["email"])!="") 
                $email=$_POST["email"];
            if(isset($_POST["mobile"]) && trim($_POST["mobile"])!="")
                $mobile=$_POST["mobile"];
            if(isset($_POST["city"]) && trim($_POST["city"])!="")
                $city=$_POST["city"];
            $sql="UPDATE `users` SET `Name` = '$name', `Email` = '$email', `MobileNo`='$mobile', `City` = '$city' WHERE `users`.`ID` = $id ";
            if(mysqli_query($con,$sql)){
                echo "$name Data Updated successfully !!<br><br><br>";
            } else {
                echo "Error You can not update !! <br><br><br>";
            }
        } else {
            echo "Error ID No. $id User does not exist !! <br><br><br>";
        }
        mysqli_close($con);
        header('Location: First.php');
    }
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Update Data</title>
    </head>
    <body>
        <h3>Enter New Values:(Leave the field empty if no update)</h3>
        <form method="post" action="Fourth.php">
            <label>Enter the Id whose data you want to update : </label> <input type="number" name="id" required><br><br>
            <label>Name : </label> <input type="text" name="name"><br><br>
            <label>Email_ID : </label> <input type="email" name="email" placeholder="abc@xyz.com"><br><br>
            <label>Mobile_No : </label> <input type="tel" name="mobile" placeholder="Ten Digits" pattern="[0-9]{10}"><br><br>
            <label>City : </label> <input type="text" name="city"><br><br>
            <input type="submit" name="update" value="Submit">
        </form>
        <br><br>
        <a href="First.php">To See All Data of User Table Click Here !! </a>
    </body>
</html>