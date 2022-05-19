dosseg 
model small
.8086

.data
ca db ?
p1 db ?
p2 db ?
p3 db 65H
p4 db 67H
p5 db 48H
p6 db 32H

.code 
mov ax, @data
mov ds , ax

mov cx, 0000H
mov ah, p3
mov al, p4
mov bh, p5
mov bl, p6

sub ax, bx
jnc skip
inc cx
skip:   mov p1, ah
        mov p2, al
mov ca, cl  

mov ax, 4c00H
int 21H
end