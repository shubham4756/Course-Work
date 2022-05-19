/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _LEAP_H_RPCGEN
#define _LEAP_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct input {
	int year;
};
typedef struct input input;

#define LEAP_PROG 0x12345678
#define LEAP_VERS 1

#if defined(__STDC__) || defined(__cplusplus)
#define leap 1
extern  int * leap_1(input *, CLIENT *);
extern  int * leap_1_svc(input *, struct svc_req *);
extern int leap_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define leap 1
extern  int * leap_1();
extern  int * leap_1_svc();
extern int leap_prog_1_freeresult ();
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

#endif /* !_LEAP_H_RPCGEN */