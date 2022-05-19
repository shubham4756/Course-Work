dosseg
model small
.8086

.data
a db 22H
b db ?

.code
mov ax, @data
mov ds, ax

mov al,a
mov bl, a

ror bl,04H
and al, 0FH
and bl, 0FH

cmp al, bl
je endd

mov b,0ffH
jmp enddd

endd: mov b,00H
enddd:  mov ax, 4C00H
int 21h
end