<!DOCTYPE html>
<html>
    <?php
        session_start();
        session_destroy();
        echo 'Thank you';
        echo "</br>";
        echo "Logged out";
        echo "</br>";        
        echo "<a href = 'six.php'>Go back to the home page</a>";
    ?>
</html>