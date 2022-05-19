<!DOCTYPE html>
<html>
    <head>
        <title>Send Mail Using PHP</title>
    </head>
        <form action="First-Help.php" method="post">
            <label>Sender:</label><br>
            <input type="email" name="send" required><br><br>
            <label>Sender Password:</label><br>
            <input type="password" name="password" required><br><br>
            <label>Receiver:</label><br>
            <input type="email" name="receive" required><br><br>
            <label>CC:</label><br>
            <input type="email" name="cc" required><br><br>
            <label>BCC:</label><br>
            <input type="email" name="bcc" required><br><br>
            <label>Subject:</label><br>
            <input type="text" name="sub" required><br><br>
            <label>Content:</label><br>
            <textarea name="body" required></textarea><br><br>
            <input type="submit" name="submit">
        </form>
    </body>
</html>