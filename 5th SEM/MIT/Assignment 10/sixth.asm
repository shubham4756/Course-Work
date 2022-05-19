dosseg
model small
.8086

.data
a db 04H
b db ?

.code
mov ax, @data
mov ds, ax

mov ah,00H
mov al, a
mov bl, 02H
div bl

cmp ah, 00H

jc  dn
    mov b , 00H      
    jmp label1
    dn:
    mov b , 0FFH       
 
label1: nop

mov ax, 4C00H
int 21h
end