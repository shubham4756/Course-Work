/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "sqroot.h"

bool_t
xdr_input (XDR *xdrs, input *objp)
{
	register int32_t *buf;

	 if (!xdr_float (xdrs, &objp->a))
		 return FALSE;
	return TRUE;
}
