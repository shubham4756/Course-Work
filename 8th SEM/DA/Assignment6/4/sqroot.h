/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _SQROOT_H_RPCGEN
#define _SQROOT_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct input {
	float a;
};
typedef struct input input;

#define SQROOT_PROG 0x12345678
#define SQROOT_VERS 1

#if defined(__STDC__) || defined(__cplusplus)
#define sqroot 1
extern  float * sqroot_1(input *, CLIENT *);
extern  float * sqroot_1_svc(input *, struct svc_req *);
extern int sqroot_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define sqroot 1
extern  float * sqroot_1();
extern  float * sqroot_1_svc();
extern int sqroot_prog_1_freeresult ();
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

#endif /* !_SQROOT_H_RPCGEN */