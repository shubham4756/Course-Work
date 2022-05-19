	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC :init: */
;
		;
		
	case 4: // STATE 2
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 5: // STATE 3
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC fac */

	case 6: // STATE 1
		;
	/* 0 */	((P0 *)_this)->n = trpt->bup.oval;
		;
		;
		goto R999;
;
		;
		;
		;
		
	case 9: // STATE 4
		;
		now.res = trpt->bup.oval;
		;
		goto R999;

	case 10: // STATE 5
		;
	/* 0 */	((P0 *)_this)->n = trpt->bup.oval;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 11: // STATE 8
		;
		p_restor(II);
		;
		;
		goto R999;
	}

