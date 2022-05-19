struct input{
    int n1;
    int n2;
    char s1[50];
    char s2[50];
};

struct output{
    char s[100];
};

program CMP_PROG{
    version CMP_VERS{
        output cmp(input)=1;
    }=1;
}=0x12345678;