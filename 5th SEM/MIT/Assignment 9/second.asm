dosseg 
model small
.8086

.data
a dw 0004H
b dw 0FFFEH
c dw 0
d dw 0

.code 
mov ax, @data
mov ds, ax

mov ax, a
mov bx, b
mul bx

mov c, ax
mov d, dx


mov ax, 4C00H
int 21h
end