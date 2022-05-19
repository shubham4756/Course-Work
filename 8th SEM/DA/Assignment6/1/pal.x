struct input {
	int n;
	char s[100];
};

program PAL_PROG {
	version PAL_VERS {
		int pal(input)=1;
	}=1;
}=0x12345678;

