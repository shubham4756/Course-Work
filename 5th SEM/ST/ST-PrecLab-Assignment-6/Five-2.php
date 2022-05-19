<!-- Profile Page -->
<?php
    $db_host='127.0.0.1:3306';
    $db_username='root';
    $db_password='';
    $db_name='auth';

    $con=mysqli_connect($db_host,$db_username,$db_password,$db_name);
    if(!$con){
        die("Connection Failed");
    }
    session_start();
    $email=$_SESSION["email"];
    $pwd=$_SESSION["password"];
    $sql="SELECT * FROM `users` WHERE `Email` = '$email' AND `Password` = '$pwd'";
    $result=mysqli_query($con,$sql);
    $row=mysqli_fetch_all($result,MYSQLI_ASSOC);
    if(isset($_POST['logout'])){
        session_destroy();
        header('Location: Five-3.php');
    }
    if(isset($_POST['update'])){
        session_destroy();
        session_start();
        $_SESSION['email']=$email;
        $_SESSION['name']=$row[0]['Name'];
        $_SESSION['mobileno']=$row[0]['MobileNo'];
        $_SESSION['password']=$pwd;
        header('Location: Five-4.php');
    }
    if(isset($_POST['delete'])){
        $sql="DELETE FROM users where Email='$email'";
        if(mysqli_query($con,$sql)){
            session_destroy();
            header('Location: Five-1.php');
        } else {
            echo "Error : can not Delete !!! <br><br>";
        }
    }
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Profile</title>
    </head>
    <body>
            <p>Name : <?php echo $row[0]['Name']; ?> </p><br>
            <p>Email : <?php echo $row[0]['Email']; ?> </p><br>
            <p>Mobile No : <?php echo $row[0]['MobileNo']; ?> </p><br>
    </body>
    <br><br>
    <form method="POST" action="Five-2.php">
        <input type="submit" value="Log Out" name="logout" >
    </form>
    <br>
    <form method="POST" action="Five-2.php">
        <input type="submit" value="Update Details" name="update" >
    </form>
    <br>
    <form method="POST" action="Five-2.php">
        <input type="submit" value="Delete Account" name="delete" >
    </form>
</html>