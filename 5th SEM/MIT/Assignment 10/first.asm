dosseg
model small
.8086

.data
a dw 002AH
b dw ?
c dw ?
d dw ?
e dw ?

.code
mov ax, @data
mov ds, ax

mov dx, 0000H
mov ax, a
mov bx, a
mul bx
mov b, ax
mov c, dx
mul bx
mov d, ax
mov e, dx

mov ax, 4C00H
int 21h
end