struct input{
    int num;
};

program FIB_PROG{
    version FIB_VERS{
        string fib(input)=1;
    }=1;
}=0x12345678;