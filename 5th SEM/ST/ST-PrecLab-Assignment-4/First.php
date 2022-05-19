<!DOCTYPE html>
<html>
    <head>
        <title>Validate Email</title>
    </head>
    <body>
        <form method="post" action="First.php">
            <label for="email">Email:</label>
            <input name="email" type="text">
            <button type="submit" value='Submit'> Submit</button>
        </form>
        <?php
            if($_SERVER["REQUEST_METHOD"] == "POST") {
                $ans = "";
                $str = $_POST["email"];
                if(!empty($_POST["email"]) and preg_match("^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$^", $str) ) {
                    $ans =  "Your Email address is ".$str;
                } else {
                    $ans = "Email address is not valid";
                }
                echo $ans;
                echo "</br>";
            }
        ?>
    </body>
</html>