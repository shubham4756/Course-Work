dosseg 
model small
.8086

.data
a db 28H
b db -03H
c dw ?

.code 
mov ax, @data
mov ds, ax

mov ax,0000H
mov bx,0000H

mov al,a
mov bl,b
idiv bl

mov c,ax

mov ax, 4C00H
int 21h
end