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
    $sql="Select * from users";
    $result=mysqli_query($con,$sql);
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Display Data</title>
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
            </tr>
            <?php while($row = mysqli_fetch_assoc($result)){?>
                <tr>
                <td><?php echo $row["ID"]; ?></td>
                <td><?php echo $row["Name"]; ?></td>
                <td><?php echo $row["Email"]; ?></td>
                <td><?php echo $row["MobileNo"]; ?></td>
                <td><?php echo $row["City"]; ?></td>
                </tr>
            <?php } ?>
        </table>
        <br><br><a href="Second.php">Delete Data</a>
        <br><br><a href="Third.php">Insert Data</a>
        <br><br><a href="Fourth.php">Update Data</a>
    </body>
</html>