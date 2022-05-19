proctype gcd(int a;int b) {
    if
    :: (b == 0) -> printf("gcd is %d\n", a)
    :: (b != 0) -> run gcd(b, a % b)
    fi
}

init {
    printf("gcd for 14 and 21\n");
    run gcd(14, 21);
}
