struct input{
    int n;
    int a[100];
};

struct output{
    int a[100];
};

program CMP_PROG{
    version CMP_VERS{
        output cmp(input)=1;
    }=1;
}=0x12345678;