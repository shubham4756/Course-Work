struct input{
    int n1;
    int n2;
    char s1[50];
    char s2[50];
};

program CMP_PROG{
    version CMP_VERS{
        int cmp(input)=1;
    }=1;
}=0x12345678;