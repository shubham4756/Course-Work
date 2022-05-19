<?php

function digit($x){
    $cnt=0;
    $div=10;
    while($x>=1) {
        $x=$x/$div;
        $cnt++;
    }
    return $cnt;
}

function power($x,$n){
    $res=1;
    while($n!=0){
        $res*=$x;
        $n--;
    }
    return $res;
}
$num=500;
echo "Amstrong numbers Till $num : ";
for($i=1;$i<=$num;$i++){
    $n=digit($i);
    $x=$i;
    $ans=0;
    while($x!=0){
        $tp=$x%10;
        $ans+=power($tp,$n);
        $x=$x/10;
    }
    if($ans==$i){
        echo "$i ";
    }
}
