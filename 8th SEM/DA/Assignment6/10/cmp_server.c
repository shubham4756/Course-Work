/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "cmp.h"

output *
cmp_1_svc(input *argp, struct svc_req *rqstp)
{
	static output  result;

	/*
	 * insert server code here
	 */

	int n = argp->n;
	printf("Received array of size %d \n", n);

	for(int i = 0; i < n; i++) {
		result.a[i] = argp->a[n - 1 - i];
	}

	return &result;
}
