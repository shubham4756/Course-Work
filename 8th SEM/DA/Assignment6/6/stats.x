struct input{
    int n;
    int arr[100];
};

struct output{
    int mx;
    int mn;
    float avg;
};

program STATS_PROG{
    version STATS_VERS{
        output stats(input)=1;
    }=1;
}=0x12345678;