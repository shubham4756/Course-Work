<!DOCTYPE html>
<html>
    <head>
        <title>FilterVarFunction</title>
    </head>
    <body>
        <form method="post" action="Third.php">
            <label for="email">Email:</label>
            <input name="email" type="text">
            <button type="submit" value='Submit'> Submit</button>
        </form>
        <?php
            if($_SERVER["REQUEST_METHOD"] == "POST") {
                $str = $_POST["email"];
                $ans="";
                if(!empty($_POST["email"]) and filter_var($str, FILTER_VALIDATE_EMAIL)) {
                    $ans =  "Email address is valid";
                } else {
                    $ans = "Email address is not valid";
                }
                echo $ans;
                echo "</br>";
            }
        ?>
    </body>
</html>