/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "leap.h"

bool_t
xdr_input (XDR *xdrs, input *objp)
{
	register int32_t *buf;

	 if (!xdr_int (xdrs, &objp->year))
		 return FALSE;
	return TRUE;
}