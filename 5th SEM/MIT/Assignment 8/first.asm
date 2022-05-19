dosseg
model small
.8086

.data 
ca db ?
p1 db ?
p2 db 80H
p3 db 44H

.code 
mov ax, @data
mov ds, ax

mov cl , 00H
mov al, p2
add al, p3
jnc skip
inc cl
skip: mov p1, al

mov ca, cl

mov ax, 4C00H
int 21h
end