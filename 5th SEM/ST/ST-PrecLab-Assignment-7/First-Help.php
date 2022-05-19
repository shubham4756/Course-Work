<?php
if(isset($_POST['submit'])) {
    require_once('../PHPMailer/PHPMailerAutoload.php');
    $from=$_POST['send'];
    $to=$_POST['receive'];
    $password=$_POST['password'];
    $cc=$_POST['cc'];
    $bcc=$_POST['bcc'];
    $sub=$_POST['sub'];
    $body=$_POST['body'];
    $mail=new PHPMailer();
    $mail->isSMTP();
    $mail->SMTPAuth = true;
    $mail->SMTPSecure = 'ssl';
    $mail->Host = 'smtp.gmail.com';
    $mail->Port = '465';
    $mail->isHTML();
    $mail->Username = $from;
    $mail->Password = $password;
    $mail->Subject = $sub;
    $mail->Body = $body;
    $mail->AddAddress($to);
    $mail->AddBCC($bcc);
    $mail->AddCC($cc);
    if($mail->Send()){
        echo "<h2>Email sent successfully!</h2>";
        echo "<script>alert('Subject: $sub \\nBody: $body');</script>";
    }
    else echo "<h2>Something went wrong !!, try again!</h2>";
}
?>