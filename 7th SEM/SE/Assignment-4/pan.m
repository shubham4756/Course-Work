#define rand	pan_rand
#define pthread_equal(a,b)	((a)==(b))
#if defined(HAS_CODE) && defined(VERBOSE)
	#ifdef BFS_PAR
		bfs_printf("Pr: %d Tr: %d\n", II, t->forw);
	#else
		cpu_printf("Pr: %d Tr: %d\n", II, t->forw);
	#endif
#endif
	switch (t->forw) {
	default: Uerror("bad forward move");
	case 0:	/* if without executable clauses */
		continue;
	case 1: /* generic 'goto' or 'skip' */
		IfNotBlocked
		_m = 3; goto P999;
	case 2: /* generic 'else' */
		IfNotBlocked
		if (trpt->o_pm&1) continue;
		_m = 3; goto P999;

		 /* PROC :init: */
	case 3: // STATE 1 - prog3.pml:11 - [printf('factorial of 5 is\\n')] (0:0:0 - 0)
		IfNotBlocked
		reached[1][1] = 1;
		Printf("factorial of 5 is\n");
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - prog3.pml:12 - [(run fac(5))] (0:0:0 - 0)
		IfNotBlocked
		reached[1][2] = 1;
		if (!(addproc(II, 1, 0, 5)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - prog3.pml:13 - [-end-] (0:0:0 - 0)
		IfNotBlocked
		reached[1][3] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC fac */
	case 6: // STATE 1 - prog3.pml:5 - [((n==1))] (0:0:1 - 0)
		IfNotBlocked
		reached[0][1] = 1;
		if (!((((P0 *)_this)->n==1)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: n */  (trpt+1)->bup.oval = ((P0 *)_this)->n;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P0 *)_this)->n = 0;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 2 - prog3.pml:5 - [printf('ans is %d\\n',res)] (0:0:0 - 0)
		IfNotBlocked
		reached[0][2] = 1;
		Printf("ans is %d\n", now.res);
		_m = 3; goto P999; /* 0 */
	case 8: // STATE 3 - prog3.pml:6 - [((n>1))] (0:0:0 - 0)
		IfNotBlocked
		reached[0][3] = 1;
		if (!((((P0 *)_this)->n>1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 4 - prog3.pml:6 - [res = (res*n)] (0:0:1 - 0)
		IfNotBlocked
		reached[0][4] = 1;
		(trpt+1)->bup.oval = now.res;
		now.res = (now.res*((P0 *)_this)->n);
#ifdef VAR_RANGES
		logval("res", now.res);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 10: // STATE 5 - prog3.pml:6 - [(run fac((n-1)))] (0:0:1 - 0)
		IfNotBlocked
		reached[0][5] = 1;
		if (!(addproc(II, 1, 0, (((P0 *)_this)->n-1))))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: n */  (trpt+1)->bup.oval = ((P0 *)_this)->n;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P0 *)_this)->n = 0;
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 8 - prog3.pml:8 - [-end-] (0:0:0 - 0)
		IfNotBlocked
		reached[0][8] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */
	case  _T5:	/* np_ */
		if (!((!(trpt->o_pm&4) && !(trpt->tau&128))))
			continue;
		/* else fall through */
	case  _T2:	/* true */
		_m = 3; goto P999;
#undef rand
	}

