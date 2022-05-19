<!DOCTYPE html>
<html>
    <?php
        session_start();
        $_SESSION["name"] = $_POST["name"];
        if(isset($_SESSION["name"]) && !empty($_SESSION["name"])) {
            echo "Welcome, " . $_SESSION["name"];
            echo "</br>";
            echo "Logged in";
            echo "</br>";
            echo "<a href='sixHelp.php'>To logout click here!!!</a>";
            echo "</br>";
        } else {
            echo "Something went wrong";
            echo "</br>";
            echo "<a href = 'sixth.php'>Go back to the home page</a>";
            echo "</br>";
        }
    ?>
</html>