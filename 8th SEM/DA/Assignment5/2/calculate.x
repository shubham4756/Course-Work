struct inputs{
    float num1;
    float num2;
    char op;
};

program CALCULATE_PROG{
    version CALCULATE_VERS{
        float add(inputs)=1;
        float sub(inputs)=2;
        float mul(inputs)=3;
        float div(inputs)=4;
    }=1;
}=0x12345678;