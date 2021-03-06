/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _PAL_H_RPCGEN
#define _PAL_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct input {
	int n;
	char s[100];
};
typedef struct input input;

#define PAL_PROG 0x12345678
#define PAL_VERS 1

#if defined(__STDC__) || defined(__cplusplus)
#define pal 1
extern  int * pal_1(input *, CLIENT *);
extern  int * pal_1_svc(input *, struct svc_req *);
extern int pal_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define pal 1
extern  int * pal_1();
extern  int * pal_1_svc();
extern int pal_prog_1_freeresult ();
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

#endif /* !_PAL_H_RPCGEN */
