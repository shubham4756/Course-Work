<?php
echo "Date and Time function usage :<br>";
$d=date("d/m/y");
echo "Today's date is :". $d."<br>";
echo "Time at present is :".date("h:i:s")."<br>";
echo "To get the current Date : getDate()= <br>";
print_r(getdate());
echo "<br>";
$date1 = date_create("01-01-2020");
$date2 = date_create();
$interval = date_diff($date1, $date2);
echo "Differnce between 01-01-2020 and Today\'s date " ;
print($interval->format('%Y years %m months %d days'));
echo '<br>';
?>