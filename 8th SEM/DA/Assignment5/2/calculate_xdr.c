/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "calculate.h"

bool_t
xdr_inputs (XDR *xdrs, inputs *objp)
{
	register int32_t *buf;

	 if (!xdr_float (xdrs, &objp->num1))
		 return FALSE;
	 if (!xdr_float (xdrs, &objp->num2))
		 return FALSE;
	 if (!xdr_char (xdrs, &objp->op))
		 return FALSE;
	return TRUE;
}