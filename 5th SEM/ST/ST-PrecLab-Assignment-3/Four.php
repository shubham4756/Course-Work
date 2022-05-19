<?php
$num=5;
for($i=1;$i<=$num;$i++){
    $ans=$i;
    for($j=1;$j<=10;$j++){
        echo "$ans ";
        $ans=$ans+$i;
    }
    echo "<br>\n";
}
?>