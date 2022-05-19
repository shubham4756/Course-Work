dosseg
model small
.8086

.data
a db 5AH
b db ?

.code
mov ax, @data
mov ds, ax

mov al, a
mov bl, 00H
mov cx, 0008H
loop1:   rol bl, 01H
        ror al, 01H
        jnc label1
        or bl, 01H
        label1: nop
        loop loop1

mov al,a
cmp al, bl
je endd

mov b,0ffH
jmp enddd

endd: mov b,00H
enddd:  mov ax, 4C00H
int 21h
end