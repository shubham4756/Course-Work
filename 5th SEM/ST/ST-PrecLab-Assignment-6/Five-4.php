<!-- Update Page -->
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
    $name=$_SESSION['name'];
    $email=$_SESSION['email'];
    $mobile=$_SESSION['mobileno'];
    $pwd=$_SESSION['password'];
    if(isset($_POST['update'])){
        $newN=$_POST['name'];
        $newE=$_POST['email'];
        $newM=$_POST['mobile'];
        $newP=$_POST['password'];
        $sql="DELETE FROM users where Email='$email'";
        echo "here deleted <br><br>";
        $result=mysqli_query($con,$sql);
        session_destroy();
        $sql="INSERT INTO `users` (`Name`,`Email`,`MobileNo`,`Password`) VALUES('$newN','$newE','$newM','$newP')";
        if(mysqli_query($con,$sql)){
            session_start();
            $_SESSION["email"]=$newE;
            $_SESSION["password"]=$newP;
            echo "$name Data Added successfully !!<br><br><br>";
            header('Location: Five-2.php');
        } else {
            echo "Error You can not update data !!!. <br><br><br>";
        }
    }
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Update</title>
    </head>
    <body> 
        <p>change only those details which you want to update.</p>
        <form method="POST" action="Five-4.php">
            <label>Name : </label> <input type="text" name="name" value="<?php echo $name; ?>" required><br><br>
            <label>Email_ID : </label> <input type="email" name="email" value="<?php echo $email; ?>" required><br><br>
            <label>Mobile_No : </label> <input type="tel" name="mobile" value="<?php echo $mobile; ?>" pattern="[0-9]{10}" required><br><br>
            <label>Password : </lable> <input type="password" name="password" value="<?php echo $pwd; ?>" required> <br><br>
            <input type="submit" name="update" value="Update">
        </form>
    </body>
</html>
