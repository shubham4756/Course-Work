.model small
.data

List dw 259,257,256,258,255

.code
mov ax, @data
mov ds, ax
mov ax, 0000h


mov bx, 0005h

up1:
mov si, offset List
mov cx, 0005h

up:
mov dx, [si]
inc si
inc si
mov ax, [si]
cmp ax, dx
JNC skip
xchg [si], dx
xchg [si-2], ax 
skip:
loop up

dec bx
jnz up1

mov ah, 4ch
int 21h
end