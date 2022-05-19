/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _CMP_H_RPCGEN
#define _CMP_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct input {
	int n1;
	int n2;
	char s1[50];
	char s2[50];
};
typedef struct input input;

#define CMP_PROG 0x12345678
#define CMP_VERS 1

#if defined(__STDC__) || defined(__cplusplus)
#define cmp 1
extern  int * cmp_1(input *, CLIENT *);
extern  int * cmp_1_svc(input *, struct svc_req *);
extern int cmp_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define cmp 1
extern  int * cmp_1();
extern  int * cmp_1_svc();
extern int cmp_prog_1_freeresult ();
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

#endif /* !_CMP_H_RPCGEN */