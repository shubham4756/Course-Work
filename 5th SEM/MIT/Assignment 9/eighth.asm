dosseg 
model small
.8086

.data
s1 db "UVWXYZ$"
s2 db 6 dup(0)

.code
mov ax, @data
mov ds, ax

mov si,offset s1
mov di,offset s2
mov cx,0006h

up: mov al,[si]
    mov [di],al
    inc si
    inc di
    dec cx
    jnz up

mov ax, 4C00H
int 21h
end