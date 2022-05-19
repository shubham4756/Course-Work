#include<iostream>
#include<graphics.h>
#include<math.h>

void dda(int x0, int y0, int x1, int y1) {
	
	int i;
	float x,y,dx,dy, steps;
	dx = (float)(x1 - x0);  
    dy = (float)(y1 - y0);  
    if(dx>=dy)  
           {  
        steps = dx;  
    }  
    else  
           {  
        steps = dy;  
    }  
    dx = dx/steps;  
    dy = dy/steps;  
    x = x0;  
    y = y0;  
    i = 1;  
    while(i<= steps)  
    {  
        putpixel(x, y,WHITE);  
        x += dx;  
        y += dy;  
        i=i+1;  
    } 
}

void bresenham (int x0, int y0, int x1, int y1)  
{  
    int dx, dy, p, x, y;  
    dx=x1-x0;  
    dy=y1-y0;  
    x=x0;  
    y=y0;  
    p=2*dy-dx;  
    while(x<x1)  
    {  
        if(p>=0)  
        {  
            putpixel(x,y,WHITE);  
            y=y+1;  
            p=p+2*dy-2*dx;  
        }  
        else  
        {  
            putpixel(x,y,WHITE);  
            p=p+2*dy;
		}  
        x=x+1;  
    } 
}

void home() {
	// Triangle
	line(100,150, 160,80);
	line(160,80,220,150);
	line(220,150,100,150);
	
	// parallelogram
	line(160,80,420,80);
	line(420,80,480,150);
	line(480,150,220,150);
	
	//rectangle
	line(100,150,100,350);
	line(100,350,220,350);
	line(220,350,220,150);
	
	//rectanlge
	line(480,150,480,350);
	line(480,350,220,350);
	
	//door
	line(120,350,120,200);
	line(120,200,200,200);
	line(200,200,200,350);
	
	//window
	line(260,200,440,200);
	line(440,200,440,280);
	line(440,280,260,280);
	line(260,280,260,200);
	
	setfillstyle(9, 6);
	floodfill(121,201,WHITE);
	setfillstyle(10, 7);
	floodfill(261,201,WHITE);
	setfillstyle(11,3);
	floodfill(151,149,WHITE);
	floodfill(221,151,WHITE);
	floodfill(151,151,WHITE);
	floodfill(221,149,WHITE);
}

void kite() {
	line(200,200,300,100);
	line(300,100,400,200);
	line(400,200,300,300);
	line(300,300,200,200);
	line(300,100,300,300);
	
	arc(300,300,45,135,140);
	setfillstyle(SOLID_FILL,BLUE);
	
	floodfill(301,105,WHITE);
	setfillstyle(SOLID_FILL,LIGHTRED);
	
	floodfill(299,105,WHITE);
	setfillstyle(SOLID_FILL,BLUE);
	
	
	floodfill(299,275,WHITE);
	setfillstyle(SOLID_FILL,LIGHTRED);
	floodfill(301,275,WHITE);
	
	line(300,300,250,350);
	line(250,350,350,350);
	line(300,300,350,350);
	setfillstyle(SOLID_FILL,YELLOW);
	floodfill(300,310,WHITE);
}

void flag() {
	int maxx = getmaxx();
	int maxy = getmaxy();
	
	rectangle(0,0,maxx,maxy/3);
	rectangle(0,maxy/3,maxx,2*maxy/3);
	rectangle(0,2*maxy/3,maxx,maxy);
	
	setfillstyle(SOLID_FILL,BROWN);
	floodfill(1,1,WHITE);
	
	setfillstyle(SOLID_FILL,WHITE);
	floodfill(maxx/2,maxy/2,WHITE);
	
	setfillstyle(SOLID_FILL,GREEN);
	floodfill(maxx/2,2*maxy/3+5,WHITE);
	
	setcolor(BLUE);
	int a = maxx/2, b = maxy/2,r = maxy/6 - 5;
	circle(a,b, maxy/6 -5);
	
	for(int i = 0;i<=360;i += 15) {
		int tx = r*cos(i*3.14/180);
		int ty = r*sin(i*3.14/180);
		line(a,b,a+tx,b - ty);
	}
}

void movingcar() {
	for(int i = 0;i<250;i++) {
		
		cleardevice();
		line(0,290,639,290);
		line(50 + i,270,90 + i,270);
		arc(110 + i,270,0,180,20);
		line(130 + i,270,220 + i,270);
		arc(240 + i,270,0,180,20);
		line(260 + i,270,300 + i,270);
		arc(300 + i,260,270,90,10);
		line(300 + i,250,240 + i,230);
		line(240 + i,230,200 + i,200);
		line(200 + i,200,110 + i,200);
		line(110 + i,200,80 + i,230);
		line(80 + i,230,50 + i,240);
		line(50 + i,240,50 + i,270);
		
		line(165 + i,205,165 + i,230);
		line(165 + i,230,230 + i,230);
		line(230 + i,230,195 + i,205);
		line(195 + i,205,165 + i,205);
		
		line(160 + i,205,160 + i,230);
		line(160 + i,230,95 + i,230);
		line(95 + i,230,120 + i,205);
		line(120 + i,205,160 + i,205);
		
		circle(110 + i,270,17);
		circle(240 + i,270,17);
	
		circle(110 + i,270,15);
		circle(240 + i,270,15);
		delay(10);
	}
}

void linedrawing() {
	while(1) {
		printf("1 for DDA Algorithm \n");
		printf("2 for Bresenham Algorithm \n");
		printf("3 for Exit \n");
		int d;
		scanf("%d",&d);
		if(d==1 || d==2) {
			int x0,y0,x1,y1;
			printf("Enter x0,y0, x1,y1 space separated\n");
			scanf("%d %d %d %d",&x0,&y0,&x1,&y1);
			
			if(d==1) {
				dda(x0,y0,x1,y1);
			} else {
				bresenham(x0,y0,x1,y1);
			}
			getch();	
		} else {
			break;
		}
	}
}

int main() {
	int gd = DETECT, gm;
	initgraph(&gd,&gm,"");
	
	home();
	
	getch();
	
	cleardevice();
	graphdefaults();
	
	kite();
	getch();
	
	cleardevice();graphdefaults();
	
	flag();
	getch();
	
	cleardevice();
	graphdefaults();
	
	
	movingcar();
	getch();
	
	cleardevice();
	graphdefaults();
	
	linedrawing();
	
	getch();
	closegraph();
	return 0;
}