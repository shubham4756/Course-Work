ch=1
while [ $ch -gt 0 ]
do
    echo "Select Any one option"
    echo "1-> Number of Users Logged Into System"
    echo "2-> Print Calendar for current year"
    echo "3->print the date"
    read d
    case $d in 
        1) who | wc -l
        ;;
        2) cal 2021
        ;;
        3) date
        ;;
        *)break
    esac 
    echo "Do u wish to Continue(1/0)"
    read ch
done 