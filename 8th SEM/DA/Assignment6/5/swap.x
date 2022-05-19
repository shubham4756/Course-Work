struct input {
	int a;
    int b;
};

struct output {
    int a;
    int b;
};

program SWAP_PROG {
	version SWAP_VERS {
        output SWAP(input)=1;
	}=1;
}=0x12345678;

