/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "gcd.h"

bool_t
xdr_input (XDR *xdrs, input *objp)
{
	register int32_t *buf;

	 if (!xdr_int (xdrs, &objp->a))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->b))
		 return FALSE;
	return TRUE;
}
