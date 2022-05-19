dosseg 
model small
.8086

.data
ca db ?
p1 db ?
p2 db ?
p3 db 41H
p4 db 42H
p5 db 61H
p6 db 62H

.code 
mov ax, @data
mov ds , ax

mov cx, 0000H
mov ah, p3
mov al, p4
mov bh, p5
mov bl, p6

add ax, bx
jnc skip
inc cx
skip:   mov p1, ah
        mov p2, al
mov ca, cl  

mov ax, 4c00H
int 21H
end