/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "stats.h"


void
stats_prog_1(char *host, int n, int *arr)
{
	CLIENT *clnt;
	output  *result_1;
	input  stats_1_arg;

#ifndef	DEBUG
	clnt = clnt_create (host, STATS_PROG, STATS_VERS, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */

	stats_1_arg.n = n;
	for(int i = 0; i < n; i++) {
		stats_1_arg.arr[i] = arr[i];
	}
	result_1 = stats_1(&stats_1_arg, clnt);
	if (result_1 == (output *) NULL) {
		clnt_perror (clnt, "call failed");
	} else {
		printf("MAX = %d\n", result_1->mx);
		printf("MIN = %d\n", result_1->mn);
		printf("AVG = %f\n", result_1->avg);
	}
#ifndef	DEBUG
	clnt_destroy (clnt);
#endif	 /* DEBUG */
}


int
main (int argc, char *argv[])
{
	char *host;

	if (argc < 2) {
		printf ("usage: %s server_host\n", argv[0]);
		exit (1);
	}
	host = argv[1];

	int n;
	printf("Enter the number of elements: \n");
	scanf("%d", &n);

	int arr[100];

	printf("Enter %d elements: \n", n);

	for(int i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}

	stats_prog_1 (host, n, arr);
exit (0);
}
