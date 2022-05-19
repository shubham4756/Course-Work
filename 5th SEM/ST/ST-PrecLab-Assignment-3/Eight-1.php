<?php
$a=array(array(1,2,3),array(4,5,6),array(7,8,9));
for($x=0;$x<count($a);$x++){
    for($y=0;$y<count($a[$x]);$y++){
        $b[$y][$x]=$a[$x][$y];
    }
}
for($x=0;$x<count($b);$x++){
    for($y=0;$y<count($b[$x]);$y++){
        echo $b[$x][$y];
        echo " ";
    }
    echo "<br>";
}
?>