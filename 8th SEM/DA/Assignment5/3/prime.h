/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _PRIME_H_RPCGEN
#define _PRIME_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct input {
	int num;
};
typedef struct input input;

#define PRIME_PROG 0x12345678
#define PRIME_VERS 1

#if defined(__STDC__) || defined(__cplusplus)
#define isprime 1
extern  bool_t * isprime_1(input *, CLIENT *);
extern  bool_t * isprime_1_svc(input *, struct svc_req *);
extern int prime_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define isprime 1
extern  bool_t * isprime_1();
extern  bool_t * isprime_1_svc();
extern int prime_prog_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_input (XDR *, input*);

#else /* K&R C */
extern bool_t xdr_input ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_PRIME_H_RPCGEN */
