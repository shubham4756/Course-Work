/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _SWAP_H_RPCGEN
#define _SWAP_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct input {
	int a;
	int b;
};
typedef struct input input;

struct output {
	int a;
	int b;
};
typedef struct output output;

#define SWAP_PROG 0x12345678
#define SWAP_VERS 1

#if defined(__STDC__) || defined(__cplusplus)
#define SWAP 1
extern  output * swap_1(input *, CLIENT *);
extern  output * swap_1_svc(input *, struct svc_req *);
extern int swap_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define SWAP 1
extern  output * swap_1();
extern  output * swap_1_svc();
extern int swap_prog_1_freeresult ();
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

#endif /* !_SWAP_H_RPCGEN */
