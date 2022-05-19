dosseg 
model small
.8086

.data
a dw 0188H
b dw -0012H
c dw ?
d dw ? 

.code 
mov ax, @data
mov ds, ax

mov ax,0000H
mov bx,0000H
mov dx,0000H

mov ax,a
mov bx,b
idiv bx

mov c,ax
mov d,dx

mov ax, 4C00H
int 21h
end