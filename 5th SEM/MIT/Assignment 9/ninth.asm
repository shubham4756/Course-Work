dosseg 
model small
.8086

.data
s1 db "UVWXYZ$"

.code
mov ax, @data
mov ds, ax

mov si,offset s1
mov cx,0006h
mov bl,05h
mov di,offset [s1+3]

    up: inc si
        inc di
        dec bl
        jnz up

    go: mov al,[si]
        mov [di],al
        dec si
        dec di
        dec cx
        jnz go

mov ax, 4C00H
int 21h
end