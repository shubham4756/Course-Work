/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _STATS_H_RPCGEN
#define _STATS_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct input {
	int n;
	int arr[100];
};
typedef struct input input;

struct output {
	int mx;
	int mn;
	float avg;
};
typedef struct output output;

#define STATS_PROG 0x12345678
#define STATS_VERS 1

#if defined(__STDC__) || defined(__cplusplus)
#define stats 1
extern  output * stats_1(input *, CLIENT *);
extern  output * stats_1_svc(input *, struct svc_req *);
extern int stats_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define stats 1
extern  output * stats_1();
extern  output * stats_1_svc();
extern int stats_prog_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_input (XDR *, input*);
extern  bool_t xdr_output (XDR *, output*);

#else /* K&R C */
extern bool_t xdr_input ();
extern bool_t xdr_output ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_STATS_H_RPCGEN */
