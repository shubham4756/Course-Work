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
	case 3: // STATE 1 - prog.pml:35 - [i = 0] (0:0:1 - 0)
		IfNotBlocked
		reached[1][1] = 1;
		(trpt+1)->bup.oval = ((int)((P1 *)_this)->_2_6_i);
		((P1 *)_this)->_2_6_i = 0;
#ifdef VAR_RANGES
		logval(":init::i", ((int)((P1 *)_this)->_2_6_i));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - prog.pml:36 - [((i<4))] (0:0:0 - 0)
		IfNotBlocked
		reached[1][2] = 1;
		if (!((((int)((P1 *)_this)->_2_6_i)<4)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - prog.pml:36 - [(run Philosopher(i))] (0:0:0 - 0)
		IfNotBlocked
		reached[1][3] = 1;
		if (!(addproc(II, 1, 0, ((int)((P1 *)_this)->_2_6_i))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 6: // STATE 4 - prog.pml:36 - [i = (i+1)] (0:0:1 - 0)
		IfNotBlocked
		reached[1][4] = 1;
		(trpt+1)->bup.oval = ((int)((P1 *)_this)->_2_6_i);
		((P1 *)_this)->_2_6_i = (((int)((P1 *)_this)->_2_6_i)+1);
#ifdef VAR_RANGES
		logval(":init::i", ((int)((P1 *)_this)->_2_6_i));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 11 - prog.pml:40 - [-end-] (0:0:0 - 0)
		IfNotBlocked
		reached[1][11] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC Philosopher */
	case 8: // STATE 1 - prog.pml:7 - [printf('Plilosopher with id %d is thinking\\n',id)] (0:0:0 - 0)
		IfNotBlocked
		reached[0][1] = 1;
		Printf("Plilosopher with id %d is thinking\n", ((int)((P0 *)_this)->id));
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 2 - prog.pml:9 - [((fork[id]==0))] (0:0:0 - 0)
		IfNotBlocked
		reached[0][2] = 1;
		if (!((((int)now.fork[ Index(((int)((P0 *)_this)->id), 4) ])==0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 10: // STATE 3 - prog.pml:9 - [fork[id] = (id+1)] (0:0:1 - 0)
		IfNotBlocked
		reached[0][3] = 1;
		(trpt+1)->bup.oval = ((int)now.fork[ Index(((int)((P0 *)_this)->id), 4) ]);
		now.fork[ Index(((P0 *)_this)->id, 4) ] = (((int)((P0 *)_this)->id)+1);
#ifdef VAR_RANGES
		logval("fork[Philosopher:id]", ((int)now.fork[ Index(((int)((P0 *)_this)->id), 4) ]));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 5 - prog.pml:10 - [((fork[((id+1)%4)]==0))] (0:0:0 - 0)
		IfNotBlocked
		reached[0][5] = 1;
		if (!((((int)now.fork[ Index(((((int)((P0 *)_this)->id)+1)%4), 4) ])==0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 12: // STATE 6 - prog.pml:10 - [fork[((id+1)%4)] = (id+1)] (0:0:1 - 0)
		IfNotBlocked
		reached[0][6] = 1;
		(trpt+1)->bup.oval = ((int)now.fork[ Index(((((int)((P0 *)_this)->id)+1)%4), 4) ]);
		now.fork[ Index(((((P0 *)_this)->id+1)%4), 4) ] = (((int)((P0 *)_this)->id)+1);
#ifdef VAR_RANGES
		logval("fork[((Philosopher:id+1)%4)]", ((int)now.fork[ Index(((((int)((P0 *)_this)->id)+1)%4), 4) ]));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 13: // STATE 10 - prog.pml:16 - [((fork[id]==(id+1)))] (0:0:0 - 0)
		IfNotBlocked
		reached[0][10] = 1;
		if (!((((int)now.fork[ Index(((int)((P0 *)_this)->id), 4) ])==(((int)((P0 *)_this)->id)+1))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 14: // STATE 11 - prog.pml:16 - [((fork[((id+1)%4)]==0))] (0:0:0 - 0)
		IfNotBlocked
		reached[0][11] = 1;
		if (!((((int)now.fork[ Index(((((int)((P0 *)_this)->id)+1)%4), 4) ])==0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 15: // STATE 12 - prog.pml:16 - [fork[((id+1)%4)] = (id+1)] (0:0:1 - 0)
		IfNotBlocked
		reached[0][12] = 1;
		(trpt+1)->bup.oval = ((int)now.fork[ Index(((((int)((P0 *)_this)->id)+1)%4), 4) ]);
		now.fork[ Index(((((P0 *)_this)->id+1)%4), 4) ] = (((int)((P0 *)_this)->id)+1);
#ifdef VAR_RANGES
		logval("fork[((Philosopher:id+1)%4)]", ((int)now.fork[ Index(((((int)((P0 *)_this)->id)+1)%4), 4) ]));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 16: // STATE 13 - prog.pml:17 - [nr_eat = (nr_eat+1)] (0:0:1 - 0)
		IfNotBlocked
		reached[0][13] = 1;
		(trpt+1)->bup.oval = ((int)nr_eat);
		nr_eat = (((int)nr_eat)+1);
#ifdef VAR_RANGES
		logval("nr_eat", ((int)nr_eat));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 17: // STATE 15 - prog.pml:21 - [((fork[id]==0))] (0:0:0 - 0)
		IfNotBlocked
		reached[0][15] = 1;
		if (!((((int)now.fork[ Index(((int)((P0 *)_this)->id), 4) ])==0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 18: // STATE 16 - prog.pml:21 - [((fork[((id+1)%4)]==(id+1)))] (0:0:0 - 0)
		IfNotBlocked
		reached[0][16] = 1;
		if (!((((int)now.fork[ Index(((((int)((P0 *)_this)->id)+1)%4), 4) ])==(((int)((P0 *)_this)->id)+1))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 19: // STATE 17 - prog.pml:21 - [fork[id] = (id+1)] (0:0:1 - 0)
		IfNotBlocked
		reached[0][17] = 1;
		(trpt+1)->bup.oval = ((int)now.fork[ Index(((int)((P0 *)_this)->id), 4) ]);
		now.fork[ Index(((P0 *)_this)->id, 4) ] = (((int)((P0 *)_this)->id)+1);
#ifdef VAR_RANGES
		logval("fork[Philosopher:id]", ((int)now.fork[ Index(((int)((P0 *)_this)->id), 4) ]));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 20: // STATE 18 - prog.pml:22 - [nr_eat = (nr_eat+1)] (0:0:1 - 0)
		IfNotBlocked
		reached[0][18] = 1;
		(trpt+1)->bup.oval = ((int)nr_eat);
		nr_eat = (((int)nr_eat)+1);
#ifdef VAR_RANGES
		logval("nr_eat", ((int)nr_eat));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 21: // STATE 22 - prog.pml:26 - [printf('Plilosopher with id %d is eating\\n',id)] (0:0:0 - 0)
		IfNotBlocked
		reached[0][22] = 1;
		Printf("Plilosopher with id %d is eating\n", ((int)((P0 *)_this)->id));
		_m = 3; goto P999; /* 0 */
	case 22: // STATE 26 - prog.pml:27 - [D_STEP27]
		IfNotBlocked

		reached[0][26] = 1;
		reached[0][t->st] = 1;
		reached[0][tt] = 1;

		if (TstOnly) return 1;

		sv_save();
		S_022_0: /* 2 */
		nr_eat = (((int)nr_eat)-1);
#ifdef VAR_RANGES
		logval("nr_eat", ((int)nr_eat));
#endif
		;
S_023_0: /* 2 */
		now.fork[ Index(((((P0 *)_this)->id+1)%4), 4) ] = 0;
#ifdef VAR_RANGES
		logval("fork[((Philosopher:id+1)%4)]", ((int)now.fork[ Index(((((int)((P0 *)_this)->id)+1)%4), 4) ]));
#endif
		;
S_024_0: /* 2 */
		now.fork[ Index(((P0 *)_this)->id, 4) ] = 0;
#ifdef VAR_RANGES
		logval("fork[Philosopher:id]", ((int)now.fork[ Index(((int)((P0 *)_this)->id), 4) ]));
#endif
		;
		goto S_026_0;
S_026_0: /* 1 */

#if defined(C_States) && (HAS_TRACK==1)
		c_update((uchar *) &(now.c_state[0]));
#endif
		_m = 3; goto P999;

	case 23: // STATE 28 - prog.pml:29 - [-end-] (0:0:0 - 0)
		IfNotBlocked
		reached[0][28] = 1;
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

