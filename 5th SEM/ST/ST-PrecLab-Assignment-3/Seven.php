<?php
function cal($a,$b,$c){
    switch($c){
        case '+':
            $ans=$a+$b;
        break;
        case '-':
            $ans=$a-$b;
        break;
        case '*':
            $ans=$a*$b;
        break;
        case '/':
            $ans=$a/$b;
        break;
        case '%':
            $ans=$a%$b;
        break;
    }
    echo "$a $c $b = $ans <br>";
}
cal(5,7,'+');
cal(7,5,'-');
cal(3,5,'*');
cal(4,1,'/');
cal(5,3,'%');
?>
