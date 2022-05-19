<!-- Sign Up Page -->
<?php
    $db_host='127.0.0.1:3306';
    $db_username='root';
    $db_password='';
    $db_name='auth';

    $con=mysqli_connect($db_host,$db_username,$db_password,$db_name);
    if(!$con){
        die("Connection Failed");
    }
    if(isset($_POST['SignUp'])){
        $name=$_POST['name'];
        $email=$_POST['email'];
        $mobile=$_POST['mobile'];
        $pwd=$_POST['password'];
        $sql="INSERT INTO `users` (`Name`,`Email`,`MobileNo`,`Password`) VALUES('$name','$email','$mobile','$pwd')";
        $result=mysqli_query($con,$sql);
        if($result){
            session_start();
            $_SESSION["email"]=$email;
            $_SESSION["password"]=$pwd;
            echo "$name Data Added successfully !!<br><br><br>";
            header('Location: Five-2.php');
        } else {
            echo "Error You can not Sign up. <br><br><br>";
        }
    }
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Sign Up</title>
    </head>
    <body>
        <form method="POST" action="Five-1.php">
            <label>Name : </label> <input type="text" name="name" required><br><br>
            <label>Email_ID : </label> <input type="email" name="email" placeholder="abc@xyz.com" required><br><br>
            <label>Mobile_No : </label> <input type="tel" name="mobile" placeholder="Ten Digits" pattern="[0-9]{10}" required><br><br>
            <label>Password : </lable> <input type="password" name="password" required> <br><br>
            <input type="submit" name="SignUp" value="Sign Up">
        </form>
        <br><br>
        <form method="POST" action="Five-3.php">
        <input type="submit" value="Log In" name="in" >
        </form>
    </body>
</html>
