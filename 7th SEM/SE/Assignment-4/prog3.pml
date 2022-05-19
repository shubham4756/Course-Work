int res = 1;

proctype fac(int n) {
    if
    :: (n == 1) -> printf("ans is %d\n", res)
    :: (n > 1 ) -> res = res * n; run fac(n-1)
    fi
}

init {
	printf("factorial of 5 is\n");
    run fac(5);
}
