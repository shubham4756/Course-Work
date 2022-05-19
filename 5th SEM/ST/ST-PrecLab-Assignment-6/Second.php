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
    if(isset($_POST['delete'])){
        $delid = $_POST['id'];
        $sql="DELETE FROM users where ID=$delid";
        if(mysqli_query($con,$sql))
            echo "<br>User Data Deleted successfully !!<br><br><br>";
        else 
            echo "<br> Error You can not delete ID no. $delid Users. <br>";
        
    }
    $sql="Select * from users";
    $result=mysqli_query($con,$sql);
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Delete Data</title>
        <style>
            table,th,td,a{
                text-align : center;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <table style="width: 50%; border-collapse: collapse;">
            <tr>
                <th>User ID</th>
                <th>User Name</th>
                <th>User Email_ID</th>
                <th>User Mobile_No</th>
                <th>User City</th>
                <th>Delete Button</th>
            </tr>
            <?php while($row = mysqli_fetch_assoc($result)) {?>
                <tr>
                <td><?php echo $row["ID"]; ?></td>
                <td><?php echo $row["Name"]; ?></td>
                <td><?php echo $row["Email"]; ?></td>
                <td><?php echo $row["MobileNo"]; ?></td>
                <td><?php echo $row["City"]; ?></td>
                <td>
                    <form method="POST" action="Second.php">
                        <input type="hidden" name="id" value= <?php echo $row["ID"] ?> >
                        <input type="submit" value="Delete" name="delete" >
                    </form>
                </td>
                </tr>
            <?php } ?>
        </table>
        <br><br>
        <a href="First.php">To See All Data of User Table Click Here !! </a>
    </body>
</html>