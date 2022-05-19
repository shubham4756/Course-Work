<!-- Log In Page -->
<?php
    $db_host='127.0.0.1:3306';
    $db_username='root';
    $db_password='';
    $db_name='auth';

    $con=mysqli_connect($db_host,$db_username,$db_password,$db_name);
    if(!$con){
        die("Connection Failed");
    }
    if(isset($_POST['login'])){
        $email=$_POST['email'];
        $pwd=$_POST['password'];
        $sql="SELECT * FROM `users` WHERE `Email` = '$email' AND `Password` = '$pwd'";
        $result=mysqli_query($con,$sql);
        $row=mysqli_fetch_assoc($result);
        if(isset($row)){
            session_start();
            $_SESSION["email"]=$email;
            $_SESSION["password"]=$pwd;
            header('Location: Five-2.php');
        } else {
            echo "Invalid Email and password !! <br><br>";
        }
    }
?>
<!DOCTYPE html>
<html>
    <head>
        <title>Log In</title>
    </head>
    <body>
        <form method="POST" action="Five-3.php">
            <label>Email_ID : </label> <input type="email" name="email" placeholder="abc@xyz.com" required><br><br>
            <label>Password : </lable> <input type="password" name="password" required> <br><br>
            <input type="submit" name="login" value="Log In">
        </form>
        <br><br>
        <form method="POST" action="Five-1.php">
        <input type="submit" value="Sign Up" name="up" >
        </form>
    </body>
</html>