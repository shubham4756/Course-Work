<?php
$a=array(array(2,3,4),array(3,4,5),array(4,5,6));
$b=array(array(5,6,7),array(6,7,8),array(7,8,9));
for($x=0;$x<count($a);$x++){
    for($y=0;$y<count($a[$x]);$y++){
        $temp=0;
        for($k=0;$k<count($a[$x]);$k++){
            $temp+=$a[$x][$k]*$b[$k][$y];
        }
        $c[$x][$y]=$temp;
    }
}
for($x=0;$x<count($c);$x++){
    for($y=0;$y<count($c[$x]);$y++){
        echo $c[$x][$y]; echo " ";
    }
    echo "<br>";
}
?>
