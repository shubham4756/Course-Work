	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC :init: */

	case 3: // STATE 1
		;
		((P1 *)_this)->_2_6_i = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 5: // STATE 3
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 6: // STATE 4
		;
		((P1 *)_this)->_2_6_i = trpt->bup.oval;
		;
		goto R999;

	case 7: // STATE 11
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC Philosopher */
;
		;
		;
		;
		
	case 10: // STATE 3
		;
		now.fork[ Index(((P0 *)_this)->id, 4) ] = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 12: // STATE 6
		;
		now.fork[ Index(((((P0 *)_this)->id+1)%4), 4) ] = trpt->bup.oval;
		;
		goto R999;
;
		;
		;
		;
		
	case 15: // STATE 12
		;
		now.fork[ Index(((((P0 *)_this)->id+1)%4), 4) ] = trpt->bup.oval;
		;
		goto R999;

	case 16: // STATE 13
		;
		nr_eat = trpt->bup.oval;
		;
		goto R999;
;
		;
		;
		;
		
	case 19: // STATE 17
		;
		now.fork[ Index(((P0 *)_this)->id, 4) ] = trpt->bup.oval;
		;
		goto R999;

	case 20: // STATE 18
		;
		nr_eat = trpt->bup.oval;
		;
		goto R999;
;
		;
			case 22: // STATE 26
		sv_restor();
		goto R999;

	case 23: // STATE 28
		;
		p_restor(II);
		;
		;
		goto R999;
	}

