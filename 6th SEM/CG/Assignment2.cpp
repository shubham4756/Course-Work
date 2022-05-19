#include<stdio.h>
#include<graphics.h>


int main() {
	
	int gd = DETECT, gm;
	initgraph(&gd,&gm,"");
	
	char arrr[100];
	sprintf(arrr, "Current background color = %d and line color = %d",getbkcolor(),getcolor());
	settextstyle(5,0,1); 
	outtext(arrr);
	
	//blank
	rectangle(50,20,150,45);
	
	line(50,60,150,60); //line

	setcolor(GREEN);
	circle(250,100,50); // cicle
	setcolor(WHITE);
	outtextxy(225,95,"Circle");
	
	setlinestyle(2,0,1);
	arc(375,100,0,135,50);//arc
	setlinestyle(0,0,1);
	arc(375,100,200,340,50);
	
	bar(450, 40, 480, 150);//bar
	
	ellipse(550,100,0,360,50,80);
	outtextxy(525,90,"ellipse");
	int ar[] = {50,70,150,100,50,130,90,100,50,70};
	drawpoly(5,ar);
	int p = getbkcolor();
	//fill
	int arr[] = {50,170,150,200,50,230,90,200,50,170};
	fillpoly(5,arr);
	pieslice(375,220,20,80,50);
	setfillstyle(SOLID_FILL,5);
	circle(250,200,40);
	floodfill(250,200,WHITE);
	
	
	
	getch();
	cleardevice();
	setbkcolor(GREEN);
	outtext("Press any key for car");
	getch();
	setbkcolor(0);
	cleardevice();

	line(50,270,90,270);
	arc(110,270,0,180,20);
	line(130,270,220,270);
	arc(240,270,0,180,20);
	line(260,270,300,270);
	arc(300,260,270,90,10);
	line(300,250,240,230);
	line(240,230,200,200);
	line(200,200,110,200);
	line(110,200,80,230);
	line(80,230,50,240);
	line(50,240,50,270);
	
	line(165,205,165,230);
	line(165,230,230,230);
	line(230,230,195,205);
	line(195,205,165,205);
	
	line(160,205,160,230);
	line(160,230,95,230);
	line(95,230,120,205);
	line(120,205,160,205);
	
	circle(110,270,17);
	circle(240,270,17);
	line(0,290,639,290);
	getch();
	
	cleardevice();
	outtext("Press any key for Smiley Face");
	getch();
	cleardevice();
	
	setcolor(YELLOW); 
  	circle(300, 200, 80); 
    setfillstyle(SOLID_FILL, YELLOW); 
    floodfill(300, 200, YELLOW); 
  
    setcolor(BLACK); 
    setfillstyle(SOLID_FILL, BLACK); 
  
    fillellipse(325, 175, 6, 15); 
    fillellipse(275, 175, 6, 15); 
  
    ellipse(300, 210, 205, 335, 60, 29); 
    ellipse(300, 210, 205, 335, 60, 30); 
    ellipse(300, 210, 205, 335, 60, 31); 
    getch();
	
	setcolor(15);
	cleardevice();

	outtext("Press any key for circles");
	getch();
	cleardevice();
	
	setcolor(BLUE);
	circle(250,200,100);

	setcolor(CYAN);
	circle(250,200,80);

	setcolor(YELLOW);
	circle(250,200,60);

	setcolor(RED);
	circle(250,200,40);

	setcolor(WHITE);
	circle(250,200,20);
	getch();
	cleardevice();
	
	outtext("Press any key for Traffic Signal");
	getch();
	cleardevice();
	
	int midx = getmaxx()/2;
    int midy = getmaxy()/2;

	setcolor(WHITE);
   	rectangle(300,150,360,310);
   	circle(330, 180, 22);
   	setfillstyle(SOLID_FILL,RED);
   	floodfill(330, 180,WHITE);
   	delay(2000);
 
   	cleardevice();
   	setcolor(WHITE);
   	rectangle(300,150,360,310);
   	circle(330, 230, 22);
   	setfillstyle(SOLID_FILL,YELLOW);
   	floodfill(330, 230,WHITE);
   	delay(2000);

   	cleardevice();
   	setcolor(WHITE);
   	rectangle(300,150,360,310);
	circle(330, 280, 22);
   	setfillstyle(SOLID_FILL,GREEN);
   	floodfill(330, 280,WHITE);
 	
	getch();
	closegraph();
	return 0;
}
